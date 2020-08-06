package com.mycompany.mygroup.core.mapper;

import com.mycompany.mygroup.core.entity.BankAccount;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BankAccountMapper implements RowMapper<BankAccount> {

	@Override
	public BankAccount mapRow(ResultSet resultSet) {
		try {
			BankAccount bankAccount = new BankAccount();
			bankAccount.setNumber(resultSet.getString("nnumber"));
			bankAccount.setBalance(resultSet.getBigDecimal("balance"));
			return bankAccount;
		} catch (SQLException e) {
			return null;
		}	
	}
}
