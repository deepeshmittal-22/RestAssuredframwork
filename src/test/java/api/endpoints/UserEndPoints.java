package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;

// created for perform Create,Read,Update,Delete requests in this user apis


public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.post_url);
		return response;
				
	}
	
	public static Response readUser(String userName)
	{
		Response response=given()
				.pathParam("username", userName)
				.when()
				.get(Routes.get_url);
		return response;
				
	}
	
	public static Response updateUser(String userName,User payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(Routes.put_url);
		return response;
				
	}
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
				.pathParam("username", userName)
				.when()
				.delete(Routes.delete_url);
		return response;
				
	}

}
