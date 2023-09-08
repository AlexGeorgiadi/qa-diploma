package ru.netology.qadiploma.page;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private SelenideElement card = $x("//span/following::input[1]");
    private SelenideElement month = $x("//span/following::input[2]");
    private SelenideElement year = $x("//span/following::input[3]");
    private SelenideElement name = $x("//span/following::input[4]");
    private SelenideElement cvc = $x("//span/following::input[5]");
    private SelenideElement nextButton = $x("//span/following::button[2]");
    private SelenideElement buyButton = $x("//*[contains(text(),'Купить')]");
    private SelenideElement buyOnCreditButton = $x("//*[contains(text(),'Купить в кредит')]");
    private SelenideElement successNotification = $x("//*[contains(text(),'Успешно')]");
    private SelenideElement refusalNotification = $x("//*[contains(text(),'Банк отказал')]");
    private SelenideElement wrongFormatNotification = $x("//*[contains(text(),'Неверный формат')]");
    private SelenideElement wrongDateNotification = $x("//*[contains(text(),'Неверно указан')]");
    private SelenideElement cardExpiredNotification = $x("//*[contains(text(),'Истёк срок действия карты')]");
    private SelenideElement requiredField = $x("//*[contains(text(),'Поле обязательно для заполнения')]");
    private SelenideElement buyHeader = $x("//*[contains(text(),'Оплата по карте')]");
    private SelenideElement buyOnCreditHeader = $x("//*[contains(text(),'Кредит по данным карты')]");
    public MainPage() {
    }

    public MainPage buyButtonCLick() {
        buyButton.click();
        buyHeader.shouldBe(visible);
        return this;
    }

    public MainPage buyOnCreditButtonCLick() {
        buyOnCreditButton.click();
        buyOnCreditHeader.shouldBe(visible);
        return this;
    }

    public MainPage fillTheForm(String cardNumber, String monthMM, String yearYY, String nameLastName, String CVVCVC) {
        card.setValue(cardNumber);
        month.setValue(monthMM);
        year.setValue(yearYY);
        name.setValue(nameLastName);
        cvc.setValue(CVVCVC);
        nextButton.click();
        return this;
    }

    public MainPage checkSuccessNotification(){
        successNotification.should((appear), Duration.ofSeconds(15));
        return this;
    }

    public MainPage checkCardExpiredNotification() {
        cardExpiredNotification.shouldBe(visible);
        return this;
    }

    public MainPage checkWrongFormatNotification(){
        wrongFormatNotification.shouldBe(visible);
        return this;
    }

    public MainPage checkRefusalNotification(){
        refusalNotification.should((appear), Duration.ofSeconds(15));
        return this;
    }

    public MainPage checkRequiredField(){
        requiredField.shouldBe(visible);
        return this;
    }

    public MainPage checkWrongDateNotification(){
        wrongDateNotification.shouldBe(visible);
        return this;
    }
}
