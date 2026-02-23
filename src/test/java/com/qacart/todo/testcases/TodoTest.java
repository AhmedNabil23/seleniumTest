package com.qacart.todo.testcases;

import com.qacart.todo.Api.RegisterApi;
import com.qacart.todo.Api.TasksApi;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.TOdoPage;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature( "ToDO Feature")
public class TodoTest extends BaseTest {

// driver uses-> extends from BaseTest Class

    @Story("Add todo")
    @Test (description = "should be AddTodo ")
    public void shouldBeAbleTOAddNewTodo () {

      //to login with restAssured
        RegisterApi registerApi = new RegisterApi(); // اوبجكت من الكلاس
        registerApi.register(); //عملت تسجيل عشان يرجعلي كوكيز

       NewTodoPage newTodoPage =new NewTodoPage(driver);
        newTodoPage.load();// قبل مانجيكت الكوكيز هترجعلي ال loginPage , لكن بعد مضفلها الكوكيز هتفتحلي ال newTodoPage
        injectCookiesToBrowser( registerApi.getRestAssuredCookies());// استخدمت المثود اللي ف baseClass اللي بتاخد رست كوكيز وبتحولها ل سلينيم كوكيز وتضيفها للبراوزر
        String actualResult = newTodoPage
             .load()
             .addNewTodoTask("Selenium curse First project")
             .getTodoText();

       //to assert
        Assert.assertEquals(actualResult,"Selenium curse First project");

    }

    //enable false : will not run if enable=false
    @Story("delete todo")
    @Test (description = "should be delete Todo ")
    public void shouldBeAbleToDeleteTodo() {
        //registration
        RegisterApi registerApi = new RegisterApi();
        registerApi.register();

        //add new todoTask
        TasksApi tasksApi = new TasksApi();
        tasksApi.addTask(registerApi.getAccessToken());


        TOdoPage todoPage =new TOdoPage(driver);
        todoPage.load();
        injectCookiesToBrowser( registerApi.getRestAssuredCookies());

        boolean  isNoTodoMessageDisplay = todoPage
                .load()
                .clickOnDeleteIcon()
                .isNoTodoMessageDisplay();

        Assert.assertTrue(isNoTodoMessageDisplay);

    }

}
