/**
 * Programmer: Ji yeon Jeong
 * Language:	Java
 * Date:      10/06/2014
 * Purpose:  Store required info. in Sell
 
 */

import java.util.ArrayList;


public class Sell
{
    private String fName;
    private String lName;
    private int clientId;
    private String itemId;
    private int quantity;
    private int storeId; private double shippingFee;
    private double shippingDate;
    private Date date;
    private double total;
    private double discount = 1.0;
    private boolean discounting = false;
    
    //constructor, build item after the sale, and store history
    /**
     *
     * @param clientId - customer ID
     * @param itemId = item Id
     * @param quantity = amount of item
     * @param storeId = store id
     * @param Items = ArrayList<Item>
     * @param Customers = ArrayList<Customer>
     * @param date = date purchased or sold
     */
         public Sell(int clientId, String itemId,int quantity,int storeId,
             ArrayList<Item> Items,ArrayList<Customer> Customers,Date date){
        this.clientId = clientId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.storeId = storeId;
        this.date = date;
        //build item
        int[] able = new int[10];
         for (Customer client : Customers){
            if (client.getCId() == clientId){
                if(client.getCustTy().equalsIgnoreCase("gold")){
                    discount = 0.97;
                        } 
                    } 
            } 
          for (Item item : Items){
            if (item.getItemId().equalsIgnoreCase(itemId)){
                able = item.getAble().clone();
                able[storeId-1] = able[storeId-1] - quantity;
                computeTotal(item);
                item.setAble(able);
                }     
            }
        //determine whether discount will be applicable
     }
    public void computeTotal(Item item){
       total = discount * item.getPrice() * quantity;
    }
    public double getTotal(){
        return total;
    }
     // returns the client id
    public int getClientId() {
        return clientId;
    }
    public Date getDate(){
        return date;
    }
    public String getItemId(){
        return itemId;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getfName() 
    {
        return fName;
    }

    public String getlName() 
    {
        return lName;
    }
    public double getShippingDate(){
        return shippingDate;
    }
    public double getShippingFee(){
        return shippingFee;
    }
    public String printHistory(){
        
        String output =  String.format("%d quantity of %s"+"for %.2f" + "on %s", quantity, itemId, getTotal(), getDate());
        return output;
    }
     public boolean isDiscount() 
    {
        return discounting;
    }
    
    @Override
    public String toString() {
        String output = String.format("\nClient: #%d \n\tBought: %s (%s) \n\tAt Store: #%s \n\tDate of Purchase: %s "
                + "\n\tTotal Price: %.2f",clientId,itemId,quantity,storeId,date, getTotal());
        return output;
    } //end of toString method
    
    
}
