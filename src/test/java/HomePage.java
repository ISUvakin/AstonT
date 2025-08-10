import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends MtsTests {
    private final By paymentLogosLocator = By.cssSelector("div.pay__partners img");
    private final WebDriver driver;

    @FindBy(id = "cookie-agree")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = "//h2[.='Онлайн пополнение без комиссии']")
    private WebElement paymentBlockTitle;

    @FindBy(linkText = "Подробнее о сервисе")
    private WebElement moreAboutServiceLink;

    @FindBy(id = "connection-phone")
    private static WebElement phoneInput;

    @FindBy(id = "connection-sum")
    private static WebElement amountInput;

    @FindBy(id = "arrears-email")
    private static WebElement emailInput;

    @FindBy(xpath = "//form[@id='pay-connection']//button[@type='submit']")
    private static WebElement submitButton;

    @FindBy(xpath = "//iframe[contains(@class, 'bepaid-iframe')]")
    private WebElement paymentIframe;

    @FindBy(className = "select__header")
    private WebElement serviceDropdown;

    @FindBy(xpath = "//p[text()='Домашний интернет']")
    private WebElement homeInternetTab;

    @FindBy(xpath = "//p[text()='Рассрочка']")
    private WebElement installmentTab;

    @FindBy(xpath = "//p[text()='Задолженность']")
    private WebElement debtTab;

    @FindBy(id = "internet-phone")
    private WebElement universalAccountInput;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void click(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickHomeInternetTab() {
        click(serviceDropdown);
        click(wait.until(ExpectedConditions.visibilityOf(homeInternetTab)));
    }

    public void clickInstallmentTab() {
        click(serviceDropdown);
        click(wait.until(ExpectedConditions.visibilityOf(installmentTab)));
    }

    public void clickDebtTab() {
        click(serviceDropdown);
        click(wait.until(ExpectedConditions.visibilityOf(debtTab)));
    }

    public void acceptAllCookies() {
        try {
            WebDriverWait cookieWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            cookieWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
        } catch (TimeoutException e) {
            System.out.println("Cookie баннер не был найден.");
        }
    }
    public String getPaymentBlockTitleText() {
        wait.until(ExpectedConditions.visibilityOf(paymentBlockTitle));
        return paymentBlockTitle.getText();
    }
    public List<WebElement> getPaymentLogos() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(paymentLogosLocator));
        return driver.findElements(paymentLogosLocator);
    }
    public void clickMoreAboutServiceLink() {
        moreAboutServiceLink.click();
    }
    public void fillPhoneNumber(String phoneNumber) {
        phoneInput.sendKeys(phoneNumber);
    }
    public void fillAmount(String amount) {
        amountInput.sendKeys(amount);
    }
    public void fillEmail(String email) {
        emailInput.sendKeys(email);
    }
    public void clickContinueButton() {
        submitButton.click();
    }
    public WebElement getPaymentIframe() {
        return paymentIframe;
    }
    public void navigateTo(String url) {
        driver.get(url);
    }
    public String getHomeInternetPlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(universalAccountInput));
        return universalAccountInput.getAttribute("placeholder");
    }
    public String getInstallmentPlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(universalAccountInput));
        return universalAccountInput.getAttribute("placeholder");
    }
    public String getDebtPlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(universalAccountInput));
        return universalAccountInput.getAttribute("placeholder");
    }
    public String getPhoneInputPlaceholder() {
        return phoneInput.getAttribute("placeholder");
    }
}