package com.shri.webscrapper;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private static final String PROXY_HOST = "";
	private static final String PROXY_PORT = "";
	private static final String PROXY_USER = "";
	private static final String PROXY_PASSSWORD = "";

	private static final String OUTPUT_BASE_FOLDER_STR = "toi_output";
	private static final String NEWS_FOLDER_STR = "news";
	private static final String NEWS_LINKS_FILE_STR = "news";
	private static final String NEWS_LINKS_FILE_EXTN = ".json";

	public static final String TOI_INDORE_URL_PATTERN = "http://timesofindia.indiatimes.com/articlelist/9644624.cms?curpg={1}";
	public static final String TOI_INDORE_NEWS_SELECTOR = "#c_articlelist_stories_2>ul.list5.clearfix>li>span.w_tle>a";

	public static final long START_PAGE_NUMBER = 34;

	private static final String INDORE_FILE_SUFFIX = "INDORE";

	public static final File OUTPUT_BASE_FOLDER = createBaseFolder(OUTPUT_BASE_FOLDER_STR);
	public static final File NEWS_FOLDER = createFolder(NEWS_FOLDER_STR);
	public static final File NEWS_LINKS_FILE = createFile(NEWS_LINKS_FILE_STR);

	public static final String[] KEYWORDS = { "crime" };
	public static final Pattern PATTERN = createPattern(KEYWORDS);

	private Utils() {
	}

	private static Pattern createPattern(String[] keywords) {
		StringBuilder regex = new StringBuilder();
		for (int i = 0; i < keywords.length; i++) {
			regex.append(keywords[i]);
			if (keywords.length - i != 1) {
				regex.append("|");
			}
		}
		return Pattern.compile(regex.toString());
	}

	private static File createBaseFolder(String outputBaseFolderStr) {
		File baseFolder = new File(outputBaseFolderStr);
		baseFolder.mkdirs();
		return baseFolder;
	}

	private static File createFolder(String newsFolderStr) {
		File newsFolder = new File(OUTPUT_BASE_FOLDER, newsFolderStr);
		newsFolder.mkdirs();
		return newsFolder;
	}

	private static File createFile(String newsLinksStr) {
		File newsLinksFile = new File(OUTPUT_BASE_FOLDER,
				newsLinksStr + "_" + INDORE_FILE_SUFFIX + "_" + START_PAGE_NUMBER + NEWS_LINKS_FILE_EXTN);
		try {
			newsLinksFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newsLinksFile;
	}

	static {
		System.setProperty("http.proxyHost", PROXY_HOST);
		System.setProperty("http.proxyPort", PROXY_PORT);
		System.setProperty("http.proxyUser", PROXY_USER);
		System.setProperty("http.proxyPassword", PROXY_PASSSWORD);
		System.setProperty("https.proxyHost", PROXY_HOST);
		System.setProperty("https.proxyPort", PROXY_PORT);
		System.setProperty("https.proxyUser", PROXY_USER);
		System.setProperty("https.proxyPassword", PROXY_PASSSWORD);
	}

}
