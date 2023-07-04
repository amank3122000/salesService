package com.medistock.salesService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public class SalesDTO {

    private int salesId;

    @Min(value = 1, message = "Batch Code cannot be negative or 0.")
    private int batchCode;

    @Min(value = 1, message = "Customer Code cannot be negative or 0")
    private int custCode;

    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Min(value = 1, message = "Quantity should be equal or more than 1.")
    private int quantity;

    @Min(value = 0, message = "Price cannot be less than 0.")
    private double price;

	public SalesDTO() {
        super();
    }

    public SalesDTO(int salesId, int batchCode, int custCode, LocalDate date, int quantity, double price) {
        this.salesId = salesId;
        this.batchCode = batchCode;
        this.custCode = custCode;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public int getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(int batchCode) {
        this.batchCode = batchCode;
    }

    public int getCustCode() {
        return custCode;
    }

    public void setCustCode(int custCode) {
        this.custCode = custCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}