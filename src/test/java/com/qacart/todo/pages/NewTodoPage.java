package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.config.EndPoint;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewTodoPage extends BasePage {

    public NewTodoPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "[data-testid=\"email\"]")
    private WebElement emailInput;

    @FindBy(css = "[data-testid=\"password\"]")
    private WebElement passwordInput;

    @FindBy(css = "[data-testid=\"submit\"]")
    private WebElement submitInput;

    //for new Todo
    @FindBy(css = "[data-testid=\"new-todo\"]")
    private WebElement newTodoInput;

    @FindBy(css = "[data-testid=\"submit-newTask\"]")
    private WebElement newTodoSubmit;

    @Step
    public NewTodoPage load() {
        driver.get(ConfigUtils.getInstance().getBrodUrl()+ EndPoint.API_NewTodo_ENDPOINT);
        return this;
    };
    @Step
    public TOdoPage addNewTodoTask(String task){
        newTodoInput.sendKeys(task);
        newTodoSubmit.click();

        return new TOdoPage(driver);
    }


}
