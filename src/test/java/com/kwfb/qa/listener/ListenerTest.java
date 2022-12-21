package com.kwfb.qa.listener;

import com.kwfb.qa.base.Base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

public class ListenerTest extends Base implements ITestListener {
	public String name;

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + "  : onTestStart");
		extent.attachReporter(spark);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + "  : onTestSuccess");
		name = result.getMethod().getMethodName();
		ExtentTest test = extent.createTest(result.getMethod().getMethodName()).assignAuthor("Adarsh prajapati");
		test.pass("Test method pass...");
		test.addScreenCaptureFromPath(takeScreenShot(driver));
		extent.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		name = result.getMethod().getMethodName();
		ExtentTest test = extent.createTest(result.getMethod().getMethodName()).assignAuthor("Adarsh prajapati");
		test.fail("Test method failed...");
		test.addScreenCaptureFromPath(takeScreenShot(driver));
		extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getMethodName()).assignAuthor("Adarsh prajapati");
		test.skip("Test method skipped...");
		extent.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
