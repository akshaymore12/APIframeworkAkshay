package authmanager;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.ConfigReader;

public class TokenGenerator {
	
	
	public static String renewToken() throws IOException
	{
		HashMap<String, String> param = new HashMap<String, String>();
		
		param.put("grant_type", "refresh_token");
		param.put("refresh_token", ConfigReader.readConfig("refresh_token"));
		param.put("client_id", "42fe377a38474d38a15fde8d243d3c3a");
		param.put("client_secret", "0cf568f10c764ad5af9be9fab324038e");
		
		
		RestAssured.baseURI = "https://accounts.spotify.com";
		
		Response response = given()
		
		.contentType(ContentType.URLENC)
		
		.formParams(param)
		
		.when()
		
		.post("/api/token")
		
		.then()
		
		.extract()
		
		.response();
		
		
	JsonPath jp = response.jsonPath();
	
	String accessToken = jp.getString("access_token");
		
//	if the acess token failed to get generate then we should know why the api calls are getting fail
	
	if(response.statusCode() != 200)
	{
		throw new RuntimeException("Failed to generate Access token");
	}
		
		return accessToken;
		
		
		
	}

}
