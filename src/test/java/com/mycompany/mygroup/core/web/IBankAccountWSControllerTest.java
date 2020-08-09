package com.mycompany.mygroup.core.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.management.InstanceNotFoundException;

import com.mycompany.mygroup.core.commons.BankAccountMessageConstant;
import com.mycompany.mygroup.core.exceptions.BankAccountBadRequestException;
import com.mycompany.mygroup.core.mvc.BankAccountController;
import com.mycompany.mygroup.core.mvc.IBankAccountController;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountWSController.class)
@EnableConfigurationProperties
public class IBankAccountWSControllerTest {
    @TestConfiguration
    public static class IBankAccountControllerTestConfiguration {
        @Bean
        IBankAccountController bankAccountController() throws InstanceNotFoundException {
            return new BankAccountController();
        }
    }
    @MockBean
    private BankAccountBoundary bankAccountBoundary;
    @MockBean
    private BankAccountMessageConstant bankAccountMessageConstant;
    @Autowired
    private MockMvc mvc;

    @Test
    public void withdraw_IfWithdrawBankAccountBoundarySuccessful_ReturnResponseSuccessful() {
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        when(bankAccountMessageConstant.getWithdrawSuccessful()).thenReturn("Sucessful");
        ResponseModel responseModelForBankAccountBoundary = new ResponseModel(bankAccountMessageConstant.getWithdrawSuccessful());
        when(bankAccountBoundary.withdraw(Mockito.isA(RequestModel.class))).thenReturn(responseModelForBankAccountBoundary);
        try {
          mvc.perform(MockMvcRequestBuilders
              .post("/bankAccount/withdraw")
              .contentType(MediaType.APPLICATION_JSON)
              .content(input))
            .andExpect(status().isOk());
        } catch (Exception e) {
            assert false;
        }
    }
    @Test
    public void withdraw_IfWithdrawBankAccountBoundaryFailed_ReturnResponseFailed() {
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        BankAccountBadRequestException bankAccountBadRequestException = new BankAccountBadRequestException(this.bankAccountMessageConstant.getWithdrawFailed());
        when(bankAccountBoundary.withdraw(Mockito.isA(RequestModel.class))).thenThrow(bankAccountBadRequestException);
        try {
          mvc.perform(MockMvcRequestBuilders
              .post("/bankAccount/withdraw")
              .contentType(MediaType.APPLICATION_JSON)
              .content(input))
            .andExpect(status().isInternalServerError());
        } catch (Exception e) {
            assert true;
        }
    }
    @Test
    public void deposit_IfDepositBankAccountBoundarySuccessful_ReturnResponseSuccessful() {
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        when(bankAccountMessageConstant.getDepositSuccessful()).thenReturn("Sucessful");
        ResponseModel responseModelForBankAccountBoundary = new ResponseModel(bankAccountMessageConstant.getDepositSuccessful());
        when(bankAccountBoundary.deposit(Mockito.isA(RequestModel.class))).thenReturn(responseModelForBankAccountBoundary);
        try {
          mvc.perform(MockMvcRequestBuilders
              .post("/bankAccount/deposit")
              .contentType(MediaType.APPLICATION_JSON)
              .content(input))
            .andExpect(status().isOk());
        } catch (Exception e) {
            assert false;
        }
    }
    @Test
    public void withdraw_IfDepositBankAccountBoundaryFailed_ReturnResponseFailed() {
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        BankAccountBadRequestException bankAccountBadRequestException = new BankAccountBadRequestException(this.bankAccountMessageConstant.getDepositFailed());
        when(bankAccountBoundary.withdraw(Mockito.isA(RequestModel.class))).thenThrow(bankAccountBadRequestException);
        try {
          mvc.perform(MockMvcRequestBuilders
              .post("/bankAccount/withdraw")
              .contentType(MediaType.APPLICATION_JSON)
              .content(input))
            .andExpect(status().isInternalServerError());
        } catch (Exception e) {
            assert true;
        }
    }
}
