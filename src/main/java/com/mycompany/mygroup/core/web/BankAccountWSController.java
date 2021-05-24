package com.mycompany.mygroup.core.web;

import com.mycompany.mygroup.core.commons.BankAccountMessageConstant;
import com.mycompany.mygroup.core.exceptions.BankAccountBadRequestException;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountWSController implements IBankAccountWSController {

    private static Logger LOGGER = LoggerFactory.getLogger(IBankAccountWSController.class);

    @Autowired
    private BankAccountMessageConstant bankAccountMessageConstant;

    @Autowired
    private BankAccountBoundary bankAccountBoundary;

    public BankAccountWSController(){
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ResponseModel> withdraw(@RequestBody RequestModel requestModel) throws ServletException, IOException {
        try {
            ResponseModel responseModel = this.bankAccountBoundary.withdraw(requestModel);
            return ResponseEntity.ok()
                    .body(responseModel);
        } catch (BankAccountBadRequestException bankAccountBadRequestException){
            LOGGER.error("Exception", bankAccountBadRequestException);
            return ResponseEntity.badRequest().body(new ResponseModel(bankAccountMessageConstant.getWithdrawFailed()));
        } catch (Exception exception){
            LOGGER.error("Exception", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseModel> deposit(@RequestBody RequestModel requestModel) throws ServletException, IOException {
        try {
            ResponseModel responseModel = this.bankAccountBoundary.deposit(requestModel);
            return ResponseEntity.ok()
                    .body(responseModel);
        } catch (BankAccountBadRequestException bankAccountBadRequestException) {
            LOGGER.error("Exception", bankAccountBadRequestException);
            return ResponseEntity.badRequest().body(new ResponseModel(bankAccountMessageConstant.getDepositFailed()));
        } catch (Exception exception){
            LOGGER.error("Exception", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
