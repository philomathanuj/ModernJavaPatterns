package com.java.patterns.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.LongConsumer;


public class EcomData implements Consumer<EcomData> {

    private String invoiceNumber;
    private String stockCode;
    private String description;
    private long quantity;
    private LocalDateTime invoiceDate;
    private BigDecimal unitPrice;
    private Long customerId;
    private String country;

    @Override
    public void accept(EcomData e) {
        this.quantity += e.getQuantity();
        this.unitPrice.add(e.getUnitPrice());
    }

    public void combine(EcomData other) {
        this.quantity += other.getQuantity();
        this.unitPrice.add(other.getUnitPrice());
    }
    public static class EcomDataBuilder {

        private String invoiceNumber;
        private String stockCode;
        private String description;
        private long quantity;
        private LocalDateTime invoiceDate;
        private BigDecimal unitPrice;
        private Long customerId;
        private String country;
        public EcomDataBuilder() {
        }
        public EcomDataBuilder invoiceNumber(String invoiceNumber){
            this.invoiceNumber = invoiceNumber;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }
        public EcomDataBuilder stockCode(String stockCode){
            this.stockCode = stockCode;
            return this;
        }
        public EcomDataBuilder description(String description){
            this.description = description;
            return this;
        }
        public EcomDataBuilder quantity(long quantity){
            this.quantity = quantity;
            return this;
        }

        public EcomDataBuilder unitPrice(BigDecimal unitPrice){
            this.unitPrice = unitPrice;
            return this;
        }

        public EcomDataBuilder customerId(Long customerId){
            this.customerId = customerId;
            return this;
        }

        public EcomDataBuilder country(String country){
            this.country = country;
            return this;
        }

        public EcomDataBuilder invoiceDate(LocalDateTime invoiceDate){
            this.invoiceDate = invoiceDate;
            return this;
        }
        public EcomData build(){
            return new EcomData(this);
        }
    }
    private EcomData(EcomDataBuilder ecomDataBuilder) {
        this.country = ecomDataBuilder.country;
        this.customerId = ecomDataBuilder.customerId;
        this.description = ecomDataBuilder.description;
        this.invoiceDate = ecomDataBuilder.invoiceDate;
        this.invoiceNumber = ecomDataBuilder.invoiceNumber;
        this.quantity = ecomDataBuilder.quantity;
        this.stockCode = ecomDataBuilder.stockCode;
        this.unitPrice = ecomDataBuilder.unitPrice;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getDescription() {
        return description;
    }

    public long getQuantity() {
        return quantity;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCountry() {
        return country;
    }

    public EcomData() {
        this.quantity = 0;
        this.unitPrice = new BigDecimal(0);
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
