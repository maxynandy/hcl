package com.loan.database.service;


public interface LoanDetailsRepositoryService {
	
	boolean isUserSubmittedBefore(Long ssnNumber);
	
}
