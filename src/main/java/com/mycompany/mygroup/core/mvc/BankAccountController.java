package com.mycompany.mygroup.core.mvc;

import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;

import javax.management.InstanceNotFoundException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

@Component
@Primary
public class BankAccountController implements IBankAccountController {
    @Autowired
    private BankAccountBoundary bankAccountBoundary;

    public BankAccountController() throws InstanceNotFoundException {
    }

    public ResponseModel deposit(String accountNumber, BigDecimal amount) {
        RequestModel requestModel = new RequestModel(accountNumber, amount);
        return this.bankAccountBoundary.deposit(requestModel);
    }

    public ResponseModel withdraw(String accountNumber, BigDecimal amount) {
        RequestModel request = new RequestModel(accountNumber, amount);
        return this.bankAccountBoundary.withdraw(request);
    }
}
