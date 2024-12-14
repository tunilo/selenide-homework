package ge.tbc.testautomation.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static com.codeborne.selenide.Selenide.*;

public class HelperFunctions {

    public List<Double> collectAllPrices(String selector) {
        List<Double> prices = new ArrayList<>();
        int currentPage = 1;
        navigateToNextPage(currentPage);
        do {
            ElementsCollection priceElements = $$x(selector);
            prices.addAll(priceElements.stream()
                    .map(el -> extractPrice(el.getText()))
                    .collect(Collectors.toList()));

            currentPage++;
        } while (navigateToNextPage(currentPage));

        return prices;
    }

    private boolean navigateToNextPage(int currentPage) {
        try {
            SelenideElement nextPageButton = $x("//div[text()='" + currentPage + "']");
            if (!nextPageButton.exists() && currentPage != 1) return false;
            else if (nextPageButton.should(Condition.exist).shouldBe(Condition.visible).exists()) {
                nextPageButton.scrollTo().click();
                $x("//div[text()='" + currentPage + "']").shouldBe(Condition.visible);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public double extractPrice(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    }
}
