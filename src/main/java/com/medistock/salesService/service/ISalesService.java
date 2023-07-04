package com.medistock.salesService.service;

import com.medistock.salesService.dto.SalesDTO;
import com.medistock.salesService.exception.SalesNotFoundException;

import java.util.List;

public interface ISalesService {

    public SalesDTO addSales(SalesDTO sales);

    public SalesDTO updateSales(SalesDTO sales, int salesId) throws SalesNotFoundException;

    public void deleteSales(int salesId) throws SalesNotFoundException;

    public SalesDTO viewById(int salesId) throws SalesNotFoundException;

    public List<SalesDTO> viewAllSales() throws SalesNotFoundException;


}

