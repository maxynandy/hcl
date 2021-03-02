package com.loan.service;



import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.loan.request.LoanRequest;
import com.loan.response.LoanResponse;







@ActiveProfiles(("test"))
@SpringBootTest
public class LoanServiceImplTest {

    @Autowired
	private LoanService loanService;
    
    @Test
	void checkEligibilityBasedOnCreditScoreScenarioEligible() throws Exception{
    	LoanRequest request = new LoanRequest();
    	request.setCurrentAnnualIncome(100000L);
    	request.setLoanAmount(5000L);
    	request.setSsnNumber(123456L);
    	Integer creditScore = new Integer(721);
    	LoanResponse loanResponse = loanService.checkEligibilityBasedOnCreditScore(request, creditScore);    	
        assertThat(loanResponse.isLoanEligible()).isEqualTo(true);
        assertThat(loanResponse.getSanctionedAmount()).isEqualTo(50000);
        assertThat(loanResponse.getMessage()).isEqualTo("Eligible for this loan amount");   
	}
    
    @Test
	void checkEligibilityBasedOnCreditScoreScenarioCreditScoreNotAvailable() throws Exception{
    	LoanRequest request = new LoanRequest();
    	request.setCurrentAnnualIncome(100000L);
    	request.setLoanAmount(5000L);
    	request.setSsnNumber(123456L);
    	Integer creditScore = null;
    	LoanResponse loanResponse = loanService.checkEligibilityBasedOnCreditScore(request, creditScore);    	
        assertThat(loanResponse.isLoanEligible()).isEqualTo(false);
        assertThat(loanResponse.getSanctionedAmount()).isNull();
        assertThat(loanResponse.getMessage()).isEqualTo("Not Eligible as credit score not available");   
	}
    
    @Test
	void checkEligibilityBasedOnCreditScoreScenarioNotEligibleBasedOnCreditScore() throws Exception{
    	LoanRequest request = new LoanRequest();
    	request.setCurrentAnnualIncome(100000L);
    	request.setLoanAmount(5000L);
    	request.setSsnNumber(123456L);
    	Integer creditScore = new Integer(500);
    	LoanResponse loanResponse = loanService.checkEligibilityBasedOnCreditScore(request, creditScore);    	
        assertThat(loanResponse.isLoanEligible()).isEqualTo(false);
        assertThat(loanResponse.getSanctionedAmount()).isNull();
        assertThat(loanResponse.getMessage()).isEqualTo("Not Eligible for loan based on credit score");   
	}
    
    @Test
	void checkEligibilityBasedOnCreditScoreScenarioNotEligibleBasedOnAnnualIncome() throws Exception{
    	LoanRequest request = new LoanRequest();
    	request.setCurrentAnnualIncome(100000L);
    	request.setLoanAmount(5000000L);
    	request.setSsnNumber(123456L);
    	Integer creditScore = new Integer(701);
    	LoanResponse loanResponse = loanService.checkEligibilityBasedOnCreditScore(request, creditScore);    	
        assertThat(loanResponse.isLoanEligible()).isEqualTo(false);
        assertThat(loanResponse.getSanctionedAmount()).isEqualTo(50000L);
        assertThat(loanResponse.getMessage()).isEqualTo("Not Eligible for this loan amount");   
	}
    
  
}
