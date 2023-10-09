package com.spotify.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.pojo.Playlist;

import api.PlaylistAPI;
import authmanager.TokenGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import utils.ConfigReader;


@Epic("Spotify OATH 2.0")
@Feature("Playlist API")
public class PlayListWithReusableCalls {
	String playlistId;
	
	@Story("create playlist")
	@Description("Creation of the playlist using playlist api")
	@Test(priority = 1)
	public void createPlayList() throws IOException
	{
		Playlist reqPlaylist = new Playlist();
		
		reqPlaylist.setName(ConfigReader.readConfig("playlistname"));
		reqPlaylist.setDescription(ConfigReader.readConfig("description"));
		reqPlaylist.setPublic(false);
		
		Response response = PlaylistAPI.post(reqPlaylist, TokenGenerator.renewToken());
		
		Playlist responsePlaylist = response.as(Playlist.class);
		
		 playlistId = responsePlaylist.getId();
		
		String responseName= responsePlaylist.getName();
		
		String requestName= reqPlaylist.getName();
		
		Assert.assertEquals(responseName, requestName);
		
		
	}
	
	@Story("create playlist")
	@Description("fetching details of playlist")
	@Test(priority = 2)
	public void getAPlaylist() throws IOException
	{
		Response response = PlaylistAPI.get(playlistId, TokenGenerator.renewToken() );
		
		Playlist resPlaylist = response.as(Playlist.class);
		
		String description = resPlaylist.getDescription();
		
		System.out.println(description);
		
		Assert.assertEquals(description, ConfigReader.readConfig("description"));
		
		
		
		
	}
	@Story("Update Items of playlist")
	@Description("Modifying the details of playlist")
	@Test(priority = 3)
	public void validateUpdatePlaylist() throws IOException
	{
	Playlist reqPlaylist = new Playlist();
		
		reqPlaylist.setName(ConfigReader.readConfig("modifiedplaylistname"));
		reqPlaylist.setDescription(ConfigReader.readConfig("modifieddescription"));
		reqPlaylist.setPublic(false);
		
		Response response = PlaylistAPI.update(playlistId, reqPlaylist, TokenGenerator.renewToken());
		
		int stsCode = response.statusCode();
		
		Assert.assertEquals(stsCode, 201);
	}
	
	
		
		
	

}
