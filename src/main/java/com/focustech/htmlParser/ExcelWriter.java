package com.focustech.htmlParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelWriter {

	public static void main(String[] args) {
		List<String> data = new ArrayList<String>();
		data.add("focus tech");
		data.add("12345");
		data.add("+86 21495345");
		writer("F:\\test\\fisrtExcel.xls", 2, data);
	}

	public static void writer(String fileDirectory, int startRow,
			List<String> data) {
		try {
			if (data == null || data.size() <= 0
					|| StringUtils.isBlank(fileDirectory) || startRow < 0) {
				return;
			}
			File file = new File(fileDirectory);
			HSSFWorkbook workbook = null;
			if (!file.exists()) {
				workbook = new HSSFWorkbook();
			} else {
				workbook = new HSSFWorkbook(new FileInputStream(file));
			}
			HSSFSheet sheet = workbook.getSheet("数据收集");
			if (sheet == null) {
				sheet = workbook.createSheet("数据收集");
			}
			HSSFRow row = sheet.createRow(startRow);
			for (int i = 0; i < data.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(data.get(i));
			}
			FileOutputStream os = new FileOutputStream(fileDirectory);
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
