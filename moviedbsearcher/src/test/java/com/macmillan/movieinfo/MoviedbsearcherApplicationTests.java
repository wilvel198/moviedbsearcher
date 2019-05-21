package com.macmillan.movieinfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.macmillan.movieinfo.controllers.Controllers;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviedbsearcherApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	
	@Test
	public void testCreate() throws Exception {
		
		String json = "{\n" + 
				"  \"fullJson\": \"string\"," + 
				"  \"genres\": \"string\"," + 
				"  \"id\": 0," + 
				"  \"movieID\": \"string\"," + 
				"  \"popularity\": \"string\"," + 
				"  \"release_date\": \"string\"," + 
				"  \"spoken_languages\": \"string\"," + 
				"  \"title\": \"string\"" + 
				"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movieinfo/addmoviedata")
		.contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk())
		.andDo(print());

	}

	@Test
	public void testsGetAllRecords() throws Exception {
      
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/movieinfo/getallrecords").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testHealthCheck() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movieinfo/healthcheck").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("status").value("success")).andDo(print());

	}
	
	@Test
	public void testGetMovieById() throws Exception{
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/movieinfo/getmoviebyid/12345").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());
		
	}

	@Test
	public void testSearchByName() throws Exception{
		
		String json = "{\"fullJson\": \"John Wick\"}"; 

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movieinfo/searchbyname")
		.contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
}
