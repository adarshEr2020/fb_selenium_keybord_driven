package com.kbfb.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.kbfb.qa.base.Base;

public class ExecutionEngine extends Base {
	public static String SENERIO_SHEET_PATH = "E:\\ADARSH Current data\\keybord_driven_fb\\src\\main\\java\\com\\kbfb\\qa\\fb_senerioes\\fb_keyboad_senerioes.xlsx";

	public static Workbook book;
	public static Sheet sheet;
	public static WebDriver driver;
	public static Properties prop;
	WebElement element;
	public static Base base;

	public void startExecution(String sheetname) {
		String locatorName = null;
		String locatorValue = null;

		FileInputStream file = null;

		try {
			file = new FileInputStream(SENERIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetname);

		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
				
				String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim(); // id = username
//				System.out.println("locator Col Value:   " + locatorColValue);
				if (!locatorColValue.equalsIgnoreCase("NA")) {
					locatorName = locatorColValue.split("=")[0].trim(); // id
					System.out.println("locator name:   " + locatorName);
					locatorValue = locatorColValue.split("=")[1].trim();// username
				}

				String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
//				System.out.println("action of :   " + action);
				String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
//				System.out.println("value of :   " + value);
				switch (action) {
				case "open browser":
					base = new Base();
					prop = base.init_properties();
					if (value.isEmpty() || value.equals("NA")) {
						driver = base.init_driver(prop.getProperty("browser"));
					} else {
						driver = base.init_driver(value);
					}
					break;

				case "enter url":
					if (value.isEmpty() || value.equals("NA")) {
						System.out.println("enter url:   ");
						driver.get(prop.getProperty("url"));
					} else {
						System.out.println("enter url:   " + value);
						driver.get(value);
					}
					break;

				case "exit":
					break;

				default:
					break;
				}

				switch (locatorName) {
				case "id":
					WebElement element = driver.findElement(By.id(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if(action.equalsIgnoreCase("click")){
						element.click();
					}
					locatorName = null;
					break;

				case "name":
					element = driver.findElement(By.name(locatorValue));
					element.click();
					locatorName = null;
					break;

				case "linkText":
					element = driver.findElement(By.linkText(locatorValue));
					element.click();
					locatorName = null;
					break;

				default:
					break;
				}
			} catch (Exception e) {
			
			}

		}
	}
}
