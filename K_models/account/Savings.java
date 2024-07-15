package K_models.account;

public class Savings extends Account{

//A savings account contains an id, name, and balance.
//A savings account allows users to deposit or withdraw an amount Of money
//Deposits are free, but the savings account charges a $5.00 fee for every
    private static final double WITHDRAWL_FEE = 5.00;

    public Savings(String id,String name,double balance){

        super( id, name,balance );
    }

    public Savings(Savings source){

        super(source);
    }
    @Override
    public Account clone() {
        return new Savings(this);
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(super.round(super.getBalance() +amount));
    }

    @Override
    public boolean withdrawl(double amount) {
        super.setBalance(super.round(super.getBalance()-amount-WITHDRAWL_FEE));
        return true;
    }
}
