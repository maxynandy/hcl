package com.loan.database.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.database.repository.LoanDetailsRepository;
import com.loan.database.service.LoanDetailsRepositoryService;

@Service
public class LoanDetailsRepositoryServiceImpl implements LoanDetailsRepositoryService {
	
	@Autowired
	private LoanDetailsRepository loanDetailsRepository;

	@Override
	public boolean isUserSubmittedBefore(Long ssnNumber) {		
		return loanDetailsRepository.isUserSubmittedBefore(ssnNumber);
	}

}
