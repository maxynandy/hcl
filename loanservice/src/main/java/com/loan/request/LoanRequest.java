package com.loan.request;




import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.loan.constants.ErrorConstants;



@JsonIgnoreProperties( ignoreUnknown = true )
public class LoanRequest {
	
	@NotNull( message=ErrorConstants.ERR_EMPTY_SSNNUMBER)
	private Long ssnNumber;
	
	@NotNull( message=ErrorConstants.ERR_EMPTY_LOANAMOUNT)
	private Long loanAmount;
	
	//can add correlation id or request id as well
	 
	public Long getSsnNumber() {
		return ssnNumber;
	}

	public void setSsnNumber(Long ssnNumber) {
		this.ssnNumber = ssnNumber;
	}

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Long getCurrentAnnualIncome() {
		return currentAnnualIncome;
	}

	public void setCurrentAnnualIncome(Long currentAnnualIncome) {
		this.currentAnnualIncome = currentAnnualIncome;
	}

	@NotNull( message=ErrorConstants.ERR_EMPTY_CURRENTANNUALINCOME)
	private Long currentAnnualIncome;
	


}
