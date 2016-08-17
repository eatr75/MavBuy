/**
 * main GUI class to collect every Gui class
 * @author 
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//Fimport java.io.*;
public class MyGUI extends JPanel{
    private JList myJList; // create Jlist
    private JScrollPane scrollPane1; //create scrollPane
    private JScrollPane scrollPane2;
    private String[] myString;  // creat string array
    //private int i; 
    private JTextArea showInfo; // create textAre 
    private JPanel topHolder = new JPanel();
    
    
    public MyGUI(MavBuy mavBuy)  {
        super(new BorderLayout(2,1));
        UIManager.put("TabbedPane.selected", new javax.swing.plaf.ColorUIResource(Color.RED));
        JTabbedPane tabbedPane = new JTabbedPane(); //build JTabbedPane
        // set tabs
        tabbedPane.addTab("Employee", new EmployeeGUI(mavBuy).getPanelMain());
        tabbedPane.addTab("Client",  new ClientGUI(mavBuy).getPanelMain());
        tabbedPane.addTab("Item", new ItemGUI(mavBuy).getPanelMain());
        tabbedPane.addTab("Sales Map", new SaleGUI(mavBuy).getPanelMain());
      // set color
        tabbedPane.setBackground(Color.YELLOW);
     //Add the tabbed pane to this panel.
        add(tabbedPane);
        //set background color
        setBackground(Color.BLACK);  
    }
    

    public static void createAndShowGUI(MavBuy mavBuy) {
        //Create and set up the window.
        JFrame frame = new JFrame("Welcome to MavBuy Inc.");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //Add content to the window.
        //frame.add(new Board(),BorderLayout.CENTER);
        frame.add(new MyGUI(mavBuy), BorderLayout.CENTER);
        //set color
        frame.setBackground(Color.RED);
        //Display the window.
        frame.pack();
        // set visible for display
        frame.setVisible(true);
    }
}
