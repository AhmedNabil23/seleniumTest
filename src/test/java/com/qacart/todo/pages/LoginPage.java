package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.utils.ConfigUtils;
import com.qacart.todo.utils.PropertiesUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Properties;

public class LoginPage extends BasePage  {
    public LoginPage(WebDriver driver) {
       super(driver);
    }

    //to find element in the page -> and retrieves the WebElement
    @FindBy(css = "[data-testid=\"email\"]")
    private WebElement emailInput ;

    @FindBy(css = "[data-testid=\"password\"]")
    private WebElement passwordInput ;

    @FindBy(css = "[data-testid=\"submit\"]")
    private WebElement submit ;

    @Step("load the Login Page")
    public LoginPage load() {
        driver.get(ConfigUtils.getInstance().getBrodUrl());
        //to use it with builder pattern
        return this;
    };

    //this method to set email&password from LoginTest
    @Step
    public TOdoPage login(String email,String password) {

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submit.click();

        return new TOdoPage(driver);
    };

}
