package com.medistock.salesService;

import com.medistock.salesService.controller.SalesController;
import com.medistock.salesService.dto.SalesDTO;
import com.medistock.salesService.entity.Sales;
import com.medistock.salesService.exception.SalesNotFoundException;
import com.medistock.salesService.repository.ISalesRepository;
import com.medistock.salesService.service.SalesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SalesTest {

    @Autowired
    private SalesService salesService;

    @MockBean
    private ISalesRepository salesRepository;

    @InjectMocks
    private SalesController salesController;

    private static final Logger LOGGER = LogManager.getLogger(SalesServiceApplicationTests.class);

    Sales s1, s2;
    SalesDTO sd1, sd2;
    Optional<Sales> s3, s4;

    @BeforeAll
    public static void initBefore() {
        LOGGER.info("Sales module testing initialized.");
    }


    @BeforeEach
    public void init() {
        s1 = new Sales(1, 10, 15, LocalDate.now(), 40, 80);
        s2 = new Sales(2, 12, 20, LocalDate.now(), 30, 90);

        sd1 = new SalesDTO(1, 10, 15, LocalDate.now(), 40, 80);
        sd2 = new SalesDTO(2, 12, 20, LocalDate.now(), 30, 90);

        s3 = Optional.of(s1);
        s4 = Optional.of(s2);
    }

    @Test
    void testAddSales1() {
        when(salesRepository.save(s1)).thenReturn(s1);
        assertEquals(sd1.getSalesId(), salesService.addSales(sd1).getSalesId());
        assertEquals(sd1.getBatchCode(), salesService.addSales(sd1).getBatchCode());
        assertEquals(sd1.getCustCode(), salesService.addSales(sd1).getCustCode());
        assertEquals(sd1.getDate(), salesService.addSales(sd1).getDate());
        assertEquals(sd1.getQuantity(), salesService.addSales(sd1).getQuantity());
        assertEquals(sd1.getPrice(), salesService.addSales(sd1).getPrice());
    }

    @Test
    void testAddSales2() {
        when(salesRepository.save(s1)).thenReturn(s1);
        assertNotNull(salesService.addSales(sd1));
    }

    @Test
    void testUpdateSales1() throws SalesNotFoundException {
        when(salesRepository.existsById(s2.getSalesId())).thenReturn(true);
        when(salesRepository.findById(s2.getSalesId())).thenReturn(s3);
        when(salesRepository.save(s2)).thenReturn(s2);
        assertEquals(sd2.getBatchCode(), salesService.updateSales(sd2, sd2.getSalesId()).getBatchCode());
        assertEquals(sd2.getCustCode(), salesService.updateSales(sd2, sd2.getSalesId()).getCustCode());
        assertEquals(sd2.getDate(), salesService.updateSales(sd2, sd2.getSalesId()).getDate());
        assertEquals(sd2.getQuantity(), salesService.updateSales(sd2, sd2.getSalesId()).getQuantity());
        assertEquals(sd2.getPrice(), salesService.updateSales(sd2, sd2.getSalesId()).getPrice());
    }

    @Test
    void testUpdateSales2() {
        when(salesRepository.existsById(s1.getSalesId())).thenReturn(false);
        SalesNotFoundException exception = assertThrows(SalesNotFoundException.class, () -> salesService.updateSales(sd1, sd1.getSalesId()));
        assertEquals("Sales Not Found.", exception.getMessage());
    }

    @Test
    void testDeleteSales1() throws SalesNotFoundException {
        when(salesRepository.existsById(s1.getSalesId())).thenReturn(true);
        when(salesRepository.findById(s1.getSalesId())).thenReturn(s3);
        salesService.deleteSales(s1.getSalesId());
        verify(salesRepository).delete(s1);
    }

    @Test
    void testDeleteSales2() {
        when(salesRepository.existsById(s1.getSalesId())).thenReturn(false);
        SalesNotFoundException exception = assertThrows(SalesNotFoundException.class, () -> salesService.deleteSales(s1.getSalesId()));
        assertEquals("Sales Not Found.", exception.getMessage());
    }

    @Test
    void testViewById1() throws SalesNotFoundException {
        when(salesRepository.existsById(s1.getSalesId())).thenReturn(true);
        when(salesRepository.findById(s1.getSalesId())).thenReturn(s3);
        assertEquals(sd1.getSalesId(), salesService.viewById(sd1.getSalesId()).getSalesId());
        assertEquals(sd1.getBatchCode(), salesService.viewById(sd1.getSalesId()).getBatchCode());
        assertEquals(sd1.getCustCode(), salesService.viewById(sd1.getSalesId()).getCustCode());
        assertEquals(sd1.getDate(), salesService.viewById(sd1.getSalesId()).getDate());
        assertEquals(sd1.getQuantity(), salesService.viewById(sd1.getSalesId()).getQuantity());
        assertEquals(sd1.getPrice(), salesService.viewById(sd1.getSalesId()).getPrice());
    }

    @Test
    void testViewById2() {
        when(salesRepository.existsById(s1.getSalesId())).thenReturn(false);
        SalesNotFoundException exception = assertThrows(SalesNotFoundException.class, () -> salesService.viewById(sd1.getSalesId()));
        assertEquals("Sales Not Found.", exception.getMessage());
    }

    @Test
    void testViewAllSales1() {
        List<Sales> s = new ArrayList<>();
        s.add(s1);
        s.add(s2);
        when(salesRepository.findAll()).thenReturn(s);
        assertEquals(s.size(), salesService.viewAllSales().size());
    }

    @Test
    void testViewAllSales2() {
        List<SalesDTO> s = salesService.viewAllSales();
        assertTrue(s.isEmpty());

        System.out.println(s1.toString() + sd1.toString());
    }


//	@Test
//	void addSales_ValidSales_ReturnsCreatedResponse() {
//		SalesDTO sales = new SalesDTO();
//		when(salesService.addSales(sales)).thenReturn(sales);
//
//		ResponseEntity<SalesDTO> response = salesController.addSales(sales);
//
//		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
//		assertEquals(sales, response.getBody());
//		verify(salesService, times(1)).addSales(sales);
//	}

}





