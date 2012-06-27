package Application;

/**
 *
 * @author pin3da
 */
public class Transaction{
    Cuenta cuenta;
    double amount;
    boolean d; //true if augment by "debe", false otherwise
    
    public Transaction(){}
    public Transaction(Cuenta c, double a,boolean d){
        this.cuenta = c;
        this.amount = a;
        this.d = d;
    }
}
