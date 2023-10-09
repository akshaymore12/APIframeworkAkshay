package com.spotify.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.pojo.Playlist;

import api.PlaylistAPI;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;

public class PlaylistWithNegativeScenario {
	
	@Story("Negative scenarios of playlist")
	@Description("Validating authorization with negative aspect")
	@Test(priority = 4)
	public void shouldNotBeAuthorized()
	{
		Playlist reqPlaylist = new Playlist();
		
		reqPlaylist.setName("Invalid oct Playlist");
		reqPlaylist.setDescription("Wednesday songs");
		reqPlaylist.setPublic(false);
		
		Response response = PlaylistAPI.post(reqPlaylist, "akhdkajshdkjashdkjdhsajhdkahask");
		
		int stsCode = response.statusCode();
		
		Assert.assertEquals(stsCode, 401);
				
	}

}
