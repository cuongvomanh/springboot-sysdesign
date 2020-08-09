package com.mycompany.mygroup.core.usecase.account.interactor;

import javax.management.InstanceNotFoundException;

import com.mycompany.mygroup.core.commons.BankAccountMessageConstant;
import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.exceptions.BankAccountBadRequestException;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;
import com.mycompany.mygroup.core.usecase.account.BankAccountPresentBoundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountInteractor implements BankAccountBoundary {
    @Autowired
    private BankAccountGateway bankAccountGateway;
    @Autowired
    private BankAccountPresentBoundary bankAccountPresentBoundary;
    @Autowired
    private BankAccountMessageConstant bankAccountMessageConstant;

    public BankAccountInteractor() throws InstanceNotFoundException {
    }

    public ResponseModel withdraw(RequestModel request) {
        BankAccount account = bankAccountGateway.getByNumber(request.getAccountNumber());
        boolean withdrawResult = account.withdraw(request.getAmmount());
        ResponseModel response = new ResponseModel();
        if (withdrawResult) {
            bankAccountGateway.save(account);
            response.setResult("Withdraw Successful!");
            this.bankAccountPresentBoundary.accept();
            return response;
        } else {
            throw new BankAccountBadRequestException(bankAccountMessageConstant.getWithdrawFailed());
        }
    }

    public ResponseModel deposit(RequestModel request) {
        BankAccount account = bankAccountGateway.getByNumber(request.getAccountNumber());
        boolean depositResult = account.deposit(request.getAmmount());
        ResponseModel response = new ResponseModel();
        if (depositResult) {
            bankAccountGateway.save(account);
            response.setResult("Deposit Successful!");
            return response;
        } else {
            throw new BankAccountBadRequestException(bankAccountMessageConstant.getDepositFailed());
        }
    }
}
