package K_models.account;

import java.text.DecimalFormat;

public abstract class Account {
    private String id;
    private String name;
    private double balance;

    public Account( String id, String name,double balance) {
        if(id == null || id.isBlank() || name == null || name.isBlank() ){
            throw new IllegalArgumentException("Invalid paramater.");
        }
        this.balance = balance;
        this.id = id;
        this.name = name;
    }

    public Account(Account source) {
        this.balance = source.balance;
        this.id = source.id;
        this.name = source.name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id == null || id.isBlank() ){
            throw new IllegalArgumentException("Invalid ID.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank() ){
            throw new IllegalArgumentException("Invalid Name.");
        }
        this.name = name;
    }

    public double getBalance() {

        return balance;
    }

    public void setBalance(double balance) {

        this.balance = balance;
    }
    public abstract void deposit(double amount);
    public abstract boolean withdrawl(double amount);
    public abstract Account clone();

    protected double round(double amount){
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.parseDouble(formatter.format(amount));
    }

    public String toString() {
        return (this.getClass().getSimpleName()) +
                "\t" + this.getId() + "" +
                "\t" + this.getName() + "" +
                "\t$" + this.getBalance() + "";
    }
}
