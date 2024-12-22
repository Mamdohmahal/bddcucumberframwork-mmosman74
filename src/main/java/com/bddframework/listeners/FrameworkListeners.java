package com.bddframework.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.bddframework.utils.VideoRecorder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FrameworkListeners implements ITestListener {

    private WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Execution Started");
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        System.out.println(testName + " started");
        try {
            VideoRecorder.startRecording(testName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        System.out.println(testName + " passed");
        try {
            VideoRecorder.stopRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        System.out.println(testName + " failed");

        // Take screenshot on failure
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get("test-output/ScreenShots", testName + ".png");
        try {
            Files.createDirectories(destination.getParent());
            Files.copy(screenshot.toPath(), destination);
            System.out.println("Screenshot saved to " + destination.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            VideoRecorder.stopRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        System.out.println(testName + " skipped");

        // Save video on skipped
        try {
            VideoRecorder.stopRecording();
            Path source = Paths.get("test-output/Videos", testName + ".avi");
            Path destination = Paths.get("test-output/SkippedTestVideo", testName + ".avi");
            Files.createDirectories(destination.getParent());
            Files.move(source, destination);
            System.out.println("Video saved to " + destination.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Execution Finished");
        if (driver != null) {
            driver.quit();
        }
    }
}
