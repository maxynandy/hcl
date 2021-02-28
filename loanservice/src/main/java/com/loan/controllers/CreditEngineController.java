package com.loan.controllers;




import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loan.constants.ControllerConstants;
import com.loan.constants.ErrorConstants;
import com.loan.request.LoanRequest;
import com.loan.response.LoanResponse;
import com.loan.service.LoanService;

@RestController
@Validated
public class CreditEngineController {   
	
	@Autowired
	private LoanService loanService;
	
	
    @PostMapping(ControllerConstants.GET_LOAN_ELIGIBILITY)
    public LoanResponse getEligibleLoanAmont(@Valid @RequestBody @NotNull(message=ErrorConstants.ERR_EMPTY_REQUEST) LoanRequest request) throws Exception {         	
        return loanService.getLoanEligibilityDetails(request);     
    }
    

}
