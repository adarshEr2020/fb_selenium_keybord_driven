package com.kwfb.qa.testcases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.kwfb.qa.util.ExecutionEngine;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.kwfb.qa.listener.ListenerTest.class)
public class LoginTest {
	public ExecutionEngine exeEngine;
	public static String sheetname = "fb_senerios";


	@Test
	@Description("login fb user...")
	@Severity(SeverityLevel.CRITICAL)
	@Feature("login feature")
	@Story("Indivisual facebook user login... ")
	public void loginTest() {
		exeEngine = new ExecutionEngine();
		exeEngine.startExecution(sheetname);
	}

}
