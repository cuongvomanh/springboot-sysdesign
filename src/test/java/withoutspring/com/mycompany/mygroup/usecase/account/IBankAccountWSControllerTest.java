package withoutspring.com.mycompany.mygroup.usecase.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.mygroup.core.commons.BankAccountMessageConstant;
import com.mycompany.mygroup.core.entity.BankAccount;
import com.mycompany.mygroup.core.exceptions.BankAccountBadRequestException;
import com.mycompany.mygroup.core.gateway.BankAccountGateway;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountPresentBoundary;
import com.mycompany.mygroup.core.usecase.account.interactor.BankAccountInteractor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IBankAccountWSControllerTest {

    @Mock
    private BankAccountMessageConstant bankAccountMessageConstant;

    @Mock
    private BankAccountGateway bankAccountGateway;

    @Mock
    private BankAccountPresentBoundary bankAccountPresentBoundary;

    @Mock
    private BankAccount bankAccount;

    @InjectMocks
    BankAccountInteractor bankAccountBoundary;

    @Test
    public void withdraw_IfWithdrawBankAccountSuccessful_ReturnResponseSuccessful() {
        when(bankAccountMessageConstant.getWithdrawSuccessful()).thenReturn("Withdraw Successful!");
        when(bankAccountGateway.getByNumber(Mockito.any())).thenReturn(bankAccount);
        when(bankAccount.withdraw(Mockito.any())).thenReturn(true);
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            ResponseModel responseModel = bankAccountBoundary.withdraw(requestModel);
            assertEquals(responseModel.getResult(), bankAccountMessageConstant.getWithdrawSuccessful());
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void withdraw_IfWithdrawBankAccountFailed_ReturnResponseFailed() {
        when(bankAccountMessageConstant.getWithdrawBadRequest()).thenReturn("Withdraw Bad Request!");
        when(bankAccountGateway.getByNumber(Mockito.any())).thenReturn(bankAccount);
        when(bankAccount.withdraw(Mockito.any())).thenReturn(false);
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            bankAccountBoundary.withdraw(requestModel);
        } catch (BankAccountBadRequestException bankAccountBadRequestException){
            assertEquals(bankAccountBadRequestException.getMessage(), bankAccountMessageConstant.getWithdrawBadRequest());
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void withdraw_IfGatewaySaveFailed_ReturnResponseFailed() {
        when(bankAccountGateway.getByNumber(Mockito.any())).thenReturn(bankAccount);
        when(bankAccount.withdraw(Mockito.any())).thenReturn(true);
        when(bankAccountGateway.save(Mockito.any())).thenThrow(RuntimeException.class);
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            assertThrows(RuntimeException.class,() -> bankAccountBoundary.withdraw(requestModel));
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void withdraw_IfGatewayGetByNumberFailed_ReturnResponseFailed() {
        when(bankAccountMessageConstant.getWithdrawBadRequestNotFound()).thenReturn("Withdraw Bad Request Not Found!");
        when(bankAccountGateway.getByNumber(Mockito.any())).thenReturn(null);
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            bankAccountBoundary.withdraw(requestModel);
        } catch (BankAccountBadRequestException bankAccountBadRequestException){
            assertEquals(bankAccountBadRequestException.getMessage(), bankAccountMessageConstant.getWithdrawBadRequestNotFound());
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void deposit_IfDepositBankAccountSuccessful_ReturnResponseSuccessful() {
        when(bankAccountMessageConstant.getDepositSuccessful()).thenReturn("Deposit Successful!");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumber("001");
        bankAccount.setBalance(new BigDecimal(100));
        when(bankAccountGateway.getByNumber(Mockito.any())).thenReturn(bankAccount);
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            bankAccountBoundary.deposit(requestModel);
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void deposit_IfDepositBankAccountFailed_ReturnResponseFailed() {
        when(bankAccountMessageConstant.getDepositBadRequest()).thenReturn("Deposit Bad Request!");
        when(bankAccountGateway.getByNumber(Mockito.any())).thenReturn(bankAccount);
        when(bankAccount.deposit(Mockito.any())).thenReturn(false);
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            bankAccountBoundary.deposit(requestModel);
        } catch (BankAccountBadRequestException bankAccountBadRequestException){
            assertEquals(bankAccountBadRequestException.getMessage(), bankAccountMessageConstant.getDepositBadRequest());
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void deposit_IfGatewaySaveFailed_ReturnResponseFailed() {
        when(bankAccountGateway.getByNumber(Mockito.any())).thenReturn(bankAccount);
        when(bankAccount.deposit(Mockito.any())).thenReturn(true);
        when(bankAccountGateway.save(Mockito.any())).thenThrow(RuntimeException.class);
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            assertThrows(RuntimeException.class,() -> bankAccountBoundary.deposit(requestModel));
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void deposit_IfGatewayGetByNumberFailed_ReturnResponseFailed() {
        when(bankAccountMessageConstant.getDepositRequestNotFound()).thenReturn("Deposit Bad Request Not Found!");
        when(bankAccountGateway.getByNumber(Mockito.any())).thenReturn(null);
        String input = "{\"accountNumber\":\"001\",\"ammount\":1}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestModel requestModel = objectMapper.readValue(input, RequestModel.class);
            bankAccountBoundary.deposit(requestModel);
        } catch (BankAccountBadRequestException bankAccountBadRequestException){
            assertEquals(bankAccountBadRequestException.getMessage(), bankAccountMessageConstant.getDepositRequestNotFound());
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

}
