package com.shri.webscrapper;

public class News {
	private long pageNo;
	private String link;
	private String title;

	public News() {
		// Required for Jackson
	}

	public News(long pageNo, String link, String title) {
		super();
		this.pageNo = pageNo;
		this.link = link;
		this.title = title;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
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
		return "News [pageNo=" + pageNo + ", link=" + link + ", title=" + title + "]";
	}

}
