package api;

import static io.restassured.RestAssured.given;

import com.spotify.pojo.Playlist;

import io.restassured.response.Response;

public class PlaylistAPI {
	
	
	public static Response post(Playlist reqBody, String token)
	{
		return given()
				
		.header("Authorization", "Bearer "+token)	
		
		.spec(SpecBuilders.reqSpec())

		.body(reqBody)
		
		.when()
		
		.post("/users/31pgmat4yqipg6vdxvdnyz5n73fm/playlists")
		
		.then()
		
		.spec(SpecBuilders.resSpec())
		
		.extract()
		
		.response();
	}
	
	public static Response get(String playlistId, String token)
	{
	return	given()
			
			.header("Authorization", "Bearer "+token)	
		
		.spec(SpecBuilders.reqSpec())
		
		.pathParam("pid", playlistId)

		.when()
		
		.get("playlists/{pid}")
		
		.then()
		
		.spec(SpecBuilders.resSpec())
		
		.extract()
		
		.response();
	}
	
	public static Response update(String playlistId, Playlist reqBody, String token)
	{
		return given()
				
		.header("Authorization", "Bearer "+token)	
		
		.spec(SpecBuilders.reqSpec())
		
		.pathParam("pid", playlistId)
		
		.body(reqBody)

		.when()
		
		.put("playlists/{pid}")
		
		.then()
		
		.log().all()
		
		.extract()
		
		.response();
	}

}
