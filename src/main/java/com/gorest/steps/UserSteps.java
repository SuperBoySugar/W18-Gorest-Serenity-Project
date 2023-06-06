package com.gorest.steps;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {

    @Step("Creating user with name : {0}, gender : {1}, status : {2}, emial : {3}")
    public ValidatableResponse createUser(String name, String gender, String email, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2d471ea93b4e52c80b57e1e8227dea702bb54173c4c781467e9f2b6f9f369a36")
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step("Get user info by id : {0}")
    public HashMap<String, Object> getUserInfoById(int userID) {
        HashMap<String, Object> userData = SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 2d471ea93b4e52c80b57e1e8227dea702bb54173c4c781467e9f2b6f9f369a36")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then()
                .extract()
                .path("");
        return userData;

    }

    @Step("Update user details with name : {0} , gender : {1}, userid : {2},email : {3}, status : {4}")
    public ValidatableResponse updateUser(String name, String gender, int userID, String email, String status){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 2d471ea93b4e52c80b57e1e8227dea702bb54173c4c781467e9f2b6f9f369a36")
                .pathParam("userID", userID)
                .body(userPojo)
                .when()
                .patch(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }
    @Step("Update user with ID : {0}")
    public ValidatableResponse getUserByID(int userID){

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2d471ea93b4e52c80b57e1e8227dea702bb54173c4c781467e9f2b6f9f369a36")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then();
    }
    @Step("Delete user with ID : {0}")
    public ValidatableResponse deleteUser(int userID){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2d471ea93b4e52c80b57e1e8227dea702bb54173c4c781467e9f2b6f9f369a36")
                .pathParam("userID", userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }



}
