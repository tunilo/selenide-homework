package ge.tbc.testautomation.listeners;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import java.util.List;

public class CustomReportListener implements IReporter {
        @Override
        public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
            for (ISuite suite : suites) {
                for (ISuiteResult result : suite.getResults().values()) {
                    ITestContext context = result.getTestContext();
                    context.getFailedTests().getAllResults().forEach(failedTest -> {
                        System.out.println("Failed Test: " + failedTest.getName());
                    });
                }
            }
        }
    }

