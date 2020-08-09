package com.mycompany.mygroup.core.exceptions;
public class BankAccountException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
  public BankAccountException(String message, Throwable throwable) {
      super(message, throwable);
  }
  public BankAccountException(String message) {
      super(message);
  }
}
