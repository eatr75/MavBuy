/**
 *
 * @author 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EmployeeGUI extends JComponent{
    // initizliae panels
    private JPanel panelMain = new JPanel();
    private JPanel left = new JPanel();
    private JPanel right = new JPanel();
    private JPanel mid = new JPanel();
    // initizliae label
    private JLabel label = new JLabel("List of Employees:");
    // initizliae buttons
    private JButton myButton = new JButton("Show");
    //private JButton clearText = new JButton("Clear");
    private JButton calcAge = new JButton("Oldest Employees");
    // initizliae elements
    private String[] myString;
    // initizliae Jlist
    private JList myJList;
    // initizliae scroll pane
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    // initizliae textarea
    private JTextArea showInfo;

    private int i;  private String type;
    private String output;
    // initizliae arraylist
    private ArrayList<Person> myEmployees;
     /*
    *  employee GUI constructor 
    * @param  MavBuy mavBuy
    */
    public EmployeeGUI(MavBuy mavBuy) {
        myEmployees = mavBuy.getMyEmployees();
        i = 0;
        //set layout
        panelMain.setLayout(new BorderLayout());
        
        calcAge.setToolTipText("Show Oldest Employee");
        
        myString = new String[mavBuy.getMyEmployees().size()];
        i = 0;
        // add names
        for(Person currPerson : mavBuy.getMyEmployees()) {
            myString[i] = currPerson.getfName() + " " + currPerson.getlName();
            i++;
        }
        // initialize jlist
        myJList = new JList(myString);
        myJList.setSelectionMode((ListSelectionModel.MULTIPLE_INTERVAL_SELECTION));
        //myJList.add(new JLabel("MY LIST"));
        // set fonds type and size
        myJList.setFont(new Font("Roman", Font.ROMAN_BASELINE, 12));
        // initialize scrollpane
        scrollPane1 = new JScrollPane(myJList);
        // initialize size
        scrollPane1.setPreferredSize(new Dimension(200,200));
        // event handler
        calcAge.addActionListener(
        new ActionListener()     {
            @Override
            public void actionPerformed (ActionEvent event)    {
                calcOldestAge();
            }
        });
        /*
        clearText.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent event)
            {
                showInfo.setText("");
            }   });
        */
        // event handler
        myButton.addActionListener(
        new ActionListener()  {
            @Override
            public void actionPerformed (ActionEvent event)  {
                Object selectedValues[] = myJList.getSelectedValues();
                for ( i = 0; i < selectedValues.length; i ++)  {
                    showInfo.append(getOutput(selectedValues[i],myEmployees));
                }
               // showInfo.setText("Selected " + selectedValues.length + " employees");
            }       });

        showInfo = new JTextArea(30,30);
        showInfo.setEditable(false);
        scrollPane2 = new JScrollPane(showInfo);
        
        mid.setLayout(new GridLayout(10,1,10,10));
        mid.add(myButton);
        mid.add(calcAge);
        //mid.add(clearText);
        
        left.add(scrollPane1);
        right.add(scrollPane2);

        panelMain.add(label,BorderLayout.NORTH);
        panelMain.add(left,BorderLayout.WEST);
        panelMain.add(mid,BorderLayout.CENTER);
        panelMain.add(right,BorderLayout.EAST);
        mid.setOpaque(false);
        left.setOpaque(false);
        right.setOpaque(false);
        panelMain.setBackground(Color.white);
    }

    // print output
    public String getOutput(Object empNameTemp,ArrayList <Person> myEmployees) {
        String empName = (String) empNameTemp;
        for (Person currEmp : myEmployees)   {
            if ((currEmp.getfName()+ " " + currEmp.getlName()).equalsIgnoreCase(empName))   {
                type = currEmp.getClass().getName();
                output = String.format("Employee ID: "+currEmp.getPersonId()+ "\nType: " +type+
                                        "\nName: "+currEmp.getfName() + " " + currEmp.getlName() +
                                        "\nGender: " + currEmp.getGender() +
                                        "\nBirth Date: " + currEmp.getDob() + 
                                        "\nJoined: " + currEmp.getJoined()+"\n\n");
                break;
            }
        }
        return output;
    }
    // calculate oldest member
    public void calcOldestAge() {
        i = 0;
        int maxTime = 0;
        Date today = new Date();
        int[] time = new int[myEmployees.size()];
        for (Person person : myEmployees) {
            time[i] = person.getJoined().daysBetween(today);
            i++;
        }
        maxTime = time[0];
        for ( i = 0; i < time.length; i++){
            if (time[i] > maxTime)
                maxTime = time[i];
        }
        
        for (Person person : myEmployees) {
            if (person.getJoined().daysBetween(today) == maxTime) {
                showInfo.append("Oldest Member(s)");
                output = String.format("\nEmployeeID: %s"+"\nType: %s"+"\nName: %s %s"+
                                        "\nDOB: %s"+"\nTime: %s days\n\n",
                                                person.getPersonId(),person.getClass().getName()
                                                ,person.getfName(),person.getlName(),person.getDob(), maxTime);
                showInfo.append(output);
            }
        }
    }
    // return panel main
    public JPanel getPanelMain() {
        return panelMain;
    }
        
        
}
