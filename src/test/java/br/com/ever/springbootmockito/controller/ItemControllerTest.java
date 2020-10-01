package br.com.ever.springbootmockito.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.ever.springbootmockito.business.ItemBusinessService;
import br.com.ever.springbootmockito.model.Item;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;


@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ItemBusinessService businessService;
	
	
	@Test
	public void dummyItem_basic() throws Exception {
		String expected = "{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
		RequestBuilder request = MockMvcRequestBuilders
				.get("/dummy-item")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
				.andReturn();
		 
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}
	
	
	
	@Test
	public void itemFromBusinessService_basic() throws Exception {
		
		Mockito.when(businessService.retreiveHardcodedItem()).thenReturn(new Item(2,"Item2",10,10));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/item-from-business-service")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{id:2,name:Item2,price:10}"))
				.andReturn();
	}
	
	
	
	@Test
	public void retrieveAllItems_basic() throws Exception {
		
		Mockito.when(businessService.retrieveAllItems()).thenReturn(
				Arrays.asList(new Item(2,"Item2",10,10),
						new Item(3,"Item3",20,20)));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/all-items-from-database")
				.accept(MediaType.APPLICATION_JSON);
		
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("["
						+ "{id:2,name:Item2,price:10} , "
						+ "{id:3,name:Item3,price:20} ]"))
				.andReturn();//json usa JSONAssert = deve ser exatamente igual
	}
	
	
	@Test
	public void retrieveAllItems_noitems() throws Exception {
		Mockito.when(businessService.retrieveAllItems()).thenReturn(
				Arrays.asList()
				);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/all-items-from-database")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[]"))
				.andReturn();
		//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}
	
	
	
	 
	public void dummyItem_post_basic() throws Exception {
		String content = "{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/dummy-item")
				.accept(MediaType.APPLICATION_JSON)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON);
				
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andExpect(header()
						.string("location",
								containsString("/item/")))
						
				.andReturn();
		
		//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}
	
	
	@Test
	public final void testToString() {
		Item item = new Item(1, "John", 10, 2);
	    assertTrue(item.toString().contains("John"));
	}
	
	
}
