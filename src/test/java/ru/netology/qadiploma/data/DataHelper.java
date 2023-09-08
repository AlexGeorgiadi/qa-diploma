package ru.netology.qadiploma.data;

import com.github.javafaker.Faker;
import ru.homyakin.iuliia.Schemas;
import ru.homyakin.iuliia.Translator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DataHelper {

    private DataHelper() {
    }

    public static String cardA() {
        String cardA = "4444 4444 4444 4441";
        return cardA;
    }

    public static String cardB() {
        String cardB = "4444 4444 4444 4442";
        return cardB;
    }

    public static String invalidCard() {
        String invalidCard = cardA().replaceAll(".$", "");
        return invalidCard;
    }


    public static String generateNameCYR(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
    }

    public static String generateNameLAT() {
        var translator = new Translator(Schemas.ICAO_DOC_9303);
        String name = translator.translate(generateNameCYR("RU"));
        return name.toUpperCase();
    }

    public static String generateNameWithoutSpaces() {
        return generateNameLAT().replaceAll(" ", "");
    }

    public static String getDate(String pattern) {
        var faker = new Faker();
        int addDays = faker.number().numberBetween(1, 1826);
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getDateMoreThan5Years(String pattern) {
        var faker = new Faker();
        int addDays = faker.number().numberBetween(1826, 10000);
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static long diffBetweenMay22AndNow() throws ParseException {
        var faker = new Faker();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        Date secondDate = sdf.parse("05/01/2022");
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return diff;
    }

    public static String getDateAfterMay22BeforeToday(String pattern) throws ParseException {
        var faker = new Faker();
        long minusDays = faker.number().numberBetween(1, diffBetweenMay22AndNow());
        return LocalDate.now().minusDays(minusDays).minusDays(31).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getDateBeforeMay22(String pattern) throws ParseException {
        var faker = new Faker();
        long daysToGetBeforeApr22 = faker.number().numberBetween(diffBetweenMay22AndNow(), 5000);
        return LocalDate.now().minusDays(daysToGetBeforeApr22).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCVC() {
        var faker = new Faker();
        return faker.number().digits(3);
    }

    public static String getInvalidMonth() {
        var faker = new Faker();
        int number = faker.number().numberBetween(13, 99);
        return String.valueOf(number);
    }


    public static String getInvalidCVC() {
        return getCVC().replaceAll(".$", "");
    }
}