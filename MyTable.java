/**
 * @author s1036171
 * R.DeRosa
 * MyTable.java
 * Copyright (C) 2016
 */
package edu.monmouth.s1036171;

import java.awt.Color;
import java.awt.Font;
//import java.awt.TextField;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableRowSorter;

public class MyTable {

public static JTextField firstName = new JTextField();
public static JTextField lastName = new JTextField();
public static JTextField age = new JTextField();
public static TextArea address = new TextArea(15,2);
public static JTextField phoneNumber = new JTextField();
public static JTextField email = new JTextField();
public final Object[] row = new Object[6];
public final DefaultTableModel model = new DefaultTableModel();
public final JTable table = new JTable();
public final JFrame frame = new JFrame();//

public MyTable(){
    Object[] columns = {"First Name","Last Name","Age","Address","Phone Number","Email"};
    model.setColumnIdentifiers(columns);
    
    // set model to the table
    table.setRowSorter(new TableRowSorter<DefaultTableModel>(model));
    table.setModel(model);
    
    table.setBackground(Color.LIGHT_GRAY);
    table.setForeground(Color.black);
    Font font = new Font("",1,22);
    table.setFont(font);
    table.setRowHeight(30);
    
    // JScrollPane
    JScrollPane pane = new JScrollPane(table);
    pane.setBounds(0, 0, 880, 200);
    
    frame.setLayout(null);
    
    frame.add(pane);
    
    frame.setSize(900,200);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(false); 
    
	
	
}

 
}