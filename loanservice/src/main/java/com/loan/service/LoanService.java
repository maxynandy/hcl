package com.loan.service;

import com.loan.request.LoanRequest;
import com.loan.response.LoanResponse;

public interface LoanService {
	
	LoanResponse getLoanEligibilityDetails(LoanRequest loanRequest);
	LoanResponse checkEligibilityBasedOnCreditScore(LoanRequest request,Integer creditScore);

}
