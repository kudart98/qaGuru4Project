package com.demoqa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegFormWFaker {

    Faker fake = new Faker();

    String firstName = fake.name().firstName(),
            lastName = fake.name().lastName(),
            email = fake.internet().emailAddress(),
            gender = "Male",
            mobileNumber = fake.phoneNumber().subscriberNumber(10),
            subjects = "English",
            address = fake.address().fullAddress(),
            state = "Rajasthan",
            city = "Jaiselmer";

    @Test
    void fieldTheFormWFakeTest() {

        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(mobileNumber);
        // date of Birth !!! Calendar pick 15/10/89
        $("#dateOfBirthInput").click();
        $x("//div[@class='react-datepicker__month-dropdown-container react-datepicker__month-dropdown-container--select']").click();
        $x("//option[@value='9']").click();
        $x("//select[@class='react-datepicker__year-select']").click();
        $x("//option[@value='1989']").click();
        $x("//div[contains(@aria-label,'15th')]").click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        // hobbies 2 of 3
        $(byText("Sports")).click();
        $(byText("Reading")).click();
        // $x("//label[contains(text(),'Sport')]").click();
        // $x("//label[contains(text(),'Reading')]").click();
        $("#uploadPicture").uploadFile(new File("./src/test/resources/img/img1.jpg"));
        $("#currentAddress").setValue(address);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        // verification section
        $$(".table-responsive tr").filterBy(text("Student Name")).shouldHave(texts(firstName + " " + lastName));
        $$(".table-responsive tr").filterBy(text("Student Email")).shouldHave(texts(email));
        $$(".table-responsive tr").filterBy(text("Gender")).shouldHave(texts(gender));
        $$(".table-responsive tr").filterBy(text("Mobile")).shouldHave(texts(mobileNumber));
        $$(".table-responsive tr").filterBy(text("Date of Birth")).shouldHave(texts("15 October,1989"));
        $$(".table-responsive tr").filterBy(text("Subjects")).shouldHave(texts(subjects));
        $$(".table-responsive tr").filterBy(text("Hobbies")).shouldHave(texts("Sports, Reading"));
        $$(".table-responsive tr").filterBy(text("Picture")).shouldHave(texts("img1.jpg"));
        $$(".table-responsive tr").filterBy(text("Address")).shouldHave(texts(address));
        $$(".table-responsive tr").filterBy(text("State and City")).shouldHave(texts(state + " " + city));

    }

}
