package com.mycompany.mygroup.core.web;

import java.math.BigDecimal;

public class RequestModelDTO {
    private String accountNumber;
    private String ammount;

    public RequestModelDTO() {
    }

    public RequestModelDTO(String accountNumber, String ammount) {
        this.accountNumber = accountNumber;
        this.ammount = ammount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }
}

