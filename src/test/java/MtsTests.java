import com.lesson11.MtsMainPage;
import com.lesson11.PayFrame;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Тесты веб-интерфейса МТС")
@Feature("Проверка функционала оплаты")
public class MtsTests {

    private WebDriver driver;
    private MtsMainPage mtsMainPage;

    @BeforeAll
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mtsMainPage = new MtsMainPage(driver);
        mtsMainPage.navigateTo("https://www.mts.by/");
        mtsMainPage.acceptCookiesIfPresent();
    }

    @Test
    @Story("Проверка плейсхолдеров")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка плейсхолдеров на всех вкладках")
    @Description("Тест проверяет корректность плейсхолдеров на разных вкладках сервиса")
    public void testPlaceholdersOnAllTabs() {
        assertEquals("Номер телефона", mtsMainPage.getPhoneInputPlaceholder());

        mtsMainPage.clickHomeInternetTab();
        assertEquals("Номер абонента", mtsMainPage.getHomeInternetPlaceholder());

        mtsMainPage.clickInstallmentTab();
        assertEquals("Номер абонента", mtsMainPage.getInstallmentPlaceholder());

        mtsMainPage.clickDebtTab();
        assertEquals("Номер абонента", mtsMainPage.getDebtPlaceholder());
    }

    @Test
    @Story("Проверка окна оплаты")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка появления платежного фрейма")
    @Description("Тест проверяет появление окна оплаты")
    public void testPaymentIframeAppears() {
        mtsMainPage.fillPhone("297777777");
        mtsMainPage.fillSum("350");
        mtsMainPage.fillEmail("test@mail.ru");
        mtsMainPage.clickContinueButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement iframeElement = wait.until(ExpectedConditions.visibilityOf(mtsMainPage.getPaymentIframe()));
        assertTrue(iframeElement.isDisplayed(), "Окно для оплаты (iframe) не появилось");

        driver.switchTo().defaultContent(); // Возврат из фрейма
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (driver != null) {
            // Прикрепление скриншота при падении теста
            if (testInfo.getTestMethod().get().isAnnotationPresent(DisplayName.class) &&
                    testInfo.getTestMethod().get().getAnnotation(DisplayName.class).value().contains("Проверка")) {
                attachScreenshot();
            }
            driver.quit();
        }
    }

    @Attachment(value = "Скриншот при падении", type = "image/png")
    private byte[] attachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}