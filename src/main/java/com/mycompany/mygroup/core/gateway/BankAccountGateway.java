package com.mycompany.mygroup.core.gateway;

import com.mycompany.mygroup.core.entity.BankAccount;

public interface BankAccountGateway extends EntityGateway<BankAccount> {
    public BankAccount getByNumber(String number);
    public Long save(BankAccount entity);
}
