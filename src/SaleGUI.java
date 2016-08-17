/**
 *
 * @author 
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SaleGUI extends JComponent{
    // initialize elements
    private JPanel panelMain = new JPanel();
    private JPanel panelTop = new JPanel();
    private JTextArea showInfo;
    private Map<String,ArrayList<Sell>> saleHash;
    private Map<Double,String> temp = new HashMap<Double,String>();
    private JScrollPane scrollPane;
    private JLabel title = new JLabel("List of Sold Items");
    private double[] total;
     /*
    * sale constructor 
    * @param  MavBuy mavBuy
    */
    public SaleGUI(MavBuy mavBuy)    {
        //set background color
        panelMain.setBackground(Color.white);
        panelTop.setOpaque(false);
        // object for hash map
        saleHash = mavBuy.getSaleHash();
        total = new double[saleHash.size()];
        // determine size of area
        showInfo = new JTextArea(20,40);
        showInfo.setEditable(false);
        // set fond type and size
        title.setFont(new Font("Roman", Font.ROMAN_BASELINE, 12));
        scrollPane = new JScrollPane(showInfo);
        scrollPane.setPreferredSize(new Dimension(700,300));
        // set layout type
        panelMain.setLayout(new BorderLayout());
        
        Set<String> itemIds = saleHash.keySet();
        int i = 0;
        // find item id
        for (String currId : itemIds)  {
            ArrayList<Sell> mySales = saleHash.get(currId);
            // find total
            for (Sell sale : mySales)   {
                total[i] = total[i] + sale.getTotal();
            }
            temp.put(total[i], currId);
            i++;
        }
        
        Set<Double> itemTotal = temp.keySet();
        // make hash map reverse order
        TreeSet<Double> sortedTotal = new TreeSet<Double>(Collections.reverseOrder());
        sortedTotal.addAll(itemTotal);
        // print info
        for (double total : sortedTotal)        {
            showInfo.append("Item : " + temp.get(total) + "\n");
            for (Sell sale : saleHash.get(temp.get(total)))    {
                String output = String.format("Sold on: %s" + "\nQuantity: %s" + "\nFor: $%.2f"
                                                , sale.getDate() , sale.getQuantity() , sale.getTotal());
                showInfo.append(output);
            }
            showInfo.append(String.format("\nTotal Profit: $%.2f\n\n", total));
        }
        // add title
        panelTop.add(title);
        // add panel to layout
        panelMain.add(panelTop,BorderLayout.NORTH);
        panelMain.add(scrollPane,BorderLayout.CENTER);
    }
    // return panel main
    public JPanel getPanelMain() 
    {
        return panelMain;
    }
}

    
    