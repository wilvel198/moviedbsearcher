package com.macmillan.movieinfo.utilities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.macmillan.movieinfo.models.MovieData;
import com.macmillan.movieinfo.repositories.MovieDataRepository;
import com.macmillan.movieinfo.searchmodel.DBMovieSearch;
import com.macmillan.movieinfo.searchmodel.Results;
import com.macmillan.movieinfo.smoviemodel.DBMovieInfo;
import com.macmillan.movieinfo.smoviemodel.Genres;
import com.macmillan.movieinfo.smoviemodel.Spoken_languages;

public class Utilities {

	final static Log logger = LogFactory.getLog(Utilities.class);

	@Autowired
	private static MovieDataRepository movieDataRepository;
	
	@Value("${api_key}")
	private static String apiKey;

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

	public static MovieData getMovieById(String movieId) {
		logger.info("retrieving movie by ID " + movieId);
		String json = null;
		String genreString = "";
		String langString = "";
		Gson gson = new Gson();
		MovieData myMovie = new MovieData();
		String URL = "https://api.themoviedb.org/3/movie/" + movieId
				+ "?api_key=88c29ac032cbe7242634c3450e93bdfd&language=en-US";

		logger.info("==========================> get the movie <=====================");
		logger.info(URL);
		ResponseEntity<String> serverInfo = conntectToService(URL);

		logger.info(serverInfo.getBody().toString());

		json = serverInfo.getBody().toString();

		DBMovieInfo movieData = gson.fromJson(json, DBMovieInfo.class);

		logger.info("movie title =>" + movieData.getTitle());
		myMovie.setTitle(movieData.getTitle());
		myMovie.setId(Long.parseLong(movieData.getId()));
		myMovie.setRelease_date(movieData.getRelease_date());
		myMovie.setPopularity(movieData.getPopularity());

		// getting genres from array to string
		Genres[] myGenres = movieData.getGenres();
		for (int x = 0; x < myGenres.length; x++) {

			genreString = genreString + ":" + myGenres[x].getName();
		}

		myMovie.setPopularity(movieData.getPopularity());

		myMovie.setGenres(genreString);
		myMovie.setFullJson(movieData.getId());
		myMovie.setMovieID(movieData.getId());

		Spoken_languages[] langs = movieData.getSpoken_languages();

		for (int y = 0; y < langs.length; y++) {

			langString = langString + ":" + langs[y].getName();
		}

		myMovie.setSpoken_languages(langString);

		return myMovie;
	}

	public static List<MovieData> searchByName(String searchString) {
		logger.info("==================== searching by name ========================");
		logger.info(apiKey);
		
		String searchURL = "https://api.themoviedb.org/3/search/movie?api_key=88c29ac032cbe7242634c3450e93bdfd&language=en-US&query="
				+ searchString + "&page=1&include_adult=false";
		String json = null;
		Gson gson = new Gson();

		List<MovieData> movieResults = new ArrayList<MovieData>();

		ResponseEntity<String> serverInfo = conntectToService(searchURL);

		logger.info(serverInfo.getBody().toString());

		json = serverInfo.getBody().toString();

		DBMovieSearch searchResults = gson.fromJson(json, DBMovieSearch.class);

		Results[] resultInfo = searchResults.getResults();

		logger.info("number of items found ==>" + resultInfo.length);

		for (int x = 0; x < resultInfo.length; x++) {

			logger.info("movie ID ==>" + resultInfo[x].getId());
			String movieID = resultInfo[x].getId();
			movieResults.add(getMovieById(movieID));

		}

		return movieResults;
	}

}
