package com.sainsburys.imagefinder.app.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.sainsburys.imagefinder.app.exception.CustomException;
import com.sainsburys.imagefinder.app.service.ImageFinderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageFinderControllerTests {

	private ImageFinderController finderController;

	@Mock
	private ImageFinderService imageFinderService;

	@Before
	public void setUp() {
		finderController = new ImageFinderController();
		imageFinderService = mock(ImageFinderService.class);
		ReflectionTestUtils.setField(finderController, "imageFinderService", imageFinderService);
	}

	@Test
	public void testGetNasaImages() {
		List<String> hrefList = new ArrayList<String>();
		hrefList.add("url1");
		hrefList.add("url2");
		hrefList.add("url3");
		try {
			when(imageFinderService.fetchNasaImages()).thenReturn(hrefList);
			Assert.assertNotNull(finderController.getNasaImages());
			Assert.assertEquals(3, finderController.getNasaImages().getBody().getHrefList().size());
		} catch (CustomException e) {
			Assert.assertFalse(true);
		}
	}

}
