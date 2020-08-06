package com.mycompany.mygroup.core.gateway;

import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.infradi.DependencyResolver;
import junit.framework.TestCase;

import javax.management.InstanceNotFoundException;
import java.math.BigDecimal;

public class BankAccountGatewayTest extends TestCase {
    private BankAccountGateway bankAccountGateway = (BankAccountGateway) DependencyResolver.getInstance("BankAccountGateway");
    private BankAccount bankAccount;
    public BankAccountGatewayTest() throws InstanceNotFoundException {
    }

    private void createBankAccount() {
        bankAccount = new BankAccount();
        bankAccount.setNumber("001");
        bankAccount.setBalance(new BigDecimal(100));
    }

    public void testGetByNumber() {
        // Setup
        createBankAccount();
        // Run
        bankAccountGateway.save(bankAccount);
        BankAccount bankAccountResult = bankAccountGateway.getByNumber("001");
        //Assert
        assertEquals(bankAccountResult.getNumber(), "001");
    }

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
