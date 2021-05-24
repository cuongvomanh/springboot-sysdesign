package withoutspring.com.mycompany.mygroup.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.mygroup.core.commons.BankAccountMessageConstant;
import com.mycompany.mygroup.core.exceptions.BankAccountBadRequestException;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;
import com.mycompany.mygroup.core.web.BankAccountWSController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IBankAccountWSControllerTest {

    @Mock
    private BankAccountMessageConstant bankAccountMessageConstant;

    @Mock
    private BankAccountBoundary bankAccountBoundary;

    @InjectMocks
    private BankAccountWSController bankAccountWSController;

    @Test
    public void withdraw_IfWithdrawBankAccountBoundarySuccessful_ReturnResponseSuccessful() {
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        when(bankAccountMessageConstant.getWithdrawSuccessful()).thenReturn("Withdraw Successful!");
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            ResponseModel responseModelForBankAccountBoundary = new ResponseModel(bankAccountMessageConstant.getWithdrawSuccessful());
            when(bankAccountBoundary.withdraw(Mockito.isA(RequestModel.class))).thenReturn(responseModelForBankAccountBoundary);
            ResponseEntity<ResponseModel> responseEntity = bankAccountWSController.withdraw(requestModel);
            assertEquals(responseEntity.getBody().getResult(), bankAccountMessageConstant.getWithdrawSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void withdraw_IfWithdrawBankAccountBoundaryFailed_ReturnResponseFailed() {
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        when(bankAccountMessageConstant.getWithdrawFailed()).thenReturn("Withdraw Failure!");
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            BankAccountBadRequestException bankAccountBadRequestException = new BankAccountBadRequestException(this.bankAccountMessageConstant.getWithdrawFailed());
            when(bankAccountBoundary.withdraw(Mockito.isA(RequestModel.class))).thenThrow(bankAccountBadRequestException);
            ResponseEntity<ResponseModel> responseEntity = bankAccountWSController.withdraw(requestModel);
            assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
            assertEquals(responseEntity.getBody().getResult(), bankAccountMessageConstant.getWithdrawFailed());
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void withdraw_IfWithdrawBankAccountBoundaryException_ReturnResponseInternalError() {
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            RuntimeException exception = new RuntimeException();
            when(bankAccountBoundary.withdraw(Mockito.isA(RequestModel.class))).thenThrow(exception);
            ResponseEntity<ResponseModel> responseEntity = bankAccountWSController.withdraw(requestModel);
            assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void deposit_IfDepositBankAccountBoundarySuccessful_ReturnResponseSuccessful() {
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        when(bankAccountMessageConstant.getDepositSuccessful()).thenReturn("Deposit Successful!");
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            ResponseModel responseModelForBankAccountBoundary = new ResponseModel(bankAccountMessageConstant.getDepositSuccessful());
            when(bankAccountBoundary.deposit(Mockito.isA(RequestModel.class))).thenReturn(responseModelForBankAccountBoundary);
            ResponseEntity<ResponseModel> responseEntity = bankAccountWSController.deposit(requestModel);
            assertEquals(responseEntity.getBody().getResult(), bankAccountMessageConstant.getDepositSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void deposit_IfDepositBankAccountBoundaryFailed_ReturnResponseFailed() {
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        when(bankAccountMessageConstant.getDepositFailed()).thenReturn("Withdraw Failure!");
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            BankAccountBadRequestException bankAccountBadRequestException = new BankAccountBadRequestException(this.bankAccountMessageConstant.getDepositFailed());
            when(bankAccountBoundary.deposit(Mockito.isA(RequestModel.class))).thenThrow(bankAccountBadRequestException);
            ResponseEntity<ResponseModel> responseEntity = bankAccountWSController.deposit(requestModel);
            assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
            assertEquals(responseEntity.getBody().getResult(), bankAccountMessageConstant.getDepositFailed());
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void withdraw_IfDepositBankAccountBoundaryException_ReturnResponseInternalError() {
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            RuntimeException exception = new RuntimeException();
            when(bankAccountBoundary.deposit(Mockito.isA(RequestModel.class))).thenThrow(exception);
            ResponseEntity<ResponseModel> responseEntity = bankAccountWSController.deposit(requestModel);
            assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

}
