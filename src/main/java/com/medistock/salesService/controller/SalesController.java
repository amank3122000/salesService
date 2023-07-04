package com.medistock.salesService.controller;

import com.medistock.salesService.dto.SalesDTO;
import com.medistock.salesService.exception.SalesNotFoundException;
import com.medistock.salesService.service.SalesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class SalesController {

    @Autowired
    private static SalesService salesService;

    @Autowired
    private Environment environment;

    @PostMapping(value = "/addsales")
    public ResponseEntity<SalesDTO> addSales(@Valid @RequestBody SalesDTO sales) {
        return new ResponseEntity<>(salesService.addSales(sales), HttpStatus.CREATED);
    }

    @PutMapping(value = "/updatesales/{salesId}")
    public ResponseEntity<String> updateSales(@Valid @RequestBody SalesDTO sales, @PathVariable Integer salesId)
            throws SalesNotFoundException {
        salesService.updateSales(sales, salesId);
        String successMessage = environment.getProperty("Sales Updated Successfully.");
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletesales/{salesId}")
    public ResponseEntity<String> deleteSales(@PathVariable Integer salesId)
            throws SalesNotFoundException {
        salesService.deleteSales(salesId);
        String successMessage = environment.getProperty("Sales Deleted Successfully.");
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    @GetMapping(value = "/viewbyid/{salesId}")
    public ResponseEntity<SalesDTO> viewById(@PathVariable Integer salesId)
            throws SalesNotFoundException {
        SalesDTO sales = salesService.viewById(salesId);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping(value = "/viewallsales")
    public ResponseEntity<List<SalesDTO>> viewAllSales() {
        List<SalesDTO> salesList = salesService.viewAllSales();
        return new ResponseEntity<>(salesList, HttpStatus.OK);
    }
}

