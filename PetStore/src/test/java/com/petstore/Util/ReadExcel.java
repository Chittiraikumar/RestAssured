package com.petstore.Util;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	
	
	public static String[][] readExcel(String fileName, String sheetName) {

		String data[][] = null;
		System.out.println(sheetName);
		System.out.println(fileName);
		
		try {
			String rootPath = System.getProperty("user.dir") + "/src/test/resources/";
			FileInputStream inputStream = new FileInputStream(rootPath + "/Data/" + fileName + ".xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int cellCount = sheet.getRow(0).getLastCellNum();

			data = new String[rowCount][cellCount];

			for (int i = 1; i <= rowCount; i++) {
				for (int j = 0; j < cellCount; j++) {
					String cellValue = sheet.getRow(i).getCell(j).getStringCellValue();
					data[i - 1][j] = cellValue;
				}
			}

			wb.close();

		} catch (Exception e) {
			e.fillInStackTrace();
		}
		//System.out.println(data);
		return data;
	}



}
