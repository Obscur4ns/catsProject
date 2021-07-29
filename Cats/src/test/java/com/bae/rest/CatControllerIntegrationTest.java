package com.bae.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.data.Cat;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:cat-schema.sql",
		"classpath:cat-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class CatControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Cat testKit = new Cat("JiJi", "British Shorthair", 5, true, "Black");
		String testKitAsJSON = this.mapper.writeValueAsString(testKit);
		System.out.println(testKit);
		System.out.println(testKitAsJSON);
		RequestBuilder request = post("/createCat").contentType(MediaType.APPLICATION_JSON).content(testKitAsJSON);
		ResultMatcher checkStatus = status().is(201);
		Cat testCreatedKit = new Cat("Satoshi", "Moggy", 3, true, "Black");
		testCreatedKit.setId(2);
		String testCreatedKitAsJSON = this.mapper.writeValueAsString(testCreatedKit);
		ResultMatcher checkBody = content().json(testCreatedKitAsJSON);
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testCreateAbridged() throws Exception {
		this.mockMVC
				.perform(post("/createCat").contentType(MediaType.APPLICATION_JSON).content(
						this.mapper.writeValueAsString(new Cat("JiJi", "British Shorthair", 5, true, "Black"))))
				.andExpect(status().is(201)).andExpect(content().json(
						this.mapper.writeValueAsString(new Cat(1, "JiJi", "British Shorthair", 5, true, "Black"))));
	}

	@Test
	void testUpdate() throws Exception {
		int id = 1;
		Cat newCat = new Cat(id, "Horation", "White", 4, true, "Black");
		String newCatAsJSON = this.mapper.writeValueAsString(newCat);
		RequestBuilder req = put("/replaceCat/" + id).contentType(MediaType.APPLICATION_JSON).content(newCatAsJSON);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(newCatAsJSON);
		this.mockMVC.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByName() throws Exception {
		RequestBuilder request = get("getByName/JiJi");
		ResultMatcher checkStatus = status().isOk();
		List<Cat> testCats = List.of(new Cat(1, "JiJi", "British Shorthair", 5, true, "Black"));
		String testCatsAsJSON = this.mapper.writeValueAsString(testCats);
		ResultMatcher checkBody = content().json(testCatsAsJSON);
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testGetById() throws Exception {
		RequestBuilder request = get("GetById/1");
		ResultMatcher checkStatus = status().isOk();
		Cat testCat = new Cat(2, "Satoshi", "Moggy", 3, true, "Black");
		String testCatAsJSON = this.mapper.writeValueAsString(testCat);
		ResultMatcher checkBody = content().json(testCatAsJSON);
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testReplaceCat() throws Exception {
		int id = 1;
		Cat newCat = new Cat(id, "JiJi", "British Shorthair", 5, true, "Black");
		String newCatAsJSON = this.mapper.writeValueAsString(newCat);
		RequestBuilder request = put("/replaceCat/" + id).contentType(MediaType.APPLICATION_JSON).content(newCatAsJSON);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(newCatAsJSON);
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
		RequestBuilder request = delete("/deleteCat/1");
		ResultMatcher checkStatus = status().is(204);
		ResultMatcher checkBody = content().string("Deleted: 1");
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

}
