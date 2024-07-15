package K_models.account;

public class Loan extends Account{
//A loan account contains an id, name, and balance.
//A loan account allows users to deposit or withdraw an amount of money.
//A withdrawal can't made if the debt exceeds $10,000.
//Every withdrawal is charged a fixed interest rate of 2%.

    private static final double INTEREST_RATE = 0.02;
    private static final double MAX_DEBT = 10000;

    public Loan(String id,String name,double balance){

        super(id, name ,balance);
    }

    public Loan(Loan source){

        super(source);
    }

    @Override
    public Account clone() {
        return new Loan(this);
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(super.round(super.getBalance()-amount));
    }

    @Override
    public boolean withdrawl(double amount) {
        if(super.getBalance()+amount > MAX_DEBT){
            return false;
        }
        super.setBalance(super.round(super.getBalance()+amount+(amount*INTEREST_RATE)));
        return true;
    }

}
