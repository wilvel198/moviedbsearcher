package com.macmillan.movieinfo.utilities;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macmillan.movieinfo.models.MovieData;


public class Utilities {

final static Log logger = LogFactory.getLog(Utilities.class);
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static ResponseEntity<String> conntectToService(String URL) {
		logger.info("=============> get service information <===================");
		String respInfo = null;
		ResponseEntity<String> result = null;
		
		 RestTemplate restTemplate = new RestTemplate();
		 
		 try {
			URI uri = new URI(URL);
			
		    result = restTemplate.getForEntity(uri, String.class);
			  
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	public static MovieData getMovieById(String movieId){
		logger.info("retrieving movie by ID " + movieId);
		String json = null;
		MovieData myMovie = new MovieData();
		String URL = "https://api.themoviedb.org/3/movie/"+ movieId +"?api_key=88c29ac032cbe7242634c3450e93bdfd&language=en-US";
		
		logger.info("==========================> get the movie <=====================");
		logger.info(URL);
		ResponseEntity<String> serverInfo = conntectToService(URL);
		
		logger.info(serverInfo.getBody().toString());
		
		
		
		myMovie.setTitle("the loast bys");
		
		return myMovie;
	}
}
