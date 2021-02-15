package com.demoqa;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;


import java.io.File;

public class StudentRegistrationFormPositiveTest {
    //test data
    String  firstName = "Kolj",
            lastName = "Ivanov",
            email = "mail@gmail.com",
            mobileNumber = "9213870987",
            subjects = "English",
            address = "SPb, Fontanka 127",
            state = "Rajasthan",
            city = "Jaiselmer";

    @Test
    void fieldTheForm() {

        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $x("//label[contains(text(),'Male')]").click();
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
        $x("//label[contains(text(),'Sport')]").click();
        $x("//label[contains(text(),'Reading')]").click();
        $("#uploadPicture").uploadFile(new File("./src/test/resources/img/img1.jpg"));
        $("#currentAddress").setValue(address);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        // verification section refactor
        $x("//div[@class='modal-content']").shouldHave(text(firstName + " " + lastName), text(email), text(mobileNumber), text(subjects), text(address), text(state), text(city));

    }

}
