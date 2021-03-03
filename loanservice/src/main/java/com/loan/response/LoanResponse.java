package com.loan.response;

import lombok.ToString;

@ToString
public class LoanResponse {
	
	private boolean isLoanEligible;
	
	private Long sanctionedAmount;
	
	private String message;

	public boolean isLoanEligible() {
		return isLoanEligible;
	}

	public void setLoanEligible(boolean isLoanEligible) {
		this.isLoanEligible = isLoanEligible;
	}

	public Long getSanctionedAmount() {
		return sanctionedAmount;
	}

	public void setSanctionedAmount(Long sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
