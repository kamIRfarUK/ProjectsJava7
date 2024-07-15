package K_test;

import K_models.account.Account;
import K_models.account.Chequing;
import K_models.account.Loan;
import K_models.account.Savings;
import K_models.account.implement.Taxable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTests {

    Account[] accounts;

    @Before
    public void setup(){
        accounts = new Account[]{
                new Chequing("f84c43f4-a634-4c57-aa644-7602f8840870","Michael Scott",1524.51),
                new Savings("ce07d7b3-9093-43db-83ae-77fd9c0450c9","Saul Goodman",2241.56),
                new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5","Phoebe Buffay",2537.31)
        };
    }

    @Test
    public void withdrawl(){
        accounts[0].withdrawl(1440);
        assertEquals(84.51,accounts[0].getBalance(),0.0);
    }

    @Test
    public void overdraft(){
        accounts[0].withdrawl(1534.43);
        assertEquals(-15.42,accounts[0].getBalance(),0.0);
    }

    @Test
    public void overdraftLimit(){
        accounts[0].withdrawl(1726);
        assertEquals(1524.51,accounts[0].getBalance(),0.0);
    }

    @Test
    public void withdrawlFee(){
        accounts[1].withdrawl(100);
        assertEquals(2136.56,accounts[1].getBalance(),0.0);
    }

    @Test
    public void withdrawlInterest(){
        accounts[2].withdrawl(2434.31);
        assertEquals(5020.31,accounts[2].getBalance(),0.0);
    }

    @Test
    public void withdrawlimit(){
        accounts[2].withdrawl(7463.69);
        assertEquals(2537.31,accounts[2].getBalance(),0.0);
    }

    @Test
    public void deposit(){
        accounts[0].deposit(5000);
        assertEquals(6524.51,accounts[0].getBalance(),0.0);
    }

    @Test
    public void loanDeposit(){
        accounts[2].deposit(1000);
        assertEquals(1524.51,accounts[0].getBalance(),0.0);
    }

    @Test
    public void incomeTax(){
        double income = 4000;
        accounts[0].deposit(income);

        ((Taxable)accounts[0]).tax(income);
        assertEquals(5374.51,accounts[0].getBalance(),0.0);
    }
}
