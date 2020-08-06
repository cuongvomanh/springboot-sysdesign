package com.mycompany.mygroup.core.entity;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BankAccountTest extends TestCase {
    private BankAccount bankAccount = new BankAccount();
    private final HashMap<Integer, Integer> withdrawTestCaseMap = new HashMap<Integer, Integer>() {{
        put(10, 90);
        put(-10, 100);
        put(-200, 100);
        put(200, 100);
    }};
    private final HashMap<Integer, Integer> depositTestCaseMap = new HashMap<Integer, Integer>() {{
        put(10, 110);
        put(-10, 100);
        put(-200, 100);
        put(200, 300);
    }};

    private void createBankAccount() {
        bankAccount.setNumber("001");
        bankAccount.setBalance(new BigDecimal(100));
    }

    public void testGetNumber() {
        for (Map.Entry<Integer, Integer> entry : withdrawTestCaseMap.entrySet()) {
            createBankAccount();
            bankAccount.withdraw(new BigDecimal(entry.getKey()));
            assertEquals(bankAccount.getBalance(), new BigDecimal(entry.getValue()));
        }
    }

    public void testDeposit() {
        for (Map.Entry<Integer, Integer> entry : depositTestCaseMap.entrySet()) {
            createBankAccount();
            bankAccount.deposit(new BigDecimal(entry.getKey()));
            assertEquals(bankAccount.getBalance(), new BigDecimal(entry.getValue()));
        }
    }
}
