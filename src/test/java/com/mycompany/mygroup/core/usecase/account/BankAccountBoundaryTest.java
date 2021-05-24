package com.mycompany.mygroup.core.usecase.account;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.management.InstanceNotFoundException;

import com.mycompany.mygroup.core.commons.BankAccountMessageConstant;
import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.exceptions.BankAccountBadRequestException;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.mvc.BankAccountPresenter;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.interactor.BankAccountInteractor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    }
    @Autowired
    private BankAccountBoundary bankAccountBoundary;
    @MockBean
    private BankAccountMessageConstant bankAccountMessageConstant;
    @MockBean
    private BankAccountGateway bankAccountGateway;
    @MockBean
    private BankAccount bankAccount;
    @MockBean
    private BankAccountPresentBoundary bankAccountPresentBoundary;

    @Test
    public void withdraw_IfWithdrawBankAccountSuccessful_ReturnResponseSuccessful() {
        when(bankAccountGateway.getByNumber(Mockito.isA(String.class))).thenReturn(bankAccount);
        when(bankAccountGateway.save(Mockito.isA(BankAccount.class))).thenReturn(bankAccount);
        when(bankAccount.withdraw(Mockito.isA(BigDecimal.class))).thenReturn(true);
        when(bankAccountMessageConstant.getWithdrawSuccessful()).thenReturn("Sucessful");
        RequestModel requestModel = new RequestModel("001", new BigDecimal(1));
        ResponseModel responseModel = bankAccountBoundary.withdraw(requestModel);
        assertEquals(responseModel.getResult(), bankAccountMessageConstant.getWithdrawSuccessful());
    }

    @Test
    public void withdraw_IfWithdrawBankAccountFailed_ReturnResponseFailed() {
        when(bankAccountGateway.getByNumber(Mockito.isA(String.class))).thenReturn(bankAccount);
        when(bankAccountGateway.save(Mockito.isA(BankAccount.class))).thenReturn(bankAccount);
        when(bankAccount.withdraw(Mockito.isA(BigDecimal.class))).thenReturn(false);
        when(bankAccountMessageConstant.getWithdrawFailed()).thenReturn("Failed");
        RequestModel requestModel = new RequestModel("001", new BigDecimal(1));
        assertThrows(BankAccountBadRequestException.class, () -> {bankAccountBoundary.withdraw(requestModel);});
    }

    @Test
    public void withdraw_IfGatewaySaveFailed_ReturnResponseFailed() {
        when(bankAccountGateway.getByNumber(Mockito.isA(String.class))).thenReturn(bankAccount);
        when(bankAccount.withdraw(Mockito.isA(BigDecimal.class))).thenReturn(true);
        when(bankAccountMessageConstant.getWithdrawSuccessful()).thenReturn("Sucessful");
        when(bankAccountGateway.save(Mockito.isA(BankAccount.class))).thenThrow();
        RequestModel requestModel = new RequestModel("001", new BigDecimal(1));
        assertThrows(Exception.class, () -> {bankAccountBoundary.withdraw(requestModel);});
    }
    @Test
    public void withdraw_IfGatewayGetByNumberFailed_ReturnResponseFailed() {
        when(bankAccountGateway.getByNumber(Mockito.isA(String.class))).thenReturn(bankAccount);
        when(bankAccount.withdraw(Mockito.isA(BigDecimal.class))).thenReturn(true);
        when(bankAccountMessageConstant.getWithdrawSuccessful()).thenReturn("Sucessful");
        when(bankAccountGateway.getByNumber(Mockito.isA(String.class))).thenThrow();
        RequestModel requestModel = new RequestModel("001", new BigDecimal(1));
        assertThrows(Exception.class, () -> {bankAccountBoundary.withdraw(requestModel);});
    }

    @Test
    public void deposit_IfDepositBankAccountSuccessful_ReturnResponseSuccessful() {
        when(bankAccountGateway.getByNumber(Mockito.isA(String.class))).thenReturn(bankAccount);
        when(bankAccountGateway.save(Mockito.isA(BankAccount.class))).thenReturn(bankAccount);
        when(bankAccount.deposit(Mockito.isA(BigDecimal.class))).thenReturn(true);
        when(bankAccountMessageConstant.getDepositSuccessful()).thenReturn("Sucessful");
        RequestModel requestModel = new RequestModel("001", new BigDecimal(1));
        ResponseModel responseModel = bankAccountBoundary.deposit(requestModel);
        assertEquals(responseModel.getResult(), bankAccountMessageConstant.getDepositSuccessful());
    }

    @Test
    public void deposit_IfDepositBankAccountFailed_ReturnResponseFailed() {
        when(bankAccountGateway.getByNumber(Mockito.isA(String.class))).thenReturn(bankAccount);
        when(bankAccountGateway.save(Mockito.isA(BankAccount.class))).thenReturn(bankAccount);
        when(bankAccount.deposit(Mockito.isA(BigDecimal.class))).thenReturn(false);
        when(bankAccountMessageConstant.getDepositFailed()).thenReturn("Failed");
        RequestModel requestModel = new RequestModel("001", new BigDecimal(1));
        assertThrows(BankAccountBadRequestException.class, () -> {bankAccountBoundary.deposit(requestModel);});
    }
    @Test
    public void deposit_IfGatewaySaveFailed_ReturnResponseFailed() {
        when(bankAccountGateway.getByNumber(Mockito.isA(String.class))).thenReturn(bankAccount);
        when(bankAccount.deposit(Mockito.isA(BigDecimal.class))).thenReturn(true);
        when(bankAccountMessageConstant.getDepositSuccessful()).thenReturn("Sucessful");
        when(bankAccountGateway.save(Mockito.isA(BankAccount.class))).thenThrow();
        RequestModel requestModel = new RequestModel("001", new BigDecimal(1));
        assertThrows(Exception.class, () -> {bankAccountBoundary.deposit(requestModel);});
    }
    @Test
    public void deposit_IfGatewayGetByNumberFailed_ReturnResponseFailed() {
        when(bankAccountGateway.getByNumber(Mockito.isA(String.class))).thenReturn(bankAccount);
        when(bankAccount.deposit(Mockito.isA(BigDecimal.class))).thenReturn(true);
        when(bankAccountMessageConstant.getDepositSuccessful()).thenReturn("Sucessful");
        when(bankAccountGateway.getByNumber(Mockito.isA(String.class))).thenThrow();
        RequestModel requestModel = new RequestModel("001", new BigDecimal(1));
        assertThrows(Exception.class, () -> {bankAccountBoundary.deposit(requestModel);});
    }
}
