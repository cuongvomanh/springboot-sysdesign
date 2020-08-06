package com.mycompany.mygroup.core.usecase.account.interactor;

import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.infradi.DependencyResolver;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;
import com.mycompany.mygroup.core.usecase.account.BankAccountPresentBoundary;

import javax.management.InstanceNotFoundException;

public class BankAccountInteractor implements BankAccountBoundary {
    private BankAccountGateway bankAccountGateway;
    private BankAccountPresentBoundary bankAccountPresentBoundary;

    public BankAccountInteractor() throws InstanceNotFoundException {
        this.bankAccountGateway = (BankAccountGateway) DependencyResolver.getInstance("BankAccountGateway");
        this.bankAccountPresentBoundary = (BankAccountPresentBoundary) DependencyResolver.getInstance("BankAccountPresentBoundary");
    }

    public ResponseModel withdraw(RequestModel request) {
        BankAccount account = bankAccountGateway.getByNumber(request.getAccountNumber());
        boolean withdrawResult = account.withdraw(request.getAmmount());
        ResponseModel response = new ResponseModel();
        if (withdrawResult) {
            bankAccountGateway.save(account);
            response.setResult("Withdraw Successful!");
        } else {
            response.setResult("Withdraw Failed!");
        }
        this.bankAccountPresentBoundary.accept();
        return response;
    }

    public ResponseModel deposit(RequestModel request) {
        BankAccount account = bankAccountGateway.getByNumber(request.getAccountNumber());
        boolean depositResult = account.deposit(request.getAmmount());
        ResponseModel response = new ResponseModel();
        if (depositResult) {
            bankAccountGateway.save(account);
            response.setResult("Deposit Successful!");
        } else {
            response.setResult("Deposit Failed!");
        }
        return response;
    }
}
