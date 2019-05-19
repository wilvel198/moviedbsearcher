package com.macmillan.movieinfo.controllers;

import java.util.List;

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
	String getallrecords() {
		
		List<MovieData> all = movieDataRepository.findAll();
		
		all.size();
		System.out.println("the size is ===>" + all.size());
		
		//System.out.println(all.get(0).getGenres());
		
		for(int x = 0; x < all.size();x++) {
			
			System.out.println(all.get(x).getTitle());
			
		}
		return "succes";
	}
	
	@GetMapping("/getmoviebyid/{movieId}")
	MovieData getMovieById(@PathVariable("movieId") String movieId) {
		
		logger.info("ID Received " + movieId);
		
		MovieData movieInfo = Utilities.getMovieById(movieId);
		
		MovieData myMovie = new MovieData();
		myMovie.setTitle("the lost boys");
		
		return myMovie;
	}
	

}
