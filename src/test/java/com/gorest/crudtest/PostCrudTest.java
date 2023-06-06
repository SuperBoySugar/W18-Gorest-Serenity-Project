package com.gorest.crudtest;

import com.gorest.steps.PostSteps;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class PostCrudTest extends TestBase {

    static  int id = 129;
    static int user_id = 1290;
    static String title = "Acidus versus terebro claustrum modi tyrannus asdfg";
    static String body = "Curso combibo super. Voluptas demum speciosus. Enim cado alias. Candidus antea animus. Alter facilis sub. Autem creber amitto. Ambitus comptus sunt. Modi delinquo antiquus. Auxilium angustus volubilis. Aufero urbs balbus. Deorsum ultra vigor. Crux sponte synagoga. Consequatur triumphus tamisium. Vulgo avaritia cohaero. Capillus minus cattus. Aggredior ut iusto. Officiis torrens autem.\";\n" +
            "    static int postID;";
    static int postID;

    @Steps
    PostSteps postSteps;

    @Test
    public void test001(){
        ValidatableResponse response = postSteps.getAllPosts();
        List<?> totalRecord = response.log().all().extract().path("");
        Assert.assertEquals(10, totalRecord.size());

    }

    @Title("This test will create a new post")
    @Test
    public void test002(){
        ValidatableResponse response = postSteps.createPost(id,user_id,title,body);
        response.log().all().statusCode(201);
        postID = response.log().all().extract().path("id");
        System.out.println(postID);

    }
    @Title("This test will get by ID")
    @Test
    public void test003(){
        ValidatableResponse response = postSteps.getPostById(postID);
        response.log().all().statusCode(200);
        HashMap<?,?> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(title));

    }
    @Title("This test will update the title")
    @Test
    public void test004(){
        title = title + "_updated";
        ValidatableResponse response = postSteps.updatePost(postID, id, user_id, title, body);
        response.log().all().statusCode(200);
        HashMap<String, Object> postMap  = response.log().all().extract().path("");
        Assert.assertThat(postMap, hasValue(title));

    }
    @Title("This test will delete the post")
    @Test
    public void test005(){
        ValidatableResponse response = postSteps.deletePosts(postID);
        response.log().all().statusCode(204);
        ValidatableResponse response1 = postSteps.getPostById(postID);
        response1.log().all().statusCode(404);
    }
}
