package com.mycompany.mygroup.core.sqldb.impl;

import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.gateway.EntityGateway;
import com.mycompany.mygroup.core.mapper.BankAccountMapper;
import com.mycompany.mygroup.core.sqldb.GenericDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankAccountDAO extends AbstractDAO<BankAccount> implements BankAccountGateway, GenericDAO<BankAccount> {
    @Override
    public BankAccount getByNumber(String number) {
        return findOne(number);
    }

    @Override
	public BankAccount save(BankAccount bankAccount) {
		StringBuilder sql = new StringBuilder("INSERT INTO bank_account (nnumber, balance)");
		sql.append(" VALUES(?, ?)");
		insert(sql.toString(), bankAccount.getNumber(), bankAccount.getBalance().intValue());
		return bankAccount;
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
