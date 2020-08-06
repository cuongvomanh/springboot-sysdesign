package com.mycompany.mygroup.core.usecase;

import java.math.BigDecimal;

public class RequestModel {
    private String accountNumber;
    private BigDecimal ammount;

    public RequestModel() {
    }

    public RequestModel(String accountNumber, BigDecimal ammount) {
        this.accountNumber = accountNumber;
        this.ammount = ammount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }
}
