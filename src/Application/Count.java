package Application;


import com.mongodb.BasicDBObject;

/**
 *
 * @author pin3da
 */
public class Count {
    protected String id;
    protected String name;
    private String descprition;
    
    public Count(){}
    public Count(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    public Count(String id, String name, String description){
        this.id = id;
        this.name = name;
        this.descprition = description;
    }
    
    public BasicDBObject getDocument(){
        return new BasicDBObject("id", this.getId()).append("name", this.getName()).append("desc",this.getDescprition());
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

    /**
     * @return the descprition
     */
    public String getDescprition() {
        return descprition;
    }

    /**
     * @param descprition the descprition to set
     */
    public void setDescprition(String descprition) {
        this.descprition = descprition;
    }
}
