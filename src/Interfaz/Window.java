/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import Application.General;
import Application.Test;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import java.awt.Color;
import java.net.UnknownHostException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Carlos
 */
public final class Window extends JFrame{
    Color color = Color.lightGray;
    General jesus;
    JComboBox accountBox;
    //LinkedList<BasicDBObject> accountList;
    
    public Window () throws UnknownHostException{
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(525, 160);
        this.setTitle("SuperContables");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        Mongo m= new Mongo();
        
        jesus=new General(m, "contables");
        
        adminSwitch();
         loadList();
    }
    
    private void adminSwitch(){
        JPanel father= new JPanel();
        father.setLayout(new GroupLayout(father));
        father.setBounds(0,0,this.getWidth(), this.getHeight());
        father.setBackground(color);
        
        JLabel dateLab= new JLabel("Ingrese la Fecha de la transacción:");
        dateLab.setBounds(10, 5, 330, 20);
        
        JLabel amountLab= new JLabel("Ingrese la Cantidad de dinero:");
        amountLab.setBounds(10, 45, 330, 20);
                
        JLabel accountLab= new JLabel ("Seleccione la Cuenta:");
        accountLab.setBounds(this.getWidth()-150, 5, 140, 20);
        
        JLabel oweLab= new JLabel ("Deber:");
        oweLab.setBounds(this.getWidth()-150, 65, 55, 20);
        
        JLabel haveLab= new JLabel ("Haber:");
        haveLab.setBounds(this.getWidth()-85, 65, 55, 20);
        
        JRadioButton haveBut=new JRadioButton();
        haveBut.setBounds(this.getWidth()-45, 65, 20, 20);
        
        JRadioButton oweBut=new JRadioButton();
        oweBut.setBounds(this.getWidth()-110, 65, 20, 20);
        
        JTextField dateField=new JTextField("dd/mm/aaaa");
        dateField.setBounds(this.getWidth()-148, 25, 135, 20);
        dateField.setBackground(Color.white);
        
        JTextField amountField=new JTextField();
        amountField.setBounds(this.getWidth()-490, 65, 330, 20);
        amountField.setBackground(Color.white);
        
        accountBox= new JComboBox();
        accountBox.setBounds(this.getWidth()-490, 25, 330, 20);
        accountBox.setBackground(Color.white);
        accountBox.setEditable(true);
        accountBox.getEditor().getEditorComponent().setBackground(Color.white);
        
        JButton addTButton=new JButton("Añadir Transacción");
        addTButton.setBounds(5, 85, 140, 40);
        
        JButton addAButton=new JButton("Añadir Cuenta");
        addAButton.setBounds(135, 85, 110, 40);
        
        JButton libMButton=new JButton("Libro Mayor");
        libMButton.setBounds(235, 85, 100, 40);
        
        JButton resulButton=new JButton("Resultados");
        resulButton.setBounds(325, 85, 100, 40);
        
        JButton balButton=new JButton("B. General");
        balButton.setBounds(415, 85, 100, 40);
        
        father.add(balButton);
        father.add(resulButton);
        father.add(libMButton);
        father.add(addAButton);
        father.add(addTButton);
        father.add(haveLab);
        father.add(haveBut);
        father.add(oweBut);
        father.add(oweLab);
        father.add(amountField);        
        father.add(amountLab);
        father.add(dateLab);
        father.add(dateField);
        father.add(accountBox);
        father.add(accountLab);
        this.add(father);
    }
    
    private void loadList(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(); 
        for (BasicDBObject mio : jesus.listCuentas() ) {
            modelo.addElement((BasicDBObject)new Test(mio));
        }
        accountBox.setModel(modelo);
    }
    
    
}
