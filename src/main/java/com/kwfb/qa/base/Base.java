package com.kwfb.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base {
	public WebDriver driver;
	public Properties prop;

	public ExtentReports extent = new ExtentReports();
	public ExtentSparkReporter spark = new ExtentSparkReporter("extent-reports/ExtentReport.html");
	ExtentTest test;

	public WebDriver init_driver(String brouserName) {
		if (brouserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"E:\\All software for development\\softwares\\chromedriver_win32\\chromedriver.exe");
			if (prop.getProperty("headless").equals("yes")) {
				// headless mode
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			} else {
				driver = new ChromeDriver();
			}
		}
		return driver;
	}

	public Properties init_properties() {
		prop = new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream(
					"E:\\ADARSH Current data\\keyword_driven_fb\\src\\main\\java\\com\\kwfb\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public String takeScreenShot(WebDriver driver) {
		TakesScreenshot takScrnShot = ((TakesScreenshot) driver);
		File file = takScrnShot.getScreenshotAs(OutputType.FILE);
		File fileDest = new File(".//kwfb_screenshots// " + System.currentTimeMillis() + ".png");
		String absolutePath = fileDest.getAbsolutePath();
		try {
			FileHandler.copy(file, fileDest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return absolutePath;
	}
}
