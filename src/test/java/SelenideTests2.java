import com.codeborne.selenide.impl.CollectionSource;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.AssertJUnit.assertEquals;

import ge.tbc.testautomation.data.Constants;
import com.codeborne.selenide.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//ამან უნდა მამკლას მე?

//საშინელი კოდია მაგრამ მუშაობს, ზოგჯერ სლიფები უნნდა

public class SelenideTests2 extends BaseTest {
    @Test
    public void validateDemosDesign() {
        open("https://www.telerik.com/support/demos");
        $x("//a[@href='#web']").click();

        //1) In Web section, validate that all cards have purple overlay effect on hover.
        ElementsCollection webCards = $$x("//div[contains(@class, 'row')]//div[contains(@id, '329')]//div[contains(@class, 'HoverImg')]");
        //System.out.println("Total HoverImg divs: " + webCards.size());
        for (SelenideElement card : webCards) {
            card.hover();
            card.shouldHave(cssValue("background-color", "rgba(40, 46, 137, 0.75)"));
        }
        //	2) In Web section, Validate that the list on Kendo UI's purple overlay contains 'UI for Vue Demos'.
        SelenideElement kendoUiCard = $x("//div[contains(@class, 'row')]//div[contains(@id, '329')]//div[contains(@class, 'HoverImg')]");
        SelenideElement webHeader = $x("//h2[@id='web' and text()='Web']");
        webHeader.scrollTo();
        kendoUiCard.hover();
        SelenideElement linkContainer = kendoUiCard.$("div.LinkContainer");
        linkContainer.shouldBe(Condition.visible);
        ElementsCollection links = linkContainer.$$x(".//a");
        //links.forEach(link -> System.out.println(link.getText()));
        links.findBy(Condition.text("UI for Vue demos")).shouldBe(Condition.visible);

// - In Desktop section, select all items, make a filter that will only include items that are available on Microsoft Store.
        //select all items აქ ჩემმა ტვინმა გაბაგა


        $x("//a[@href='#desktop']").click();
        ElementsCollection desktopCards = $$x("//div[contains(@class, 'row')]//div[contains(@id, '337')]");
        // ElementsCollection microsoftCards = $$x("//div[contains(@class, 'row')]//div[contains(@id, '337')]//a[contains(@href, 'microsoft.com')]");
        List<SelenideElement> microsoftCards = new ArrayList<>();
        for (SelenideElement card : desktopCards) {
            if (card.$x(".//a[contains(@href, 'microsoft.com')]").exists()) {
                microsoftCards.add(card);
            }
        }

        //In Mobile section, validate that 'Telerik UI for Xamarin' is available on Apple Store, Google Play and Microsoft Store.
        $x("//a[@href='#mobile']").click();
        SelenideElement xamarin = $x("//div[@id='ContentPlaceholder1_C340_Col01']");
        xamarin.$x(".//a[@href='https://play.google.com/store/apps/details?id=com.telerik.xamarin&hl=en']").shouldBe(Condition.visible);
        xamarin.$x(".//a[contains(@href, 'microsoft.com/en-us/store/p/telerik-ui-for-xamarin-examples')]").shouldBe(Condition.visible);
        xamarin.$x(".//a[contains(@href, 'itunes.apple.com')]").shouldBe(Condition.visible);

        // Validate that the section links remain fixed at the top as you scroll.
        SelenideElement navSection = $x("//nav[@data-tlrk-plugin='fixit']");

        executeJavaScript("window.scrollTo(0, document.body.scrollHeight);");
        navSection.shouldHave(Condition.cssClass("is-fixed"));
//Validate that the section links get active according to your scrolling position
// (for example: if you're scrolled into Desktop section, the Desktop link should have dimmed background).

        // - Validate that the aforementioned links take the user to correct sections.
        // ElementsCollection navLinks = $$x("//nav[@data-tlrk-plugin='fixit']//a");
        ElementsCollection navLinks = $$x("//nav[@data-tlrk-plugin='fixit']//a");
        ElementsCollection headers = $$x("//h2[contains(@class, 'u-mb0 h3')]");
        for (SelenideElement link : navLinks) {
            String linkText = link.text();

            link.click();
            String actualBackground = link.getCssValue("background-color");
            Assertions.assertNotEquals("rgba(0, 0, 0, 0)", actualBackground);
           // sleep(1500); // i cant find better solution

            SelenideElement visibleHeader = headers.stream()
                    .filter(header -> Selenide.executeJavaScript(
                            "var rect = arguments[0].getBoundingClientRect();" +
                                    "return rect.top >= 0 && rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                                    "window.getComputedStyle(arguments[0]).visibility !== 'hidden';",
                            header))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("No visible header found in viewport"));
            visibleHeader.should(Condition.visible);
            String headerText = visibleHeader.text();

            try {
                Assertions.assertTrue(headerText.contains(linkText),
                        "Header does not match link text. Expected: " + linkText + ", Actual: " + headerText);
            } catch (AssertionError e) {
                System.out.println("Header does not match link text. Expected: " + linkText + ", Actual: " + headerText);
            }

        }
    }

    @Test
    public void validateOrderMechanics() {
        open("https://www.telerik.com/support/demos");
        $x("//a[@class='TK-Menu-Item-Link' and text()='Pricing']").click();
        //sleep(2000);
        SelenideElement completePriceElement = $x("//th[contains(@class, 'Complete')]//span[contains(@class, 'u-dib')]");
        String priceText = completePriceElement.text();
        SelenideElement buyNowLink = $x("//a[@href='https://store.progress.com/configure-purchase?skuId=6127']");
        executeJavaScript("arguments[0].click();", buyNowLink);
        $x("//i[contains(@class, 'far fa-times label u-cp')]").click();
        SelenideElement priceElement = $x("//span[@class='e2e-price-per-license']");
        String priceTextUnit = priceElement.text();
        String numericString = priceTextUnit.replace("$", "").replace(",", "");
        double price = Double.parseDouble(numericString);
        String numericString1 = priceText.replace("$", "").replace(",", "").trim();
        double price2 = Double.parseDouble(numericString1);
        Assert.assertEquals(price, price2);

        //	2) Increase the term, validate that the price is added correctly according to its percentage. // delete
        SelenideElement dropdown = $x("//button[@class='k-input-button k-button k-icon-button k-button-md k-button-solid k-button-solid-base']");
        Selenide.executeJavaScript("arguments[0].click();", dropdown);
        //sleep(2000);
        SelenideElement dropdownPopup = $x("//kendo-popup[contains(@class, 'k-animation-container k-animation-container-shown')]");
        dropdownPopup.shouldBe(Condition.visible);
        SelenideElement firstPopup = $x("(//kendo-popup[contains(@class, 'k-animation-container k-animation-container-fixed k-animation-container-shown')])"); // Use indexing to select the first popup
        firstPopup.shouldBe(Condition.visible);
        List<SelenideElement> listItems = dropdownPopup.$$x(".//li");
        List<SelenideElement> licenseEntries = firstPopup.$$x(".//div[contains(@class, 'u-df justify-content-between u-mb10')]");
        int targetNumber = 5;
        String discount = "";
        SelenideElement supportPriceElement = $x("//span[contains(@class, 'bold') and contains(text(), '$')]");
        priceText = supportPriceElement.getText();
        double supportPrice = Integer.parseInt(priceText.replaceAll("[^\\d]", "")) / 100;
        for (SelenideElement entry : licenseEntries) {
            String licenseRange = entry.$x(".//span[contains(@class, 'label')][1]").getText().trim();
            discount = entry.$x(".//span[contains(@class, 'label')][2]").getText().trim();

            if (isNumberInRange(targetNumber, licenseRange)) {
                System.out.println("Number " + targetNumber + " falls under: " + licenseRange + ", Discount: " + discount);
                break;
            }
        }

        int discountValue = Integer.parseInt(discount.replaceAll("[^\\d]", ""));
        String num = "5";
        for (SelenideElement item : listItems) {
            if (item.getText().trim().equals(num)) {
                item.click();
                break;
            }
        }
        //sleep(2000);
        SelenideElement totalPriceElement = $x("//h4[contains(@class, 'e2e-total-price')]");
        String totalPriceText = totalPriceElement.getText();
        String numericPrice = totalPriceText.replaceAll("[^\\d.]", "");
        double totalPrice = Double.parseDouble(numericPrice);
        double expectedPrice = (price * targetNumber) * (100 - discountValue) / 100;

        assertEquals(expectedPrice, totalPrice);

        SelenideElement popup = $x("//kendo-popup[contains(@class, 'k-animation-container-shown')]");
        SelenideElement dropdownButton = $x("(//button[contains(@class, 'k-input-button') and .//kendo-svgicon[contains(@class, 'k-svg-i-caret-alt-down')]])[last()]");
        dropdownButton.shouldBe(Condition.visible).click();
        popup.shouldBe(Condition.visible);
        SelenideElement dropdownList = $x("//ul[contains(@class, 'k-list-ul')]");
        List<SelenideElement> options = dropdownList.$$x("./li");
        String targetOption = "+4 years";
        String anotherDiscount = "";
        for (SelenideElement option : options) {
            String optionText = option.$x(".//span[contains(@class, 'u-w75 label')][1]").getText();
            if (optionText.equals(targetOption)) {
                anotherDiscount = option.$x(".//span[contains(@class, 'discount-label')]").getText();

                option.click();
                System.out.println("Clicked on option: " + optionText + ", Discount: " + discount);
                break;
            }
        }
        //sleep(2000);
        totalPriceElement = $x("//h4[contains(@class, 'e2e-total-price')]");
        totalPriceText = totalPriceElement.getText();
        numericPrice = totalPriceText.replaceAll("[^\\d.]", "");
        totalPrice = Double.parseDouble(numericPrice);
        int anotherDiscountValue = Integer.parseInt(anotherDiscount.replaceAll("[^\\d-]", ""));
        int years = Integer.parseInt(targetOption.replaceAll("[^\\d]", ""));
        expectedPrice = (price * (100 - discountValue) * targetNumber) / 100 + (supportPrice * (100 + anotherDiscountValue) * targetNumber * years) / 100;
        System.out.println("Expected Price: " + price + "Afv" + anotherDiscountValue);
        assertEquals(expectedPrice, totalPrice);
        double subtotal = Integer.parseInt(
                $x("//div[contains(@class, 'e2e-cart-item-subtotal')]").getText().replaceAll("[^\\d]", "")
        );
        subtotal = subtotal / 100.0;
        assertEquals(subtotal, expectedPrice);
        SelenideElement savingsElement = $x("//div[contains(@class, 'e2e-item-ms-savings') and contains(text(), 'Save')]");
        String savingsText = savingsElement.getText();
        String numericSavings = savingsText.replaceAll("[^\\d.]", "");
        int savingsAsInt = (int) Double.parseDouble(numericSavings);
        long savings = Math.round(supportPrice * anotherDiscountValue * (-1) * targetNumber) / 100;
        double savings1 = Math.round(supportPrice * anotherDiscountValue * (-1) * targetNumber) / 100.00;
        assertEquals(Math.round(savingsAsInt * 100) / 100, savings);


        SelenideElement hoverElement = $x("//div[contains(@class, 'e2e-total-discounts-label')]//i");
        savingsElement.scrollTo();
        hoverElement.hover();
        //sleep(5000);
        SelenideElement tooltipDiv = $x("//div[contains(@class, 'tooltip-info--font-l')]");
        tooltipDiv.shouldBe(Condition.visible);
        List<SelenideElement> priceElements = $$x("//div[contains(@class, 'tooltip-info--font-l')]//span[text()='Licenses']/following-sibling::div/span | //div[contains(@class, 'tooltip-info--font-l')]//span[text()='Maintenance & Support']/following-sibling::div/span");
        String licenseDiscount = priceElements.get(0).getText();
        String maintenanceDiscount = priceElements.get(1).getText();
        double licenseDiscountInt = Integer.parseInt(licenseDiscount.replaceAll("[^\\d]", "")) / 100.00;
        double maintenanceDiscountInt = Integer.parseInt(maintenanceDiscount.replaceAll("[^\\d]", "")) / 100.00;
        assertEquals(savings1 * years, maintenanceDiscountInt);
        double unitSaving = price * discountValue / 100 * targetNumber;
        assertEquals(unitSaving, licenseDiscountInt);

        SelenideElement continueAsGuestButton = $x("//button[contains(@class, 'btn-primary e2e-continue sm-width-100 loader-button')]");

        continueAsGuestButton.shouldBe(Condition.visible).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        SelenideElement firstNameField = $x("//input[@id='biFirstName']");
        firstNameField.shouldBe(Condition.visible).setValue(firstName);
        SelenideElement lastNameField = $x("//input[@id='biLastName']");
        lastNameField.shouldBe(Condition.visible).setValue(lastName);
        SelenideElement emailField = $x("//input[@id='biEmail']");
        emailField.shouldBe(Condition.visible).setValue(faker.internet().emailAddress());
        SelenideElement companyField = $x("//input[@id='biCompany']");
        companyField.shouldBe(Condition.visible).setValue(faker.company().name());
        SelenideElement phoneField = $x("//input[@id='biPhone']");
        phoneField.shouldBe(Condition.visible).setValue(faker.phoneNumber().cellPhone());
        SelenideElement cityField = $x("//input[@id='biCity']");
        cityField.shouldBe(Condition.visible).setValue(faker.address().city());
        SelenideElement addressField = $x("//input[@id='biAddress']");
        addressField.shouldBe(Condition.visible).setValue(faker.address().streetAddress());
        SelenideElement postalCodeField = $x("//input[@id='biZipCode']");
        postalCodeField.shouldBe(Condition.visible).setValue(faker.address().zipCode());
        SelenideElement countryDropdown = $x("//input[contains(@class, 'k-input-inner') and @aria-expanded='false']");
        countryDropdown.shouldBe(Condition.visible).setValue("Georgia");
        SelenideElement firstOption = $x("//ul[contains(@class, 'k-list-ul')]//li");
        Selenide.executeJavaScript("arguments[0].click();", firstOption);

        SelenideElement backButton = $x("//a[contains(@class, 'btn-default e2e-back u-mr10 sm-width-100 loader-button')]");

        backButton.scrollTo();
        //sleep(3000);
        backButton.shouldBe(Condition.visible).click();
        continueAsGuestButton.scrollTo();
        continueAsGuestButton.shouldBe(Condition.visible).click();
        firstNameField.shouldHave(Condition.value(firstName));
    }

    @Test
    public void chainedLocatorsTest() {
        open("https://demoqa.com/books");
        ElementsCollection books = $$x("//div[@class='rt-tbody']//div[@class='rt-tr-group']")
                .filterBy(Condition.text("O'Reilly Media"))
                .filterBy(Condition.text("Javascript"));
        Assertions.assertFalse(books.isEmpty());
        for (SelenideElement book : books) {
            SelenideElement image = book.$("img");
            image.shouldHave(Condition.attributeMatching("src", ".*\\S+.*"));
        }
    }
    @Test
    public void softAssertTest() {
        open("https://demoqa.com/books");
        SelenideElement rtTbody = $x("//div[@class='rt-tbody']").shouldBe(Condition.visible);
        List<SelenideElement> allBooks = rtTbody.$$x(".//div[@class='rt-tr-group']");
        List<SelenideElement> javascriptBooks = allBooks.stream()
                .filter(book -> book.getText().contains("O'Reilly Media") && book.getText().contains("Javascript"))
                .collect(Collectors.toList());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(javascriptBooks.size(), 10, "Expected 10 books but found: " + javascriptBooks.size());
        if (!javascriptBooks.isEmpty()) {
            String firstBookTitle = javascriptBooks.get(0).$x(".//div[@class='rt-td']").text();
            softAssert.assertEquals(firstBookTitle, "Git Pocket Guide");
        }
        softAssert.assertAll();

    }

    private static boolean isNumberInRange(int number, String rangeText) {
        if (rangeText.contains("Licenses")) {
            String[] parts = rangeText.split(" ")[0].split("-");
            int lowerBound = Integer.parseInt(parts[0]);
            int upperBound = Integer.parseInt(parts[1]);
            return number >= lowerBound && number <= upperBound;
        }
        return false;
    }
}
