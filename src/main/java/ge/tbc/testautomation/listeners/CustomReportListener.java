package ge.tbc.testautomation.listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;

public class CustomReportListener implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
            String suiteName = suite.getName();
            Reporter.log("Suite: " + suiteName + " - Generating report", true);

            for (ISuiteResult result : suite.getResults().values()) {
                ITestContext context = result.getTestContext();

                Reporter.log("Test Context: " + context.getName(), true);

                context.getFailedTests().getAllResults().forEach(failedTest -> {
                    String testName = failedTest.getName();
                    String className = failedTest.getTestClass().getName();
                    String errorMessage = failedTest.getThrowable() != null ? failedTest.getThrowable().getMessage() : "No error message";

                    Reporter.log("Failed Test: " + testName, true);
                    Reporter.log("Test Class: " + className, true);
                    Reporter.log("Reason: " + errorMessage, true);
                });
            }
        }
    }
}

