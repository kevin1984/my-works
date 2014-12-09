package com.focustech.htmlParser;

import java.io.IOException;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

	public static void main(String[] args) throws IOException {
		Properties prop = System.getProperties();
		prop.setProperty("http.proxyHost", "192.168.16.189");
		prop.setProperty("http.proxyPort", "8080");
		// TODO Auto-generated method stub
		Document doc = Jsoup
				.connect(
						"http://www.electronica-media.de/prj_806/view/index.cfm?nv=1.1.1&LNG=2&elb=806.1100.1759.1.1111")
				.timeout(30000).get();
		Element e = doc.getElementById("exd_expdgruppec");
		Elements es = e.select("a[href]");
		for (int i = 0; i < es.size(); i++) {
			Element ele = es.get(i);
			if (ele != null) {
				System.out.println(ele.text());
			}
		}
		// System.out.println(es.size());

	}
}
