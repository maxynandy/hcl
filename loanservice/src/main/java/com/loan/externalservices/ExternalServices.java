package com.loan.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Setter;

@Service
@Setter
public class ExternalServices {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("credit.score.service.url")
	private String creditScoreURL;

    public Integer fecthCreditScore(Long ssnNumber) {
    	return restTemplate.getForObject(creditScoreURL, Integer.class);
    }
    
    @Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
