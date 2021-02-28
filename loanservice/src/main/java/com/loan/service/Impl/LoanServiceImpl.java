package com.loan.service.Impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.externalservices.ExternalServices;
import com.loan.request.LoanRequest;
import com.loan.response.LoanResponse;
import com.loan.service.LoanService;





@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	private ExternalServices externalService;

	@Override
	public LoanResponse getLoanEligibilityDetails(LoanRequest request) {		 
		   //TODO add logger
		   //TODO Junit
    	   //TODO check from internal h2database whether applicant has applied wihin 30 days 
		   int creditScore= getRandomNumberUsingInts(700,1000);		 
		   //TODO external services will be developed and exposed as service in another port
		   //Integer creditScore=externalService.fecthCreditScore(request.getSsnNumber());
		   return checkEligibilityBasedOnCreditScore(request, creditScore);	        	   
	}
	
	public LoanResponse checkEligibilityBasedOnCreditScore(LoanRequest request,Integer creditScore)
	{
		LoanResponse loanResponse = new LoanResponse();
		Long sanctionedLoadAmount = 0L;
		if(creditScore!=null) {
			if(creditScore>700) {
		        sanctionedLoadAmount= request.getCurrentAnnualIncome()/2;
			        if(request.getLoanAmount()>sanctionedLoadAmount) {	      
			        	loanResponse.setLoanEligible(false);
			        	loanResponse.setSanctionedAmount(sanctionedLoadAmount);
			        	loanResponse.setMessage("Not Eligible for this loan amount");				        	
			        }
			        else {
			        	loanResponse.setLoanEligible(true);
			        	loanResponse.setSanctionedAmount(sanctionedLoadAmount);
			        	loanResponse.setMessage("Eligible for this loan amount");			        
			        }
		        }else {
		        	loanResponse.setLoanEligible(false);
		        	loanResponse.setMessage("Not Eligible for loan based on credit score");		        
		        }             
	
		}else {
			loanResponse.setLoanEligible(false);
        	loanResponse.setMessage("Not Eligible as credit score not available ");
		}
	  return loanResponse;		
	}
	
	//Just for demo
	public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
          .findFirst()
          .getAsInt();
    }
	
}
