package com.gorest.steps;

import com.gorest.constants.EndPoints;
import com.gorest.model.PostsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

public class PostsSteps {
    @Step("Getting all posts")
    public ValidatableResponse getAllPosts() {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer a58049a1423e7d9a9d0f744f34e6a883d3f4228d9f8e198bd163aa265bbf5906")
                .when()
                .get(EndPoints.GET_ALL_POST)
                .then();
    }

    @Step("This test will create post")
    public ValidatableResponse createPost(int id, int user_id, String title, String body) {
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setId(id);
        postsPojo.setUser_id(user_id);
        postsPojo.setTitle(title);
        postsPojo.setBody(body);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer a58049a1423e7d9a9d0f744f34e6a883d3f4228d9f8e198bd163aa265bbf5906")
                .body(postsPojo)
                .when()
                .post(EndPoints.CREATE_POST)
                .then();
    }

    @Step("This test will get ID")
    public ValidatableResponse getPostByID(int postID) {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer a58049a1423e7d9a9d0f744f34e6a883d3f4228d9f8e198bd163aa265bbf5906")
                .pathParam("postID", postID)
                .when()
                .get(EndPoints.UPDATE_POST_BY_ID)
                .then();
    }

    @Step("Update post with title")
    public ValidatableResponse updatePost(int postId, int id, int user_id, String title, String body) {
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setId(id);
        postsPojo.setUser_id(user_id);
        postsPojo.setTitle(title);
        postsPojo.setBody(body);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer a58049a1423e7d9a9d0f744f34e6a883d3f4228d9f8e198bd163aa265bbf5906")
                .body(postsPojo)
                .pathParam("postID", postId)
                .when()
                .patch(EndPoints.UPDATE_POST_BY_ID)
                .then();
    }

    @Step("This test delete post")
    public ValidatableResponse deletePosts(int postID) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer a58049a1423e7d9a9d0f744f34e6a883d3f4228d9f8e198bd163aa265bbf5906")
                .pathParam("postID", postID)
                .when()
                .delete(EndPoints.DELETE_POST_BY_ID)
                .then();
    }


}
