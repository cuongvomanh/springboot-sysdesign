package com.mycompany.mygroup.core.mvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.management.InstanceNotFoundException;

import com.mycompany.mygroup.core.commons.BankAccountMessageConstant;
import com.mycompany.mygroup.core.db.BankAccountInMemoryDB;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountPresentBoundary;
import com.mycompany.mygroup.core.usecase.account.interactor.BankAccountInteractor;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
public class IBankAccountControllerTest extends TestCase {
    @TestConfiguration
    @EnableConfigurationProperties
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
        @Bean
        BankAccountMessageConstant bankAccountMessageConstant() {
            return new BankAccountMessageConstant();
        }
    }
    @Autowired
    private IBankAccountController controller;
    @Autowired
    private BankAccountMessageConstant bankAccountMessageConstant;

    private HashMap<Integer, String> withdrawTestCaseMap;
    private HashMap<Integer, String> depositTestCaseMap;

    @BeforeEach
    void setup() {
      final String WITHDRAW_SUCCESSFUL = this.bankAccountMessageConstant.getWithdrawSuccessful();
      final String WITHDRAW_FAILED = "Exception";
      final String DEPOSIT_SUCCESSFUL = this.bankAccountMessageConstant.getDepositSuccessful();
      final String DEPOSIT_FAILED = "Exception";
      withdrawTestCaseMap = new HashMap<Integer, String>() {{
        put(10, WITHDRAW_SUCCESSFUL);
        put(-10, WITHDRAW_FAILED);
        put(-200, WITHDRAW_FAILED);
      }};
      depositTestCaseMap = new HashMap<Integer, String>() {{
        put(10, DEPOSIT_SUCCESSFUL);
        put(-10, DEPOSIT_FAILED);
        put(-200, DEPOSIT_FAILED);
        put(200, DEPOSIT_SUCCESSFUL);
      }};
    }

    public IBankAccountControllerTest() throws InstanceNotFoundException {
    }

    @Test
    public void testWithdraw() {
        setup();
        for (Map.Entry<Integer, String> entry : withdrawTestCaseMap.entrySet()) {
            // Run test
            // Assert
            try {
                ResponseModel responseModel = controller.withdraw("001", new BigDecimal(entry.getKey()));
                if (entry.getValue() == "Exception") {
                    assert false;
                } else {
                    assertTrue(responseModel.getResult().matches(entry.getValue()));
                }
            } catch (Exception e) {
                if (entry.getValue() == "Exception") {
                    assert true;
                } else {
                    assert false;
                }
            }
        }
    }

    @Test
    public void testDeposit() {
        setup();
        for (Map.Entry<Integer, String> entry : depositTestCaseMap.entrySet()) {
            // Run test
            // Assert
            try {
                ResponseModel responseModel = controller.deposit("001", new BigDecimal(entry.getKey()));
                if (entry.getValue() == "Exception") {
                    assert false;
                } else {
                    assertTrue(responseModel.getResult().matches(entry.getValue()));
                }
            } catch (Exception e) {
                if (entry.getValue() == "Exception") {
                    assert true;
                } else {
                    assert false;
                }
            }
        }
    }
}
