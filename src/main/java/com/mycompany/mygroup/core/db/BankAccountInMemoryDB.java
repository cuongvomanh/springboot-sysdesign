package com.mycompany.mygroup.core.db;

import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Repository
@Primary
public class BankAccountInMemoryDB implements BankAccountGateway {
    private Map accountDB;

    @PostConstruct
    private void init() {
        accountDB = new HashMap();
        BankAccount account1 = new BankAccount();
        account1.setId(1L);
        account1.setNumber("001");
        account1.setBalance(BigDecimal.valueOf(100000));
        accountDB.put(1L, account1);
        BankAccount account2 = new BankAccount();
        account2.setId(2L);
        account2.setNumber("002");
        account2.setBalance(BigDecimal.valueOf(500000));
        accountDB.put(2L, account2);
        BankAccount account3 = new BankAccount();
        account3.setId(3L);
        account3.setNumber("003");
        account3.setBalance(BigDecimal.valueOf(700000));
        accountDB.put(3L, account3);
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
    public BankAccount save(BankAccount entity) {
        System.out.println("Save BankAccount");
        try {
            accountDB.put(entity.getId(), entity);
            return entity;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }


    public BankAccount findOne(Long id) {
        return null;
    }


}
