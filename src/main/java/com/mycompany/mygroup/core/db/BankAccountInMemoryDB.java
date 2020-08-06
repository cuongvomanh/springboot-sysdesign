package com.mycompany.mygroup.core.db;

import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BankAccountInMemoryDB implements BankAccountGateway {
    private static Map accountDB = new HashMap();

    static {
        {
            BankAccount account = new BankAccount();
            account.setId(1L);
            account.setNumber("001");
            account.setBalance(BigDecimal.valueOf(100000));
            accountDB.put(1L, account);
        }
        {
            BankAccount account = new BankAccount();
            account.setId(2L);
            account.setNumber("002");
            account.setBalance(BigDecimal.valueOf(500000));
            accountDB.put(2L, account);
        }
        {
            BankAccount account = new BankAccount();
            account.setId(3L);
            account.setNumber("003");
            account.setBalance(BigDecimal.valueOf(700000));
            accountDB.put(3L, account);
        }

    }

    public BankAccountInMemoryDB() {
    }

    public BankAccount getByNumber(String number) {
        Iterator iterator = accountDB.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            BankAccount account = (BankAccount) entry.getValue();
            if (account.getNumber().equals(number)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Long save(BankAccount entity) {
        try {
            System.out.println("Save BankAccount");
            accountDB.put(entity.getId(), entity);
            return 1L;
        } catch (Exception e) {
            return 0L;
        }
    }


    public BankAccount findOne(Long id) {
        return null;
    }


}
