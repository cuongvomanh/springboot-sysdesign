package com.mycompany.mygroup.core.usecase.account;

import com.mycompany.mygroup.core.infradi.DependencyResolver;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import junit.framework.TestCase;

import javax.management.InstanceNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BankAccountBoundaryTest extends TestCase {
    private BankAccountBoundary bankAccountBoundary = (BankAccountBoundary) DependencyResolver.getInstance("BankAccountBoundary");
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

    public BankAccountBoundaryTest() throws InstanceNotFoundException {
    }

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
