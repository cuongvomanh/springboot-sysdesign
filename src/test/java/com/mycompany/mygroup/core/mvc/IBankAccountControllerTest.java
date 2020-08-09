package com.mycompany.mygroup.core.mvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.management.InstanceNotFoundException;

import com.mycompany.mygroup.core.commons.BankAccountMessageConstant;
import com.mycompany.mygroup.core.exceptions.BankAccountBadRequestException;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;

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
public class IBankAccountControllerTest extends TestCase {
    @TestConfiguration
    public static class IBankAccountControllerTestConfiguration {
        @Bean
        IBankAccountController bankAccountController() throws InstanceNotFoundException {
            return new BankAccountController();
        }
    }
    @Autowired
    private IBankAccountController controller;
    @MockBean
    private BankAccountBoundary bankAccountBoundary;
    @MockBean
    private BankAccountMessageConstant bankAccountMessageConstant;

    @Test
    public void withdraw_IfWithdrawBankAccountBoundarySuccessful_ReturnResponseSuccessful() {
        when(bankAccountMessageConstant.getWithdrawSuccessful()).thenReturn("Sucessful");
        ResponseModel responseModelForBankAccountBoundary = new ResponseModel(bankAccountMessageConstant.getWithdrawSuccessful());
        when(bankAccountBoundary.withdraw(Mockito.isA(RequestModel.class))).thenReturn(responseModelForBankAccountBoundary);
        ResponseModel responseModel = controller.withdraw("001", new BigDecimal(1));
        assertEquals(responseModel.getResult(), this.bankAccountMessageConstant.getWithdrawSuccessful());
    }
    @Test
    public void withdraw_IfWithdrawBankAccountBoundaryFailed_ReturnResponseFailed() {
        BankAccountBadRequestException bankAccountBadRequestException = new BankAccountBadRequestException(this.bankAccountMessageConstant.getWithdrawFailed());
        when(bankAccountBoundary.withdraw(Mockito.isA(RequestModel.class))).thenThrow(bankAccountBadRequestException);
        assertThrows(BankAccountBadRequestException.class, () -> {controller.withdraw("001", new BigDecimal(1));});
    }
    @Test
    public void deposit_IfDepositBankAccountBoundarySuccessful_ReturnResponseSuccessful() {
        when(bankAccountMessageConstant.getDepositSuccessful()).thenReturn("Sucessful");
        ResponseModel responseModelForBankAccountBoundary = new ResponseModel(bankAccountMessageConstant.getDepositSuccessful());
        when(bankAccountBoundary.deposit(Mockito.isA(RequestModel.class))).thenReturn(responseModelForBankAccountBoundary);
        ResponseModel responseModel = controller.deposit("001", new BigDecimal(1));
        assertEquals(responseModel.getResult(), this.bankAccountMessageConstant.getDepositSuccessful());
    }
    @Test
    public void deposit_IfDepositBankAccountBoundaryFailed_ReturnResponseFailed() {
        BankAccountBadRequestException bankAccountBadRequestException = new BankAccountBadRequestException(this.bankAccountMessageConstant.getDepositFailed());
        when(bankAccountBoundary.deposit(Mockito.isA(RequestModel.class))).thenThrow(bankAccountBadRequestException);
        assertThrows(BankAccountBadRequestException.class, () -> {controller.deposit("001", new BigDecimal(1));});
    }
}
