package com.loan.service.Impl;

import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.database.service.LoanDetailsRepositoryService;
import com.loan.externalservices.ExternalServices;
import com.loan.request.LoanRequest;
import com.loan.response.LoanResponse;
import com.loan.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService{
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);

	
	@Autowired
	private ExternalServices externalService;
	
	@Autowired
	private LoanDetailsRepositoryService loanDetailsRepositoryService;

	@Override
	public LoanResponse getLoanEligibilityDetails(LoanRequest request) {
		log.info("LoanServiceImpl--> {}",request.toString());
		 if(checkUserAppliedBefore(request)!=null) {
			 return checkUserAppliedBefore(request);
		 }		 
		   int creditScore= getRandomNumberUsingInts(700,1000); 
		   //TODO if required external services can be developed and exposed as service in another port
		   //Integer creditScore=externalService.fecthCreditScore(request.getSsnNumber());
		   log.info("LoanServiceImpl--> Credit score {}",creditScore);
		   return checkEligibilityBasedOnCreditScore(request, creditScore);	        	   
	}
	
	//To check user already tried/applied 30 days before
	@Override
	public LoanResponse checkUserAppliedBefore(LoanRequest request) {
		log.info("LoanServiceImpl--> checkUserAppliedBefore {}");
		LoanResponse loanResponse = null;
		if(loanDetailsRepositoryService.isUserSubmittedBefore(request.getSsnNumber())){
			log.info("LoanServiceImpl--> UserAppliedBefore within 30 days");	
			loanResponse= new LoanResponse();
			loanResponse.setLoanEligible(false);
			loanResponse.setMessage("Not Eligible as User tried/Applied 30 days before");
		}
		log.info("LoanServiceImpl <-- checkUserAppliedBefore {}");
		return loanResponse;
	}
	
	//To check Loan Eligibility based on credit score
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
        	loanResponse.setMessage("Not Eligible as credit score not available");
		}
		log.info("LoanServiceImpl--> checkEligibilityBasedOnCreditScore {}", loanResponse);
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
