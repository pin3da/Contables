package Application;

import com.mongodb.BasicDBObject;

/**
 *
 * @author pin3da
 */
public class Transaction{
    
    String acId;
    String acCat;
    long amount;
    String date;
    boolean d; //true if augment by "debe", false otherwise
    
    public Transaction( long amount, boolean d, String acId, String acCat, String date ){
        this.acId=acId;
        this.acCat=acCat;
        this.amount=amount;
        this.d=d;
        this.date=date;
        
    }
    
    public String getAcId(){
        return this.acId;
    }
    
    public String getAcCat(){
        return this.acCat;
    }
    
    public String getDate(){
        return this.date;
    }
    
    public long getAmount(){
        return this.amount;
    }
    
    public boolean getD(){
        return this.d;
    }
    
    public BasicDBObject getDocument(){
        return new BasicDBObject("account", this.getAcId()).append("amount", this.getAmount()).append("debe",this.getD()).append("category", this.getAcCat()).append("date", this.getDate());
    }
    
    
}
