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
        // open https://demoqa.com/automation-practice-form
        open("https://demoqa.com/automation-practice-form");
        // fill first name
        $("#firstName").setValue(firstName);
        // fill last name
        $("#lastName").setValue(lastName);
        // email
        $("#userEmail").setValue(email);
        // radio button gender = male
        $x("//label[contains(text(),'Male')]").click();
        // mobile phone number 10 digits
        $("#userNumber").setValue(mobileNumber);
        // date of Birth !!! Calendar pick 15/10/89
        $("#dateOfBirthInput").click();
        $x("//div[@class='react-datepicker__month-dropdown-container react-datepicker__month-dropdown-container--select']").click();
        $x("//option[@value='9']").click();
        $x("//select[@class='react-datepicker__year-select']").click();
        $x("//option[@value='1989']").click();
        $x("//div[contains(@aria-label,'15th')]").click();
        // subject
        $("#subjectsInput").setValue(subjects).pressEnter();
        // hobbies 2 of 3
        $x("//label[contains(text(),'Sport')]").click();
        $x("//label[contains(text(),'Reading')]").click();
        // upload a picture
        $("#uploadPicture").uploadFile(new File("./src/test/resources/img/img1.jpg"));
        // add current address
        $("#currentAddress").setValue(address);
        // select State from dropdown menu
        $("#react-select-3-input").setValue(state).pressEnter();
        // select City from dropdown menu
        $("#react-select-4-input").setValue(city).pressEnter();
        // click on submit button
        $("#submit").click();

        // verification section refactor
        $x("//div[@class='modal-content']").shouldHave(text(firstName + " " + lastName), text(email), text(mobileNumber), text(subjects), text(address), text(state), text(city));

       // sleep(5000);
    }

}
