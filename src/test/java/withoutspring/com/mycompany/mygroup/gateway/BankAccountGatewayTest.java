//package withoutspring.com.mycompany.mygroup.gateway;
//
//import com.mycompany.mygroup.core.db.BankAccountInMemoryDB;
//import com.mycompany.mygroup.core.entity.BankAccount;
//import com.mycompany.mygroup.core.gateway.BankAccountGateway;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//import java.math.BigDecimal;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//@ExtendWith(MockitoExtension.class)
//public class BankAccountGatewayTest {
//
//    private BankAccount bankAccount;
//
//    @Mock
//    private Map accountDB;
//
//    @InjectMocks
//    private BankAccountInMemoryDB bankAccountGateway;
//
//    private void createBankAccount() {
//        bankAccount = new BankAccount();
//        bankAccount.setNumber("001");
//        bankAccount.setBalance(new BigDecimal(100));
//    }
//
//    @Test
//    public void testGetByNumber() {
//        accountDB.remove(bankAccount.getNumber());
//        BankAccount bankAccount1 = bankAccountGateway.getByNumber(bankAccount.getNumber());
//        assertNotNull(bankAccount1);
//    }
//
//    @Test
//    public void testSave() {
//        // Setup
//        createBankAccount();
//        // Run
//        bankAccountGateway.save(bankAccount);
//        BankAccount bankAccountResult = bankAccountGateway.getByNumber("001");
//        //Assert
//        assertEquals(bankAccountResult.getNumber(), "001");
//        assertEquals(bankAccountResult.getBalance(), new BigDecimal(100));
//    }
//}
