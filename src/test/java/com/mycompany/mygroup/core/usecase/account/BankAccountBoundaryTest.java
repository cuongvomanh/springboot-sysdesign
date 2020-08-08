package com.mycompany.mygroup.core.usecase.account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.management.InstanceNotFoundException;

import com.mycompany.mygroup.core.db.BankAccountInMemoryDB;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.mvc.BankAccountPresenter;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.interactor.BankAccountInteractor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class BankAccountBoundaryTest extends TestCase {
    @TestConfiguration
    public static class BankAccountBoundaryTestConfiguration {
        @Bean
        BankAccountInteractor bankAccountBoundary() throws InstanceNotFoundException {
            return new BankAccountInteractor();
        }
        @Bean
        BankAccountGateway bankAccountGateway() {
            return new BankAccountInMemoryDB();
        }
        @Bean
        BankAccountPresentBoundary bankAccountPresentBoundary() {
            return new BankAccountPresenter();
        }

    }
    @Autowired
    private BankAccountBoundary bankAccountBoundary;
    private final String WITHDRAW_SUCCESSFUL = "Withdraw Successful!";
    private final String WITHDRAW_FAILED = "Withdraw Failed!";
    private final String DEPOSIT_SUCCESSFUL = "Deposit Successful!";
    private final String DEPOSIT_FAILED = "Deposit Failed!";
    private final HashMap<Integer, String> withdrawTestCaseMap = new HashMap<Integer, String>() {{
        put(10, WITHDRAW_SUCCESSFUL);
        put(-10, WITHDRAW_FAILED);
        put(-200, WITHDRAW_FAILED);
    }};
    private final HashMap<Integer, String> depositTestCaseMap = new HashMap<Integer, String>() {{
        put(10, DEPOSIT_SUCCESSFUL);
        put(-10, DEPOSIT_FAILED);
        put(-200, DEPOSIT_FAILED);
        put(200, DEPOSIT_SUCCESSFUL);
    }};


    @Test
    public void testWithdraw() {
        for (Map.Entry<Integer, String> entry : withdrawTestCaseMap.entrySet()) {
            // Setup
            RequestModel requestModel = new RequestModel("001", new BigDecimal(entry.getKey()));
            // Run
            ResponseModel responseModel = bankAccountBoundary.withdraw(requestModel);
            // Assert
            assertEquals(responseModel.getResult(), entry.getValue());
        }
    }

    @Test
    public void testDeposit() {
        for (Map.Entry<Integer, String> entry : depositTestCaseMap.entrySet()) {
            // Setup
            RequestModel requestModel = new RequestModel("001", new BigDecimal(entry.getKey()));
            // Run
            ResponseModel responseModel = bankAccountBoundary.deposit(requestModel);
            // Assert
            assertEquals(responseModel.getResult(), entry.getValue());
        }
    }
}
