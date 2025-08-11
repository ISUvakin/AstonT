package com.lesson11;

import com.lesson11.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PayFrame extends BasePage {

    private final By sumDisplayLocator = By.className("pay-description_cost");
    private final By googlePayButtonLocator = By.className("GPay-logo");
    private final By phoneNumberDisplayLocator = By.className("pay-description_text");
    private final By cardNumberInputLocator = By.id("cardNumber");
    private final By expiryDateInputLocator = By.id("cardDate");
    private final By cvcInputLocator = By.id("cardCVC");
    private final By paymentLogosInIframeLocator = By.xpath("//ul[@class='cards-brands']/li/img");

    public PayFrame(WebDriver driver) {
        super(driver);
    }

    @Step("Получение суммы платежа из фрейма")
    public String getSum() {
        WebElement sumDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(sumDisplayLocator));
        return sumDisplay.getText().replace(" BYN", "");
    }

    @Step("Проверка наличия кнопки Google Pay")
    public boolean isGooglePayButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(googlePayButtonLocator)).isDisplayed();
    }

    @Step("Получение номера телефона из фрейма")
    public String getPhoneNumber() {
        WebElement phoneNumberDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberDisplayLocator));
        return phoneNumberDisplay.getText();
    }

    @Step("Получение плейсхолдера поля 'Номер карты'")
    public String getCardNumberPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInputLocator)).getAttribute("placeholder");
    }

    @Step("Получение плейсхолдера поля 'Срок действия'")
    public String getExpiryDatePlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(expiryDateInputLocator)).getAttribute("placeholder");
    }

    @Step("Получение плейсхолдера поля 'CVC'")
    public String getCvcPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cvcInputLocator)).getAttribute("placeholder");
    }

    @Step("Проверка отображения логотипов платежных систем")
    public boolean areLogosDisplayed() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(paymentLogosInIframeLocator));
        return !driver.findElements(paymentLogosInIframeLocator).isEmpty();
    }
}