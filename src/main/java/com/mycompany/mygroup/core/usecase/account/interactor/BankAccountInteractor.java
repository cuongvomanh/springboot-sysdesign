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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class BankAccountInteractor implements BankAccountBoundary {
    @Autowired
    private BankAccountGateway bankAccountGateway;
    @Autowired
    private BankAccountPresentBoundary bankAccountPresentBoundary;
    @Autowired
    private BankAccountMessageConstant bankAccountMessageConstant;

    private static Logger LOGGER = LoggerFactory.getLogger(BankAccountBoundary.class);

    public BankAccountInteractor() {
    }

    public ResponseModel withdraw(RequestModel request) {
        BankAccount account = bankAccountGateway.getByNumber(request.getAccountNumber());
        if (account == null){
            throw new BankAccountBadRequestException(bankAccountMessageConstant.getWithdrawBadRequestNotFound());
        }
        boolean withdrawResult = account.withdraw(request.getAmmount());
        ResponseModel response = new ResponseModel();
        if (withdrawResult) {
            try {
                bankAccountGateway.save(account);
            } catch (Exception exception){
                LOGGER.error("Exception", exception);
                throw exception;
            }
            response.setResult(bankAccountMessageConstant.getWithdrawSuccessful());
            this.bankAccountPresentBoundary.accept();
            return response;
        } else {
            throw new BankAccountBadRequestException(bankAccountMessageConstant.getWithdrawBadRequest());
        }
    }

    public ResponseModel deposit(RequestModel request) {
        BankAccount account = bankAccountGateway.getByNumber(request.getAccountNumber());
        if (account == null){
            throw new BankAccountBadRequestException(bankAccountMessageConstant.getDepositRequestNotFound());
        }
        boolean depositResult = account.deposit(request.getAmmount());
        ResponseModel response = new ResponseModel();
        if (depositResult) {
            bankAccountGateway.save(account);
            response.setResult(bankAccountMessageConstant.getDepositSuccessful());
            return response;
        } else {
            throw new BankAccountBadRequestException(bankAccountMessageConstant.getDepositBadRequest());
        }
    }
}
