package com.loan.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.loan.database.service.LoanDetailsRepositoryService;

@ActiveProfiles(("test"))
@SpringBootTest
public class LoanDetailsRepositoryServiceTest {
	
	@Autowired
	private LoanDetailsRepositoryService detailsRepositoryService;
	

	@Test
	public void testFetch() {		
		assertThat(detailsRepositoryService.isUserSubmittedBefore(1234L)).isEqualTo(false);
		assertThat(detailsRepositoryService.isUserSubmittedBefore(5678L)).isEqualTo(true);
	}

}
