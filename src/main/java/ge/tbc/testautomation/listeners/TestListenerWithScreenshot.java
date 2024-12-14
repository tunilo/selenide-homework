package ge.tbc.testautomation.listeners;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestListenerWithScreenshot implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("Test [" + result.getMethod().getMethodName() + "] failed. Taking screenshot...", true);

        try {
            File screenshot = Selenide.screenshot(OutputType.FILE);

            if (screenshot != null) {
                Path targetPath = Path.of("screenshots", screenshot.getName());
                Files.createDirectories(targetPath.getParent());
                Files.copy(screenshot.toPath(), targetPath);

                String screenshotPath = targetPath.toAbsolutePath().toString();
                Reporter.log("Screenshot saved at: <a href='" + screenshotPath + "'>" + screenshotPath + "</a>", true);
            } else {
                Reporter.log("Failed to capture screenshot.", true);
            }
        } catch (IOException e) {
            Reporter.log("Error while saving screenshot: " + e.getMessage(), true);
        }
    }
}
