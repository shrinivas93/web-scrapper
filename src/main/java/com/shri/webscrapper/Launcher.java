package com.shri.webscrapper;

import static com.shri.webscrapper.Utils.END_DATE;
import static com.shri.webscrapper.Utils.KEYWORDS;
import static com.shri.webscrapper.Utils.NEWS_LINKS_FILE;
import static com.shri.webscrapper.Utils.OBJECT_MAPPER;
import static com.shri.webscrapper.Utils.PATTERN;
import static com.shri.webscrapper.Utils.START_DATE;
import static com.shri.webscrapper.Utils.TOI_ARCHIVE_NEWS_SELECTOR;
import static com.shri.webscrapper.Utils.TOI_ARCHIVE_URL_PATTERN;
import static com.shri.webscrapper.Utils.getDayNumber;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Launcher {

	private Launcher() {
	}

	public static void main(String[] args) throws IOException {
		List<News> news = new ArrayList<>();
		long start = getDayNumber(START_DATE);
		long end = getDayNumber(END_DATE);
		System.out.println("Fetching news for " + (end - start + 1) + " days");
		double executionStart = System.currentTimeMillis();
		long i = start;
		while (i <= end) {
			Document document = Jsoup.connect(
					TOI_ARCHIVE_URL_PATTERN.replace("{1}",
							String.valueOf(start))).get();
			Elements elements = document.select(TOI_ARCHIVE_NEWS_SELECTOR);
			for (Element element : elements) {
				String link = element.absUrl("href");
				String title = element.text();
				if (PATTERN.matcher(title).find()) {
					news.add(new News(start, link, title));
				}
			}
			System.out.println("Day " + (i - start + 1));
			i++;
		}
		double executionEnd = System.currentTimeMillis();
		String newsJson = OBJECT_MAPPER.writeValueAsString(new NewsCriteria(
				KEYWORDS, news));
		try (FileWriter fw = new FileWriter(NEWS_LINKS_FILE)) {
			fw.write(newsJson);
		}

		System.out.println("News fetched = " + news.size());
		System.out.println("Time taken = " + (executionEnd - executionStart)
				/ 1000 + " seconds");
		System.out.println("News list available at = "
				+ NEWS_LINKS_FILE.getAbsolutePath());
	}

}
