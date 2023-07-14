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


public class BuyTest {

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
        var buyButton = new MainPage().buyButtonCLick();
    }

    @Test
    @DisplayName("Should buy a tour with Card A")
    void shouldBuyATourWithCardA() {
        var buyWithA = new MainPage().buyTourWithCardA();
        var paymentRecord = getLastPaymentRecord();
        var orderRecord = getLastOrderRecord();
        assertAll(
                () -> assertEquals(Approved, paymentRecord.getStatus()),
                () -> assertEquals(paymentRecord.getTransaction_id(), orderRecord.getPayment_id()));
    }

    @Test
    @DisplayName("Should buy a tour with Card A expired after 01.05.2022")
    void shouldBuyATourWithCardAExpiredAfterMay22() throws ParseException {
        var buyWithA = new MainPage().buyTourWithCardAExpiredAfterMay22();
        var paymentRecord = getLastPaymentRecord();
        var orderRecord = getLastOrderRecord();
        assertAll(
                () -> assertEquals(Approved, paymentRecord.getStatus()),
                () -> assertEquals(paymentRecord.getTransaction_id(), orderRecord.getPayment_id()));
    }

    @Test
    @DisplayName("Should not buy a tour with Card A expired before 01.05.2022")
    void shouldNotBuyATourWithCardAExpiredBeforeMay22() throws ParseException {
        var buyWithA = new MainPage().buyTourWithCardAExpiredBeforeMay22();
    }

    @Test
    @DisplayName("Entering a name in cyrillic when buying a tour with Card A")
    void cyrillicNameWhenBuyATourWithCardA() {
        var buyWithA = new MainPage().cyrillicNameWhenBuyTourWithCardA();
    }

    @Test
    @DisplayName("Should not buy a tour with Card B")
    void shouldNotBuyATourWithCardB() {
        var buyWithB = new MainPage().buyTourWithCardB();
        var paymentRecord = getLastPaymentRecord();
        var orderRecord = getLastOrderRecord();
        assertAll(
                () -> assertEquals(Declined, paymentRecord.getStatus()),
                () -> assertEquals(paymentRecord.getTransaction_id(), orderRecord.getPayment_id()));
}

    @Test
    @DisplayName("Leaving empty Month field when buying a tour with Card A")
    void emptyMonthWhenBuyATourWithCardA() {
        var buyWithA = new MainPage().emptyMonthFieldWhenBuyTourWithCardA();
    }

    @Test
    @DisplayName("Leaving empty Year field when buying a tour with Card A")
    void emptyYearWhenBuyATourWithCardA() {
        var buyWithA = new MainPage().emptyYearFieldWhenBuyTourWithCardA();
    }

    @Test
    @DisplayName("Leaving empty Name field when buying a tour with Card A")
    void emptyNameWhenBuyATourWithCardA() {
        var buyWithA = new MainPage().emptyNameFieldWhenBuyTourWithCardA();
    }

    @Test
    @DisplayName("Leaving empty CVC field when buying a tour with Card A")
    void emptyCVCWhenBuyATourWithCardA() {
        var buyWithA = new MainPage().emptyCVCFieldWhenBuyTourWithCardA();
    }

    @Test
    @DisplayName("Entering 15-digit card number when buying a tour")
    void entering15DigitCardWhenBuyingATour() {
        var buyWithA = new MainPage().cardNumberOf15Digits();
    }

    @Test
    @DisplayName("Entering digits in the name field when buying a tour")
    void enteringDigitsInTheNameFieldWhenBuyingATour() {
        var buyWithA = new MainPage().digitsInTheNameField();
    }

    @Test
    @DisplayName("Entering name in one word when buying a tour")
    void enteringNameIn1WordWhenBuyingATour() {
        var buyWithA = new MainPage().nameInOneWord();
    }

    @Test
    @DisplayName("Entering cvc/cvv in two digits when buying a tour")
    void enteringCVCIn2DigitsWhenBuyingATour() {
        var buyWithA = new MainPage().cvcIn2Digits();
    }

    @Test
    @DisplayName("Entering invalid Month when buying a tour")
    void enteringInvalidMonthWhenBuyingATour() {
        var buyWithA = new MainPage().invalidMonth();
    }

    @Test
    @DisplayName("Entering card expiration date more than 5 years when buying a tour")
    void enteringCardExpirationMore5YearsBuyingATour() {
        var buyWithA = new MainPage().cardExpirationAfter5Years();
    }
}