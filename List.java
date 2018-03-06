/**
 * @author s1036171
 * R.DeRosa
 * List.java
 * Copyright (C) 2016
 */
package edu.monmouth.s1036171;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.*;
//import javax.swing.event.*;

import java.util.*;


public class List extends JFrame implements ActionListener{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
public static String thisFile;
JLabel l1,l2,l3,l4,l5,l6,l7;
JTextField firstnamet,lastnamet,phoneNum,emailt,aget;	
TextArea addresst; 	
JButton addcontact,btnDelete,btnUpdate,resetTable, clearFields/*,saveTable,openTable*/;
public static JPanel jp = new JPanel();
public static final MyTable j = new MyTable();
public static Locale currentLocale;
public static ResourceBundle messages;
public static String language;
public static String country;
public static int change = 0;
private static final Logger logger = Logger.getLogger(List.class.getName());





public List() throws SecurityException, IOException{
	
	// Begin Log
	Handler fileHandler = null;
	SimpleFormatter formatter = null;
	fileHandler = new FileHandler("./contactLog.txt");
	formatter = new SimpleFormatter();
	fileHandler.setFormatter(formatter);
	fileHandler.setLevel(Level.ALL);
	logger.addHandler(fileHandler);
	//logger.info(messages.getString("LogStartApp")); // First Log of program
	
	
	jp.setLayout(new GridLayout(10,0));
	
	
	
	

	    language = new String("en");
	    country = new String("US");
	    currentLocale = new Locale(language, country);	
	    messages = ResourceBundle.getBundle("edu/monmouth/s1036171/Messages",currentLocale);
	
    

    //messages = ResourceBundle.getBundle("edu/monmouth/s1036171/Messages",currentLocale);
	    
	    String line2;
	    try( BufferedReader bw = new BufferedReader(new FileReader("./saveColor.txt"))){
        	while( (line2 = bw.readLine() ) != null ) {
        		Color savedColor = new Color(Integer.parseInt(line2));
        		jp.setBackground(savedColor);
        		j.table.setBackground(savedColor);
        }

        	  bw.close();
        	  
        } catch (IOException ex) {
            System.err.format("Caught IOException: " + ex.getMessage());
      }  
	    
	    
	    
    
   // l1 = new JLabel(ResourceBundle.getBundle("edu/monmouth/s1036171/Messages_du_NL.properties").getString("FirstName"));
	l1 = new JLabel(" " + messages.getString("FirstName") + " ");
	jp.add(l1);
	firstnamet = new JTextField(10);
	jp.add(firstnamet);
	l1.setLabelFor(firstnamet);
	
	l2 = new JLabel(" " + messages.getString("LastName") + " ");
	jp.add(l2);
	lastnamet = new JTextField(10);
	jp.add(lastnamet);
	l2.setLabelFor(lastnamet);
	
	
	l3 = new JLabel(" " + messages.getString("Age") + " ");
	jp.add(l3);
	aget = new JTextField(15);
	jp.add(aget);
    
    
    l4 = new JLabel(" " + messages.getString("Address") + " ");
    jp.add(l4);
    addresst = new TextArea(15,2);
    jp.add(addresst);	
		
	l5 = new JLabel(" " + messages.getString("PhoneNumber") + " ");
	jp.add(l5);
	phoneNum = new JTextField(10);
	jp.add(phoneNum);
	
	l6 = new JLabel(" " + messages.getString("Email") + " ");
	jp.add(l6);
	emailt = new JTextField(15);
	jp.add(emailt);
	
	addcontact = new JButton(messages.getString("AddContact"));
	btnDelete = new JButton(messages.getString("DeleteContact"));
	btnUpdate = new JButton(messages.getString("UpdateContact"));
	resetTable = new JButton(messages.getString("ResetTable"));
	clearFields = new JButton(messages.getString("Clear"));
	/*openTable = new JButton("Open");
	saveTable = new JButton("Save As");*/
	jp.add(addcontact);
    jp.add(btnDelete);
    jp.add(btnUpdate);
    jp.add(resetTable);
    jp.add(clearFields);
  /*  jp.add(saveTable);
    jp.add(openTable);*/

   
    
    // button add row
 addcontact.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
        	
        	j.frame.setVisible(true); 
         
            j.row[0] = firstnamet.getText();
            j.row[1] = lastnamet.getText();
            
            if (aget.getText().matches("[0-9]+"))
            {
            	j.row[2] = aget.getText();
            }
            else
            {
            	aget.setText("");
            	j.row[2] = "";
            }
            
            j.row[3] = addresst.getText();
            
            if (phoneNum.getText().matches("[0-9]+"))
            {
            	j.row[4] = phoneNum.getText();
            }
            else
            {
            	phoneNum.setText("");
            	j.row[4] = "";
            }
            
            if (emailt.getText().matches(".+[@].+"))
            {
            	j.row[5] = emailt.getText();
            }
            else
            {
            	emailt.setText("");
            	j.row[5] = "";
            }
            
            // add row to the model
            

            
            j.model.addRow(j.row);
            logger.info(messages.getString("LogNewContact"));
            
        }
    });
    
 btnDelete.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
        
            
            int i = j.table.getSelectedRow();
            if(i >= 0){
                // remove row
                j.model.removeRow(i);
            }
            logger.info(messages.getString("LogDelContact"));
        }
    });
    
 btnUpdate.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
     
        // i = the index of the selected row
        int i = j.table.getSelectedRow();
        
        if(i >= 0) 
        {
           j.model.setValueAt(firstnamet.getText(), i, 0);
           j.model.setValueAt(lastnamet.getText(), i, 1);
           j.model.setValueAt(aget.getText(), i, 2);
           j.model.setValueAt(addresst.getText(), i, 3);
           j.model.setValueAt(phoneNum.getText(), i, 4);
           j.model.setValueAt(emailt.getText(), i, 5);
        }
        logger.info(messages.getString("LogUpContact"));
    }
});
    
    
    
    
resetTable.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	
    	Object[] options = {"Yes",
        "No"};
int n = JOptionPane.showOptionDialog(null, messages.getString("AreYouSure"), 
	  messages.getString("Warning"), 
      JOptionPane.YES_NO_OPTION, 
      JOptionPane.WARNING_MESSAGE, 
      null, options, options[1]);
if (n == JOptionPane.YES_OPTION) {
	     j.model.setRowCount(0);
	     logger.info(messages.getString("LogResTable"));
		}
else{logger.info(messages.getString("LogNResTable"));}

    }
});

clearFields.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	firstnamet.setText("");
    	lastnamet.setText("");
    	aget.setText("");
    	addresst.setText(" ");
    	phoneNum.setText("");
    	emailt.setText("");
    	logger.info(messages.getString("LogFieldReset"));

    }
});


    
	add(jp);
	
	addJFrame();
		
	}
	    
	

	
	
	
	public static void addJFrame(){

	    // add the panel into JFrame

	    JFrame f = new JFrame();

	    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    

	    f.setJMenuBar(createMenuBar());

	    f.setVisible(true);

	    f.setSize(300, 600);

	    f.add(jp);


	}
	
	
	
	
	public static JMenuBar createMenuBar() {

	    JMenuBar menuBar;
	    JMenu menuFile, menuEdit;
	    JMenuItem menuOpen, menuSave, menuColor, menuLang, menuAbout;
	    
	    //Create the menu bar.
	    menuBar = new JMenuBar();
	    
	    //Build menu
	    menuFile = new JMenu(messages.getString("File"));
	    menuBar.add(menuFile);
	    
	    //Build menu
	    menuEdit = new JMenu(messages.getString("Preferences"));
	    menuBar.add(menuEdit);
	    
	    menuAbout = new JMenuItem(messages.getString("About"));

	    menuAbout.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    	
	    	JFrame jfA = new JFrame();
	    	JLabel jl3 = new JLabel(messages.getString("AboutText"));
	    	
	    	jfA.add(jl3);
			jfA.setVisible(true);
			jfA.setSize(300, 200);
			jfA.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			


	     }
	    });

	    menuFile.add(menuAbout);


	    menuOpen = new JMenuItem("Open");

	    menuOpen.addActionListener(new ActionListener(){
	    	
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        JFileChooser fileChooser = new JFileChooser();
	        File workingDirectory = new File(System.getProperty("user.dir"));
	        fileChooser.setCurrentDirectory(workingDirectory);
	        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        if (fileChooser.showOpenDialog(createMenuBar()) == JFileChooser.APPROVE_OPTION) {
	        	  File file = fileChooser.getSelectedFile();
	        	  thisFile = file.getName();
	          // System.out.println(thisFile);
	        }
	       
	        String line;
	        j.model.setRowCount(0);
	        try( BufferedReader bw = new BufferedReader(new FileReader(thisFile))){
	        	while( (line = bw.readLine() ) != null ) {
	        	  j.model.addRow( line.split("\t") );
	        }

	        	  bw.close();
	        	  
	        } catch (IOException ex) {
	            System.err.format("Caught IOException: " + ex.getMessage());
	      }  
	        logger.info("Clicked Open");
	     }
	    });

	    menuFile.add(menuOpen);

	    menuSave = new JMenuItem("Save As");
	    menuSave.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	
	        	JFileChooser fileChooser = new JFileChooser();
	        	File workingDirectory = new File(System.getProperty("user.dir"));
	        	fileChooser.setCurrentDirectory(workingDirectory);
	        	fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        	if (fileChooser.showSaveDialog(createMenuBar()) == JFileChooser.APPROVE_OPTION) {
	        	   File file = fileChooser.getSelectedFile();
	        	   thisFile = file.getName();
	        	}
	        	
	        	  try (BufferedWriter bw = new BufferedWriter(new FileWriter(thisFile))){

	        	  for (int i = 0 ; i < j.table.getRowCount(); i++)
	        	  {
	        		if (i > 0){bw.newLine();}
	        	    for(int c = 0 ; c < j.table.getColumnCount();c++)
	        	    {
	        	      bw.write((String)(j.table.getValueAt(i,c)));
	        	      bw.write("\t");;
	        	    }
	        	  }
	        	  bw.close();
	    	    } catch (IOException ex) {
	    	        System.err.format("Caught IOException: " + ex.getMessage());
	          }  
	        	  logger.info("Clicked Save As");
	        }
	    });

	    menuFile.add(menuSave);
	    
	    
	    

	    menuColor = new JMenuItem("Choose a Color");
	    menuColor.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        Color c = JColorChooser.showDialog(null, "Select a color for your Program", Color.WHITE);
	        jp.setBackground(c);
	        j.table.setBackground(c);
	        logger.info("Clicked Choose a Color");
	        String colorS = Integer.toString(c.getRGB());
	        
	        
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./saveColor.txt"))){


	        	      bw.write(colorS);
	        	      bw.close();
	    	    } catch (IOException ex) {
	    	        System.err.format("Caught IOException: " + ex.getMessage());
	          }  
	        
	        }
	    });

	    menuEdit.add(menuColor);
	    
	    
	    menuLang = new JMenuItem("Language");
	    menuLang.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	/*
		    	JFrame jfA2 = new JFrame();
		    	JLabel jl32 = new JLabel("<html><p>This is a contact list program developed by Robby DeRosa. Fill out the "
		    			+ "contact list and add contacts to the table. Change your user preferences and they will save automatically!</p></html>");
		    	
		    	jfA2.add(jl32);
				jfA2.setVisible(true);
				jfA2.setSize(300, 200);
				jfA2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        	*/
	        	
	        	JFrame frame2 = new JFrame("Change Language");
	            frame2.setLayout(new BorderLayout());
	            final JRadioButton US,DE,FR;
	             JPanel panel = new JPanel();
	              ButtonGroup buttonGroup = new ButtonGroup();
	              US = new JRadioButton("English");
	              buttonGroup.add(US);
	              panel.add(US);
	              DE = new JRadioButton("Dutch");
	              buttonGroup.add(DE);
	              panel.add(DE);
	              FR = new JRadioButton("French");
	              buttonGroup.add(FR);
	              panel.add(FR);
	              US.setSelected(true);
	              JButton apply = new JButton(" Apply ");
	              apply.setBounds(300,100,2,2);
	              
	              
	              apply.addActionListener(new ActionListener(){
	            	    @Override
	            	    public void actionPerformed(ActionEvent e) {
	            	     
	            	        if (US.isSelected())
	            	        {
	            	        	System.out.println("USA");
	            	            
	            	            language = new String("en");
	            	            country = new String("US");

	            	           
	            	            currentLocale = new Locale(language, country);

	            	            messages = ResourceBundle.getBundle("edu/monmouth/s1036171/Messages",currentLocale);

	            	        	
	            	        }
	            	        else if(DE.isSelected())
	            	        {
	            	        	change = 1;
	            	        	System.out.println("NL");
	            	            
	            	            language = new String("du");
	            	            country = new String("NL");

	            	           
	            	            currentLocale = new Locale(language, country);

	            	            messages = ResourceBundle.getBundle("edu/monmouth/s1036171/Messages",currentLocale);
	            	            //ResourceBundle.clearCache();
	            	            System.out.println(messages.getLocale().getLanguage());
	            	        	
	            	        }else if(FR.isSelected())
	            	        {
	            	        	System.out.println("FR");
	            	            
	            	            language = new String("fr");
	            	            country = new String("FR");

	            	           
	            	            currentLocale = new Locale(language, country);

	            	            messages = ResourceBundle.getBundle("edu/monmouth/s1036171/Messages",currentLocale);
	            	        	
	            	        }
	            	        
	            	     
	            	    	
	            	    	
	            	        logger.info("Clicked Language Option");
	            	    }
	            	});
	              
	              
	              
	              
	              
	              
	              
	           
	              panel.add(apply);             
	              
	              
	              
	              frame2.add(panel);
	              frame2.setSize(182,150);
	              frame2.setVisible(true);
	              
	        
	        }
	    });

	    menuEdit.add(menuLang);
	    
	    return menuBar;

	} // end menu method


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}

