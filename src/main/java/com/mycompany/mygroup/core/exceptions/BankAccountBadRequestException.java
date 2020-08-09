package com.mycompany.mygroup.core.exceptions;
public class BankAccountBadRequestException extends BankAccountException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public BankAccountBadRequestException(String message, Throwable throwable) {
		super(message, throwable);
	}
	public BankAccountBadRequestException(String message) {
		super(message);
	}


}
