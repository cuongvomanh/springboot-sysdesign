package com.mycompany.mygroup.core.mvc;

import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceNotFoundException;
import java.math.BigDecimal;

@Component
public class BankAccountAsyncController implements IBankAccountController {
    @Autowired
    private BankAccountBoundary bankAccountBoundary;

    public BankAccountAsyncController() throws InstanceNotFoundException {
    }

    public ResponseModel deposit(String accountNumber, BigDecimal amount) {
        RequestModel requestModel = new RequestModel(accountNumber, amount);
        new Thread(() -> {
            this.bankAccountBoundary.deposit(requestModel);
        }).start();
        return new ResponseModel("200");
    }

    public ResponseModel withdraw(String accountNumber, BigDecimal amount) {
        RequestModel requestModel = new RequestModel(accountNumber, amount);
        new Thread(() -> {
            this.bankAccountBoundary.withdraw(requestModel);
        }).start();
        return new ResponseModel("200");
    }
}
