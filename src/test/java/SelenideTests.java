import ge.tbc.testautomation.utils.RetryAnalyzer;
import ge.tbc.testautomation.utils.RetryCount;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import ge.tbc.testautomation.data.Constants;
import com.codeborne.selenide.*;


public class SelenideTests extends BaseTest {
    @Test(groups = {"Selenide 1"},priority = 4, retryAnalyzer = RetryAnalyzer.class)
    @RetryCount(count = 5)
    public void validateBundleOffers() throws InterruptedException {
        open(Constants.DEMOS_URL);
        $x("//a[@class='TK-Menu-Item-Link' and text()='Pricing']").click();
//ეს დავალება 40 ზე მეტი უნდა იყოს, გვტანჯა
        SelenideElement stickyContainer = $x("//div[@class='PricingTable-Sticky']");
// სცენა 1 დუბლი 1
        $x("//h5[text()='DevCraft UI']").shouldBe(Condition.visible);
        $x("//h5[text()='DevCraft UI']/ancestor::table//tr[contains(@class, 'Pricings-info')]//th[contains(@class, 'UI') and not(.//*[text()='Mocking solution for rapid unit testing'])]")
                .shouldBe(Condition.exist);
        //სცენა 1 დუბლი 2
        $x("//th[@class='Ultimate u-pb2']//p[contains(normalize-space(), 'Issue escalation')]")
                .shouldBe(Condition.exist);

        $x("//th[@class='UI u-pb2']//p[contains(normalize-space(), 'Issue escalation')]")
                .shouldNot(Condition.exist);
        $x("//th[@class='Complete u-pb2']//p[contains(normalize-space(), 'Issue escalation')]")
                .shouldNot(Condition.exist);

        //სცენა 1 დუბლი 3
        $x("//th[@class='Ultimate']//li[contains(text(), 'End-to-end report management solution')]")
                .shouldBe(Condition.exist);

        $x("//th[@class='UI']//li[contains(text(), 'End-to-end report management solution')]")
                .shouldNot(Condition.exist);

        $x("//th[@class='Complete']//li[contains(text(), 'End-to-end report management solution')]")
                .shouldNot(Condition.exist);

        //move on
        int uiColumnIndex = ((Long) Selenide.executeJavaScript(
                "return document.evaluate(\"count(//th[div/h5[text()='DevCraft UI']]/preceding-sibling::th) + 1\", " +
                        "document, null, XPathResult.NUMBER_TYPE, null).numberValue;"
        )).intValue();
        //აქ წერტილების ელემენტებს ვინახავდი, აღარ დამჭირდა ბათ ასეც კარგი სოლუშენი გამოდიოდა
        ElementsCollection devCraftUiCells = $$x("//tbody/tr/td[" + uiColumnIndex + "]//span[@class='dot']");
        System.out.println("Total visible dots: " + devCraftUiCells.size());

        int completeColumnIndex = ((Long) Selenide.executeJavaScript(
                "return document.evaluate(\"count(//th[div/h5[text()='DevCraft Complete']]/preceding-sibling::th) + 1\", " +
                        "document, null, XPathResult.NUMBER_TYPE, null).numberValue;"
        )).intValue();

        ElementsCollection devCraftComplateCells = $$x("//tbody/tr/td[" + completeColumnIndex + "]//span[@class='dot']");
        System.out.println("Total visible columns: " + devCraftComplateCells.size());

        int ultimateColumnIndex = ((Long) Selenide.executeJavaScript(
                "return document.evaluate(\"count(//th[div/h5[text()='DevCraft Ultimate']]/preceding-sibling::th) + 1\", " +
                        "document, null, XPathResult.NUMBER_TYPE, null).numberValue;"
        )).intValue();
        ElementsCollection devCraftUltimateCells = $$x("//tbody/tr/td[" + ultimateColumnIndex + "]//span[@class='dot']");
        System.out.println("Total visible columns: " + devCraftUltimateCells.size());

//Telerik Test Studio Dev Edition
        SelenideElement testStudioRow = $x("//tr[td[contains(., 'Telerik Test Studio Dev Edition')]]");
        testStudioRow.$x("./td[" + ultimateColumnIndex + "]//span[@class='dot']").shouldBe(Condition.exist);
        testStudioRow.$x("./td[" + uiColumnIndex + "]//span[@class='dot']").shouldNot(Condition.exist);
        testStudioRow.$x("./td[" + completeColumnIndex + "]//span[@class='dot']").shouldNot(Condition.exist);

//Kendo UI for jQuery
        SelenideElement kendoRow = $x("//tr[td[contains(., 'Kendo UI for jQuery')]]");
        kendoRow.$x("./td[" + uiColumnIndex + "]//span[@class='dot']").shouldBe(Condition.exist);
        kendoRow.$x("./td[" + completeColumnIndex + "]//span[@class='dot']").shouldBe(Condition.exist);
        kendoRow.$x("./td[" + ultimateColumnIndex + "]//span[@class='dot']").shouldBe(Condition.exist);

        //DevCraft Ultimate supports 1 instance of 'Telerik Report Server' with 15 users.

        SelenideElement reportServerRow = $x("//tr[td[contains(., 'Telerik Report Server')]]");
        reportServerRow.$x("./td[" + ultimateColumnIndex + "]//p[contains(., '1 instance with 15 users')]")
                .shouldBe(Condition.visible);
        reportServerRow.$x("./td[" + uiColumnIndex + "]//p[contains(., '1 instance with 15 users')]")
                .shouldNot(Condition.exist);
        reportServerRow.$x("./td[" + completeColumnIndex + "]//p[contains(., '1 instance with 15 users')]")
                .shouldNot(Condition.exist);

        //'Telerik Reporting' is supported by only DevCraft Complete and DevCraft Ultimate.
        SelenideElement reportingRow = $x("//tr[td[contains(., 'Telerik Reporting')]]");
        reportingRow.$x("./td[" + completeColumnIndex + "]//span[@class='dot']").shouldBe(Condition.exist);
        reportingRow.$x("./td[" + ultimateColumnIndex + "]//span[@class='dot']").shouldBe(Condition.exist);
        reportingRow.$x("./td[" + uiColumnIndex + "]//span[@class='dot']").shouldNot(Condition.exist);

        // 'Access to on-demand videos' is supported by all offers.
        SelenideElement videosRow = $x("//tr[td[contains(., 'Access to on-demand videos')]]");
        videosRow.$x("./td[" + uiColumnIndex + "]//span[@class='dot']").shouldBe(Condition.exist);
        videosRow.$x("./td[" + completeColumnIndex + "]//span[@class='dot']").shouldBe(Condition.exist);
        videosRow.$x("./td[" + ultimateColumnIndex + "]//span[@class='dot']").shouldBe(Condition.exist);


        //თავიდან პირდაპირ ინდექსებით მეწერა და მენანება წასაშლელად "დდ
        //სცენა 1 დუბლი 4
//        $x("//tr[td/p[contains(., 'Telerik Test Studio Dev Edition')]]/td[2]//span[@class='dot']")
//                .shouldNot(Condition.exist);
//
//        // Validate absence in DevCraft Complete column (2nd)
//        $x("//tr[td/p[contains(., 'Telerik Test Studio Dev Edition')]]/td[3]//span[@class='dot']")
//                .shouldNot(Condition.exist);
//
//        // Validate presence in DevCraft Ultimate column (3rd)
//        $x("//tr[td/p[contains(., 'Telerik Test Studio Dev Edition')]]/td[4]//span[@class='dot']")
//                .shouldBe(Condition.exist);
//
//
//
////        //სცენა 1 დუბლი 5
//        $x("//tr[td[contains(., 'Kendo UI for jQuery')]]/td[2]//span[@class='dot']")
//                .shouldBe(Condition.exist);
//        $x("//tr[td[contains(., 'Kendo UI for jQuery')]]/td[3]//span[@class='dot']")
//                .shouldBe(Condition.exist);
//        $x("//tr[td[contains(., 'Kendo UI for jQuery')]]/td[4]//span[@class='dot']")
//                .shouldBe(Condition.exist);
//        //სცენა 1 დუბლი 6
//        $x("//tr[td[contains(., 'Telerik Report Server')]]//td[4]//span[contains(., '1 instance with 15 users')]").shouldBe(Condition.exist);
//        //სცენა 1 დუბლი 7
//        $x("//tr[td[contains(., 'Telerik Reporting')]]/td[3]//span[@class='dot']")
//                .shouldBe(Condition.exist);
//        $x("//tr[td[contains(., 'Telerik Reporting')]]/td[4]//span[@class='dot']")
//                .shouldBe(Condition.exist);
//        $x("//tr[td[contains(., 'Telerik Reporting')]]/td[2]//span[@class='dot']")
//                .shouldNot(Condition.exist);
//        //სცენა 1 დუბლი 8
//        $x("//tr[td[contains(., 'Access to on-demand videos')]]/td[2]//span[@class='dot']")
//                .shouldBe(Condition.exist);
//        $x("//tr[td[contains(., 'Access to on-demand videos')]]/td[3]//span[@class='dot']")
//                .shouldBe(Condition.exist);
//        $x("//tr[td[contains(., 'Access to on-demand videos')]]/td[4]//span[@class='dot']")
//                .shouldBe(Condition.exist);
//


        //სცენა 1 დუბლი 9
        SelenideElement targetElement = $x("//tr[td[contains(., 'Access to on-demand videos')]]");

        executeJavaScript("arguments[0].scrollIntoView(true);", targetElement);
        stickyContainer.shouldBe(Condition.visible);
    }

    @Test(groups = {"Selenide 1"}, priority = 5,retryAnalyzer = RetryAnalyzer.class)
    @RetryCount(count = 5)
    public void validateIndividualOffers() {
        //სცენა 2 კადრი 1
        open(Constants.DEMOS_URL);
        $x("//a[@class='TK-Menu-Item-Link' and text()='Pricing']").click();
        Wait().until(webDriver -> !webDriver.getCurrentUrl().equals(Constants.DEMOS_URL));
        $x("//a[@href='#individual-products' and contains(@class, 'Tabs-tab')]").click();
        //სცენა 2 კადრი 2
        SelenideElement kendoUIOffer = $x("//div[@data-opti-expid='Kendo UI']");
        SelenideElement kendoReactOffer = $x("//div[@data-opti-expid='KendoReact']");

        SelenideElement kendoUiNinjaImage = $x("//div[@data-opti-expid='Kendo UI']//div[contains(@class, 'Box-ninja')]//img[contains(@title, 'Kendo Ui Ninja')]");
        SelenideElement kendoReactNinjaImage = $x("//div[@data-opti-expid='KendoReact']//div[contains(@class, 'Box-ninja')]//img[contains(@title, 'Kendo React Ninja')]");

        kendoReactOffer.hover();
        kendoReactNinjaImage.shouldBe(Condition.visible);

        kendoReactNinjaImage.shouldBe(Condition.disappear);

        kendoUIOffer.hover();
        kendoUiNinjaImage.shouldBe(Condition.visible);

        //სცენა 2 კადრი 3
        SelenideElement kendoUiDropdown = $x("//div[@data-opti-expid='Kendo UI']//a[contains(@class, 'Btn Dropdown-control')]");
        kendoUiDropdown.shouldHave(Condition.text("Priority Support"));
        SelenideElement kendoReactDropdown = $x("//div[@data-opti-expid='KendoReact']//a[contains(@class, 'Btn Dropdown-control')]");
        kendoReactDropdown.shouldHave(Condition.text("Priority Support"));


        //სცენა 2 კადრი 4
        SelenideElement kendoReactPrice = $x("//div[@data-opti-expid='KendoReact']//span[@class='js-price']");
        kendoReactPrice.shouldHave(Condition.text("999"));

        //სცენა 2 კადრი 5
        SelenideElement kendoUIPrice = $x("//div[@data-opti-expid='Kendo UI']//span[@class='js-price']");
        kendoUIPrice.shouldHave(Condition.text("1,149"));
    }

    //სცენა 3 იზი პიზი
    @Test(groups = {"Selenide 1", "CheckBoxes-FrontEnd"} , priority = 3)
    public void checkBoxTest() {
        open("http://the-internet.herokuapp.com/checkboxes");
        SelenideElement firstCheckbox = $x("//form[@id='checkboxes']/input");
        if (!firstCheckbox.isSelected()) {
            firstCheckbox.click();
        }
        firstCheckbox.shouldBe(Condition.selected);
        SelenideElement firstCheckboxElement = $x("//form[@id='checkboxes']/input[1]");
        SelenideElement secondCheckboxElement = $x("//form[@id='checkboxes']/input[2]");

        firstCheckboxElement.shouldHave(Condition.attribute("type", "checkbox"));
        secondCheckboxElement.shouldHave(Condition.attribute("type", "checkbox"));

    }

    //სცენა 4 //ეს ტესტი კი მიდის ლიწინით და სულ არ ჭირდება retry
    @Test(groups = {"Selenide 2", "dropDown-FrontEnd"}, priority = 1, retryAnalyzer = RetryAnalyzer.class)
    @RetryCount(count = 5)
    public void dropDownTest() {
        open(Constants.HEROKUAPP_URL);
        SelenideElement dropdown = $("#dropdown");
        dropdown.shouldHave(text("Please select an option"));
        dropdown.selectOption("Option 2");
        dropdown.shouldHave(value("2"));
        dropdown.getSelectedOption().shouldHave(text("Option 2"));
    }
    //სცენა 5

    @Test(priority = 2, groups = {"Selenide 2"})
    public void collectionsTest()  {
        WebDriverManager.firefoxdriver().setup();
        WebDriverRunner.setWebDriver(new FirefoxDriver());
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.browser = "firefox";

        open(Constants.DEMOCA_URL);
        $("#userName").setValue(Constants.FULL_NAME);
        $("[id='userEmail']").setValue(Constants.EMAIL);
        $x("//textarea[@id='currentAddress']").setValue(Constants.ADDRESS);
        $x("//textarea[contains(@id,'permanentAddress')]").setValue(Constants.ADDRESS);

        $("#submit").scrollTo().click();
        SelenideElement output = $(".border");
        output.$("#name").shouldHave(Condition.text("Name:" + Constants.FULL_NAME));
        output.$("#email").shouldHave(Condition.text("Email:" + Constants.EMAIL));
        output.$("#currentAddress").shouldHave(Condition.text("Current Address :" + Constants.ADDRESS));
        output.$("#permanentAddress").shouldHave(Condition.text("Permananet Address :" + Constants.ADDRESS));

        WebDriverRunner.getWebDriver().quit();
    }
}
