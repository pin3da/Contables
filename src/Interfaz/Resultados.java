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
import javax.swing.JTextArea;

/**
 *
 * @author Carlos
 */
public class Resultados extends JFrame{
    General jesus;
    JTextArea data;
    JScrollPane sdata;
    
    public Resultados(General jesus){
        this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.setSize(500, 400);
        this.setTitle("Estado de resultados.");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        
        this.jesus = jesus;
        this.data = new JTextArea();
        this.data.setEditable(false);
        data.setForeground(Color.decode("3564683"));
        this.sdata = new JScrollPane(data);
        this.sdata.setBounds(0,0,this.getWidth(),this.getHeight());
        
        this.add(sdata);
        this.data.setBackground(Color.white);
        //this.data.setForeground(Color.CYAN);
    }
    
    public void update(){
        String  h = "\t\tEstado de Resultados, Empresa :3\n\n";
        LinkedList<Long> ingre = jesus.resumeIngresos();
        LinkedList<Long> gastos = jesus.resumeGastos();
        LinkedList<BasicDBObject> acIngre = jesus.getACIngresos();
        LinkedList<BasicDBObject> acGastos = jesus.getACGastos();
        long ans=0;
        for (Long o : ingre)ans+=o; 
        h+="  Total ingresos: \t\t\t" + String.valueOf(ans)+"\n";
        
        for(int i=0;i<acIngre.size();i++){
            h+="\t " + (String)acIngre.get(i).get("name") + "\t\t\t" +String.valueOf(ingre.get(i))+ "\n";
        }
        ans=0;
        for (Long o : gastos)ans+=o; 
        h+="\n  Total gastos: \t\t\t" + String.valueOf(ans)+"\n";
        
        for(int i=0;i<acGastos.size();i++){
            h+="\t " + (String)acGastos.get(i).get("name") + "\t\t\t" +String.valueOf(gastos.get(i))+ "\n";
        }
        
        h+="\n  Utilidad antes de impuesto: \t\t" + jesus.getActualUtiliy();
        h+="\n  Impuesto: \t\t\t\t" + jesus.getActualUtiliy()*0.33;
        h+="\n  Utilidad después de impuesto: \t" + jesus.getActualUtiliy()*(1-0.33);
        
        data.setText(h);
    }
}


