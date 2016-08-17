/**
 *
 * @author 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ClientGUI extends JComponent{
    //initialize panels and buttons for the main panel 
    private JPanel panelMain = new JPanel();
    private JPanel left = new JPanel();
    private JPanel right = new JPanel();
    private JPanel mid = new JPanel();
    // add label for main info
    private JLabel label = new JLabel("List of Clients:");
    // Create buttons
    private JButton myButton = new JButton("Show Info");
    private JButton portButton = new JButton("Portfolio");
    //private JButton clearText = new JButton("Clear");
    private JButton rankButton = new JButton("Rank");
    
    private JList myJList;
    // create scroll pane
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    // create text area
    private JTextArea showInfo;
    // defult list model to refresh
    private DefaultListModel modelID = new DefaultListModel();
    private DefaultListModel modelNames = new DefaultListModel();
    // create radio buttons
    private JRadioButton showIdList = new JRadioButton("ID",false);
    private JRadioButton showNameList = new JRadioButton ("Names",true);
    private JRadioButton yrAll = new JRadioButton("All",true);
    private JRadioButton yr2014 = new JRadioButton("2014",false);
    private JRadioButton yr2013 = new JRadioButton ("2013",false);
    private ButtonGroup radioGroup = new ButtonGroup();
    private ButtonGroup radioYrGroup = new ButtonGroup();
    
    private int i;
    
    private String output; // store output as string
    private String[] temp; // hold info temporarly
    private int year; 
    private ArrayList<Customer> myClients; // call Array
    private ArrayList<Sell> mySales;

    /**
     * constructor for client GUI 
     * @param myMav 
     */
    public ClientGUI(MavBuy myMav )    {
        myClients = myMav.getMyClients(); // call the list
        mySales = myMav.getMySales(); 
        mid.setOpaque(false); // call swing method setOpaque
        left.setOpaque(false);
        right.setOpaque(false);
        panelMain.setBackground(Color.white); // set background as white
        showIdList.setOpaque(false);
        showNameList.setOpaque(false);
        yrAll.setOpaque(false);
        yr2014.setOpaque(false);
        yr2013.setOpaque(false);
        i = 0;
        radioGroup.add(showIdList);
        radioGroup.add(showNameList);
        radioYrGroup.add(yr2013);
        radioYrGroup.add(yr2014);
        radioYrGroup.add(yrAll);
        
        panelMain.setLayout(new BorderLayout());
        temp = new String[myClients.size()];
        // search id among list
        for(Customer currClient : myMav.getMyClients()) {
            modelID.addElement(Integer.toString(currClient.getCId()));
            modelNames.addElement(currClient.getfName()+currClient.getlName());
        }
        
        myJList = new JList();
        myJList.setModel(modelNames);
        // select selection mode
        myJList.setSelectionMode((ListSelectionModel.MULTIPLE_INTERVAL_SELECTION));
        // adjust font size and type
        myJList.setFont(new Font("Roman", Font.ROMAN_BASELINE, 12));

        scrollPane1 = new JScrollPane(myJList);
        // determine panel size
        scrollPane1.setPreferredSize(new Dimension(200,350));

        /*
        clearText.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent event)
            {
                showInfo.setText("");
            }
        }); */
        
        // link to event handlers
        showInfoHandler(myButton); 
        showPortHandler(portButton);
        calculateRank(rankButton);
        
        // adjust item list by selecting year
        yr2014.addItemListener(
            new ItemListener()       {
                @Override
                public void itemStateChanged(ItemEvent event)         {
                    year = 2014;
                }      });
        yr2013.addItemListener(
            new ItemListener()            {
                @Override
                public void itemStateChanged(ItemEvent event)           {
                    year = 2013;
                }       });
        yrAll.addItemListener(
            new ItemListener()            {
                @Override
                public void itemStateChanged(ItemEvent event)            {
                    year = 0;
                }            });
        
        // 
        showIdList.addItemListener(
            new ItemListener()            {
                @Override
                public void itemStateChanged(ItemEvent event)                {
                    myJList.setModel(modelID);
                    myJList.setSelectionMode((ListSelectionModel.SINGLE_SELECTION));
                    mid.removeAll();
                    // label button
                    myButton = new JButton ("Portfolio");
                    rankButton = new JButton("Rank");
                    // link event handler
                    showPortHandler(portButton);
                    calculateRank(rankButton);
                    mid.setLayout(new GridLayout(15,1,10,10));
                    mid.add(showNameList);
                    mid.add(showIdList);
                    // add buttons
                    mid.add(portButton);
                    mid.add(yr2013);
                    mid.add(yr2014);
                    mid.add(yrAll);
                    mid.add(rankButton);
                //    mid.add(clearText);
                }            });
        
        showNameList.addItemListener(
            new ItemListener()   {
                @Override
                public void itemStateChanged(ItemEvent event)      {
                    myJList.setModel(modelNames);
                    myJList.setSelectionMode((ListSelectionModel.MULTIPLE_INTERVAL_SELECTION));
                    mid.removeAll();
                    myButton = new JButton("Show Info");
                    rankButton = new JButton("Rank");
                    showInfoHandler(myButton);
                    calculateRank(rankButton);
                    mid.setLayout(new GridLayout(15,1,10,10));
                    mid.add(showNameList);
                    mid.add(showIdList);
                    mid.add(myButton);
                    mid.add(yr2013);
                    mid.add(yr2014);
                    mid.add(yrAll);
                    mid.add(rankButton);
                //    mid.add(clearText);
                }       });
           
        // set size for showing area
        showInfo = new JTextArea(30,40);
        showInfo.setEditable(false);
        scrollPane2 = new JScrollPane(showInfo);
        // build layout
        mid.setLayout(new GridLayout(15,1,10,10));
        // add buttons
        mid.add(showNameList);
        mid.add(showIdList);
        mid.add(myButton);
        mid.add(yr2013);
        mid.add(yr2014);
        mid.add(yrAll);
        mid.add(rankButton);
        //mid.add(clearText);
        // add panels
        left.add(scrollPane1);
        right.add(scrollPane2);
        // add them by direction
        panelMain.add(label,BorderLayout.NORTH);
        panelMain.add(left,BorderLayout.WEST);
        panelMain.add(mid,BorderLayout.CENTER);
        panelMain.add(right,BorderLayout.EAST);
    }
    // handle rankButton
    public void calculateRank(JButton rankButton)  {
        rankButton.addActionListener(
        new ActionListener()  {
            @Override
            public void actionPerformed (ActionEvent event)  {
                spendingRank(year);         
            }   });
    }
    //get rank by cases
    public void spendingRank(int myYear)  {
        switch(myYear)   {
            case (0): rankAllYear();
                break;
            case (2014): rankYear(2014);
                break;
            case (2013): rankYear(2013);
                break;
        }
    }
    // get rank from all year
    public void rankAllYear(){
        double max = 0.0;
        double min = 0.0;
        ArrayList<Customer> topSpending = new ArrayList<Customer> ();
        ArrayList<Customer> botSpending = new ArrayList<Customer> ();
        double[] purchases = new double[myClients.size()];
        // search and add them into total
        for (Sell sale : mySales)  {
            for (Customer client : myClients)  {
                if (sale.getClientId() == client.getCId()){
                    purchases[myClients.indexOf(client)] = purchases[myClients.indexOf(client)] + sale.getTotal();
                }
            }
        }

        for (i = 0; i < purchases.length; i++) {
            if (max <= purchases[i])
                max = purchases[i];
            if (min >= purchases[i])
                min = purchases[i];
        }
        // comparing higest and lowest VIP
        for (i = 0; i < purchases.length; i++) {
            if (max == purchases[i]) {
                Customer client = myClients.get(i);
                topSpending.add(client);
            }
            else if (min == purchases[i]){
                Customer client = myClients.get(i);
                botSpending.add(client);
            }
        }

        // add info to text area
        showInfo.append("The Highest VIP in "+"All year"+" is(are): \n");
        for (Customer client : topSpending)  {
            output = String.format("ID: %s %s %s\n" +"Total: $%.2f\n\n"
                                        , client.getCId(), client.getfName(), client.getlName(),max);
            showInfo.append(output);
        }
         // add info to text area
        showInfo.append("The Lowest VIP in "+"All year"+" is(are): \n");
        for (Customer client : botSpending)       {
            output = String.format("ID: %s %s %s\n" + "TOTAL of : $%.2f\n\n"
                                        , client.getCId(), client.getfName(), client.getlName(),min);
            showInfo.append(output);
        }
        
    }
    //  similar to rankAllYear method.
    // But, this method depends on years
    public void rankYear(int year)   {
        double max = 0.0;
        double min = 0.0;
        ArrayList<Customer> topSpending = new ArrayList<Customer> ();
        ArrayList<Customer> botSpending = new ArrayList<Customer> ();
        double[] purchases = new double[myClients.size()];
        // search and add them into total
        for (Sell sale : mySales)   {
            for (Customer client : myClients)    {
                if ((sale.getClientId() == client.getCId()) && (sale.getDate().getYear() == year))    {
                    purchases[myClients.indexOf(client)] = purchases[myClients.indexOf(client)] + sale.getTotal();
                }
            }
        }

        for (i = 0; i < purchases.length; i++)   {
            if (max <= purchases[i])
                max = purchases[i];
            if (min >= purchases[i])
                min = purchases[i];
        }
         // comparing higest and lowest VIP
        for (i = 0; i < purchases.length; i++)  {
            if (max == purchases[i])            {
                Customer client = myClients.get(i);
                topSpending.add(client);
            }
            else if (min == purchases[i])   {
                Customer client = myClients.get(i);
                botSpending.add(client);
            }
        }
        // print in text area
        showInfo.append("The Highest VIP in "+year+" is(are): \n");
        for (Customer client : topSpending)   {
            output = String.format("ID: %s %s %s\n" +"Total: $%.2f\n\n"
                                        , client.getCId(), client.getfName(), client.getlName(),max);
            showInfo.append(output);
        }
        showInfo.append("The Lowest VIP in "+year+" is(are): \n");
        for (Customer client : botSpending)  {
            output = String.format("ID: %s %s %s\n" + "TOTAL of : $%.2f\n\n"
                                        , client.getCId(), client.getfName(), client.getlName(),min);
            showInfo.append(output);
        }
    }
    
    //event handler to show button
    public void showInfoHandler(JButton myButton) {
        myButton.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent event)   {
                Object selectedValues[] = myJList.getSelectedValues();
                for ( i = 0; i < selectedValues.length; i ++)  {    
                    printOutput(selectedValues[i]);
                }
            }   });
    }
    //event handler to protfoilo button
    public void showPortHandler(JButton myButton) {
        myButton.addActionListener(
        new ActionListener()   {
            @Override
            public void actionPerformed (ActionEvent event)      {
                Object[] selectedValues = myJList.getSelectedValues();
                for ( i = 0; i < selectedValues.length; i ++)      {   
                    printOutputPort(selectedValues[i], year);
                }
            }   });
    }
    // prints value
    public void printOutput(Object empNameTemp)  {
        String value = (String) empNameTemp;
        //search name
            for (Customer currPerson : myClients)  {
                // name is equal print
                if ((currPerson.getfName()+currPerson.getlName()).equalsIgnoreCase(value)) {
                  output = String.format("Client ID: %s" +"\nName: %s %s" +"\nBirth Date: %s "
                            + "\nGender: %s"+"\nStatus: %s"+ "\nAddress:  %d %s %s %s\n\n"
                                                ,currPerson.getCId(),currPerson.getfName()
                                                ,currPerson.getlName(),currPerson.getDob()
                                                ,currPerson.getCustTy(),currPerson.getGender()
                                                ,currPerson.getStNum(),currPerson.getSt(), currPerson.getCity()
                                                ,currPerson.getState()   );
                    showInfo.append(output);
                    break;
                }
            }
    }
    // print portfoilo
    public void printOutputPort(Object itemType,int myYear) {
        String value = (String) itemType;
        String[] saleId = new String[mySales.size()];
        for (Sell sale :mySales)  {
            saleId[i] = Integer.toString(sale.getClientId());
            i++;
        }
        // if all year
        if (myYear == 0) {
            //search among customer
            for(Customer currPerson : myClients) {
                // if id equls, print name
                    if (Integer.toString(currPerson.getCId()).equals(value) && 
                            Arrays.asList(saleId).contains(Integer.toString(currPerson.getCId()))) {
                        output = String.format("Purchases by %s %s\n", currPerson.getfName(),currPerson.getlName());
                        showInfo.append(output);
                        // if sale id and c id equls, print item
                        for (Sell sale : mySales)  {
                            if (currPerson.getCId() == sale.getClientId())  {
                                 output = String.format("Bought Item: %s \nOn: %s\nQuantity: %s \nTotal: $%.2f\n\n"
                                                        ,sale.getItemId()
                                                        ,sale.getDate(),sale.getQuantity()
                                                        ,sale.getTotal());
                                showInfo.append(output);
                            }
                        }
                    }
            }
        }
        else{ // else case
            for(Customer currPerson : myClients){
                    if (Integer.toString(currPerson.getCId()).equals(value) && 
                            Arrays.asList(saleId).contains(Integer.toString(currPerson.getCId()))) {
                        output = String.format("Purchases by %s %s in %s\n", currPerson.getfName(),currPerson.getlName(),year);
                        showInfo.append(output);
                        for (Sell sale : mySales)   {
                            if ((currPerson.getCId() == sale.getClientId()) && (sale.getDate().getYear() == myYear)) {
                                 output = String.format("Bought Item: %s \nOn: %s\nQuantity: %s \nTotal: $%.2f\n\n"
                                                        ,sale.getItemId()
                                                        ,sale.getDate(),sale.getQuantity()
                                                        ,sale.getTotal());
                                showInfo.append(output);
                            }
                        }
                    }
            }
        }
        
    }
// return panel main
    public JPanel getPanelMain() {
        return panelMain;
    }
        
}
