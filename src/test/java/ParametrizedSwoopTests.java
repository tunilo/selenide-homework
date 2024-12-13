
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.OfferDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedSwoopTests extends BaseTest {

    @Test(dataProvider = "offerPriceData", dataProviderClass = OfferDataProvider.class)
    public void validateOfferPrices(String offerTitle) {
        open(Constants.SWOOP_HOME_URL);
        $x(Constants.SPORT_SECTION).click();
        String dynamicXPath = String.format("//div[contains(@class, 'grid')]/a[contains(@class, 'cursor-pointer') and .//h4[text()='%s']]", offerTitle);
        SelenideElement offer = $x(dynamicXPath);

        String originalPriceText = offer.$x(Constants.INITIAL_PRICE_LOCATOR)
                .getText().replaceAll("[^\\d.]", "");

        String discountedPriceText = offer.$x(Constants.NEW_PRICE_LOCATOR)
                .getText().replaceAll("[^\\d.]", "");

        String discountPercentText = offer.$x(Constants.DISCOUNT_PRICE_LOCATOR)
                .getText().replaceAll("[^\\d]", "");

        double originalPrice = Double.parseDouble(originalPriceText);
        double discountedPrice = Double.parseDouble(discountedPriceText);
        double discountPercent = Double.parseDouble(discountPercentText);
        double calculatedDiscountedPrice = originalPrice - (originalPrice * (discountPercent / 100));
        Assert.assertEquals(Math.round(calculatedDiscountedPrice), Math.round(discountedPrice));
    }

    @Test(dataProvider = "offerPriceData", dataProviderClass = OfferDataProvider.class)
    public void validateCartBehavior(String offerTitle) {
        open(Constants.SWOOP_HOME_URL);
        $x(Constants.SPORT_SECTION).click();
        String dynamicXPath = String.format("//div[contains(@class, 'grid')]/a[contains(@class, 'cursor-pointer') and .//h4[text()='%s']]", offerTitle);
        SelenideElement offer = $x(dynamicXPath);
        offer.click();
        $x(Constants.ADD_TO_BASKET_BUTTON).click();
        back();
        $x(Constants.CART_BUTTON).click();
        ElementsCollection elements = $$x(Constants.CART_ITEMS_LOCATOR);
        elements.first().shouldBe(Condition.visible);
        boolean isOfferFound = false;

        for (SelenideElement element : elements) {
            String text = element.$x(".//p").getText();
            if (text.equals(offerTitle)) {
                isOfferFound = true;
                break;
            }
        }
        Assert.assertTrue(isOfferFound);
        back();
    }
    }
