import K_models.Transaction;
import K_models.account.Account;
import K_models.Bank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class K_Main {
    static String ACCOUNTS_FILE = "C:\\Users\\kamir faruk\\IdeaProjects\\Bookcamp\\src\\K_data\\accounts.txt";
    static String TRANSACTIONS_FILE = "C:\\Users\\kamir faruk\\IdeaProjects\\Bookcamp\\src\\K_data\\transactions.txt";

    static Bank bank = new Bank();

    public static void main(String[] args){

//        Chequing chequing = new Chequing("f84c43f4-a634-4c57-aa644-7602f8840870","Michael Scott",1524.51);
//        Savings savings = new Savings("ce07d7b3-9093-43db-83ae-77fd9c0450c9","Saul Goodman",2241.56);
//        Loan loan = new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5","Phoebe Buffay",2537.31);
//
//        System.out.println(chequing);
//        System.out.println(savings);
//        System.out.println(loan);


//        Chequing chequing = new Chequing("f84c43f4-a634-4c57-aa644-7602f8840870","Michael Scott",1524.51);
//        Account chequingCopy = chequing.clone();
//        Savings savings = new Savings("ce07d7b3-9093-43db-83ae-77fd9c0450c9","Saul Goodman",2241.56);
//        Account savingCopy = savings.clone();
//        Loan loan = new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5","Phoebe Buffay",2537.31);
//        Account loanCopy = loan.clone();



//        Account[] accounts = new Account[]{
//                new Chequing("f84c43f4-a634-4c57-aa644-7602f8840870","Michael Scott",1524.51),
//                new Savings("ce07d7b3-9093-43db-83ae-7602f8840870","Saul Goodman",2241.56)
//        };
//        for(Account account : accounts){
//            bank.addAccount(account);
//        }
//        Transaction[] transactions = new Transaction[]{
//                new Transaction(Transaction.Type.WITHDRAW,1546905600,"f84c43f4-a634-4c57-aa644-7602f8840870",1524.51),
//                new Transaction(Transaction.Type.DEPOSIT,1578700800,"f84c43f4-a634-4c57-aa644-7602f8840870",2240.51),
//                new Transaction(Transaction.Type.DEPOSIT,1547078400,"f84c43f4-a634-4c57-aa644-7602f8840870",1454.51),
//                new Transaction(Transaction.Type.WITHDRAW,1546732800,"f84c43f4-a634-4c57-aa644-7602f8840870",1214.51),
//                new Transaction(Transaction.Type.DEPOSIT,1578355200,"f84c43f4-a634-4c57-aa644-7602f8840870",1094.71),
//                new Transaction(Transaction.Type.WITHDRAW,1547078400,"ce07d7b3-9038-43db-83ae-7602f8840870",1004.51)
//        };
//        for(Transaction transaction: transactions){
//            bank.addTransaction(transaction);
//        }
//        Transaction[] filteredTransactions = bank.getTransaction("f84c43f4-a634-4c57-aa644-7602f8840870");
//        Account account = bank.getAccount("ce07d7b3-9093-43db-83ae-7602f8840870");


//        try {
//            Account account = createObject(new String[]{"Loan","6b8dd258-aba3-4b19-b238-45d15edd4b48","Teresa Lisbon","2609.77"});
//            System.out.println();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }


//        try{
//            ArrayList<Account> accounts = returnAccounts();
//            System.out.println();
//        }catch(FileNotFoundException e){
//            System.out.println(e.getMessage());
//        }

//        try{
//            ArrayList<Transaction> transactions = returnTransactions();
//            System.out.println();
//        }catch(FileNotFoundException e){
//            System.out.println(e.getMessage());
//        }

        try{
            ArrayList<Account> accounts = returnAccounts();
            loadAccounts(accounts);

            ArrayList<Transaction> transactions = returnTransactions();
            runTransactions(transactions);

            bank.deductTaxes();

            for(Account account : accounts){
                System.out.println("\n\t\t\t\t       ACCOUNT\n\n\t"+account+"\n\n");
                transactionHistory(account.getId());
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }


    }

    //Loan,6b8dd258-aba3-4b19-b238-45d15edd4b48,Teresa Lisbon,1059.35
    public static Account createObject(String[] values) {

//            switch(values[0]){
//                case "Chequing" : return new Chequing(values[1],values[2],Double.parseDouble(values[3]));
//                case "Saving" : return new Savings(values[1],values[2],Double.parseDouble(values[3]));
//                case "Loan" : return new Loan(values[1],values[2],Double.parseDouble(values[3]));
//            }

        try {
            return (Account)Class.forName("K_models.account."+ values[0])
                    .getConstructor(String.class, String.class, double.class)
                    .newInstance(values[1],values[2],Double.parseDouble(values[3]));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static void loadAccounts(ArrayList<Account> accounts){
        for(Account account : accounts){
            bank.addAccount(account);
        }
    }

    public static ArrayList<Account> returnAccounts() throws FileNotFoundException{
        FileInputStream fil = new FileInputStream(ACCOUNTS_FILE);
        Scanner scan = new Scanner(fil);

        ArrayList<Account> accounts = new ArrayList<Account>();

        while(scan.hasNextLine()){
            accounts.add(createObject(scan.nextLine().split(",")));
        }
        scan.close();
        return accounts;
    }

    public static ArrayList<Transaction> returnTransactions() throws FileNotFoundException{
        FileInputStream fil = new FileInputStream(TRANSACTIONS_FILE);
        Scanner scan = new Scanner(fil);

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        while(scan.hasNextLine()){
            String[] values = scan.nextLine().split(",");
            transactions.add(new Transaction(Transaction.Type.valueOf(values[1]),Long.parseLong(values[0]),values[2],Double.parseDouble(values[3]) ));
        }
        scan.close();
        Collections.sort(transactions);
        return transactions;
    }

    public static void runTransactions(ArrayList<Transaction> transactions){
        for(Transaction transaction: transactions){
            bank.executeTransaction(transaction);
        }
    }


    public static void transactionHistory(String id){
        System.out.println("\t\t\t\t       Transaction History   \n\t");
        for(Transaction transaction : bank.getTransactions(id)){
            //wait(100);
            System.out.println("\t"+transaction+"\n");
        }
        System.out.println("\n\t\t\t\t       AFTER TRANSACTION \n");
        System.out.println("\t"+bank.getAccount(id)+"\n\n\n\n");

    }



}
