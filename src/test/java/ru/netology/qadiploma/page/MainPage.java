package ru.netology.qadiploma.page;

import com.codeborne.selenide.SelenideElement;
import org.example.Main;
import ru.netology.qadiploma.data.DataHelper;
import ru.netology.qadiploma.data.DataHelper.Info;

import java.text.ParseException;
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

    public MainPage buyTourWithCardA(){
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDate("MM"));
        year.setValue(DataHelper.getDate("YY"));
        name.setValue(DataHelper.generateNameLAT());
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        successNotification.should((appear), Duration.ofSeconds(15));
        return this;
    }

    public MainPage buyTourWithCardAExpiredAfterMay22() throws ParseException {
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDateAfterMay22BeforeToday("MM"));
        year.setValue(DataHelper.getDateAfterMay22BeforeToday("YY"));
        name.setValue(DataHelper.generateNameLAT());
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        successNotification.should((appear), Duration.ofSeconds(15));
        return this;
    }

    public MainPage buyTourWithCardAExpiredBeforeMay22() throws ParseException {
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDateBeforeMay22("MM"));
        year.setValue(DataHelper.getDateBeforeMay22("YY"));
        name.setValue(DataHelper.generateNameLAT());
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        cardExpiredNotification.shouldBe(visible);
        return this;
    }

    public MainPage cyrillicNameWhenBuyTourWithCardA(){
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDate("MM"));
        year.setValue(DataHelper.getDate("YY"));
        name.setValue(DataHelper.generateNameCYR("RU"));
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        wrongFormatNotification.shouldBe(visible);
        return this;
    }

    public MainPage buyTourWithCardB(){
        card.setValue(DataHelper.cardB());
        month.setValue(DataHelper.getDate("MM"));
        year.setValue(DataHelper.getDate("YY"));
        name.setValue(DataHelper.generateNameLAT());
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        refusalNotification.should((appear), Duration.ofSeconds(15));
        return this;
    }

    public MainPage emptyMonthFieldWhenBuyTourWithCardA(){
        card.setValue(DataHelper.cardA());
        year.setValue(DataHelper.getDate("YY"));
        name.setValue(DataHelper.generateNameLAT());
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        requiredField.shouldBe(visible);
        return this;
    }

    public MainPage emptyYearFieldWhenBuyTourWithCardA(){
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDate("MM"));
        name.setValue(DataHelper.generateNameLAT());
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        requiredField.shouldBe(visible);
        return this;
    }

    public MainPage emptyNameFieldWhenBuyTourWithCardA(){
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDate("MM"));
        year.setValue(DataHelper.getDate("YY"));
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        requiredField.shouldBe(visible);
        return this;
    }

    public MainPage emptyCVCFieldWhenBuyTourWithCardA(){
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDate("MM"));
        year.setValue(DataHelper.getDate("YY"));
        name.setValue(DataHelper.generateNameLAT());
        nextButton.click();
        requiredField.shouldBe(visible);
        return this;
    }

    public MainPage cardNumberOf15Digits(){
        card.setValue(DataHelper.invalidCard());
        month.setValue(DataHelper.getDate("MM"));
        year.setValue(DataHelper.getDate("YY"));
        name.setValue(DataHelper.generateNameLAT());
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        wrongFormatNotification.shouldBe(visible);
        return this;
    }

    public MainPage digitsInTheNameField(){
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDate("MM"));
        year.setValue(DataHelper.getDate("YY"));
        name.setValue(DataHelper.getCVC());
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        wrongFormatNotification.shouldBe(visible);
        return this;
    }

    public MainPage nameInOneWord(){
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDate("MM"));
        year.setValue(DataHelper.getDate("YY"));
        name.setValue(DataHelper.generateNameWithoutSpaces());
        cvc.setValue(DataHelper.getCVC());
        nextButton.click();
        wrongFormatNotification.shouldBe(visible);
        return this;
    }

    public MainPage cvcIn2Digits(){
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDate("MM"));
        year.setValue(DataHelper.getDate("YY"));
        name.setValue(DataHelper.generateNameLAT());
        cvc.setValue(DataHelper.getInvalidCVC());
        nextButton.click();
        wrongFormatNotification.shouldBe(visible);
        return this;
    }

    public MainPage invalidMonth(){
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getInvalidMonth());
        year.setValue(DataHelper.getDate("YY"));
        name.setValue(DataHelper.generateNameLAT());
        cvc.setValue(DataHelper.getInvalidCVC());
        nextButton.click();
        wrongFormatNotification.shouldBe(visible);
        return this;
    }

    public MainPage cardExpirationAfter5Years(){
        card.setValue(DataHelper.cardA());
        month.setValue(DataHelper.getDateMoreThan5Years("MM"));
        year.setValue(DataHelper.getDateMoreThan5Years("YY"));
        name.setValue(DataHelper.generateNameLAT());
        cvc.setValue(DataHelper.getInvalidCVC());
        nextButton.click();
        wrongFormatNotification.shouldBe(visible);
        return this;
    }

}
