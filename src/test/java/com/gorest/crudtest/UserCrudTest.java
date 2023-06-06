package com.gorest.crudtest;

import com.gorest.steps.UserSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCrudTest extends TestBase {

    static String name = "Maya Patel" + TestUtils.getRandomValue();
    static String gender = "female";
    static String email = "may12" + TestUtils.getRandomValue();
    static String status = "active";
    static int userID;

    @Steps
    UserSteps userSteps;

    @Title("This test will create a new user")
    @Test
    public void test001(){
        ValidatableResponse response = userSteps.createUser(name, gender, email, status);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }
    @Title("This test will verify user added successfully")
    @Test
    public void test002(){
        HashMap<String, Object> userMap = userSteps.getUserInfoById(userID);
        Assert.assertThat(userMap, hasValue(name));
        System.out.println(userMap);
    }
    @Title("This test will update user details")
    @Test
    public void test003(){
        name = name + "_updated";
        email = "Pinki" + "48567" + "@email.com";
        ValidatableResponse response = userSteps.updateUser(name, gender, userID, email, status).statusCode(200).log().all();
        HashMap<String, Object> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(name));
    }
    @Title("This test will delete user")
    @Test
    public void test004(){
        ValidatableResponse response = userSteps.deleteUser(userID);
        response.log().all().statusCode(204);
        ValidatableResponse response1 = userSteps.getUserByID(userID);
        response1.log().all().statusCode(404);
    }
}
