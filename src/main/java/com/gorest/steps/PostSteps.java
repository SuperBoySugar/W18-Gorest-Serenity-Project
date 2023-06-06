package com.gorest.steps;

import com.gorest.constants.EndPoints;
import com.gorest.model.PostsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class PostSteps {

    @Step("Getting all posts")
    public ValidatableResponse getAllPosts(){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2d471ea93b4e52c80b57e1e8227dea702bb54173c4c781467e9f2b6f9f369a36")
                .when()
                .get(EndPoints.GET_ALL_POST)
                .then();
    }

    @Step("This test will create post")
    public ValidatableResponse createPost(int id, int user_id, String title, String body ){
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setId(id);
        postsPojo.setUser_id(user_id);
        postsPojo.setTitle(title);
        postsPojo.setBody(body);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2d471ea93b4e52c80b57e1e8227dea702bb54173c4c781467e9f2b6f9f369a36")
                .when()
                .post(EndPoints.CREATE_POST)
                .then();

    }

    @Step("This test will get ID")
    public ValidatableResponse getPostById(int postID){

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2d471ea93b4e52c80b57e1e8227dea702bb54173c4c781467e9f2b6f9f369a36")
                .when()
                .get(EndPoints.GET_POST_BY_ID)
                .then();
    }
    @Step("This test will update post by title")
    public ValidatableResponse updatePost(int postID, int id, int user_id, String title, String body){
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setId(id);
        postsPojo.setUser_id(user_id);
        postsPojo.setTitle(title);
        postsPojo.setBody(body);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2d471ea93b4e52c80b57e1e8227dea702bb54173c4c781467e9f2b6f9f369a36")
                .body(postsPojo)
                .pathParam("postID", postID)
                .when()
                .patch(EndPoints.UPDATE_POST_BY_ID)
                .then();
    }
    @Step("This test delete post")
    public ValidatableResponse deletePosts(int postID){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2d471ea93b4e52c80b57e1e8227dea702bb54173c4c781467e9f2b6f9f369a36")
                .pathParam("postID",postID)
                .when()
                .delete(EndPoints.DELETE_POST_BY_ID)
                .then();
    }


}
