import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.data.Constants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class AlternativeParametrizations extends BaseTest{
    @DataProvider(name = "formData")
    public Object[][] provideFormData() {
        return new Object[][]{
                {"nika", "vinmeshvili", "Male", "1234567890"},
                {"tekle", "shalikiani", "Female", "9876543210"},
                {"sellu", "abashidze", "Female", "1231231234"}
        };
    }

    @Test(dataProvider = "formData")
    public void fillFormUsingDataProviders(String firstName, String lastName, String gender, String mobileNumber) {
        open(Constants.DEMOCA_FORM_URL);

        $x(Constants.FIRST_NAME_INPUT).shouldBe(visible).setValue(firstName);

        $x(Constants.LAST_NAME_INPUT).shouldBe(visible).setValue(lastName);

        $x("//label[contains(text(), '" + gender + "')]").scrollTo().click();

        $x(Constants.USER_NUMBER_INPUT).shouldBe(visible).setValue(mobileNumber);

        $x(Constants.SUBMIT_BUTTON).scrollTo().shouldBe(enabled).click();

        String studentName = $x(Constants.STUDENT_NAME_CELL).shouldBe(visible).getText();
        Assert.assertEquals(studentName, firstName + " " + lastName);
    }

    @Parameters({"firstName", "lastName", "gender", "mobileNumber"})
    @Test
    public void fillFormUsingParameters(String firstName, String lastName, String gender, String mobileNumber) {
        open(Constants.DEMOCA_FORM_URL);

        $x(Constants.FIRST_NAME_INPUT).shouldBe(visible).setValue(firstName);

        $x(Constants.LAST_NAME_INPUT).shouldBe(visible).setValue(lastName);

        $x("//label[contains(text(), '" + gender + "')]").scrollTo().click();

        $x(Constants.USER_NUMBER_INPUT).shouldBe(visible).setValue(mobileNumber);

        $x(Constants.SUBMIT_BUTTON).scrollTo().shouldBe(enabled).click();

        String studentName = $x(Constants.STUDENT_NAME_CELL).shouldBe(visible).getText();
        Assert.assertEquals(studentName, firstName + " " + lastName);
    }
}
