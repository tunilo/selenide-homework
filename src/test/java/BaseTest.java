import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeTest
    @Parameters({"browserType"})
    public void setUp(@Optional("chrome") String browserType) {
        // Setup the desired browser
        if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            WebDriverRunner.setWebDriver(new ChromeDriver());
            Configuration.browser = "chrome";
        } else if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            WebDriverRunner.setWebDriver(new FirefoxDriver());
            Configuration.browser = "firefox";
        } else {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        // Common configuration
        Configuration.timeout = 10000;
        Configuration.assertionMode = AssertionMode.STRICT;
        Configuration.holdBrowserOpen = false; // Ensure the browser closes automatically
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
    @AfterTest
    public void tearDown() {
        Selenide.closeWebDriver();

    }
}