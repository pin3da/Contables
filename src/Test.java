
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.LinkedList;


/**
 *
 * @author pin3da
 */
public class Test {
    public static void main(String []Args) throws UnknownHostException{
        Mongo mio = new Mongo();
        General g = new General(mio, "contables");
        g.addCuenta("12345", "Cuenta de prueba");
        g.addCuenta("12123", "C2345prueba");
        g.addCuenta("121345", "Cue123456nta de prueba");
        g.modifyCuenta("12345", "cuenta 2");
        LinkedList<BasicDBObject>  li = g.listCuentas();
        for (BasicDBObject b : li) {
            System.out.println(b);
        }
    }
}
