/**
 * Programmer: Ji yeon Jeong
 * Language:	Java
 * Date:      10/06/2014
 * Purpose:   Class Item to hold Item informations
 */

import java.util.Arrays;

public class Item {
    private String itemId; private String brandName;
    private String condition; private double shippingFee;
    private int[] able = new int[10];  
    private double shippingDate;
    private double price; private String type;
    private String storeAb;
    private String storeDt;
    private Date storeDate;
 /**
  * Consturctor when storeAble is String 
     * @param itemId = Item Id
     * @param brandName = name of company
     * @param condition = conditon of item
     * @param shippingFee = fee to ship
     * @param shippingDate = days to ship
     * @param price = item price
     * @param type = Item type
     * @param storeAb = store Availability
     * @param storeDt = store Available Date
  */
    public Item(String itemId, String brandName, String condition,
            double shippingFee, double shippingDate, double price,
            String type, String storeAb, String storeDt){
        int count = 0;
        this.itemId = itemId;   this.brandName = brandName; 
        this.condition = condition; this.shippingFee = shippingFee;
        this.shippingDate = shippingDate;
        this.price = price;   this.type = type;
        this.storeAb = storeAb; storeDate = new Date(storeDt);
        
         for (String temp: storeAb.split(","))
        {
            able[count] = Integer.parseInt(temp);
            count = count +1;
        } // chop storeAb to get store id and its elements
     }
    
    /**
     * Constructor when store Able is array
    * @param itemId = Item Id
     * @param brandName = name of company
     * @param condition = conditon of item
     * @param shippingFee = fee to ship
     * @param shippingDate = days to ship
     * @param price = item price
     * @param type = Item type
     * @param storeAble = Store availability in array form
     * @param storeDt = store Available Date
     */
     public Item(String itemId, String brandName, String condition,
                 double shippingFee, double shippingDate, double price, 
                 String type,int[] storeAble,String storeDt){ 
        int count = 0;
        this.itemId = itemId; 
        this.brandName = brandName; 
        this.condition = condition; 
        this.shippingFee = shippingFee;
        this.shippingDate = shippingDate;
        this.price = price;
        this.type = type;
        
         for (int i: storeAble)
        {
            able[count] = i;
            count = count +1;
        }
    }
  
    // get methods to return each values in proper format
    public Date getStoreDate(){
        
        return storeDate;
    }
    public String getCondition() 
    {
        return condition;
    }
    public double getPrice() 
    {
        return price;
    }
    public String getType(){
        return type;
    }
    public double getShippingDate(){
        return shippingDate;
    }
    public double getShippingFee(){
        return shippingFee;
    }
    public String getBrandName(){
        return brandName;
    }
    public String getItemId(){
        return itemId;
    }
    public int[] getAble(){
        return able;
    }
/**
 * update the new store able after purchase with able[]
 * @param able int array
 */  
    // set methods to store each values in proper format
    public void setAble(int[] able) 
    {
        this.able = able;
    }
    
    @Override
    public String toString(){
       String output = String.format("| %-16s / %-9s / %-10s / %-7s / %-4s / %-9s / %-23s / %s / %s ",
               itemId,brandName,condition,shippingFee,shippingDate,price,type,
               storeDt, Arrays.toString(able));
        return output;
    } //end method toString
   
}
