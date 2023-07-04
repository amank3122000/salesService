package com.medistock.salesService.service;

import com.medistock.salesService.dto.SalesDTO;
import com.medistock.salesService.entity.Sales;
import com.medistock.salesService.exception.SalesNotFoundException;
import com.medistock.salesService.repository.ISalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SalesService implements ISalesService{

    static String error = "Sales Not Found.";

    @Autowired(required = false)
    ISalesRepository salesRepository;

    @Override
    public SalesDTO addSales(SalesDTO sales) {
        Sales s = new Sales();
        s.setSalesId(sales.getSalesId());
        s.setBatchCode(sales.getBatchCode());
        s.setCustCode(sales.getCustCode());
        s.setDate(sales.getDate());
        s.setQuantity(sales.getQuantity());
        s.setPrice(sales.getPrice());
        salesRepository.save(s);
        return sales;
    }

    @Override
    public SalesDTO updateSales(SalesDTO sales, int salesId) throws SalesNotFoundException {
        Sales s = salesRepository.findById(salesId).orElseThrow(()-> new SalesNotFoundException(error));
        s.setBatchCode(sales.getBatchCode());
        s.setCustCode(sales.getCustCode());
        s.setDate(sales.getDate());
        s.setQuantity(sales.getQuantity());
        s.setPrice(sales.getPrice());
        salesRepository.save(s);
        return sales;
    }

    @Override
    public void deleteSales(int salesId) throws SalesNotFoundException {
        Sales s = salesRepository.findById(salesId).orElseThrow(()->new SalesNotFoundException(error));
        salesRepository.delete(s);
    }

    @Override
    public SalesDTO viewById(int salesId) throws SalesNotFoundException {
        Sales sales = salesRepository.findById(salesId).orElseThrow(()->new SalesNotFoundException(error));
        SalesDTO s = new SalesDTO();
        s.setSalesId(sales.getSalesId());
        s.setBatchCode(sales.getBatchCode());
        s.setCustCode(sales.getCustCode());
        s.setDate(sales.getDate());
        s.setQuantity(sales.getQuantity());
        s.setPrice(sales.getPrice());
        return s;
    }

    @Override
    public List<SalesDTO> viewAllSales() {
        List<Sales> list = salesRepository.findAll();
        List<SalesDTO> sList = new ArrayList<>();
        list.forEach(sales-> {
            SalesDTO s = new SalesDTO();
            s.setSalesId(sales.getSalesId());
            s.setBatchCode(sales.getBatchCode());
            s.setCustCode(sales.getCustCode());
            s.setDate(sales.getDate());
            s.setQuantity(sales.getQuantity());
            s.setPrice(sales.getPrice());
            sList.add(s);
        });
        return sList;
    }
}


