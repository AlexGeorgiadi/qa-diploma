package ru.netology.qadiploma.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import ru.netology.qadiploma.page.MainPage;

import java.text.ParseException;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.qadiploma.data.SQLHelper.*;


public class BuyOnCredit {

    MainPage mainPage;
    private final String Approved = "APPROVED";
    private final String Declined = "DECLINED";

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        cleanDatabase();
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080", MainPage.class);
        var buyButton = new MainPage().buyOnCreditButtonCLick();
    }

    @Test
    @DisplayName("Should buy a tour on credit with Card A")
    void shouldBuyATourOnCreditWithCardA() {
        var buyWithA = new MainPage().buyTourWithCardA();
        var creditRecord = getLastCreditRecord();
        var orderRecord = getLastOrderRecord();
        assertAll(
                () -> assertEquals(Approved, creditRecord.getStatus()),
                () -> assertEquals(creditRecord.getBank_id(), orderRecord.getPayment_id()));
    }

    @Test
    @DisplayName("Should buy a tour on credit with Card A expired after 01.05.2022")
    void shouldBuyATourOnCreditWithCardAExpiredAfterMay22() throws ParseException {
        var buyWithA = new MainPage().buyTourWithCardAExpiredAfterMay22();
        var creditRecord = getLastCreditRecord();
        var orderRecord = getLastOrderRecord();
        assertAll(
                () -> assertEquals(Approved, creditRecord.getStatus()),
                () -> assertEquals(creditRecord.getBank_id(), orderRecord.getPayment_id()));
    }

    @Test
    @DisplayName("Should not buy a tour on credit with Card A expired before 01.05.2022")
    void shouldNotBuyATourOnCreditWithCardAExpiredBeforeMay22() throws ParseException {
        var buyWithA = new MainPage().buyTourWithCardAExpiredBeforeMay22();
    }

    @Test
    @DisplayName("Entering a name in cyrillic when buying a tour on credit with Card A")
    void cyrillicNameWhenBuyATourOnCreditWithCardA() {
        var buyWithA = new MainPage().cyrillicNameWhenBuyTourWithCardA();
    }

    @Test
    @DisplayName("Should not buy a tour on credit with Card B")
    void shouldNotBuyATourOnCreditWithCardB() {
        var buyWithB = new MainPage().buyTourWithCardB();
        var creditRecord = getLastCreditRecord();
        var orderRecord = getLastOrderRecord();
        assertAll(
                () -> assertEquals(Approved, creditRecord.getStatus()),
                () -> assertEquals(creditRecord.getBank_id(), orderRecord.getPayment_id()));
    }

    @Test
    @DisplayName("Leaving empty Month field when buying a tour on credit with Card A")
    void emptyMonthWhenBuyATourOnCreditWithCardA() {
        var buyWithA = new MainPage().emptyMonthFieldWhenBuyTourWithCardA();
    }

    @Test
    @DisplayName("Leaving empty Year field when buying a tour on credit with Card A")
    void emptyYearWhenBuyATourOnCreditWithCardA() {
        var buyWithA = new MainPage().emptyYearFieldWhenBuyTourWithCardA();
    }

    @Test
    @DisplayName("Leaving empty Name field when buying a tour on credit with Card A")
    void emptyNameWhenBuyATourOnCreditWithCardA() {
        var buyWithA = new MainPage().emptyNameFieldWhenBuyTourWithCardA();
    }

    @Test
    @DisplayName("Leaving empty CVC field when buying a tour on credit with Card A")
    void emptyCVCWhenBuyATourOnCreditWithCardA() {
        var buyWithA = new MainPage().emptyCVCFieldWhenBuyTourWithCardA();
    }

    @Test
    @DisplayName("Entering 15-digit card number when buying a tour on credit")
    void entering15DigitCardWhenBuyingATour() {
        var buyWithA = new MainPage().cardNumberOf15Digits();
    }

    @Test
    @DisplayName("Entering digits in the name field when buying a tour on credit")
    void enteringDigitsInTheNameFieldWhenBuyingATourOnCredit() {
        var buyWithA = new MainPage().digitsInTheNameField();
    }

    @Test
    @DisplayName("Entering name in one word when buying a tour on credit")
    void enteringNameIn1WordWhenBuyingATourOnCredit() {
        var buyWithA = new MainPage().nameInOneWord();
    }

    @Test
    @DisplayName("Entering cvc/cvv in two digits when buying a tour on credit")
    void enteringCVCIn2DigitsWhenBuyingATourOnCredit() {
        var buyWithA = new MainPage().cvcIn2Digits();
    }

    @Test
    @DisplayName("Entering invalid Month when buying a tour on credit")
    void enteringInvalidMonthWhenBuyingATour() {
        var buyWithA = new MainPage().invalidMonth();
    }

    @Test
    @DisplayName("Entering card expiration date more than 5 years when buying a tour on credit")
    void enteringCardExpirationMore5YearsBuyingATourOnCredit() {
        var buyWithA = new MainPage().cardExpirationAfter5Years();
    }
}
