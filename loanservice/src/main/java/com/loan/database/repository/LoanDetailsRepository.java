package com.loan.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.loan.database.entity.LoanDetail;

@Repository
public interface LoanDetailsRepository extends CrudRepository<LoanDetail, Integer> {
	

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM LoanDetail l WHERE l.ssnNumber = :ssnNumber and datediff('DAY',curdate(),l.appliedDate) <= 30 ")
	boolean isUserSubmittedBefore(Long ssnNumber);
	

}
