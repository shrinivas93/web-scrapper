package com.shri.webscrapper;

import static com.shri.webscrapper.Utils.KEYWORDS;
import static com.shri.webscrapper.Utils.NEWS_LINKS_FILE;
import static com.shri.webscrapper.Utils.OBJECT_MAPPER;
import static com.shri.webscrapper.Utils.PATTERN;
import static com.shri.webscrapper.Utils.START_PAGE_NUMBER;
import static com.shri.webscrapper.Utils.TOI_INDORE_NEWS_SELECTOR;
import static com.shri.webscrapper.Utils.TOI_INDORE_URL_PATTERN;

import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ToiIndoreLauncher {

	private ToiIndoreLauncher() {
	}

	public static void main(String[] args) throws IOException {
		List<News> news = new ArrayList<>();
		double executionStart = System.currentTimeMillis();
		try {
			System.out.println("Fetching news...");
			long i = START_PAGE_NUMBER;
			while (true) {
				System.out.println("Page " + i);
				Document document = null;
				try {
					document = Jsoup.connect(TOI_INDORE_URL_PATTERN.replace("{1}", String.valueOf(i))).get();
				} catch (SocketTimeoutException ste) {
					System.out.println("Couldn't download page " + i + ". Retrying...");
				}
				if (document != null) {
					Elements elements = document.select(TOI_INDORE_NEWS_SELECTOR);
					if (elements.size() == 0) {
						System.out.println("No news found on page " + i + ". Exiting....");
						break;
					}
					for (Element element : elements) {
						String link = element.absUrl("href");
						String title = element.text();
						if (PATTERN.matcher(title).find()) {
							news.add(new News(i, link, title));
						}
					}
					i++;
					Thread.sleep(10);
				}
			}

		} catch (Exception e) {
		} finally {
			double executionEnd = System.currentTimeMillis();
			String newsJson = OBJECT_MAPPER.writeValueAsString(new NewsCriteria(KEYWORDS, news));
			try (FileWriter fw = new FileWriter(NEWS_LINKS_FILE)) {
				fw.write(newsJson);
			}
			System.out.println("News fetched = " + news.size());
			System.out.println("Time taken = " + (executionEnd - executionStart) / 1000 + " seconds");
			System.out.println("News list available at = " + NEWS_LINKS_FILE.getAbsolutePath());
		}
	}

}
