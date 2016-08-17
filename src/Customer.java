/**
 * Programmer: Ji yeon Jeong
 * Language:	Java
 * Date:      10/06/2014
 * Purpose:   Class Customer to hold Customer informations
 */

import java.util.ArrayList;

public class Customer{
    private int custId; private String firstName; private String lastName;
    private Date custDob;  private int stNum;
    private String gender;  
    private String custTy; 
    private String st; 
    private String city;
    private String state;
    
    private ArrayList<String> customer;
    
    /**
     *Consturctor
     * @param stNum Street number
     * @param st Street Name
     * @param city City Name
     * @param state State Name
     */
    public Customer(int stNum, String st,String city,String state){
       this.stNum = stNum; this.st = st; this.city = city;
       this.state = state;
     }
    
   /**
     * Consturctor with firstName, lastName, gender, custTy  
     * @param custId = customer id
     * @param firstName = first name of customer
     * @param lastName = last name of customer
     * @param custDob = customer birthday
     * @param gender = male/ female
     * @param custTy = customer type
     */
    public Customer(int custId, String firstName, String lastName, Date custDob,
          String gender, String custTy){
       this.custId = custId; this.firstName = firstName;
       this.lastName = lastName; this.custDob = custDob; 
       this.gender = gender; this.custTy = custTy; 
    }

    /**
     *
     * Consturctor with firstName, lastName, gender, custTy  
     * @param custId = customer id
     * @param firstName = first name of customer
     * @param lastName = last name of customer
     * @param custDob = customer birthday
     * @param gender = male/ female
     * @param custTy = customer type
     * @param stNum Street Number
     * @param st Street Name
     * @param city City Name
     * @param state State Name
     */
    public Customer(int custId, String firstName, String lastName, Date custDob,
          String gender, String custTy, int stNum, String st,String city,
          String state){
       this.custId = custId; this.firstName = firstName;
       this.lastName = lastName;
       this.custDob = custDob; this.gender = gender; 
       this.custTy = custTy; 
       this.stNum = stNum; this.st = st; this.city = city;
       this.state = state;
    }
    // get methods to return each values in proper format
    public int getCId (){
        return custId;
    }
 
   public String getCustTy(){
        return custTy;
    }
   public int getStNum(){
        return stNum;
    }
   // get customer state
   public String getState(){
        return state;
    }
   public void setCustTy(String ty){
       custTy = ty;
   }
    public String getfName() 
    {
        return firstName;
    }

    public String getlName() 
    {
        return lastName;
    }
    public Date getDob(){
        return custDob;
    }
    public String getGender(){
        return gender;
    }
    public String getCity(){
        return city;
    }
    public String getSt(){
        return st;
    }
    @Override
    public String toString(){
        String output = String.format("Customer Id: "+custId+"\nName:\t"+firstName+" "+lastName
                +"\nGender:\t"+gender+"\nDOB:\t"+custDob+"\nType:\t"+custTy
                +"\nAddress:  %d %s %s %s", stNum, st, city, state);
        return output;
                }  //end method toString
      
}