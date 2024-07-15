package K_models;

import K_models.account.Account;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import K_models.account.Chequing;
import K_models.account.implement.Taxable;

public class Bank {

//The bank keeps a record of every account created and transaction made.
//The id of a transaction matches the id of an account.
//Depending on the account, some transactions may be denied.
//The bank can deduct taxes from taxable accounts.


    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public Bank(){
        this.accounts = new ArrayList<Account>();
        this.transactions= new ArrayList<Transaction>();
    }

    public Transaction[] getTransactions(String accoountId){
        List<Transaction> list = this.transactions.stream()
                .filter((transaction -> transaction.getId().equals(accoountId)))
                .collect(Collectors.toList());

        return list.toArray(new Transaction[list.size()]);
    }

    public Account getAccount(String transactionId){
        return accounts.stream()
                .filter((account -> account.getId().equals(transactionId)))
                .findFirst()
                .orElse(null);
    }

    public void addAccount(Account account){
        this.accounts.add(account.clone());
    }

    private void addTransaction(Transaction transaction){
        this.transactions.add(new Transaction(transaction));
    }

    public void executeTransaction(Transaction transaction){
        switch(transaction.getType()){
            case WITHDRAW: withdrawlTransaction(transaction);
                break;
            case DEPOSIT: depositTransaction(transaction);
                break;
        }
    }

    private void withdrawlTransaction(Transaction transaction){
       if( getAccount(transaction.getId()).withdrawl(transaction.getAmount()) ){
           addTransaction(transaction);
       }
    }

    private void depositTransaction(Transaction transaction){
        getAccount(transaction.getId()).deposit(transaction.getAmount());
        addTransaction(transaction);
    }

    private double getIncome(Taxable account){
        Transaction[] transactions = getTransactions(((Chequing)account).getId());
        return Arrays.stream(transactions)
                .mapToDouble((transaction) -> {
                    switch(transaction.getType()){
                        case WITHDRAW: return -transaction.getAmount();
                        case DEPOSIT: return transaction.getAmount();
                        default: return 0;
                    }
                }).sum();

    }



    public void deductTaxes(){
        for (Account account : accounts){

            if(Taxable.class.isAssignableFrom(account.getClass())){
                Taxable taxable = (Taxable)account;
                taxable.tax(getIncome(taxable));
            }

        }
    }
}
