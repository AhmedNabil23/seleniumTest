package com.qacart.todo.base;

import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.utils.CookieUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BaseTest {

    protected WebDriver driver;

    /*
     استخدامBeforeMethod مش BeforeTest
    عشان @BeforeTest -> بتشتغل مرة واحدة بس للـ Suite (مش هتنفع مع mvn command عشان هيرن الكلاس قبل ميرن ال before ) وده هيرجع error null pointer
    انما BeforeMethod -> هترن لازم قبل كل Test method (قبل كل تستاية)
        */
    @BeforeMethod
    public void setup() {
        driver = new DriverFactory().initializeDriver();
    }

    @AfterMethod
    public void teardown(ITestResult result) {

//to make screenshot with tcName
        String testCaseName = result.getMethod().getMethodName();
// "target/screenshots/{testCaseName}.png"
        File destFile = new File("target"+File.separator+"screenshots"+File.separator+testCaseName+".png");
        takeScreenshot(destFile);


        if (driver != null) {
            driver.quit();
        }
    }

    //to inject ApiCookies toBrowser (API)
    //هستقبل الكوكيز اللي راجعالي من الTC(restAssured) ,
    // ونستدعي ال method  اللي بتحول ال cookies من الريست للسلينيم
    @Step
  public void injectCookiesToBrowser(List<Cookie> restAssuredCookies) {

     List<org.openqa.selenium.Cookie> seleniumCookies = CookieUtils.convertResetAssuredCookieToSeleniumCookie(restAssuredCookies);
        for (org.openqa.selenium.Cookie Cookie : seleniumCookies) {
            driver.manage().addCookie(Cookie);
}
  }

// to takeScreenshots
  public void takeScreenshot(File destFile){
      File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      try {
          FileUtils.copyFile(file, destFile );
          InputStream iS =new FileInputStream(destFile);
          Allure.attachment("screenshot",iS);

      } catch (IOException e) {
          throw new RuntimeException(e);
      }



  }


}
