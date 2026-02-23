package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.config.EndPoint;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TOdoPage extends BasePage {
    public TOdoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-testid=\"welcome\"]")
           private WebElement welcomeMessage;

    @FindBy(css = "[data-testid=\"add\"]")
    private WebElement addButton;


    @FindBy(css = "[data-testid=\"todo-text\"]")
    private WebElement todoText;

    @FindBy(css = "[data-testid=\"delete\"]")
    private WebElement deleteIcon;

    @FindBy(css = "[data-testid=\"no-todos\"]")
    private WebElement noTodoMessage;
    @Step
    public boolean isWelcomeDisplayed() {
        return welcomeMessage.isDisplayed();
    }
    @Step
    public TOdoPage load() {
        driver.get(ConfigUtils.getInstance().getBrodUrl()+ EndPoint.API_Todo_ENDPOINT);
        return this;
    };
    @Step
    public  NewTodoPage clickOnPlusButton() {
        addButton.click();
        return new NewTodoPage(driver);
    }
    @Step
    public  String getTodoText() {

      return   todoText.getText();
    }
    @Step
    public  TOdoPage clickOnDeleteIcon() {
        deleteIcon.click();
        return this;
    }
    @Step
    public  boolean isNoTodoMessageDisplay() {
       return noTodoMessage.isDisplayed();
    }

}
