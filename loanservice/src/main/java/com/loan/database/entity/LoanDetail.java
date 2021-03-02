package com.loan.database.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDetail {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private Long ssnNumber;
    
    private Long loanAmount;
    
    private Date appliedDate;
    
    //if required can add more details applied datetime
    
    @Override
    public String toString() {
        return "LoanDetails{" +
                "id=" + id +
                ", ssnNumber='" + ssnNumber + '\'' +
                ", loanAmount=" + loanAmount +
                ", appliedDate=" + appliedDate +
                '}';
    }


}
