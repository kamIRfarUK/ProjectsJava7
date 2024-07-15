package K_models;

import J_models.Team;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Transaction implements Comparable<Transaction> {
//An account transaction can be: WITHDRAW or DEPOSIT.
//Every transaction has an id, amount, and timestamp.
//Transactions must be sorted based on timestamp.
//A transaction's timestamp must display to the user as a readable date.

    public enum Type {WITHDRAW,DEPOSIT};
    private Type type;
    private long timestamp;
    private String id;
    private double amount;

    public Transaction(Type type, long timestamp, String id, double amount) {

        if(id == null || id.isBlank() || amount < 0){
            throw new IllegalArgumentException("Invalid Parameter.");
        }
        this.type = type;
        this.timestamp = timestamp;
        this.id = id;
        this.amount = amount;
    }

    public Transaction(Transaction source) {
        this.type = source.type;
        this.timestamp = source.timestamp;
        this.id = source.id;
        this.amount = source.amount;
    }

    public Type getType() {

        return type;
    }

    public void setType(Type type) {

        this.type = type;
    }

    public long getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(long timestamp) {

        this.timestamp = timestamp;
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

    public double getAmount() {

        return amount;
    }

    public void setAmount(double amount) {
        if(amount < 0){
            throw new IllegalArgumentException("Invalid Amount.");
        }
        this.amount = amount;
    }

    public String returnDate(){
        Date date = new Date(this.timestamp * 1000);
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Transaction)){
            return false;
        }
        Transaction transaction = (Transaction)obj;
        return
                this.timestamp == transaction.timestamp &&
                Double.compare(this.amount, transaction.amount) == 0 &&
                this.type == transaction.type &&
                Objects.equals(this.id, transaction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, timestamp, id, amount);
    }

    @Override
    public int compareTo(Transaction obj) {
        return Double.compare(this.timestamp,obj.timestamp);
    }

    @Override
    public String toString() {
        return (type) + "    " +
                "\t"+ this.returnDate()  +" " +
                "\t" + id + " " +
                "\t" + amount + " ";
    }
}
