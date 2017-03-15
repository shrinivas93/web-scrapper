package com.shri.webscrapper;

public class News {
	private long date;
	private String link;
	private String title;

	public News() {
		// Required for Jackson
	}

	public News(long date, String link, String title) {
		super();
		this.date = date;
		this.link = link;
		this.title = title;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "News [date=" + date + ", link=" + link + ", title=" + title
				+ "]";
	}

}
