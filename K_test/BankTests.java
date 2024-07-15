package K_test;

import K_models.Bank;
import K_models.Transaction;
import K_models.account.Chequing;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTests {
    Bank bank;

    @Before
    public void setup(){
        bank= new Bank();
        bank.addAccount(new Chequing("f84c43f4-a634-4c57-aa644-7602f8840870","Michael Scott",1524.51));
    }

    @Test
    public void successfulTransactions(){
        this.bank.executeTransaction(new Transaction(Transaction.Type.WITHDRAW,1546905600,"f84c43f4-a634-4c57-aa644-7602f8840870",1524.51));
        this.bank.executeTransaction(new Transaction(Transaction.Type.DEPOSIT,1578700800,"f84c43f4-a634-4c57-aa644-7602f8840870",2240.51));
        assertEquals(2,bank.getTransactions("f84c43f4-a634-4c57-aa644-7602f8840870").length);
    }

    @Test
    public void failedTransaction(){
        this.bank.executeTransaction(new Transaction(Transaction.Type.WITHDRAW,1546905600,"f84c43f4-a634-4c57-aa644-7602f8840870",10000000.51));
        assertEquals(0,bank.getTransactions("f84c43f4-a634-4c57-aa644-7602f8840870").length);
    }

    @Test
    public void taxDeduction(){
        this.bank.executeTransaction(new Transaction(Transaction.Type.DEPOSIT,1546905600,"f84c43f4-a634-4c57-aa644-7602f8840870",4000));
        this.bank.deductTaxes();
        assertEquals(5374.51,bank.getAccount("f84c43f4-a634-4c57-aa644-7602f8840870").getBalance(),0.0);

    }

}
