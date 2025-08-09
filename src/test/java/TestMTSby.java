import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

class MtsPaySectionTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private final String BASE_URL = "https://www.mts.by";
    private final String PAY_SECTION_TITLE = "Онлайн пополнение без комиссии";
    private final String[] PAYMENT_LOGOS = {
            "Visa", "Verified By Visa", "MasterCard",
            "MasterCard Secure Code", "Белкарт"
    };

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @BeforeEach
    void openSite() {
        driver.get(BASE_URL);
        acceptCookiesIfPresent();
    }

    @Test
    @Order(1)
    void verifyPaySectionTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[contains(@class, 'pay')]//h2")
        ));

        String normalizedTitle = title.getText()
                .replace("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();

        Assertions.assertEquals(PAY_SECTION_TITLE, normalizedTitle);
    }

    @Test
    @Order(2)
    void verifyPaymentLogos() {
        WebElement logosContainer = driver.findElement(By.className("pay__partners"));
        List<WebElement> logos = logosContainer.findElements(By.tagName("img"));

        Assertions.assertEquals(PAYMENT_LOGOS.length, logos.size(),
                "Количество логотипов не соответствует ожидаемому");

        for (int i = 0; i < PAYMENT_LOGOS.length; i++) {
            String altText = logos.get(i).getAttribute("alt");
            Assertions.assertEquals(PAYMENT_LOGOS[i], altText,
                    "Логотип " + PAYMENT_LOGOS[i] + " не найден");
        }
    }

    @Test
    @Order(3)
    void verifyDetailsLink() {
        WebElement detailsLink = driver.findElement(
                By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")
        );

        String expectedHref = "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        Assertions.assertTrue(detailsLink.getAttribute("href").contains(expectedHref),
                "Некорректный URL ссылки");

        String originalWindow = driver.getWindowHandle();

        detailsLink.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String window : driver.getWindowHandles()) {
            if (!originalWindow.equals(window)) {
                driver.switchTo().window(window);
                break;
            }
        }

        Assertions.assertTrue(driver.getCurrentUrl().contains(expectedHref),
                "Неверный URL после перехода");

        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    @Order(4)
    void testConnectionFormSubmission() {
        WebElement form = driver.findElement(By.id("pay-connection"));

        WebElement phoneInput = form.findElement(By.id("connection-phone"));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        WebElement sumInput = form.findElement(By.id("connection-sum"));
        sumInput.clear();
        sumInput.sendKeys("350");

        WebElement emailInput = form.findElement(By.id("connection-email"));
        emailInput.clear();
        emailInput.sendKeys("test@mail.ru");

        WebElement submitButton = form.findElement(By.xpath(".//button[@type='submit']"));
        submitButton.click();
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void acceptCookiesIfPresent() {
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Принять')]")
            ));
            acceptButton.click();
        } catch (TimeoutException e) {
        }
    }
}