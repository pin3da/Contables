/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Application.Count;
import Application.General;
import Application.PCount;
import Application.PTransactions;
import com.mongodb.BasicDBObject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class LibroMayor extends JFrame {
    LinkedList<BasicDBObject> accounts;
    LinkedList<BasicDBObject> transactions;
    Window window;
    General jesus;
    JScrollPane view;
    JTable son;
    JLabel title;
    JButton previous;
    JButton next;
    int a=0;
    
    public LibroMayor(Window window, General jesus ){
        this.jesus=jesus;
        this.window=window;
        this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.setSize(500, 500);
        this.setTitle("Libro Mayor");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        
        adminSwitch();
        
        son=new JTable();
        title=new JLabel();
        view=new JScrollPane(son);
        view.setBounds(5, 30, 490, 400);
        title.setBounds(200, 10, 100, 20);
        this.add(title);
        this.add(view);
          
        
    }
    
    private void adminSwitch(){
        loadAccounts();
        JButton done=new JButton("Listo");
        done.setBounds(10, 430, 100, 40);
        
        next=new JButton("Siguiente");
        next.setBounds(110, 430, 100, 40);
        
        previous=new JButton("Anterior");
        previous.setBounds(210, 430, 100, 40);
        
        this.add(done);
        this.add(next);
        this.add(previous);
        
        done.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(a+1<=accounts.size()){
                    if(!previous.isEnabled()) previous.setEnabled(true);
                    a=a+1;
                    updateTA();
                    if(a+1>=accounts.size()) next.setEnabled(false);
                }
                
            }
        });
        
        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(a-1>=0){
                    if(!next.isEnabled()) next.setEnabled(true);
                    a=a-1;
                    updateTA();
                    if(a-1<0) previous.setEnabled(false);
                }
            }
        });
    }
    
    public void loadAccounts(){
        accounts=jesus.listCuentas();
    }
    
    public void loadTransactions(){
       transactions=jesus.getTransactions((String)accounts.get(a).getString("id"), (String)accounts.get(a).getString("category"));
    }
    
    public void updateTA(){
        
        loadTransactions();
        this.title.setText(new PCount(this.accounts.get(a)).toString());
        DefaultTableModel tmodel= new DefaultTableModel();
        tmodel.addColumn("Deber");
        tmodel.addColumn("Haber");
        int j=0;
        int k=0;
        double sum1=0;
        double sum2=0;
        for(int i=0; i<this.transactions.size();i++){

            if((Boolean)this.transactions.get(i).get("debe")){
                sum1=sum1+(Double)this.transactions.get(i).get("amount");
                if(tmodel.getRowCount()<=k) tmodel.addRow(new String[2]);
                tmodel.setValueAt(new PTransactions(this.transactions.get(i)).toString(), k, 0);
                k++;
                

            }
            else{
                if(tmodel.getRowCount()<=j) tmodel.addRow(new String[2]);
                tmodel.setValueAt(new PTransactions(this.transactions.get(i)).toString(), j, 1);
                j++;
                sum2=sum2+(Double)this.transactions.get(i).get("amount");
            }

        }
        tmodel.addRow(new String[2]);
        tmodel.addRow(new String[2]);
        tmodel.addRow(new String[2]);
        tmodel.addRow(new String[2]);
        int index=0;
        if(j>k) index=j;
        else index=k;
        tmodel.setValueAt("Cierre", index, 0);
        tmodel.setValueAt("Cierre", index, 1);
        
        
        if(((String)this.accounts.get(a).get("category")).equals("activos")){
            tmodel.setValueAt(String.valueOf(sum1), index+1, 0);
            tmodel.setValueAt(String.valueOf(sum2), index+1, 1);
            tmodel.setValueAt("Totales:", index+2, 0);
            tmodel.setValueAt(String.valueOf(sum1-sum2), index+3, 0);
        }
        if( ((String)this.accounts.get(a).get("category")).equals("gastos")){
            tmodel.setValueAt(String.valueOf(sum1-sum2), index+1, 0);
            tmodel.setValueAt(String.valueOf(sum1-sum2), index+1, 1);
            tmodel.setValueAt("Totales:", index+2, 0);
            tmodel.setValueAt(String.valueOf(0.0), index +3, 0);
        }
        if(((String)this.accounts.get(a).get("category")).equals("paypa")){
            tmodel.setValueAt(String.valueOf(sum1), index+1, 0);
            tmodel.setValueAt(String.valueOf(sum2), index+1, 1);
            tmodel.setValueAt("Totales:", index+2, 1);
            tmodel.setValueAt(String.valueOf(sum2-sum1), index+3, 0);
        }
        if( ((String)this.accounts.get(a).get("category")).equals("ingresos")){
            tmodel.setValueAt(String.valueOf(sum2-sum1), index+1, 0);
            tmodel.setValueAt(String.valueOf(sum2-sum1), index+1, 1);
            tmodel.setValueAt("Totales:", index+2, 1);
            tmodel.setValueAt(String.valueOf(0.0), index +3, 1);
        }
        
        this.son.setModel(tmodel);              
    
}
}
