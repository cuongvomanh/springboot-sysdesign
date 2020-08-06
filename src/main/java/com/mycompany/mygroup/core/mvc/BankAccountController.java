package com.mycompany.mygroup.core.mvc;

import com.mycompany.mygroup.core.infradi.DependencyResolver;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;

import javax.management.InstanceNotFoundException;
import java.math.BigDecimal;

public class BankAccountController implements IBankAccountController {
    private BankAccountBoundary bankAccountBoundary;

    public BankAccountController() throws InstanceNotFoundException {
        this.bankAccountBoundary = (BankAccountBoundary) DependencyResolver.getInstance("BankAccountBoundary");
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
