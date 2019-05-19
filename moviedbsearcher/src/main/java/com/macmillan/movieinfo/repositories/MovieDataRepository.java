package com.macmillan.movieinfo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macmillan.movieinfo.models.MovieData;

public interface MovieDataRepository extends JpaRepository<MovieData, Long>{

}
