package com.qacart.todo.Api;

import com.qacart.todo.config.EndPoint;
import com.qacart.todo.opjects.Task;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class TasksApi {

    //for adding the task with api
    public void addTask(String token){

        Task task= new Task("Selenium Api Task",false);

        Response response =
         given()
                .baseUri(ConfigUtils.getInstance().getBrodUrl())
                .header("Content-Type","application/json")
                .body(task)
                .auth().oauth2(token)
        .when()
                .post(EndPoint.API_TASK_ENDPOINT)
        .then()
                .log().all().extract().response();

        if (response.statusCode() !=201)
            throw new RuntimeException("Something went wrong when adding the todo task");

    }


}
