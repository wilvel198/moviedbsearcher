package com.macmillan.movieinfo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.macmillan.movieinfo.models.MovieData;

public interface MovieDataRepository extends JpaRepository<MovieData, Long>{
	
	  @Query("select s from MovieData s where s.movieID = :movieID")
	    List<MovieData> getMovieDbInfo(@Param("movieID") String movieID);

}
