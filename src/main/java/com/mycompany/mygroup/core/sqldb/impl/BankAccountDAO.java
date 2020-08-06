package com.mycompany.mygroup.core.sqldb.impl;

import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.gateway.EntityGateway;
import com.mycompany.mygroup.core.mapper.BankAccountMapper;
import com.mycompany.mygroup.core.sqldb.GenericDAO;

import java.util.List;

public class BankAccountDAO extends AbstractDAO<BankAccount> implements BankAccountGateway, GenericDAO<BankAccount> {
    @Override
    public BankAccount getByNumber(String number) {
        return findOne(number);
    }

    @Override
	public Long save(BankAccount bankAccount) {
		StringBuilder sql = new StringBuilder("INSERT INTO bank_account (nnumber, balance)");
		sql.append(" VALUES(?, ?)");
		return insert(sql.toString(), bankAccount.getNumber(), bankAccount.getBalance().intValue());
	}

	@Override
	public BankAccount findOne(Long id) {
		return null;
	}

	public BankAccount findOne(String id) {
		String sql = "SELECT * FROM bank_account WHERE nnumber = ?";
		List<BankAccount> bankAccounts = query(sql, new BankAccountMapper(), id);
		return bankAccounts.isEmpty() ? null : bankAccounts.get(0);
	}
}
