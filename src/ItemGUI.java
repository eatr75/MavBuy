/**
 * Item GUI
 * @author 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

public class ItemGUI extends JComponent{
    // initizliae panels
    private JPanel panelMain = new JPanel();
    private JPanel left = new JPanel();
    private JPanel right = new JPanel();
    private JPanel mid = new JPanel();
    // initialize label
    private JLabel label = new JLabel("List of Items:");
    // initialize buttons
    private JButton myButton = new JButton("Show Info");
    private JButton saleButton = new JButton("Sales");
    //private JButton clearText = new JButton("Clear");
    // initialize jlist
    private JList myJList;
    // initialize scroll panel
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    // initialize text area
    private JTextArea showInfo;
    // initialize object for default list model
    private DefaultListModel modelID =  new DefaultListModel();
    private DefaultListModel modelType=  new DefaultListModel();
    // initialize radio buttons
    private JRadioButton showIdList = new JRadioButton("ID",true);
    private JRadioButton showTypeList = new JRadioButton ("Type",false);
    private JRadioButton yrAll = new JRadioButton("All",true);
    private JRadioButton yr2014 = new JRadioButton("2014",false);
    private JRadioButton yr2013 = new JRadioButton ("2013",false);
    private ButtonGroup radioGroup = new ButtonGroup();
    private ButtonGroup radioYrGroup = new ButtonGroup();
    
    private int i;
    // initialize string, array, arraylist
    private String output;
    private String[] temp;
    private int year;
    private ArrayList<Item> myItems;
    private ArrayList<Sell> mySales;
    /*
    * Item GUI constructor 
    * @param  MavBuy mavBuy
    */
    public ItemGUI(MavBuy mavBuy)  {
        myItems = mavBuy.getMyItems();
        mySales = mavBuy.getMySales();
        // call swing method
        mid.setOpaque(false);
        left.setOpaque(false);
        right.setOpaque(false);
        panelMain.setBackground(Color.white);
        showIdList.setOpaque(false);
        showTypeList.setOpaque(false);
        yrAll.setOpaque(false);
        yr2014.setOpaque(false);
        yr2013.setOpaque(false);
        
        i = 0;
        // add radio buttons
        radioGroup.add(showIdList);
        radioGroup.add(showTypeList);
        radioYrGroup.add(yr2013);
        radioYrGroup.add(yr2014);
        radioYrGroup.add(yrAll);
        // set layout
        panelMain.setLayout(new BorderLayout());
        temp = new String[myItems.size()];
        // register items
        for(Item currItem : mavBuy.getMyItems()) {
            modelID.addElement(currItem.getItemId());
            temp[i] = currItem.getType();
            i++;
        }
      
        List <String> list = Arrays.asList(temp);
        // initialize hashSet
        Set <String> set = new HashSet <String> (list);
        // add type for Items
        for (String type : set)
            modelType.addElement(type);
        // initialize jList with fonts
        myJList = new JList();
        myJList.setModel(modelID);
        myJList.setSelectionMode((ListSelectionModel.MULTIPLE_INTERVAL_SELECTION));
        myJList.setFont(new Font("Roman", Font.ROMAN_BASELINE, 13));
        //initialize panel with size
        scrollPane1 = new JScrollPane(myJList);
        scrollPane1.setPreferredSize(new Dimension(200,500));
        /*
        clearText.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent event)  {
                showInfo.setText("");
            }
        }); */
        // initialize event handlers
        showInfoHandler(myButton);
        showSaleHandler(saleButton);
        // link event by case
        yr2014.addItemListener(
            new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent event)  {
                    year = 2014;
                }        });
        yr2013.addItemListener(
            new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent event) {
                    year = 2013;
                }       });
        yrAll.addItemListener(
            new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent event)  {
                    year = 0;
                }      });
        // initialize event handler for list
        showIdList.addItemListener(
            new ItemListener()   {
                @Override
                public void itemStateChanged(ItemEvent event)  {
                    myJList.setModel(modelID);
                    mid.removeAll();
                    // set button with label
                    myButton = new JButton("Show Info");
                    // link event handler
                    showInfoHandler(myButton);
                    //set button with label
                    saleButton = new JButton ("Sales");
                    showSaleHandler(saleButton);
                    // set layout size and type
                    mid.setLayout(new GridLayout(15,1,10,10));
                    //add buttons and textarea
                    mid.add(showIdList);
                    mid.add(showTypeList);
                    mid.add(myButton);
                    mid.add(saleButton);
                    mid.add(yr2013);
                    mid.add(yr2014);
                    mid.add(yrAll);
                   // mid.add(clearText);
                }             });
        
        showTypeList.addItemListener(
            new ItemListener()          {
                @Override
                public void itemStateChanged(ItemEvent event)            {
                    myJList.setModel(modelType);
                    myJList.setSelectionMode((ListSelectionModel.MULTIPLE_INTERVAL_SELECTION));
                    mid.removeAll();
                    myButton = new JButton("Sales");
                    
                    showSaleHandler(myButton);
                    mid.setLayout(new GridLayout(15,1,10,10));
                    mid.add(showIdList);
                    mid.add(showTypeList);
                    mid.add(myButton);
                    mid.add(yr2013);
                    mid.add(yr2014);
                    mid.add(yrAll);
                   // mid.add(clearText);
                }
            });

        
        showInfo = new JTextArea(30,40);
        
        showInfo.setEditable(false);
        scrollPane2 = new JScrollPane(showInfo);

        mid.setLayout(new GridLayout(15,1,10,10));
        mid.add(showIdList);
        mid.add(showTypeList);
        mid.add(myButton);
        mid.add(saleButton);
        mid.add(yr2013);
        mid.add(yr2014);
        mid.add(yrAll);
       // mid.add(clearText);



        left.add(scrollPane1);
        right.add(scrollPane2);


        panelMain.add(label,BorderLayout.NORTH);
    
        panelMain.add(left,BorderLayout.WEST);
        panelMain.add(mid,BorderLayout.CENTER);
        panelMain.add(right,BorderLayout.EAST);
    }

    
    public void showInfoHandler(JButton myButton)
    {
        myButton.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent event)
            {
                Object selectedValues[] = myJList.getSelectedValues();
                for ( i = 0; i < selectedValues.length; i ++)
                {    
                    printOutput(selectedValues[i]);
                }

            }
        });
    }
    
    public void showSaleHandler(JButton myButton)
    {

        myButton.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent event)
            {
                Object[] selectedValues = myJList.getSelectedValues();
                for ( i = 0; i < selectedValues.length; i ++)
                {   
                    printOutputSale(selectedValues[i], year);
                }

            }
        });
    }
    
    public void printOutput(Object empNameTemp)
    {
        String value = (String) empNameTemp;
            for (Item currItem : myItems)
            {
                if ((currItem.getItemId()).equalsIgnoreCase(value))
                {
                    output = String.format("Item ID: "+currItem.getItemId()+
                                           "\nBrand: "+currItem.getBrandName() + 
                                            "\nType: " + currItem.getType()+
                                            "\nCondition: "+ currItem.getCondition() +
                                            "\nPrice per Unit: $"+ currItem.getPrice() +
                                            "\nShipping Fee: $"+ currItem.getShippingFee() + 
                                            "\nShipping Days: " + (int)currItem.getShippingDate() +
                                            "\nAvailable on: " + currItem.getStoreDate()+
                                            "\nQuantity Available at Stores:  "+ Arrays.toString(currItem.getAble())+"\n\n");
                    showInfo.append(output);
                    break;
                }
            }
    }
    
    public void printOutputSale(Object itemType,int myYear)
    {
        double total = 0.0;
        String value = (String) itemType;
        
        if (myYear == 0)
        {
            for(Sell sale : mySales)
            {
                for (Item currItem : myItems)
                {
                    if (currItem.getType().equalsIgnoreCase(value) && currItem.getItemId().equalsIgnoreCase(sale.getItemId()))
                    {
                        output = String.format("\nItem ID: %s"+ "\nBrand: %s" + "Type: %s\n" +
                                "Sold on: %s\n" +"Discount: %s\n"+"Quantity: %s\n"+"Total Price: $%.2f\n\n",
                                                currItem.getItemId(),currItem.getBrandName(),currItem.getType()
                                                ,sale.getDate(),sale.isDiscount(),sale.getQuantity()
                                                ,sale.getTotal());
                        showInfo.append(output);
                        total = total + sale.getTotal();
                    }
                    else if (currItem.getItemId().equalsIgnoreCase(value) && currItem.getItemId().equalsIgnoreCase(sale.getItemId()))
                    {
                        output = String.format("\nItem ID: %s"+ "\nBrand: %s" + "Type: %s\n" +
                                "Sold on: %s\n" +"Discount: %s\n"+"Quantity: %s\n"+"Total Price: $%.2f\n\n", 
                                                    currItem.getItemId(),currItem.getBrandName(),currItem.getType(),
                                                    sale.getDate(),sale.getQuantity(),sale.getClientId(),sale.getTotal());
                        showInfo.append(output);
                        total = total + sale.getTotal();
                    }
                }
            }
            output = String.format("\nTotal Profit by Selling %s in All Year: $%.2f \n\n",itemType,total);
            showInfo.append(output);
        }
        else
        {
            for(Sell sale : mySales)
            {
                for (Item currItem : myItems)
                {
                    if (currItem.getType().equalsIgnoreCase(value) 
                            && currItem.getItemId().equalsIgnoreCase(sale.getItemId())
                            && sale.getDate().getYear() == myYear)
                    {
                        output = String.format("\nItem ID: %s"+ "\nBrand: %s" + "Type: %s\n" +
                                "Sold on: %s\n" +"Discount: %s\n"+"Quantity: %s\n"+"Total Price: $%.2f\n\n",
                                                currItem.getItemId(),currItem.getBrandName(),currItem.getType()
                                                ,sale.getDate(),sale.isDiscount(),sale.getQuantity()
                                                ,sale.getTotal());
                        showInfo.append(output);
                        total = total + sale.getTotal();
                    }
                    else if (currItem.getItemId().equalsIgnoreCase(value)
                                && currItem.getItemId().equalsIgnoreCase(sale.getItemId())
                                && sale.getDate().getYear() == myYear)
                    {
                        output = String.format("\nItem ID: %s"+ "\nBrand: %s" + "Type: %s\n" +
                                "Sold on: %s\n" +"Discount: %s\n"+"Quantity: %s\n"+"Total Price: $%.2f\n\n", 
                                                    currItem.getItemId(),currItem.getBrandName(),currItem.getType(),
                                                    sale.getDate(),sale.getQuantity(),sale.getClientId(),sale.getTotal());
                        showInfo.append(output);
                        total = total + sale.getTotal();
                    }
                }
            }
            output = String.format("\nTotal Profit by Selling %s in %s: $%.2f \n\n",itemType,year,total);
            showInfo.append(output);
        }
        
    }

    public JPanel getPanelMain() 
    {
        return panelMain;
    }
        
        
}
