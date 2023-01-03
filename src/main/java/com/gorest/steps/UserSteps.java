package com.gorest.steps;

import com.gorest.constants.EndPoints;
import com.gorest.model.UsersPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {
    @Step("Creating user with name : {0}, gender:{1},staus:{2},email:{3}")
    public ValidatableResponse createUser(String name, String gender, String email, String status) {
       UsersPojo userPojo = new UsersPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer a58049a1423e7d9a9d0f744f34e6a883d3f4228d9f8e198bd163aa265bbf5906")
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step("Get user info by id:{0}")
    public HashMap<String, Object> getUserInfoById(int userID){
        HashMap<String, Object> userData = SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer a58049a1423e7d9a9d0f744f34e6a883d3f4228d9f8e198bd163aa265bbf5906")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then()
                .extract().path("");
        return userData;
    }

    @Step("Update user details with name :{0},gender:{1},userid:{2},email:{3},status:{4}")
    public ValidatableResponse updateUser(String name,String gender, int userID, String email, String status){
        UsersPojo userPojo = new UsersPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer a58049a1423e7d9a9d0f744f34e6a883d3f4228d9f8e198bd163aa265bbf5906")
                .pathParam("userID",userID)
                .body(userPojo)
                .when()
                .patch(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step("Update User with ID:{0}")
    public ValidatableResponse getUserByID(int userID) {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer a58049a1423e7d9a9d0f744f34e6a883d3f4228d9f8e198bd163aa265bbf5906")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then();
    }


    @Step("Delete User with ID:{0}")
    public ValidatableResponse deleteUser(int userID) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer a58049a1423e7d9a9d0f744f34e6a883d3f4228d9f8e198bd163aa265bbf5906")
                .pathParam("userID",userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
}
