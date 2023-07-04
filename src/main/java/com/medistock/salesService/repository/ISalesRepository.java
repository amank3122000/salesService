package com.medistock.salesService.repository;


import com.medistock.salesService.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalesRepository extends JpaRepository<Sales, Integer> {

}
