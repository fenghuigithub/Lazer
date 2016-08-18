package com.example.jsonrss_test_1.model;

import java.io.Serializable;

public class FeedItem implements Serializable {

	private String title;
	private String date;
	private String attachmentUrl;
	private String id;
	private String content;
	private String url;
	private String summary;

//	private String origin;

//	public String getOrigin(){
//		return origin;
//	}
//
//	public void setOrigin(String origin){
//		this.origin = origin;
//	}

	public String getSummary(){
		return summary;
	}

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	@Override
	public String toString() {
		return "[ title=" + title + ", date=" + date + "]";
	}
}
