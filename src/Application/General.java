package Application;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.util.LinkedList;

/**
 *
 * @author pin3da
 */
public class General {
    Mongo m;
    DB db;
    DBCollection cuentas;
    DBCollection ingre;
    DBCollection gasto;
    DBCollection activos;
    DBCollection paypa;
    
    public General(Mongo m, String name){
        this.m = m;
        this.db = m.getDB(name);
        //this.db.dropDatabase();
        this.cuentas = db.getCollection("cuentas");
        this.ingre = db.getCollection("ingresos");
        this.gasto = db.getCollection("gasto");
        this.activos = db.getCollection("activos");
        this.paypa = db.getCollection("paypa");
    }

    public LinkedList<BasicDBObject> listCuentas(){
        DBCursor cur = cuentas.find();
        LinkedList<BasicDBObject> results = new LinkedList<BasicDBObject>();
        while(cur.hasNext())
            results.add((BasicDBObject)cur.next());
        return results;
    }
    
    public boolean addCuenta(String id, String name){
        BasicDBObject c = new BasicDBObject("id", id);
        if (cuentas.findOne(c) == null){
            cuentas.insert(c.append("name", name));
            return true;
        }
        return false;
    }
    
    public void modifyCuenta(String id, String name){
        BasicDBObject update = new BasicDBObject("id", id).append("name", name);
        cuentas.update(new BasicDBObject("id",id), update);
    }
    
    public boolean deleteCuenta(String id){
        BasicDBObject c = new BasicDBObject("id", id);
        if (cuentas.findOne(c) == null)
            return false;
        cuentas.dropIndex(c);
        return true;
    }
}
