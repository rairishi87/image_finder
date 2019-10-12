package com.sainsburys.imagefinder.app.model;

import java.util.Date;
import java.util.List;

public class ImageFinderResponse {

	private List<String> hrefList;
	private String msg;
	private Date timestamp;
	private int httpStatus;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public List<String> getHrefList() {
		return hrefList;
	}

	public void setHrefList(List<String> hrefList) {
		this.hrefList = hrefList;
	}
}
