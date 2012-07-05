/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

/**
 *
 * @author Carlos
 */
import com.mongodb.BasicDBObject;


public class PCount{
    private BasicDBObject data;
    
    public PCount(BasicDBObject hola){
        data = hola;
    }

    @Override
    public String toString() {
        return  data.get("id") + " - " + data.get("name");
        
    }
    
    public BasicDBObject getData(){
        return this.data;
    }
    
    
    
}