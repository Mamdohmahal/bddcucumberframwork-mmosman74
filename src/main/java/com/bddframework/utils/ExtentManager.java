package com.bddframework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            sparkReporter = new ExtentSparkReporter("extent-reports/extent-report.html");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Test Report");
            sparkReporter.config().setReportName("Extent Reports - BDD Cucumber Test Results");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }
}
