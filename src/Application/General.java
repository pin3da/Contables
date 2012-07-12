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
        return transactions;
        
        
    }
    
    public LinkedList<BasicDBObject> getACGastos(){        
        LinkedList<BasicDBObject> answer= new LinkedList<BasicDBObject>();
        DBCursor cur = cuentas.find();
        while(cur.hasNext()){
            BasicDBObject aux= (BasicDBObject)cur.next();
            if(((String)aux.get("id")).charAt(0)=='5') answer.add(aux); 
                      
        }
        return answer;
    }
    
    public LinkedList<BasicDBObject> getACIngresos(){        
        LinkedList<BasicDBObject> answer= new LinkedList<BasicDBObject>();
        DBCursor cur = cuentas.find();
        while(cur.hasNext()){
            BasicDBObject aux= (BasicDBObject)cur.next();
            if(((String)aux.get("id")).charAt(0)=='4') answer.add(aux); 
                      
        }
        return answer;
    }
    
    public LinkedList<BasicDBObject> getACActivos(){        
        LinkedList<BasicDBObject> answer= new LinkedList<BasicDBObject>();
        DBCursor cur = cuentas.find();
        while(cur.hasNext()){
            BasicDBObject aux= (BasicDBObject)cur.next();
            if(((String)aux.get("id")).charAt(0)=='1') answer.add(aux); 
                      
        }
        return answer;
    }
    
    public LinkedList<BasicDBObject> getACPasivos(){        
        LinkedList<BasicDBObject> answer= new LinkedList<BasicDBObject>();
        DBCursor cur = cuentas.find();
        while(cur.hasNext()){
            BasicDBObject aux= (BasicDBObject)cur.next();
            if(((String)aux.get("id")).charAt(0)=='2') answer.add(aux); 
        }
        return answer;
    }
    
    public LinkedList<BasicDBObject> getACPatrimonio(){        
        LinkedList<BasicDBObject> answer= new LinkedList<BasicDBObject>();
        DBCursor cur = cuentas.find();
        while(cur.hasNext()){
            BasicDBObject aux= (BasicDBObject)cur.next();
            if(((String)aux.get("id")).charAt(0)=='3') answer.add(aux); 
        }
        return answer;
    }
    
    public LinkedList<Long> resumePatrimonio(){
        LinkedList<BasicDBObject> ac = getACPatrimonio();
        LinkedList<BasicDBObject> trans;
        long sum=0;
        LinkedList<Long> answer= new LinkedList<Long>();
        for(int i=0;i<ac.size();i++){
            trans=getTransactions((String)ac.get(i).get("id"), (String)ac.get(i).get("category"));
            for(int j=0; j<trans.size();j++){
                if((Boolean)trans.get(j).get("debe")) sum=sum-(Long)trans.get(j).get("amount");
                else sum=sum+(Long)trans.get(j).get("amount");
            }
            answer.add(sum);
        }
        return answer;    
    }
    
    public LinkedList<Long> resumePasivos(){
        LinkedList<BasicDBObject> ac = getACPasivos();
        LinkedList<BasicDBObject> trans;
        long sum=0;
        LinkedList<Long> answer= new LinkedList<Long>();
        for(int i=0;i<ac.size();i++){
            trans=getTransactions((String)ac.get(i).get("id"), (String)ac.get(i).get("category"));
            for(int j=0; j<trans.size();j++){
                if((Boolean)trans.get(j).get("debe")) sum=sum-(Long)trans.get(j).get("amount");
                else sum=sum+(Long)trans.get(j).get("amount");
            }
            answer.add(sum);
        }
        return answer;
        
    }
    
    public LinkedList<Long> resumeActivos(){
        LinkedList<BasicDBObject> ac = getACActivos();
        LinkedList<BasicDBObject> trans;
        long sum=0;
        LinkedList<Long> answer= new LinkedList<Long>();
        for(int i=0;i<ac.size();i++){
            trans=getTransactions((String)ac.get(i).get("id"), (String)ac.get(i).get("category"));
            for(int j=0; j<trans.size();j++){
                if((Boolean)trans.get(j).get("debe")) sum=sum+(Long)trans.get(j).get("amount");
                else sum=sum-(Long)trans.get(j).get("amount");
            }
            answer.add(sum);
        }
        return answer;
        
    }
    
    public LinkedList<Long> resumeGastos(){
        LinkedList<BasicDBObject> ac = getACGastos();
        LinkedList<BasicDBObject> trans;
        long sum=0;
        LinkedList<Long> answer= new LinkedList<Long>();
        for(int i=0;i<ac.size();i++){
            trans=getTransactions((String)ac.get(i).get("id"), (String)ac.get(i).get("category"));
            for(int j=0; j<trans.size();j++){
                sum=sum+(Long)trans.get(j).get("amount");
            }
            answer.add(sum);
        }
        return answer;
        
    }
    
    public LinkedList<Long> resumeIngresos(){
        LinkedList<BasicDBObject> ac = getACIngresos();
        LinkedList<BasicDBObject> trans;
        long sum=0;
        LinkedList<Long> answer= new LinkedList<Long>();
        for(int i=0;i<ac.size();i++){
            trans=getTransactions((String)ac.get(i).get("id"), (String)ac.get(i).get("category"));
            for(int j=0; j<trans.size();j++){
                sum=sum+(Long)trans.get(j).get("amount");
            }
            answer.add(sum);
        }
        return answer;
        
    }
    
     
    
    
    public long getActualUtiliy(){
        LinkedList<Long> g= resumeGastos(); 
        LinkedList<Long> i= resumeIngresos();
        long ing=0, gas=0;
        for(int j=0;j<g.size();j++){
            gas=gas+g.get(j);
        }
        for(int j=0;j<i.size();j++){
            ing=ing+i.get(j);
        }
        return ing-gas;
        
    }
}
