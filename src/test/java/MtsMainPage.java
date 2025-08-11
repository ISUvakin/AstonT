package com.lesson11;

import com.lesson11.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import java.util.List;

public class MtsMainPage extends BasePage {

    // Локаторы
    @FindBy(id = "cookie-agree") private WebElement acceptCookiesButton;
    @FindBy(xpath = "//h2[.='Онлайн пополнение без комиссии']") private WebElement paymentBlockTitle;
    private final By paymentLogosLocator = By.cssSelector("div.pay__partners img");
    @FindBy(linkText = "Подробнее о сервисе") private WebElement moreAboutServiceLink;
    @FindBy(id = "connection-phone") private WebElement phoneInput;
    @FindBy(id = "connection-sum") private WebElement sumInput;
    @FindBy(id = "connection-email") private WebElement emailInput;
    @FindBy(xpath = "//form[@id='pay-connection']//button[@type='submit']") private WebElement submitButton;
    @FindBy(xpath = "//iframe[contains(@class, 'bepaid-iframe')]") private WebElement paymentIframe;
    @FindBy(className = "select__header") private WebElement serviceDropdown;
    @FindBy(xpath = "//p[text()='Домашний интернет']") private WebElement homeInternetTab;
    @FindBy(xpath = "//p[text()='Рассрочка']") private WebElement installmentTab;
    @FindBy(xpath = "//p[text()='Задолженность']") private WebElement debtTab;
    @FindBy(id = "internet-phone") private WebElement universalAccountInput;

    public MtsMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void clickViaJs(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Переход на вкладку 'Домашний интернет'")
    public void clickHomeInternetTab() {
        clickViaJs(serviceDropdown);
        clickViaJs(wait.until(ExpectedConditions.visibilityOf(homeInternetTab)));
    }

    @Step("Переход на вкладку 'Рассрочка'")
    public void clickInstallmentTab() {
        clickViaJs(serviceDropdown);
        clickViaJs(wait.until(ExpectedConditions.visibilityOf(installmentTab)));
    }

    @Step("Переход на вкладку 'Задолженность'")
    public void clickDebtTab() {
        clickViaJs(serviceDropdown);
        clickViaJs(wait.until(ExpectedConditions.visibilityOf(debtTab)));
    }

    @Step("Принятие куки, если баннер присутствует")
    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait cookieWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            cookieWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
        } catch (Exception e) {
            System.out.println("Cookie баннер не был найден.");
        }
    }

    @Step("Получение текста заголовка блока оплаты")
    public String getPaymentBlockTitleText() {
        wait.until(ExpectedConditions.visibilityOf(paymentBlockTitle));
        return paymentBlockTitle.getText();
    }

    @Step("Получение логотипов платежных систем")
    public List<WebElement> getPaymentLogos() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(paymentLogosLocator));
        return driver.findElements(paymentLogosLocator);
    }

    @Step("Ввод номера телефона: {phoneNumber}")
    public void fillPhone(String phoneNumber) {
        phoneInput.sendKeys(phoneNumber);
    }

    @Step("Ввод суммы: {Sum}")
    public void fillSum(String Sum) {
        sumInput.sendKeys(Sum);
    }

    @Step("Ввод email: {Email}")
    public void fillEmail(String Email) {
        emailInput.sendKeys(Email);
    }

    @Step("Нажатие кнопки 'Продолжить'")
    public void clickContinueButton() {
        submitButton.click();
    }
    @Step("Переход в окно оплаты'")
    public WebElement getPaymentIframe() {
        return paymentIframe;
    }

    @Step("Переход по URL: {url}")
    public void navigateTo(String url) {
        driver.get(url);
    }

    @Step("Получение плейсхолдера для 'Домашний интернет'")
    public String getHomeInternetPlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(universalAccountInput));
        return universalAccountInput.getAttribute("placeholder");
    }

    @Step("Получение плейсхолдера для 'Рассрочка'")
    public String getInstallmentPlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(universalAccountInput));
        return universalAccountInput.getAttribute("placeholder");
    }

    @Step("Получение плейсхолдера для 'Задолженность'")
    public String getDebtPlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(universalAccountInput));
        return universalAccountInput.getAttribute("placeholder");
    }

    @Step("Получение плейсхолдера поля 'Телефон'")
    public String getPhoneInputPlaceholder() {
        return phoneInput.getAttribute("placeholder");
    }
}