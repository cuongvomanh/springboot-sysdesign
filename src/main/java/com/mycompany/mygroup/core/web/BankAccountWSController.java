package com.mycompany.mygroup.core.web;

import java.io.IOException;

import javax.management.InstanceNotFoundException;
import javax.servlet.ServletException;

import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountWSController implements IBankAccountWSController {
    @Autowired
    private BankAccountBoundary bankAccountBoundary;

    public BankAccountWSController(BankAccountBoundary bankAccountBoundary) throws InstanceNotFoundException {
        this.bankAccountBoundary = bankAccountBoundary;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ResponseModel> withdraw(@RequestBody RequestModel requestModel) throws ServletException, IOException {
        ResponseModel responseModel = this.bankAccountBoundary.withdraw(requestModel);
        return ResponseEntity.ok()
            .body(responseModel);
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseModel> deposit(@RequestBody RequestModel requestModel) throws ServletException, IOException {
        ResponseModel responseModel = this.bankAccountBoundary.deposit(requestModel);
        return ResponseEntity.ok()
            .body(responseModel);
    }
}
