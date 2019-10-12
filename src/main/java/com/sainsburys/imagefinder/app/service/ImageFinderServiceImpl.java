package com.sainsburys.imagefinder.app.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sainsburys.imagefinder.app.exception.CustomException;

@Component(value = "imageFinderService")
public class ImageFinderServiceImpl implements ImageFinderService {

	@Value("${nasa.media.searching.api}")
	private String uri;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Override
	public List<String> fetchNasaImages() throws CustomException {
		
		List<String> hrefList = new ArrayList<>();
		try {
			URLConnection request = new URL(uri).openConnection();
			request.connect();
			InputStreamReader inputStreamReader = new InputStreamReader((InputStream) request.getContent());

			// map to GSON objects
			JsonElement root = new JsonParser().parse(inputStreamReader);

			// traverse the JSON data
			JsonArray items = root.getAsJsonObject().get("collection").getAsJsonObject().get("items").getAsJsonArray();

			// flatten nested arrays
			JsonArray links = new JsonArray();
			items.forEach(item -> links.addAll(item.getAsJsonObject().get("links").getAsJsonArray()));

			// filter links with "href" properties
			links.forEach(link -> {
				JsonObject linkObject = link.getAsJsonObject();
				String relString = linkObject.get("rel").getAsString();
				if ("preview".equals(relString)) {
					hrefList.add(linkObject.get("href").getAsString());
				}
			});
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		return hrefList;

	}

}