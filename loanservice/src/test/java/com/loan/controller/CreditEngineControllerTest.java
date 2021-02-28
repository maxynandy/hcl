package com.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.constants.ControllerConstants;
import com.loan.constants.ErrorConstants;
import com.loan.response.ApiError;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles({"test"})
@SpringBootTest
public class CreditEngineControllerTest {

	@Autowired
	private MockMvc mockMvc; 
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testMalformedRequest() throws Exception{
     MvcResult actions = mockMvc.perform(MockMvcRequestBuilders.post(ControllerConstants.GET_LOAN_ELIGIBILITY)
    		 .content("INVALID").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
     ApiError response=mapper.readValue(actions.getResponse().getContentAsString(), ApiError.class);
     assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
     assertThat(response.getPath()).endsWith(ControllerConstants.GET_LOAN_ELIGIBILITY);
     assertThat(response.getMessage()).startsWith(ErrorConstants.ERR_PREFIX_MALFORMED_REQUEST);       
	}
	
}
