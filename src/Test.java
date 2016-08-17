/**
 * Programmer: Ji yeon Jeong
 * Language:	Java
 * Date:      10/06/2014
 * Purpose:   combine tests together for convenience.
 */

import java.util.*;

public class Test 
{
    /**
     * @param empId = employee Id
     * @param Employees = ArrayList<Employee>
     * @return False if employee is not in list, True if employee is in.
     */
        public boolean testEmployeeId(int empId, ArrayList<Person> Employees){
        if (empId < 0)
            return false;
        for (Person string : Employees)
        {
            if (string.getPersonId() == empId)
                return true;
        }
        return false;
    }
    
        
    // check client id if it exists

    /**
     * @param clientId - customer ID
     * @param Clients = ArrayList<Customer>
     * @return True if ID is found in list, False if Id is not found
     */
        public boolean testCustomer(int clientId, ArrayList<Customer> Clients){
        for (Customer client : Clients){
            if (client.getCId() == clientId)
                return true;
           }
        System.out.println("Invalid Customer.");
        return false;
    }
    
    /**
     * @param state = Sting State
     * @param Clients = ArrayList<Customer>
     * @return True if any customer is found, False if no customer is found
     */
        /*
           // check if a client is in state
        public boolean testCustomerState(String state, ArrayList<Customer> Clients){
        for (Customer client : Clients){
            if (client.getState().equals(state))
                return true;
            }
        System.out.println("No customer in this state.");
        return false;
    }   */
    //check if an item is in the system
    /**
     * 
     * @param clientId client Id
     * @param Clients ArrayList<Customer>
     * @param Items ArrayList<Item>
     * @return 
     */
    public boolean testCustomerStatus(int clientId,  ArrayList<Customer> Clients, ArrayList<Item> Items){
        for (Customer client : Clients){
            if (clientId == client.getCId())
                if(client.getCustTy().equalsIgnoreCase("gold"))
                    
                for(Item item : Items)
                   
                return true;
            }
        return false;
    } 
    /**
     * @param itemId = String item Id   
     * @param Items = ArrayList<Item>
     * @return True if item is found, False if item is not found
     */
        public boolean testItemId(String itemId, ArrayList<Item> Items) {
        int count = 0;
        for (Item item : Items) {
            if (item.getItemId().equals(itemId))
                count = count +1;
            }
        if (count == 0){ // if not in system, return false
              System.out.println("Invalid Item.");
            return false;
             }
        else
            return true;
    }
    // check if an date String is in it.

    /**
     * @param itemId Item Id
     * @param purchaseDate Date Item purchased
     * @param items = ArrayList<Item>
     * @return True if data matched, False if data not matched
     */
        public boolean testDate(String itemId, Date purchaseDate, ArrayList<Item> items){
            Date store;
            for (Item item : items)
                if (item.getItemId().equalsIgnoreCase(itemId)){
                        if (purchaseDate.isBefore(item.getStoreDate()))
                             return false;
                        else 
                            return true;
                }
            return true;
            
       }
    //check if a store has the required number of item

    /**
     * @param itemId = item Id
     * @param quantity = unit of item
     * @param storeId = store Id
     * @param Items = ArrayList<Item>
     * @return True if item is availalbe, False if item is not available
     */
        public boolean testQuantityStore(String itemId,int quantity, int storeId,
            ArrayList<Item>Items) {
        int[] able = new int[10]; 
        if ((storeId < 1) || (storeId > 10)){ // check for Invalid Id
        
            System.out.println("We Don't Have Such Store.");
            return false;
              }
        
        for (Item item : Items){
            if (item.getItemId().equals(itemId)){ // check for item availability
                able = item.getAble().clone();
                if (able[storeId-1] < quantity){ // if avilability is smaller than quantity
                    System.out.println("Out of Stock."); // print 
                    System.out.printf("Available quantity for item %s at store %d: %d",item.getItemId(),storeId,able[storeId-1]);
                    return false;
                      }
                }     
            }
        return true;
        }
}
