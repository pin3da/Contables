/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 *
 * @author Carlos
 */
public class PTransactions {
    private BasicDBObject data;
    
    public PTransactions(BasicDBObject hola){
        data = hola;
    }

    @Override
    public String toString() {
        return  data.get("date") + " - " + data.get("amount");
        
    }
    
    public BasicDBObject getData(){
        return this.data;
    }
    
    
    
}
