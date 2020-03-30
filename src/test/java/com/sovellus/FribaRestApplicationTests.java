package com.sovellus;

//import org.aspectj.lang.annotation.Before;
//import org.hibernate.mapping.Any;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.hateoas.MediaTypes;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.test.web.reactive.server.StatusAssertions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder.*;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.WebApplicationContext;
//import org.json.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@SpringBootTest
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class FribaRestApplicationTests {

	@Autowired
	MockMvc mvc;
		
	@Mock
	IKiekkoRepository testirepo;	
	
	@Autowired
	@InjectMocks
	KiekkoController testController;
	
	@Test
	void testController() throws Exception{
		assertThat(testController).isNotNull();
	}
	
	@Test
	void testMVC() throws Exception{
		assertThat(mvc).isNotNull();
	}
	
	
	@Test 
	void testGet () throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.
				get("/Kiekot")
				.contentType("application/json")
				)
		
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());				
	}
	
	@Test 
	void testPost() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders
				.post("/Kiekot")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(new Kiekko("ValidNimi")))
				)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test 
	void testTooShortName() throws Exception{
		mvc.perform(MockMvcRequestBuilders
				.post("/Kiekot")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(new Kiekko("ab")))
				
				)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testTooLongName() throws Exception {
		
		String longName = "1234567890123456789012345678901234567890123456789012345678901234567890";
		
		mvc.perform(MockMvcRequestBuilders
				.post("/Kiekot")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(new Kiekko(longName)))
				
				)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void testUpdate() throws Exception {
		
		String originalName = "Original";
		String updatedName = "Updated";
		
		Kiekko K = new Kiekko(originalName);
		testController.postKiekko(K);
		
		mvc.perform(MockMvcRequestBuilders
				.put("/Kiekot/{id}", K.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(new Kiekko(updatedName)))
				);
		
		assertThat(testController.getOneById(K.getId()).getName().equals(updatedName));
		
	}
	
	//Metodi jolla muunnetaan olioita JSON muotoon testaamista varten
	String toJson(Object O) throws Exception {
		
		String json = new ObjectMapper().writeValueAsString(O);
		//return new ObjectMapper().writeValueAsString(O);
		
		return json;
	}
	


}
