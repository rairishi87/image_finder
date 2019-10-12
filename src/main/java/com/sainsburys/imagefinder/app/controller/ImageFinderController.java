package com.sainsburys.imagefinder.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sainsburys.imagefinder.app.exception.CustomException;
import com.sainsburys.imagefinder.app.model.ImageFinderResponse;
import com.sainsburys.imagefinder.app.service.ImageFinderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "ImageFinderController")
@RestController
public class ImageFinderController {

	@Autowired
	private ImageFinderService imageFinderService;

	@ApiOperation(value = "Get the list of image links from NASA's media", response = Iterable.class, tags = "NASA_Media_Searching_API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/getImageUriList", method = RequestMethod.GET)
	public ResponseEntity<ImageFinderResponse> getNasaImages() {

		ImageFinderResponse imageFinderResponse = new ImageFinderResponse();
		try {
			//call to service class
			List<String> hrefList = imageFinderService.fetchNasaImages();
			
			//response setup
			imageFinderResponse.setHrefList(hrefList);
			imageFinderResponse.setTimestamp(new Date());
			imageFinderResponse.setMsg("Suceess");
			imageFinderResponse.setHttpStatus(HttpStatus.OK.value());
		} catch (CustomException e) {
			imageFinderResponse.setMsg(e.getMessage());
			imageFinderResponse.setHttpStatus(HttpStatus.NOT_FOUND.value());
		}
		return new ResponseEntity<ImageFinderResponse>(imageFinderResponse, HttpStatus.OK);
	}
}
