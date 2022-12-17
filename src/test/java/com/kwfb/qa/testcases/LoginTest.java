package com.kwfb.qa.testcases;

import org.testng.annotations.Test;

import com.kwfb.qa.util.ExecutionEngine;

public class LoginTest {
	public ExecutionEngine exeEngine;
	public static String sheetname = "fb_senerios";


	@Test
	public void loginTest() {
		exeEngine = new ExecutionEngine();
		exeEngine.startExecution(sheetname);
	}

}
