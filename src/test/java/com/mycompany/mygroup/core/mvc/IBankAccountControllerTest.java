package com.mycompany.mygroup.core.mvc;

import com.mycompany.mygroup.core.db.BankAccountInMemoryDB;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountPresentBoundary;
import com.mycompany.mygroup.core.usecase.account.interactor.BankAccountInteractor;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.InstanceNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.junit.Test;

@RunWith(SpringRunner.class)
public class IBankAccountControllerTest extends TestCase {
    @TestConfiguration
    public static class IBankAccountControllerTestConfiguration {
        @Bean
        IBankAccountController bankAccountController() throws InstanceNotFoundException {
            return new BankAccountController();
        }
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
    private IBankAccountController controller;
    private final String SUCCESSFUL_PATTERN = ".*Success.*|200";
    private final String FAILED_PATTERN = ".*Failed.*|200";
    private final HashMap<Integer, String> withdrawTestCaseMap = new HashMap<Integer, String>() {{
        put(10, SUCCESSFUL_PATTERN);
        put(-10, FAILED_PATTERN);
        put(-200, FAILED_PATTERN);
    }};
    private final HashMap<Integer, String> depositTestCaseMap = new HashMap<Integer, String>() {{
        put(10, SUCCESSFUL_PATTERN);
        put(-10, FAILED_PATTERN);
        put(-200, FAILED_PATTERN);
        put(200, SUCCESSFUL_PATTERN);
    }};

    public IBankAccountControllerTest() throws InstanceNotFoundException {
    }

    @Test
    public void testWithdraw() {
        for (Map.Entry<Integer, String> entry : withdrawTestCaseMap.entrySet()) {
            // Run test
            ResponseModel responseModel = controller.withdraw("001", new BigDecimal(entry.getKey()));
            // Assert
            assertTrue(responseModel.getResult().matches(entry.getValue()));
        }
    }

    @Test
    public void testDeposit() {
        for (Map.Entry<Integer, String> entry : withdrawTestCaseMap.entrySet()) {
            // Run test
            ResponseModel responseModel = controller.deposit("001", new BigDecimal(entry.getKey()));
            // Assert
            assertTrue(responseModel.getResult().matches(entry.getValue()));
        }
    }
}
