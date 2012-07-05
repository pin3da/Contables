/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Application.Count;
import Application.General;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author pin3da
 */
public final class AddCount extends JFrame{
    Color color = Color.lightGray;
    General jesus;
    Window window;
    LibroMayor libro;
    public AddCount(Window window,General jesus, LibroMayor libro){
        this.jesus = jesus;
        this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.setSize(500, 400);
        this.setTitle("Adicionar una nueva cuenta");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        this.window=window;
        this.libro=libro;
        loadFields();
    }
    
    public void loadFields(){
        JLabel namel = new JLabel("Nombre de la cuenta:");
        namel.setBounds(20, 20, 150, 30);
        
        JLabel idl = new JLabel("Código de la cuenta:");
        idl.setBounds(20, 66, 150, 30);
        
        JLabel descl = new JLabel("Descripción de la cuenta (opcional):");
        descl.setBounds(20, 106, 150, 30);
        
        final JTextField namef = new JTextField();
        namef.setBounds(20 , 50, 400 , 20);
        namef.setBackground(Color.white);
        
        final JTextField idf = new JTextField();
        idf.setBounds(20 , 90, 400 , 20);
        idf.setBackground(Color.white);
        
        final JTextArea descf = new JTextArea();
        descf.setBounds(20 , 130, 400 , 130);
        descf.setBackground(Color.white);
        
        JButton save = new JButton("Guardar Cuenta");
        save.setBounds(15, this.getHeight()-100, 150, 50);
        
        
        JButton cancel = new JButton("Cancelar / limpiar");
        cancel.setBounds(158, this.getHeight()-100, 150, 50);
        
        JButton done = new JButton("Hecho");
        done.setBounds(300, this.getHeight()-100, 150, 50);
        
        this.add(namel);
        this.add(namef);
        this.add(idl);
        this.add(idf);
        this.add(descl);
        this.add(descf);
        this.add(save);
        this.add(cancel);
        this.add(done);
        
        //Listeners Here
        
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Validar cuentas antes de añadir
                Count mio=new Count();
                
                if(idf.getText().charAt(0)=='1') mio = new Count(idf.getText(), namef.getText() , "activos");
                if(idf.getText().charAt(0)=='2' || idf.getText().charAt(0)=='3') mio = new Count(idf.getText(), namef.getText() , "paypa");
                if(idf.getText().charAt(0)=='4') mio = new Count(idf.getText(), namef.getText() , "ingresos");
                if(idf.getText().charAt(0)=='5') mio = new Count(idf.getText(), namef.getText() , "gastos");
                
                if(jesus.addCount(mio)){
                    JOptionPane.showMessageDialog(null, "Cuenta añadida exitosamente","Cuenta añadida exitosamente",JOptionPane.INFORMATION_MESSAGE);
                    window.loadList();
                    libro.loadAccounts();
                }
                else
                    JOptionPane.showMessageDialog(null, "La cuenta no pudo ser añadida","La cuenta no pudo ser añadida",JOptionPane.ERROR_MESSAGE);
                
            }
        }); 
        
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                namef.setText("");
                idf.setText("");
                descf.setText("");
            }
        }); 
        
        done.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Falta actualizar la lista de cuentas.
                // A mi parecer esto es acto de un "fantasma informatico" porque me sale un error muy extraño
                //luego les explico. Traten de descomentar la siguiente línea, si funciona, odio mi computador.
                //Sino, odio java.
                
                namef.setText("");
                idf.setText("");
                descf.setText("");
                setVisible(false);
            }
        });
        
        
       
    }
    
}
