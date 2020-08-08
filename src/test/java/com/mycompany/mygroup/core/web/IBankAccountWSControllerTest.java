package com.mycompany.mygroup.core.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import javax.management.InstanceNotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.mygroup.core.db.BankAccountInMemoryDB;
import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.mvc.BankAccountController;
import com.mycompany.mygroup.core.mvc.BankAccountPresenter;
import com.mycompany.mygroup.core.mvc.IBankAccountController;
import com.mycompany.mygroup.core.usecase.account.BankAccountPresentBoundary;
import com.mycompany.mygroup.core.usecase.account.interactor.BankAccountInteractor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountWSController.class)
public class IBankAccountWSControllerTest {
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
    private MockMvc mvc;
    @Test
    public void testWithdraw() throws Exception {
       mvc.perform(MockMvcRequestBuilders
           .post("/bankAccount/withdraw")
           .contentType(MediaType.APPLICATION_JSON)
           .content("{\"accountNumber\":\"001\",\"ammount\":1}"))
           .andExpect(status().isOk());
          
    }
}
