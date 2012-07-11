/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Application.General;
import com.mongodb.BasicDBObject;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class BGeneral extends JFrame{
    General jesus;
    JScrollPane sdata;
    JTable data;
    
    public  BGeneral(General  jesus ){
        this.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.setSize(500, 400);
        this.setTitle("Balance General");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        
        this.jesus = jesus;
        data = new JTable();
        
        data.setEnabled(false);
        sdata = new JScrollPane(data);
        sdata.setBounds(0,0,getWidth(),getHeight());
        this.add(sdata);
                
    }
    
    public void load(){
        LinkedList<BasicDBObject> acactivos = jesus.getACActivos();
        LinkedList<BasicDBObject> acpasivos = jesus.getACPasivos();
        LinkedList<BasicDBObject> acpatrimonio = jesus.getACPatrimonio();
        LinkedList<Long> activos = jesus.resumeActivos();
        LinkedList<Long> pasivos = jesus.resumePasivos();
        LinkedList<Long> patrimonio = jesus.resumePatrimonio();
        DefaultTableModel tmodel= new DefaultTableModel();
        tmodel.addColumn("Activos");
        tmodel.addColumn("Pasivos");
        int i,j,k;
        long sa = 0, sp = 0, spt = 0;
    
        
        for ( i = 0; i < acactivos.size(); i++) {
            tmodel.addRow(new String[2]);
            sa+=activos.get(i);
            tmodel.setValueAt(String.valueOf(activos.get(i)) + " - " + (String)acactivos.get(i).get("name"), i, 0);
        }
        
        for ( j = 0; j < acpasivos.size(); j++) {
            if(j>=tmodel.getRowCount())
                tmodel.addRow(new String[2]);
            sp+=pasivos.get(j);
            tmodel.setValueAt(String.valueOf(pasivos.get(j)) + " - " + (String)acpasivos.get(j).get("name"), j, 1);
        }
        
        if(j>=tmodel.getRowCount())
            tmodel.addRow(new String[2]);
        
        
        tmodel.setValueAt("Total Pasivos: "+ String.valueOf(sp), j++, 1);
        
        if(j>=tmodel.getRowCount())
            tmodel.addRow(new String[2]);
        
        
        tmodel.setValueAt("                               Patrimonio", j++, 1);
        
        for ( k = j; k < j + acpatrimonio.size(); k++) {
            if(k+j>=tmodel.getRowCount())
                tmodel.addRow(new String[2]);
            if(((String)acpatrimonio.get(k-j).get("id")).equals("3605")){
                tmodel.setValueAt(String.valueOf(patrimonio.get(k-j) + jesus.getActualUtiliy()) + " - " + (String)acpatrimonio.get(k-j).get("name"), k, 1);
                spt+=patrimonio.get(k-j) + jesus.getActualUtiliy();
            }
            else{
                spt+=patrimonio.get(k-j);
                tmodel.setValueAt(String.valueOf(patrimonio.get(k-j)) + " - " + (String)acpatrimonio.get(k-j).get("name"), k, 1);
            }
        }
        
        if(j+k>=tmodel.getRowCount())
            tmodel.addRow(new String[2]);
        
        tmodel.setValueAt("Total Patrimonio: "+ String.valueOf(spt), k++, 1);
        
        int index = (i<k)? k:i;
        
        tmodel.addRow(new String[2]);
        tmodel.setValueAt("Total Activos: "+ String.valueOf(sa), index, 0);
        tmodel.setValueAt("Total P + P: "+ String.valueOf(sp+spt), index, 1);
        
        
        data.setModel(tmodel);
    }
}
