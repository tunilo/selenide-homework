package ge.tbc.testautomation.data;

public class Constants {
    public static final String DEMOS_URL = "https://www.telerik.com/support/demos";
    public static final String HEROKUAPP_URL = "http://the-internet.herokuapp.com/dropdown";
    public static final String DEMOCA_URL = "https://demoqa.com/text-box ";
    public static final String DEMOCA_FORM_URL = "https://demoqa.com/automation-practice-form";
    public static final String FULL_NAME ="Tekle Shalikiani";
    public static final String EMAIL = "tekla.shalikiani123@gmail.com";
    public static final String ADDRESS = "Dighomi array, Tbilisi";
    public static final String webCards = "//div[contains(@class, 'row')]//div[contains(@id, '329')]//div[contains(@class, 'HoverImg')]";
    public static final String targetClass = "HoverImg";
    public static final String targetText = "HoverImg";
    public static final String KENDO_UI_CARD_XPATH = "//div[contains(@class, 'row')]//div[contains(@id, '329')]//div[contains(@class, 'HoverImg')]";
    public static final String WEB_HEADER_XPATH = "//h2[@id='web' and text()='Web']";
    public static final String DESKTOP_CARDS_XPATH = "//div[contains(@class, 'row')]//div[contains(@id, '337')]";
    public static final String XAMARIN_SECTION_XPATH = "//div[@id='ContentPlaceholder1_C340_Col01']";
    public static final String XAMARIN_PLAY_STORE_LINK_XPATH = ".//a[@href='https://play.google.com/store/apps/details?id=com.telerik.xamarin&hl=en']";
    public static final String XAMARIN_MICROSOFT_STORE_LINK_XPATH = ".//a[contains(@href, 'microsoft.com/en-us/store/p/telerik-ui-for-xamarin-examples')]";
    public static final String XAMARIN_APPLE_STORE_LINK_XPATH = ".//a[contains(@href, 'itunes.apple.com')]";
    public static final String NAV_SECTION_XPATH = "//nav[@data-tlrk-plugin='fixit']";
    public static final String NAV_LINKS_XPATH = "//nav[@data-tlrk-plugin='fixit']//a";
    public static final String HEADERS_XPATH = "//h2[contains(@class, 'u-mb0 h3')]";
    public static final String PRICING_MENU_LINK_XPATH = "//a[@class='TK-Menu-Item-Link' and text()='Pricing']";
    public static final String COMPLETE_PRICE_XPATH = "//th[contains(@class, 'Complete')]//span[contains(@class, 'u-dib')]";
    public static final String BUY_NOW_LINK_XPATH = "//a[@href='https://store.progress.com/configure-purchase?skuId=6127']";
    public static final String CLOSE_MODAL_XPATH = "//i[contains(@class, 'far fa-times label u-cp')]";
    public static final String UNIT_PRICE_XPATH = "//span[@class='e2e-price-per-license']";
    public static final String DROPDOWN_BUTTON_XPATH = "//button[@class='k-input-button k-button k-icon-button k-button-md k-button-solid k-button-solid-base']";
    public static final String DROPDOWN_POPUP_XPATH = "//kendo-popup[contains(@class, 'k-animation-container k-animation-container-shown')]";
    public static final String FIRST_POPUP_XPATH = "(//kendo-popup[contains(@class, 'k-animation-container k-animation-container-fixed k-animation-container-shown')])";
    public static final String LICENSE_ENTRIES_XPATH = ".//div[contains(@class, 'u-df justify-content-between u-mb10')]";
    public static final String SUPPORT_PRICE_XPATH = "//span[contains(@class, 'bold') and contains(text(), '$')]";
    public static final int TARGET_NUMBER = 5;
    public static final String DISCOUNT = "";
    public static final String NUM = "5";
    public static final String TARGET_OPTION = "+4 years";
    public static final String ANOTHER_DISCOUNT = "";
    public static final String SAVINGS_ELEMENT_XPATH = "//div[contains(@class, 'e2e-item-ms-savings') and contains(text(), 'Save')]";
    public static final String POPUP_XPATH = "//kendo-popup[contains(@class, 'k-animation-container-shown')]";
    public static final String DROPDOWN_BUTTON_YERS = "(//button[contains(@class, 'k-input-button') and .//kendo-svgicon[contains(@class, 'k-svg-i-caret-alt-down')]])[last()]";


    public static final String HOLIDAY_PAGE_LINK = "//p[contains(text(), 'დასვენება')]";
    public static final String MOUNTAIN_RESORTS = "//h5[contains(text(),'მთის კურორტები')]";
    public static final String FULL_PAYMENT_OPTION = "//span[contains(text(),'სრული გადახდა')]";
    public static final String FULL_PAYMENT_ICON = "//p[text()='სრული გადახდა']";
    public static final String PRICE_LOCATOR = "//h4[contains(., '₾') and @weight='bold']";
    public static final String SORT_BUTTON = "//p[contains(text(), 'შესაბამისობით')]";
    public static final String ASCENDING_ORDER = "//p[contains(text(), 'ფასით ზრდადი')]";
    public static final String DESCENDING_ORDER = "//p[contains(text(), 'ფასით კლებადი')]";
    public static String MIN_PRICE_INPUT = "//div[contains(@class, 'relative') and ./p[contains(text(), 'დან')]]//input\n";
    public static String MAX_PRICE_INPUT = "//div[contains(@class, 'relative') and ./p[contains(text(), 'მდე')]]//input\n";
    public static String  MIN_PRICE = "0";
    public static String  MAX_PRICE = "600";
    public static int  MIN_PRICE_INT = 0;
    public static int  MAX_PRICE_INT = 600;
    public static String FILTER_BUTTON ="//button[contains(@class, 'flex group items-center justify-center') and contains(@class, 'hover:bg-primary_green-20-value')]";
    public static final String CATEGORY_2 = "//p[contains(text(), 'სპორტი')]";
    public static final String CATEGORY_1 = "//p[contains(text(), 'დასვენება')]";
    public static final String CATEGORY_3 = "//p[contains(text(), 'კვება')]";
    public static final String SWOOP_HOME_URL = "https://www.swoop.ge/";
    public static final String FIRST_NAME_INPUT = "//input[@id='firstName']";
    public static final String LAST_NAME_INPUT = "//input[@id='lastName']";
    public static final String USER_NUMBER_INPUT = "//input[@id='userNumber']";
    public static final String SUBMIT_BUTTON = "//button[@id='submit']";
    public static final String STUDENT_NAME_CELL = "//td[text()='Student Name']/following-sibling::td";
    public static final String DISCOUNT_PRICE_LOCATOR = ".//p[contains(text(), '%') and @weight='bold']";
    public static final String SPORT_SECTION = "//p[contains(text(), 'სპორტი')]";
    public static final String INITIAL_PRICE_LOCATOR = ".//h4[contains(@class, 'line-through') and contains(., '₾')]";
    public static final String ADD_TO_BASKET_BUTTON = "//p[contains(text(), 'კალათაში დამატება')]";
    public static final String CART_BUTTON = "//a[contains(@class, 'items-center') and contains(@href, '/basket')]";
    public static final String CART_ITEMS_LOCATOR = "//div[contains(@class, 'flex w-full tablet:gap-6')]";
    public static final String NEW_PRICE_LOCATOR = ".//h4[contains(., '₾') and @weight='bold']";


}
