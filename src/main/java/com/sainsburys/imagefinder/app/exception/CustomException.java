package com.sainsburys.imagefinder.app.exception;

import com.sainsburys.imagefinder.app.model.ImageFinderResponse;

public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final int HTTP_ERROR_CODE = 500;

	/**
	 * 
	 * @param errorMessage
	 */
	public CustomException(String errorMessage) {
		super(errorMessage);
		ImageFinderResponse finderResponse = new ImageFinderResponse();
		finderResponse.setMsg(errorMessage);
		finderResponse.setHttpStatus(HTTP_ERROR_CODE);
	}
}
