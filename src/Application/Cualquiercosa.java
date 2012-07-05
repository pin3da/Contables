package Application;


import Interfaz.Window;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.LinkedList;


/**
 *
 * @author pin3da
 */
public class Cualquiercosa {
    public static void main(String []Args) throws UnknownHostException{
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                 if ("CDE/Motif".equals(info.getName())) {
                 //if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        //Mongo mio = new Mongo();
        //General g = new General(mio, "contables");
        //g.db.dropDatabase();

        //Mongo mio = new Mongo();
        //General g = new General(mio, "contables");
        //g.db.dropDatabase();
        Window w= new Window();
        w.setVisible(true);
        
    }
}
