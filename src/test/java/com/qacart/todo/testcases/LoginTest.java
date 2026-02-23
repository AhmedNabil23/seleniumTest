package com.qacart.todo.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature( "Auth Feature")
public class LoginTest extends BaseTest {

    @Story("Login Story")
    @Test (description = "should be login")
    public void shouldBeAbleToLoginWithRightEmailAndPassword()
    {
        LoginPage loginPage = new LoginPage(driver);
         //builder pattern In order
        boolean isWelcomeDisplayed =  loginPage.load().login(ConfigUtils.getInstance().getBrodEmail(),ConfigUtils.getInstance().getBrodPassword()).isWelcomeDisplayed();

        Assert.assertTrue(isWelcomeDisplayed);

    }

}
