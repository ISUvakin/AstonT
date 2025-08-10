import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Добавлено для поддержки @Order
class MtsTests {
    private static WebDriver driver;
    public static WebDriverWait wait;
    private static HomePage homePage;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    @BeforeEach
    void openSite() {
        driver.get("https://www.mts.by");
        homePage = new HomePage(driver);
        homePage.acceptAllCookies();
    }

    @Test
    public void testPlaceholdersOnAllTabs() {
        // Исправлено: убрана дублирующая assertEquals
        assertEquals("Номер телефона", homePage.getPhoneInputPlaceholder());

        homePage.clickHomeInternetTab();
        assertEquals("Номер абонента", homePage.getHomeInternetPlaceholder());

        homePage.clickInstallmentTab();
        assertEquals("Номер абонента", homePage.getInstallmentPlaceholder());

        homePage.clickDebtTab();
        assertEquals("Номер абонента", homePage.getDebtPlaceholder());
    }

    @Test
    @Order(2)
    void verifyPaymentModule() {
        // Исправлено: вызов методов через экземпляр homePage
        homePage.fillPhoneNumber("297777777");
        homePage.fillAmount("350");
        //homePage.fillEmail("test@mail.ru");
        homePage.clickContinueButton();

        // Создаем экземпляр PaymentModule
        PaymentModule paymentModule = new PaymentModule(driver);

        // Исправлено: вызов методов через экземпляр
        assertEquals("Номер карты", paymentModule.getCardNumber());
        assertEquals("ММ/ГГ", paymentModule.getExpiryDate());
        assertEquals("CVC", paymentModule.getCVCPlaceholder());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement iframeElement = homePage.getPaymentIframe();
        wait.until(ExpectedConditions.visibilityOf(iframeElement));

        assertTrue(iframeElement.isDisplayed(), "Окно для оплаты (iframe) не появилось.");
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}