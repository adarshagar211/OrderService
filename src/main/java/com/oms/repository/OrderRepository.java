package com.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oms.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

	  @Query(value = "SELECT seq_order.CURRVAL FROM dual", nativeQuery = true)
	    public Integer getCurrValOrderSeq();
}
