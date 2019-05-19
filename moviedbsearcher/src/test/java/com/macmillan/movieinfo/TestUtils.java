package com.macmillan.movieinfo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.macmillan.movieinfo.utilities.Utilities;


import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUtils {

	static ResponseEntity<String> res = null;
	
	static String URL = "https://api.themoviedb.org/3/search/movie?api_key=88c29ac032cbe7242634c3450e93bdfd&language=en-US&query=the%20lost%20boys&page=1&include_adult=false";
	
	
	@Test
	public void testConnection() {
		
		Utilities utils = new Utilities();
		
		res = utils.conntectToService(URL);
		
		assertEquals(200,res.getStatusCodeValue());
			
		
	}
	
	
	@Test
	public void testResult() {
		String URL = "https://api.themoviedb.org/3/search/movie?api_key=88c29ac032cbe7242634c3450e93bdfd&language=en-US&query=the%20lost%20boys&page=1&include_adult=false";
		
		Utilities utils = new Utilities();
		
		res = utils.conntectToService(URL);
		
		System.out.println(res.getStatusCode());
		
		System.out.println(res.getBody().toString());
		
		assertEquals(200,res.getStatusCodeValue());
		
		
	}
}
