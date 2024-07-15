package K_models.account;

import K_models.account.implement.Taxable;

public class Chequing extends Account implements Taxable {

//A chequing account contains an id, name, and balance.
//A chequing account allows users to deposit or withdraw an amount Of
//The chequing account charges an overdraft fee of $5.50 if the amount
//                          being withdrawn exceed the account balance.
//The overdraft limit is $200.00 dollars.
//A chequing account is taxable. Income that exceeds $3,000 is taxed 15%.


    private static final double OVERDRAFT_FEE = 5.50;
    private static final double OVERDRAFT_LIMIT = -200;
    private static final double TAXABLE_INCOME = 3000;
    private static final double TAX_RATE = 0.15;

    public Chequing(String id,String name,double balance){
        super(id, name,balance );
    }

    public Chequing(Chequing source){
        super(source);
    }

    @Override
    public Account clone() {
        return new Chequing(this);
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(super.round(super.getBalance() +amount));
    }

    @Override
    public boolean withdrawl(double amount) {
        if(super.getBalance() - amount < OVERDRAFT_LIMIT){
             return false;
        } else if (super.getBalance() - amount < 0) {
            super.setBalance(super.round(super.getBalance() - amount - OVERDRAFT_FEE));
        }else {
            super.setBalance(super.round(super.getBalance() - amount));
        }
        return true;
    }

    @Override
    public void tax(double income) {
        double tax = Math.max(0,income - TAXABLE_INCOME)*TAX_RATE;
        super.setBalance(super.round(super.getBalance()-tax));
    }
}
