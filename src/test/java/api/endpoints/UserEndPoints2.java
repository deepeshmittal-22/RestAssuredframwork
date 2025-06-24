package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;

// note: it is created seperately for the reading data url from the properties file

// created for perform Create,Read,Update,Delete requests in this user apis


public class UserEndPoints2 {
	// method created for getting URLS from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); // Load Properties file
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(post_url);
		return response;
				
	}
	
	public static Response readUser(String userName)
	{
		String get_url=getURL().getString("get_url");
		Response response=given()
				.pathParam("username", userName)
				.when()
				.get(get_url);
		return response;
				
	}
	
	public static Response updateUser(String userName,User payload)
	{
		String update_url=getURL().getString("update_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(update_url);
		return response;
				
	}
	
	public static Response deleteUser(String userName)
	{String delete_url=getURL().getString("delete_url");
		Response response=given()
				.pathParam("username", userName)
				.when()
				.delete(delete_url);
		return response;
				
	}

}
