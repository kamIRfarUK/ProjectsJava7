package K_test;

import K_models.Transaction;
import K_models.account.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionsTests {

    Transaction transaction;

    @Before
    public void setup() {
        transaction = new Transaction(
                            Transaction.Type.WITHDRAW,
                            1720784851,
                            "6b8dd258-aba3-4b19-b238-45d15edd4b48",
                            624.99
        );
    }

    @Test
    public void correctDateTest(){
        assertEquals("12-07-2024", transaction.returnDate());
    }


    
}