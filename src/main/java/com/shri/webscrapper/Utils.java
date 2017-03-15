package com.shri.webscrapper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private static final DateFormat SDF = new SimpleDateFormat("ddMMyyyy");

	private static final String PROXY_HOST = "";
	private static final String PROXY_PORT = "";
	private static final String PROXY_USER = "";
	private static final String PROXY_PASSSWORD = "";

	private static final String OUTPUT_BASE_FOLDER_STR = "toi_output";
	private static final String NEWS_FOLDER_STR = "news";
	private static final String NEWS_LINKS_FILE_STR = "news";
	private static final String NEWS_LINKS_FILE_EXTN = ".json";

	public static final String TOI_ARCHIVE_URL_PATTERN = "http://timesofindia.indiatimes.com/2016/1/1/archivelist/year-2016,month-1,starttime-{1}.cms";
	public static final String TOI_ARCHIVE_NEWS_SELECTOR = "body>div:nth-child(1)>table:nth-child(2)>tbody>tr:nth-child(2)>td:nth-child(1)>div:nth-child(3)>table>tbody>tr:nth-child(2)>td>span>a";

	private static final Calendar DATE_1_1_1900 = initializeDate(1, 1, 1900);
	public static final Calendar START_DATE = initializeDate(1, 1, 2016);
	public static final Calendar END_DATE = initializeDate();

	private static final String FILE_SUFFIX = "_"
			+ SDF.format(START_DATE.getTime()) + "_"
			+ SDF.format(END_DATE.getTime());

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

	private static Calendar initializeDate(int date, int month, int year) {
		Calendar calendar = initializeDate();
		calendar.set(Calendar.DATE, date);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		return calendar;
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
		File newsLinksFile = new File(OUTPUT_BASE_FOLDER, newsLinksStr
				+ FILE_SUFFIX + NEWS_LINKS_FILE_EXTN);
		try {
			newsLinksFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newsLinksFile;
	}

	private static Calendar initializeDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		return calendar;
	}

	public static long getDayNumber(Calendar date) {
		long end = date.getTimeInMillis();
		long start = DATE_1_1_1900.getTimeInMillis();
		return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
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
