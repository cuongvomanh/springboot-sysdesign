package com.mycompany.mygroup.core.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import javax.management.InstanceNotFoundException;

import com.mycompany.mygroup.core.commons.BankAccountMessageConstant;
import com.mycompany.mygroup.core.db.BankAccountInMemoryDB;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.mvc.BankAccountController;
import com.mycompany.mygroup.core.mvc.BankAccountPresenter;
import com.mycompany.mygroup.core.mvc.IBankAccountController;
import com.mycompany.mygroup.core.usecase.account.BankAccountPresentBoundary;
import com.mycompany.mygroup.core.usecase.account.interactor.BankAccountInteractor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
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
        @ConfigurationProperties(prefix = "bankaccountmessage")
        BankAccountMessageConstant bankAccountMessageConstant() {
            return new BankAccountMessageConstant();
        }
    }
    @Autowired
    private MockMvc mvc;
    private final String SUCCESSFUL_PATTERN = "200";
    private final String FAILED_PATTERN = "500";
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
    @Test
    public void testWithdraw() throws Exception {
        for (Map.Entry<Integer, String> entry : withdrawTestCaseMap.entrySet()) {
          String input = "{\"accountNumber\":\"001\",\"ammount\":" + entry.getKey() + "}";
          try {
            if (entry.getValue().equals("500")) {
              mvc.perform(MockMvcRequestBuilders
                  .post("/bankAccount/withdraw")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(input))
                .andExpect(status().isInternalServerError());
            } else {
              mvc.perform(MockMvcRequestBuilders
                  .post("/bankAccount/withdraw")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(input))
                .andExpect(status().isOk());
            }
          } catch (Exception e) {
              System.out.println("DDDEBUG: " + entry.getValue().toString());
              if (entry.getValue().equals("500")) {
                  assert true;
              } else {
                  assert false;
              }
          }
        }
    }
    @Test
    public void testDeposit() throws Exception {
        for (Map.Entry<Integer, String> entry : depositTestCaseMap.entrySet()) {
          String input = "{\"accountNumber\":\"001\",\"ammount\":" + entry.getKey() + "}";
          try {
            if (entry.getValue().equals("500")) {
              mvc.perform(MockMvcRequestBuilders
                  .post("/bankAccount/deposit")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(input))
                .andExpect(status().isInternalServerError());
            } else {
              mvc.perform(MockMvcRequestBuilders
                  .post("/bankAccount/deposit")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(input))
                .andExpect(status().isOk());
            }
          } catch (Exception e) {
              System.out.println("DDDEBUG: " + entry.getValue().toString());
              if (entry.getValue().equals("500")) {
                  assert true;
              } else {
                  assert false;
              }
          }
        }
    }
}
