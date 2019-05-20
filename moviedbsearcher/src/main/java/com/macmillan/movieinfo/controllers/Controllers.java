package com.macmillan.movieinfo.controllers;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.macmillan.movieinfo.models.MovieData;
import com.macmillan.movieinfo.models.SearchObject;
import com.macmillan.movieinfo.repositories.MovieDataRepository;
import com.macmillan.movieinfo.utilities.Utilities;


@RestController
@RequestMapping("/api/v1/movieinfo")
public class Controllers {
	
	final static Log logger = LogFactory.getLog(Controllers.class);
	
	@Autowired
	private MovieDataRepository movieDataRepository;
	
	
	@GetMapping("/healthcheck")
	String healthCheck() {
		
		
		return "succes";
	}
	
	@PostMapping("/addmoviedata")
	@ResponseStatus(HttpStatus.OK)
	void create(@RequestBody MovieData movieData) {
		
		movieDataRepository.save(movieData);
		
		
	}
	
	@GetMapping("/getallrecords")
	List<MovieData> getallrecords() {
		
		List<MovieData> all = movieDataRepository.findAll();
		
		all.size();
		System.out.println("the size is ===>" + all.size());
		
		//System.out.println(all.get(0).getGenres());
		
		for(int x = 0; x < all.size();x++) {
			
			System.out.println(all.get(x).getTitle());
			System.out.println(all.get(x).getId());
			System.out.println(all.get(x).getMovieID());
			System.out.println(all.get(x).getFullJson());
			
		}
		return all;
	}
	
	@GetMapping("/getmoviebyid/{movieId}")
	MovieData getMovieById(@PathVariable("movieId") String movieId) {
		boolean movieCached = false;
		MovieData movieInfo = null;
		int movieSize = 0;
		
		logger.info("ID Received " + movieId);
		
		List<MovieData> movieIDFromDb = movieDataRepository.getMovieDbInfo(movieId);
		
		logger.info("number of items is " + movieIDFromDb.size());
		
		movieSize = movieIDFromDb.size();
		 
		if(movieSize > 0) {
			logger.info("=====> the movie is in the cache <========");
			return movieIDFromDb.get(0);
			
		}else {
		 movieInfo = Utilities.getMovieById(movieId);
		 movieDataRepository.save(movieInfo);
			
		}

		return movieInfo;
	}
	
	//this method is used to return all items from search
	@SuppressWarnings("deprecation")
	@PostMapping("/searchbyname")
	List<MovieData> movieFullSearch(@RequestBody SearchObject searcher){
		logger.info("============  searching by name =====================");
		logger.info(searcher.getSearchString());
		String searchString = null;
		searchString = URLEncoder.encode(searcher.getSearchString());
		logger.info("encoded search string " + searchString);
		
		List<MovieData> movieInfo = Utilities.searchByName(searchString);
		
		
		return movieInfo;
	}
	

}
