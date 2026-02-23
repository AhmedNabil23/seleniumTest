package com.qacart.todo.Api;

import com.qacart.todo.config.EndPoint;
import com.qacart.todo.opjects.User;
import com.qacart.todo.utils.UserUtils;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegisterApi {

    private List<Cookie> restAssuredCookies;
    private String accessToken;
    private String userId;
    private String firstName;

// Getter For getUserId
    public String getUserId() {
        return userId;
    }

// Getter For getFirstName
    public String getFirstName() {
        return firstName;
    }

// Getter For restAssuredCookies
    public List<Cookie> getRestAssuredCookies() {
        return restAssuredCookies;
    }
// Getter For accessToken
    public String getAccessToken() {
        return accessToken;
    }

    public void register (){
        User user = UserUtils.generateRandomUser();
        Response response =
                given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .header("Content-Type","application/json")
                .body( user )
                        .log().all()

                .when()
                .post(EndPoint.API_REGISTER_ENDPOINT)

                .then()
                      .log().all()
                      .extract().response();

         restAssuredCookies = response.detailedCookies().asList();
         accessToken = response.path("access_token");
         userId = response.path("userID");
         firstName =response.path("firstName");


     if(response.statusCode() != 201){
         throw new RuntimeException("Some thing went Wrong with the request");
}

    }

}
