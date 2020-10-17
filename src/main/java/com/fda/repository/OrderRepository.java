package com.fda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fda.model.Orders;

@Repository(value ="orderRepository")
public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
