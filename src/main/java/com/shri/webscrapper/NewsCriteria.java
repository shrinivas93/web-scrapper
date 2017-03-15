package com.shri.webscrapper;

import java.util.Arrays;
import java.util.List;

public class NewsCriteria {
	private String[] keywords;
	private List<News> news;

	public NewsCriteria() {
		// Required for Jackson
	}

	public NewsCriteria(String[] keywords, List<News> news) {
		super();
		this.keywords = keywords;
		this.news = news;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	@Override
	public String toString() {
		return "NewsCriteria [keywords=" + Arrays.toString(keywords)
				+ ", news=" + news + "]";
	}

}
