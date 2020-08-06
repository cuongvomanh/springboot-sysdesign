package com.mycompany.mygroup.core.mvc;

import com.mycompany.mygroup.core.usecase.ResponseModel;

import java.math.BigDecimal;

public interface IBankAccountController {
    public ResponseModel withdraw(String accountNumber, BigDecimal amount);
    public ResponseModel deposit(String accountNumber, BigDecimal amount);
}
