package com.kbfb.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	public WebDriver driver;
	public Properties prop;

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
					"E:\\ADARSH Current data\\keybord_driven_fb\\src\\main\\java\\com\\kbfb\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
