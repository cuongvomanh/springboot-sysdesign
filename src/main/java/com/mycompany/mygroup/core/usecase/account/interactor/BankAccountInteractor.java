package com.mycompany.mygroup.core.usecase.account.interactor;

import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;
import com.mycompany.mygroup.core.usecase.account.BankAccountPresentBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;

@Service
public class BankAccountInteractor implements BankAccountBoundary {
    @Autowired
    private BankAccountGateway bankAccountGateway;
    @Autowired
    private BankAccountPresentBoundary bankAccountPresentBoundary;

    public BankAccountInteractor() throws InstanceNotFoundException {
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
