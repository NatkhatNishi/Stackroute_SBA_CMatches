package com.stackroute.favouriteservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.runners.MockitoJUnitRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.config.FavMatchMessageProducer;
import com.stackroute.favouriteservice.model.Match;
import com.stackroute.favouriteservice.model.User;
import com.stackroute.favouriteservice.model.UserFavouriteMatch;
import com.stackroute.favouriteservice.service.UserfavoriteMatchService;

@SpringBootApplication
class TestApp {
	@Bean
	@Primary
	public CachingConnectionFactory cnxnFactory() {
		return Mockito.mock(CachingConnectionFactory.class);
	}
}

@RunWith(SpringRunner.class)
@WebMvcTest(FacouriteMatchController.class)
public class UserFavouriteMatchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserfavoriteMatchService service;

	@InjectMocks
	private FacouriteMatchController controller;

	@MockBean
	private FavMatchMessageProducer producer;
	
	Match m;
	UserFavouriteMatch match;
	User user;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//		factory =  Mockito.mock(CachingConnectionFactory.class);
		 user = new User();
		 user.setUsername("Jhon");
		 user.setUserpassword("Smith");
//		 user.setUserPassword("123456");
//		 user.setUserRole("Admin");
//		 user.setUserAddedDate(new Date());
		 m = new Match();
		m.setTeam1("team1");
		m.setTeam2("team2");
		match = new UserFavouriteMatch();
		match.setUserId("16");
		
		List<Match> listmatch = new ArrayList();
		listmatch.add(m);
		match.setMatchList(listmatch);
	}


	@Test
	public void testgetFavouriteMatchesForUser() throws Exception {

		Mockito.when(service.getAllMatchByUserId(Mockito.anyString())).thenReturn(new ArrayList());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fetchFavouriteMatches/1")
//				 .content(jsonToString(m))
				.contentType(MediaType.APPLICATION_JSON).param("userid", "1243"))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void addFavoriteSuccess() throws Exception {
		Mockito.when(this.service.updateFavourites(match, "")).thenReturn(match);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addtoFavourite")
											  .content(jsonToString(match))
											  .contentType("application/json"))
			   .andExpect(MockMvcResultMatchers.status().isCreated())
			   .andDo(MockMvcResultHandlers.print());
	}
	
	
	// Parsing String format data into JSON format
	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

	
}
