package com.mycompany.mygroup.core.gateway;

import com.mycompany.mygroup.core.db.BankAccountInMemoryDB;
import com.mycompany.mygroup.core.entity.BankAccount;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.InstanceNotFoundException;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
public class BankAccountGatewayTest extends TestCase {
    @TestConfiguration
    public static class BankAccountGatewayTestConfiguration {
        @Bean
        BankAccountGateway bankAccountGateway() {
            return new BankAccountInMemoryDB();
        }
    }
    @Autowired
    private BankAccountGateway bankAccountGateway;
    private BankAccount bankAccount;
    public BankAccountGatewayTest() throws InstanceNotFoundException {
    }

    private void createBankAccount() {
        bankAccount = new BankAccount();
        bankAccount.setNumber("001");
        bankAccount.setBalance(new BigDecimal(100));
    }

    @Test
    public void testGetByNumber() {
        // Setup
        createBankAccount();
        // Run
        bankAccountGateway.save(bankAccount);
        BankAccount bankAccountResult = bankAccountGateway.getByNumber("001");
        //Assert
        assertEquals(bankAccountResult.getNumber(), "001");
    }

    @Test
    public void testSave() {
        // Setup
        createBankAccount();
        // Run
        bankAccountGateway.save(bankAccount);
        BankAccount bankAccountResult = bankAccountGateway.getByNumber("001");
        //Assert
        assertEquals(bankAccountResult.getNumber(), "001");
        assertEquals(bankAccountResult.getBalance(), new BigDecimal(100));
    }
}
