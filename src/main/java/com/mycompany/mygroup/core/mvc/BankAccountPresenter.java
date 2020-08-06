package com.mycompany.mygroup.core.mvc;

import com.mycompany.mygroup.core.usecase.account.BankAccountPresentBoundary;

public class BankAccountPresenter implements BankAccountPresentBoundary {

    @Override
    public void accept() {
        System.out.println("BankAccountPresenter accept!");
    }
}
