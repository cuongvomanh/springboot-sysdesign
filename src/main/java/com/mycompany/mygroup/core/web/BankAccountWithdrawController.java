package com.mycompany.mygroup.core.web;

import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;
import com.mycompany.mygroup.core.web.utils.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InstanceNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/bankAccount/withdraw")
public class BankAccountWithdrawController implements IBankAccountController {
    @Autowired
    private BankAccountBoundary bankAccountBoundary;

    public BankAccountWithdrawController(BankAccountBoundary bankAccountBoundary) throws InstanceNotFoundException {
        this.bankAccountBoundary = bankAccountBoundary;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> doPost(@RequestBody RequestModel requestModel) throws ServletException, IOException {
        ResponseModel responseModel = this.bankAccountBoundary.withdraw(requestModel);
        return ResponseEntity.ok()
            .body(responseModel);
    }
}
