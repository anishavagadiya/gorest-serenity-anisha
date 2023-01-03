package com.gorest.crudtest;

import com.gorest.model.UsersPojo;
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
public class UsersCrudTest extends TestBase {

    static String name = "Anya Vac" + TestUtils.getRandomValue();
    static String gender = "female";
    static String email = "Anu" + TestUtils.getRandomValue() + "@email.com";
    static String status = "active";
    static int userID;

    @Steps
    UserSteps usersSteps;


    @Title("This test will Create a new User")
    @Test
    public void test001() {
        ValidatableResponse response = usersSteps.createUser(name, gender, email, status);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }

    @Title("This test will verify user added successfully")
    @Test
    public void test002() {
        HashMap<String, Object> userMap = usersSteps.getUserInfoById(userID);
        Assert.assertThat(userMap, hasValue(name));
        System.out.println(userMap);
    }

    @Title("This test will update user details")
    @Test
    public void test003() {
        name = name + "_updated";
        email = "Anu" + "65895" + "@email.com";
        ValidatableResponse response = usersSteps.updateUser(name, gender, userID, email, status).statusCode(200).log().all();
        HashMap<String, Object> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(name));
    }

    @Title("This test will delete user")
    @Test
    public void test004() {
        ValidatableResponse response = usersSteps.deleteUser(userID);
        response.log().all().statusCode(204);
        ValidatableResponse response1 = usersSteps.getUserByID(userID);
        response1.log().all().statusCode(404);

    }
}
