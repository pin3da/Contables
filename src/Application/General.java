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
        this.gasto = db.getCollection("gastos");
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
    
    public boolean addCount(Count cuenta){
        BasicDBObject c = new BasicDBObject("id", cuenta.getId());
        if (cuentas.findOne(c) == null){
            cuentas.insert(cuenta.getDocument());
            return true;
        }
        return false;
    }
    
    public boolean modifyCount(String id, String name){
        BasicDBObject update = new BasicDBObject("id", id).append("name", name);
        if(cuentas.find(update) == null)
               return false;
        cuentas.update(new BasicDBObject("id",id), update);
        return true;
    }
    public boolean modifyCount(String id, String name, String desc){
        BasicDBObject update = new BasicDBObject("id", id).append("name", name).append("desc", desc);
        if(cuentas.find(update) == null)
               return false;
        cuentas.update(new BasicDBObject("id",id), update);
        return true;
    }
    
    public boolean deleteCount(String id){
        BasicDBObject c = new BasicDBObject("id", id);
        if (cuentas.findOne(c) == null)
            return false;
        cuentas.dropIndex(c);
        return true;
    }
    
    public void addTransaction(Transaction t){
        if(t.acCat.equals("ingresos")){
            ingre.insert(t.getDocument());
        }
        
        if(t.acCat.equals("gastos")){
            gasto.insert(t.getDocument());
        }
        
        if(t.acCat.equals("activos")){
            activos.insert(t.getDocument());
        }
        
        if(t.acCat.equals("paypa")){
            paypa.insert(t.getDocument());
        } 
    }
    
    public LinkedList<BasicDBObject> getTransactions(String acId, String acCat){
        LinkedList<BasicDBObject> transactions= new LinkedList<BasicDBObject>();
        DBCursor cur;
        BasicDBObject aux;

        if(acId.equals("0")){
            cur=ingre.find();
            while(cur.hasNext()){
              aux=(BasicDBObject)cur.next();
              transactions.add(aux);
            }
            cur=gasto.find();
            while(cur.hasNext()){
              aux=(BasicDBObject)cur.next();
              transactions.add(aux);
            }
        }
        else{
            if(acCat.equals("ingresos")){
                cur=ingre.find();
                while(cur.hasNext()){
                  aux=(BasicDBObject)cur.next();
                  if(aux.get("account").equals(acId)){
                      transactions.add(aux);
                  }
                }

            }

            if(acCat.equals("gastos")){
                cur=gasto.find();
                while(cur.hasNext()){
                  aux=(BasicDBObject)cur.next();
                  if(aux.get("account").equals(acId)){
                      transactions.add(aux);
                  }
                }

            }

            if(acCat.equals("activos")){
                cur=activos.find();
                while(cur.hasNext()){
                  aux=(BasicDBObject)cur.next();
                  if(aux.get("account").equals(acId)){
                      transactions.add(aux);
                  }
                }

            }

            if(acCat.equals("paypa")){
                cur=paypa.find();
                while(cur.hasNext()){
                  aux=(BasicDBObject)cur.next();
                  if(aux.get("account").equals(acId)){
                      transactions.add(aux);
                  }
                }

            }
        }
        return transactions;
        
        
    }
}
