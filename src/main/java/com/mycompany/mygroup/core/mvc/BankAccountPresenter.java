package com.mycompany.mygroup.core.mvc;

import com.mycompany.mygroup.core.usecase.account.BankAccountPresentBoundary;
import org.springframework.stereotype.Component;

@Component
public class BankAccountPresenter implements BankAccountPresentBoundary {

    @Override
    public void accept() {
        System.out.println("BankAccountPresenter accept!");
    }
}
