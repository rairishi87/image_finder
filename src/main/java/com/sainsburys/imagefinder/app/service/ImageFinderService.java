package com.sainsburys.imagefinder.app.service;

import java.util.List;

import com.sainsburys.imagefinder.app.exception.CustomException;

public interface ImageFinderService {

	public List<String> fetchNasaImages() throws CustomException;
}