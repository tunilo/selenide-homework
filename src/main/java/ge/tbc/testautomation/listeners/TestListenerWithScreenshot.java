package ge.tbc.testautomation.listeners;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListenerWithScreenshot implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        File screenshot = Selenide.screenshot(OutputType.FILE);
        System.out.println("Screenshot saved at: " + screenshot.getAbsolutePath());
    }
}