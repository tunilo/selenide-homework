import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.utils.HelperFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedSwoopTests2 extends BaseTest {
    private final String category;

    public ParametrizedSwoopTests2(String category) {
        this.category = category;
    }

    @Test
    public void filterTest() {

        HelperFunctions helper = new HelperFunctions();
        open(Constants.SWOOP_HOME_URL);
        $x(category).shouldBe(visible).click();
        List<Double> allPrices = helper.collectAllPrices(Constants.PRICE_LOCATOR);
        System.out.println(allPrices);
        double minPrice = allPrices.stream().min(Double::compare).orElse(0.0);
        $x(Constants.SORT_BUTTON).click();
        $x(Constants.ASCENDING_ORDER).shouldBe(visible).click();
        String firstPriceText = $x(Constants.PRICE_LOCATOR).shouldBe(visible).getText();
        double firstOfferPrice = helper.extractPrice(firstPriceText);
        System.out.println(firstOfferPrice);
        Assert.assertEquals(firstOfferPrice, minPrice);
    }

    @Test
    public void priceRangeTest() {
        HelperFunctions helper = new HelperFunctions();
        open(Constants.SWOOP_HOME_URL);
        $x(category).shouldBe(visible).click();

        $x(Constants.MIN_PRICE_INPUT).shouldBe(visible).clear();
        $x(Constants.MIN_PRICE_INPUT).setValue(Constants.MIN_PRICE);

        $x(Constants.MAX_PRICE_INPUT).shouldBe(visible).clear();
        $x(Constants.MAX_PRICE_INPUT).setValue(Constants.MAX_PRICE);
        $x(Constants.FILTER_BUTTON).shouldBe(visible).click();

        List<Double> allPrices = helper.collectAllPrices(Constants.PRICE_LOCATOR);

        boolean areAllPricesInRange = allPrices.stream()
                .allMatch(price -> price >= Constants.MIN_PRICE_INT && price <= Constants.MAX_PRICE_INT);

        Assert.assertTrue(areAllPricesInRange);
    }
}