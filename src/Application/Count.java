package Application;


import com.mongodb.BasicDBObject;

/**
 *
 * @author pin3da
 */
public class Count {
    protected String id;
    protected String name;
    private String cat;
    
    public Count(){}
        
    public Count(String id, String name, String cat){
        this.id = id;
        this.name = name;
        this.cat = cat;
    }
    
    public BasicDBObject getDocument(){
        return new BasicDBObject("id", this.getId()).append("name", this.getName()).append("category",this.getCat());
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
    public String getCat() {
        return cat;
    }

    /**
     * @param descprition the descprition to set
     */
    public void setCat(String cat) {
        this.cat = cat;
    }
}
