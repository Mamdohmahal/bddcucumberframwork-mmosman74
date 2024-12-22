package com.bddframework.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import com.bddframework.listeners.FrameworkListeners;

@CucumberOptions(
    features = "src/test/resources/feature_files",
    glue = "com.bddframework.step_definition",
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true,
    tags = "@TutorialsNinjaLogin"
)

@Listeners({FrameworkListeners.class})
public class TestNGRunner extends AbstractTestNGCucumberTests {
}

