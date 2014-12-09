package com.focustech.htmlParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParserX {

	public static void main(String[] args) throws IOException {
		Properties prop = System.getProperties();
		prop.setProperty("http.proxyHost", "192.168.16.189");
		prop.setProperty("http.proxyPort", "8080");
		Map<String, String> data = new HashMap<String, String>();
		data.put("nv", "1.2");
		data.put("LNG", "2");
		data.put("tmp_ListingRows", "15");
		data.put("rqt_pagingQuery", "query_res");
		data.put("rqt_pagingDef", "15");
		// data.put("", "");
		// data.put("", "");
		for (int n = 1; n <= 2; n++) {
			data.put("StartRow_query_res_" + n, ((n - 1) * 15 + 1) + "");
			data.put("SRField", n + "");

			Document doc = Jsoup
					.connect(
							"http://www.electronica-media.de/prj_806/view/index.cfm")
					.timeout(30000).data(data).post();
			Element table = doc.select("table.global_tablestyle").first();
			// System.out.println(table);
			Elements alinks = table.select("a.ex_font");
			for (int i = 0; i < alinks.size() && i <= 1; i++) {
				Element link = alinks.get(i);
				String url = link.attr("href");
				Document subDoc = Jsoup
						.connect(
								"http://www.electronica-media.de/prj_806/view/"
										+ url).timeout(30000).get();
				Element cominfoDiv = subDoc.select("div.exd_facts").first();
				Element s = cominfoDiv.select("div.exd_inhaltheadfont").first();
				System.out.println(s.text());
			}

		}

		// System.out.println(alinks.size());
		// System.out.println(doc);

	}
}
