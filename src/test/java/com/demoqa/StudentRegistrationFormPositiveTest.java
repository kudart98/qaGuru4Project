package com.demoqa;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;


import java.io.File;

public class StudentRegistrationFormPositiveTest {

    @Test
    void fieldsTheForm() {

        //test data
        String firstName = "Kolj",
                lastName = "Ivanov",
                email = "mail@gmail.com",
                mobileNumber = "9213870987",
                subjects = "English",
                address = "SPb, Fontanka 127",
                state = "Rajasthan",
                city = "Jaiselmer";

        //open https://demoqa.com/automation-practice-form
        open("https://demoqa.com/automation-practice-form");

        //fill first name
        $("#firstName").setValue(firstName);

        //fill last name
        $("#lastName").setValue(lastName);

        //email
        $("#userEmail").setValue(email);

        //Radio button gender = male
        $x("//label[contains(text(),'Male')]").click();

        //mobile phone number 10 digits
        $("#userNumber").setValue(mobileNumber);

        //Date of Birth !!! Calendar pick 15/10/89
        $("#dateOfBirthInput").click();
        $x("//div[@class='react-datepicker__month-dropdown-container react-datepicker__month-dropdown-container--select']").click();
        $x("//option[@value='9']").click();
        $x("//select[@class='react-datepicker__year-select']").click();
        $x("//option[@value='1989']").click();
        $x("//div[contains(@aria-label,'15th')]").click();

        //Subject
        $("#subjectsInput").setValue(subjects).pressEnter();

        //Hobbies 2 of 3
        $x("//label[contains(text(),'Sport')]").click();
        $x("//label[contains(text(),'Reading')]").click();

        //Upload a picture
        $("#uploadPicture").uploadFile(new File("src/test/resources/img/img1.jpg"));

        //Add current address
        $("#currentAddress").setValue(address);

        //Select State from dropdown menu
        $("#react-select-3-input").setValue(state).pressEnter();

        //Select City from dropdown menu
        $("#react-select-4-input").setValue(city).pressEnter();

        //Click on submit button
        $("#submit").click();


        //Verification section. Not working...
        //$$x("//div[@class='modal-body']//div//table//tbody[1]").shouldHave(texts(firstName, lastName, email, mobileNumber, subjects, address, state, city));

        //Verification section
        $$x("//div[@class='modal-body']//div//table//tbody[1]").shouldHave(texts(firstName + " " + lastName));
        $$x("//div[@class='modal-body']//div//table//tbody[1]").shouldHave(texts(email));
        $$x("//div[@class='modal-body']//div//table//tbody[1]").shouldHave(texts(mobileNumber));
        $$x("//div[@class='modal-body']//div//table//tbody[1]").shouldHave(texts(subjects));
        $$x("//div[@class='modal-body']//div//table//tbody[1]").shouldHave(texts(address));
        $$x("//div[@class='modal-body']//div//table//tbody[1]").shouldHave(texts(state));
        $$x("//div[@class='modal-body']//div//table//tbody[1]").shouldHave(texts(city));




       // sleep(1000);


    }
}
