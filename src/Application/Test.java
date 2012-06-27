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
import com.mongodb.DBObject;

public class Test extends BasicDBObject implements DBObject{
    private BasicDBObject data;
    
    public Test(BasicDBObject hola){
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