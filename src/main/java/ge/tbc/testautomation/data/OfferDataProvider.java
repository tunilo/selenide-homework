package ge.tbc.testautomation.data;
import org.testng.annotations.DataProvider;
public class OfferDataProvider {
    @DataProvider(name = "offerPriceData")
    public static Object[][] provideCartOfferData() {
        return new Object[][]{
                {"კარტინგით რბოლა"},
                {"პადელის კორტი 1 საათით"},
                {"ფიტნესის აბონემენტი"},
                {"თხილამურზე სრიალის სასწავლო პროგრამა"},
                {"ულიმიტო აბონემენტი + ინსტრუქტორი"},
                {"ფიტნესის 1 თვიანი აბონიმენტი ქალბატონებისთვის"},
                {"რეგულარული და ჯგუფური ვარჯიშები"},
                {"საცხენოსნო ტურები და საცხენოსნო გაკვეთილები"},
                {"12 ვიზიტი პადელის ღია კორტზე 1 საათით 18:00 საათამდე"},
                {"8 ვიზიტი პადელის ღია კორტზე 1 საათით 18:00 საათამდე"}
        };
    }
}
