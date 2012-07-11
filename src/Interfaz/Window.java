/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import Application.General;
import Application.PCount;
import Application.PTransactions;
import Application.Transaction;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.UnknownHostException;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class Window extends JFrame{
    Color color = Color.lightGray;
    General jesus;
    JComboBox accountBox;
    AddCount addCount;
    LibroMayor libro;
    BGeneral bgeneral;
    Resultados resultados;
    //LinkedList<BasicDBObject> accountList;
    
    public Window () throws UnknownHostException{
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(540, 250);
        this.setTitle("SuperContables 2.8.e.21");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        Mongo m= new Mongo();
        
        jesus = new General(m, "contables");
        
        libro=new LibroMayor(this, jesus);
        addCount = new AddCount(this,jesus, libro);
        bgeneral = new BGeneral( jesus);
        resultados = new Resultados(jesus);
        
        
        
        adminSwitch();
        
    }
    
    private void adminSwitch(){
        JPanel father= new JPanel();
        father.setLayout(new GroupLayout(father));
        father.setBounds(0,0,this.getWidth(), this.getHeight());
        father.setBackground(color);
        
        JLabel info = new JLabel("Software contable \n Empresa :D");
        info.setBounds(100, 15, this.getWidth(), 20);
        info.setFont(new Font("Serif", Font.BOLD, 18));
        info.setForeground(Color.black);
        
        JLabel foot = new JLabel("Brought to you by Manuel, Carlos, Alejandro and Hugo.");
        foot.setBounds(this.getWidth()/2 - 180, this.getHeight()-55 , 350 , 20);
        
        JLabel dateLab= new JLabel("Seleccione la cuenta:");
        dateLab.setBounds(10, 55, 330, 20);
        
        JLabel amountLab= new JLabel("Ingrese la Cantidad de dinero:");
        amountLab.setBounds(10, 95, 330, 20);
                
        JLabel accountLab= new JLabel ("Fecha transacción:");
        accountLab.setBounds(this.getWidth()-150, 55, 140, 20);
        
        JLabel oweLab= new JLabel ("Deber:");
        oweLab.setBounds(this.getWidth()-150, 115, 55, 20);
        
        JLabel haveLab= new JLabel ("Haber:");
        haveLab.setBounds(this.getWidth()-85, 115, 55, 20);
        
        final JRadioButton haveBut=new JRadioButton();
        haveBut.setBackground(color);
        haveBut.setBounds(this.getWidth()-45, 115, 20, 20);
        
        final JRadioButton oweBut=new JRadioButton();
        oweBut.setBackground(color);
        oweBut.setBounds(this.getWidth()-110, 115, 20, 20);
        
        ButtonGroup group = new ButtonGroup();
        group.add(oweBut);
        group.add(haveBut);
        
        oweBut.setSelected(true);
        
        final JTextField dateField=new JTextField("dd/mm/aaaa");
        dateField.setBounds(this.getWidth()-148, 75, 135, 20);
        dateField.setBackground(Color.white);
        
        final JTextField amountField=new JTextField();
        amountField.setBounds(20, 115, 330, 20);
        amountField.setBackground(Color.white);
        
        accountBox=new JComboBox();
        loadList();
        accountBox.setBounds(20, 75, 330, 20);
        accountBox.setBackground(Color.white);
        
        accountBox.setEditable(false);
        
        

        
        
        JButton addTButton=new JButton("Añadir Transacción");
        addTButton.setBounds(5, 135, 145, 40);
        
        JButton addAButton=new JButton("Editar Cuentas");
        addAButton.setBounds(140, 135, 115, 40);
        
        JButton libMButton=new JButton("Libro Mayor");
        libMButton.setBounds(245,135, 100, 40);
        
        JButton resulButton=new JButton("Resultados");
        resulButton.setBounds(335,135, 100, 40);
        
        JButton balButton=new JButton("B. General");
       // balButton.setBackground(color);
        balButton.setBounds(425, 135, 100, 40);
        
        father.add(info);
        father.add(foot);
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
        
        resulButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resultados.update();
               resultados.setVisible(true);
            }
        }); 
        
        balButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bgeneral.load();
                bgeneral.setVisible(true);
            }
        }); 
        
        accountBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(((String)accountBox.getSelectedItem()).charAt(0)=='5'){
                    oweBut.setSelected(true);
                    haveBut.setEnabled(false);
                }
                else if(((String)accountBox.getSelectedItem()).charAt(0)=='4'){
                    haveBut.setSelected(true);
                    oweBut.setEnabled(false);
                }
                else {
                    oweBut.setEnabled(true);
                    haveBut.setEnabled(true);
                }
            }
        });
        
        //Action listeners here
        addAButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addCount.setVisible(true);
            }
        });
        
        addTButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String aux=(String)accountBox.getSelectedItem();
                String cat="" ;
                if(amountField.getText().equals(""))
                      JOptionPane.showMessageDialog(null, "Ingrese un valor");
                else{
                    aux=aux.split(" ")[0];
                    //System.out.println("l" + aux + "l");
                    boolean d=false;
                    if(oweBut.isSelected()) d=true;
                    if(aux.charAt(0)=='1') cat="activos";
                    if(aux.charAt(0)=='2' || aux.charAt(0)=='3') cat="paypa";
                    if(aux.charAt(0)=='4'){
                        cat="ingresos";
                        d=false;
                    }
                    if(aux.charAt(0)=='5'){
                        cat="gastos";
                        d=true;
                    }
                    
                    Transaction t= new Transaction(Long.parseLong(amountField.getText()), d, aux, cat, dateField.getText());
                    if(Pattern.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d",dateField.getText())){
                        jesus.addTransaction(t);
                        JOptionPane.showMessageDialog(null, "Transacción añadida con éxito");
                        amountField.setText("");
                        

                    }else{
                        JOptionPane.showMessageDialog(null, "El formato de fecha es incorrecto, deben ser valores numéricos");
                        dateField.setText("dd/mm/aaaa");
                    }
                }
            }
        });
        
        libMButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(libro.accounts.size()>0){
                    libro.a=0;
                    libro.updateTA();
                    if(libro.a+1<libro.accounts.size())libro.next.setEnabled(true);
                    else libro.next.setEnabled(false);
                    libro.previous.setEnabled(false);
                    libro.setVisible(true);
                }
                else JOptionPane.showMessageDialog(null, "No se han añadido cuentas");
                
            }
        });
        
        
       
    }
    
    public void loadList(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(); 
        for (BasicDBObject mio : jesus.listCuentas() ) {
            if(!mio.get("id").equals("0"))
            //System.out.println(mio.get("id"));
                modelo.addElement((new PCount(mio)).toString());
        }
        
        accountBox.setModel(modelo);
        }
       
}
