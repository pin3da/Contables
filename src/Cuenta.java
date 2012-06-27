
import com.mongodb.BasicDBObject;

/**
 *
 * @author pin3da
 */
public class Cuenta {
    protected String id;
    protected String name;
    
    public Cuenta(){}
    public Cuenta(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    public BasicDBObject getDocument(){
        return new BasicDBObject("id", this.getId()).append("name", this.getName());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
