import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentModule extends MtsTests {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By amountDisplayLocator = By.className("pay-description_cost");
    private final By googlePayButtonLocator = By.className("GPay-logo");
    private final By phoneNumberDisplayLocator = By.className("pay-description_text");
    private final By cardNumberInputLocator = By.id("cardNumber");
    private final By expiryDateInputLocator = By.id("cardDate");
    private final By cvcInputLocator = By.id("cardCVC");
    private final By paymentLogosInIframeLocator = By.xpath("//ul[@class='cards-brands']/li/img");

    public PaymentModule(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public String getAmount() {
        WebElement amountDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(amountDisplayLocator));
        return amountDisplay.getText().replace(" BYN", "");
    }

    public boolean isGooglePayButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(googlePayButtonLocator)).isDisplayed();
    }

    public String getPhoneNumber() {
        WebElement phoneNumberDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberDisplayLocator));
        return phoneNumberDisplay.getText();
    }

    public String getCardNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInputLocator)).getAttribute("placeholder");
    }

    public String getExpiryDate() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(expiryDateInputLocator)).getAttribute("placeholder");
    }

    public String getCVCPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cvcInputLocator)).getAttribute("placeholder");
    }

    public boolean areLogosDisplayed() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(paymentLogosInIframeLocator));
        return !driver.findElements(paymentLogosInIframeLocator).isEmpty();
    }
}