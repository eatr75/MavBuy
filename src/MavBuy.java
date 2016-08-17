/**
 * Programmer: Ji yeon Jeong
 * Language:	Java
 * Date:      10/06/2014
 * Purpose: it is main body of this program, dealing all the operations                    
 */
import java.util.*;

public class MavBuy implements Proj2Constants {
    public Scanner userInput;     private final String companyName;
    private ArrayList<WebDesigner> myWD;
    private ArrayList<Agent> myAG;
    private ArrayList<Accountant> myAC;   private ArrayList<Person> myEmployees;
    private ArrayList<Item> myItems;
    private ArrayList<Customer> myClients;
    private ArrayList<Sell> mySales = new ArrayList<Sell>();
    private static int employeeId = 0;
    private WebDesigner curWD;     private Agent curAG; private Accountant curAC;         
    private Item curItem;
    private Customer curClient;     private Sell sales;
    private static Test tests = new Test();
    private Map<String,ArrayList<Sell>> saleHash = new HashMap<String,ArrayList<Sell>>();
   /**
    * Constructor with basic initialized values to set company name
    * @param cName = Company name
     * @param numEmp = # of employee
     * @param numItems # of items
     * @param numClients # of clients
    */
    /*
    public MavBuy(String cName,int numEmp, int numItems,int numClients) {
        companyName = cName;
        myEmployees = new ArrayList<Person>(numEmp);
        myItems = new ArrayList<Item>(numItems);
        myClients = new ArrayList<Customer>(numClients);
    } */
    /**
    * Constructor with basic initialized values to set company name
    * @param cName = Company name
    */
    public MavBuy(String cName){
        companyName = cName;
        myEmployees = new ArrayList<Person>();
        myWD =new ArrayList<WebDesigner>();
        myAC = new ArrayList<Accountant>();
        myAG = new ArrayList<Agent>();
        myItems = new ArrayList<Item>();
        myClients = new ArrayList<Customer>();
    }
    /**
     * create static employee Id,  add employee to list
     * @param empType = employee Type
     * @param fName = first Name
     * @param lName = last Name
     * @param emdob = EMployee Birthday
     * @param gender = male/ female
     * @param hireDate = hired date
     * @param salary = monthly salary
     * @param rRate = double value to be dealt in WD, AC, AG
     */
    //Hire new employees or to add employees into arrayList
    public void hireEmp(String empType,String fName,String lName,Date emdob,
            String gender,Date hireDate,double salary, double rRate){
        ++employeeId;
        if (empType.equalsIgnoreCase("wd")){
              curWD = new WebDesigner(employeeId, fName,  lName, emdob, gender,
           hireDate, salary, rRate);
              // myWD.add(curWD);
               myEmployees.add(curWD);
        }
        if (empType.equalsIgnoreCase("agt")){
              curAG = new Agent(employeeId, fName,  lName, emdob, gender,
           hireDate, salary, rRate);
             // myAG.add(curAG);
              myEmployees.add(curAG);
        }
        if (empType.equalsIgnoreCase("acct")){
              curAC = new Accountant(employeeId, fName,  lName, emdob, gender,
           hireDate, salary, rRate);
               //myAC.add(curAC);
               myEmployees.add(curAC);
        }    
    }
    /**
     * create item with input from file, using item id, item's company, condition, price, shipping cost
     * shipping days, able
     * @param itemId = Item Id
     * @param brandName = name of company
     * @param condition = condition of item
     * @param shippingFee = fee to ship
     * @param shippingDate = days to ship
    * @param price = price
     * @param type = type of item
     * @param storeAb = Store Availabilty
     * @param storeDt = Store Available Date
     */
    public void newItems(String itemId, String brandName, String condition,
            double shippingFee, double shippingDate, double price, String type, String storeAb, String storeDt){
        curItem = new Item(itemId,brandName,condition,shippingFee,shippingDate,price,type,storeAb, storeDt);
        myItems.add(curItem);
    }
    
    /**
     * create client with input from file using client id, first name, last name, dob
     * gender, client's membership type
     * @param cId = customer Id
     * @param fName = first name
     * @param lName = last name
     * @param dob = Birthday
     * @param gender = male/ female
     * @param type = gold or regular
     * @param stNum = Street number
     * @param city = name of city
     * @param st = street name
     * @param state = name of state
     */
    public void newCustomer(int cId, String fName, String lName, Date dob,
            String gender, String type, int stNum, String st,String city,String state){
        curClient = new Customer(cId, fName, lName, dob, gender, type, stNum, st, city, state);
        myClients.add(curClient);
    }
    
    /**
     * release an employee using employee id and company's employee list
     */
    /*public void releaseEmp() {
        int empId;
        userInput = new Scanner(System.in);
        System.out.print("Enter employee Id: ");
        empId = userInput.nextInt();
        if(tests.testEmployeeId(empId-1,myEmployees) ==  false) //check employee in list
            return;
        else {
            myEmployees.remove(empId-1);    //fire employee from arraylist
        }
    } */    
    /**
     * buy item for a client by using client id, item id, quantity, store id, and 
     * optional date
     * also call test methods to test for invalid input
     */
    public void buyItems() {
        int clientId;         String itemId;
        int quantity;         int storeId;          Date date; String output;
        // Customer Id
        System.out.print("Enter Customer Id: ");
        userInput = new Scanner(System.in);
        clientId = userInput.nextInt();
       
        if(tests.testCustomer(clientId, myClients) == false) //check client Id
            return;
        //get Item Id
        System.out.print("Enter Item Id: ");
        userInput = new Scanner(System.in);
        itemId = userInput.nextLine();
        
        if(tests.testItemId(itemId, myItems) == false) //check item Id
            return;
        //get qualtity and store Id
        System.out.print("Enter Quantity: ");
        userInput = new Scanner(System.in);
        quantity = userInput.nextInt();
        System.out.print("Enter Store Id (From 1 to 10): ");
        userInput = new Scanner(System.in);
        storeId = userInput.nextInt();
     
        if(tests.testQuantityStore(itemId,quantity,storeId,myItems) == false)
        //check quantity at store
            return;
        System.out.println("MM-DD-YY or Just blank for today");
        System.out.print("Date of purchase: ");
        userInput = new Scanner(System.in);
        String dateInput = userInput.nextLine();
       
        if (dateInput.equals(" "))   //generated date and entered date
           date = new Date(); // need to fix it to accept correct Date input
        else{
            date = new Date(dateInput);
        }
        System.out.println(date);
       if(tests.testDate(itemId,date, myItems) == false){
            System.out.println("Item Is Not Prepared");
       }
       else{
           Sell sale = new Sell(clientId,itemId,quantity,storeId,myItems,myClients,date);
            System.out.print(sale);
            mySales.add(sale);
        }
    }
   
    /**
     * with state as input, find if client in that state
     * if so, print client, if not, then print false
     * if input is * then print every states
     */
   /* public void displayCustomer() {
        userInput = new Scanner(System.in);
        String choice;
        
        System.out.println("Enter * for all");
        System.out.print("Enter State: ");
        choice = userInput.nextLine();
        if (choice.equals("*"))  {
            System.out.println("\n++++++LIST OF CUSTOMERS++++++");
            for(Customer client : myClients){    //print all customers for *
            
                System.out.println(client);
                }
            }
        else {
            if (tests.testCustomerState(choice,myClients) == false)
                return;
            System.out.println("\n++++++LIST OF CUSTOMERS++++++");
            for(Customer client : myClients) {
                    if (choice.equals(client.getState())) // if input equals, print
                        System.out.println(client);
                }
        }
        System.out.println("++++++End Of List++++++");
   }  */
    
    /**
     * compare input if id is in it. if so, print. if not, print invalid.
     * 0 will show all customers
     */
    /*
    public void customerId()
    {
        userInput = new Scanner(System.in);
        int choice;      
        System.out.println("Enter 0 For All Customer");
        System.out.print("Enter Customer Id: ");
        choice = userInput.nextInt();
        if(tests.testCustomer(choice, myClients) == false) //check client Id
            return;
        System.out.println("\n++++++LIST OF CUSTOMERS++++++");
        if (choice ==0)  {
            for(Customer client : myClients)    //dislpay all customers
                System.out.println(client);    //print
        }
        else
        {
            for(Customer client : myClients)    //display customer Id with same Id
            {
                if (choice == client.getCId())
                    System.out.println(client); //print
            }
        }
        System.out.println("++++++End Of List++++++");
    }
    */
    /**
     * check throughout employee list and display all
     */
    public void displayEmp() {
        System.out.println("\n++++++LIST OF EMPLOYEES------");
        for(Person employee : myEmployees){  //check all employees
            System.out.println(employee); //and print
            System.out.println("");
        }
        System.out.println("++++++End Of List++++++");
    }
  
    /**
     * show a client information with their id as input, test for id
     * if a client is found, print all their information along with purchase history
     * if not, print error
     */
    /*
    public void customerPort() {
        int choice;
        userInput = new Scanner(System.in);
        System.out.print("Please Enter Customer Id: ");
        choice = userInput.nextInt();
        if(tests.testCustomer(choice, myClients) == false) //check for client Id
            return;
        for(Customer client : myClients)    //client with same Id
            {
                if (choice == client.getCId())
                    System.out.println(client);
                for (Sell purchase : mySales)
                {
                    if (purchase.getClientId() == client.getCId())
                        System.out.println(purchase);
                }
            }
    }
    /**
     * show all in item list
     */
    /*
    public void displayItems()
    {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++LIST OF ITEMS+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|\tItem Id    /  Company  / Condition /"+" Ship Fee / Days /   Price   /\t  Type  \t   / Available Date /   Store Availability");       
        for(Item item : myItems)
        {
            System.out.println(item);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++End Of List+++++++++++++++++++++++++++++++++++++++++++++");
    }
    
    /**
     * This will store menu in Project2 to out.txt
     */
    public void showProj2menu(){
            MavBuyTest.foutput.println("");
            MavBuyTest.foutput.println("1) List Employees.");
            MavBuyTest.foutput.println("2) List Clients In a State.");
            MavBuyTest.foutput.println("3) List Portfolio Of A Client.");
            MavBuyTest.foutput.println("4) Release An Employee.");
            MavBuyTest.foutput.println("5) Display Items For Sale.");
            MavBuyTest.foutput.println("6) Display Customer Deatails.");
            MavBuyTest.foutput.println("7) Buy An Item for Customer.");
            MavBuyTest.foutput.println("0) Exit.");
    }

    /**
     * This will buy items for clients accoding to input file
     * @param inputArray this is menu input
     */
    public void buyItem(String[] inputArray){
             String output;
             int custId = Integer.parseInt(inputArray[ONEI]);
             String itemId = inputArray[TWOI];
             String soldDate = inputArray[THREEI];
             int quantity = Integer.parseInt(inputArray[FOURI]);
             int storeId = Integer.parseInt(inputArray[FIVEI]);
             //Let's see it works
             //System.out.printf("%d %s %s %d %d \n", custId, itemId, soldDate, quantity, storeId);
             
         // check Customer Id
         if(tests.testCustomer(custId, myClients) == false)
            return;
        
         // check Item Id
         if(tests.testItemId(itemId, myItems) == false) 
            return;
        
         // check qualtity and store Id
         if(tests.testQuantityStore(itemId,quantity,storeId,myItems) == false)
            return;
        // check Date
       Date sellDate;
        
       if (soldDate.equals("*"))   //find match with *
           sellDate = new Date(); // fix string to today
        else
           sellDate = new Date(soldDate);
       if(tests.testDate(itemId, sellDate, myItems) == false){
            MavBuyTest.foutput.println("Item Is Not Prepared");
       }
     else{
          Sell sale = new Sell(custId,itemId,quantity,storeId,myItems,myClients,sellDate);
            MavBuyTest.foutput.println(sale);
            mySales.add(sale); 
        }
           Sell sale = new Sell(custId,itemId,quantity,storeId,myItems,myClients,sellDate);
        mySales.add(sale);
        if (saleHash.containsKey(itemId))
        {
            saleHash.get(itemId).add(sale);
        }
        else
        {
            ArrayList<Sell> newList = new ArrayList<Sell>();
            newList.add(sale);
            saleHash.put(itemId, newList);
        }
          
        output = String.format("Client: %s %-9s %-7s bought %s of item %-15s for $%-7.2f on %-12s at store #%-4s with shipping cost: %.2f", custId,sale.getfName(),sale.getlName(), quantity, itemId,sale.getTotal(), sellDate,storeId, sale.getShippingFee());
        MavBuyTest.foutput.println(output);
    }
    /**
     * This will list purchased item to clients
     * @param inputArray this is menu input
     */
    public void listPurchase(String [] inputArray){
         int custId = Integer.parseInt(inputArray[ONEI]);
         int soldYear; 
         // check Customer Id
        if(tests.testCustomer(custId, myClients) == false) //check for client Id
            return;
        if (inputArray[TWOI].equals("*")){
            MavBuyTest.foutput.println("Listing item bought by " +custId + "in all years");
            for (Sell purchase : mySales)
              if (custId == purchase.getClientId()){
                     for(Item item : myItems){
                        if( item.getItemId().equalsIgnoreCase(purchase.getItemId()))
                            MavBuyTest.foutput.println(purchase.printHistory());  
                    }
                }
        }
        else{
            soldYear = Integer.parseInt(inputArray[TWOI]);
            MavBuyTest.foutput.println("Listing item bought by "+ custId + " in "+ soldYear);
            for (Sell purchase : mySales)
              if (custId == purchase.getClientId()){
                     for(Item item : myItems){
                        if( item.getItemId().equalsIgnoreCase(purchase.getItemId()))
                            MavBuyTest.foutput.println(purchase.printHistory());                          
                       }
                    }
            }       
    }
    /**
     * This will hire new employee, generating empId
     * @param inputArray this is menu input
     */
    public void hireNewEmp(String[] inputArray){
           String empType = inputArray[ONEI];
           String fName = inputArray[TWOI];
           String lName = inputArray[THREEI];
           String dob = inputArray[FOURI];
           String gender = inputArray[FIVEI];
           String hireDate =inputArray[SIXI];
           double baseSalary = Double.parseDouble(inputArray[SEVENI]);
           double value = Double.parseDouble(inputArray[EIGHTI]);
           employeeId++;

           switch (empType) {
            case "WD":
                {
                    Date nDob = new Date(dob);
                    Date jDate = new Date(hireDate);
                    curWD = new WebDesigner(employeeId, fName, lName, nDob, gender, jDate, baseSalary, value);
                     myEmployees.add(curWD);
                    
                    MavBuyTest.foutput.println("Hired WebDesigner "+fName+" "+lName+" on "+ jDate + 
                            "andd given him/her employee Id" + employeeId);
                }break;
            case "AGT":
                {
                    Date nDob = new Date(dob);
                    Date jDate = new Date(hireDate);
                    curAG = new Agent(employeeId, fName, lName, nDob, gender, jDate, baseSalary, value);
                     myEmployees.add(curAG);
                    MavBuyTest.foutput.println("Hired Agent "+fName+" "+lName+" on "+ jDate + 
                            "andd given him/her employee Id" + employeeId);
                }break;
            case "ACCT":
                {
                    Date nDob = new Date(dob);
                    Date jDate = new Date(hireDate);
                    curAC = new Accountant(employeeId, fName, lName, nDob, gender, jDate, baseSalary, value);
                   myEmployees.add(curAC);
                    MavBuyTest.foutput.println("Hired Accountant "+fName+" "+lName+" on "+ jDate + 
                            "andd given him/her employee Id" + employeeId);
                }break;
            }
        }
    /**
     * This will relese employee from arrayList
     * @param inputArray this is menu input
     */
        public void releaseEmp(String[] inputArray){
            int empId = Integer.parseInt(inputArray[ONEI]);
            String empType;
            
            if(tests.testEmployeeId(empId,myEmployees) ==  false) //check employee in list
            {
            MavBuyTest.foutput.println("Not Valid Employee ID  "+ empId);
            }
        else{
            for(Person person : myEmployees){
                if(empId == person.getPersonId())
                {
                 myEmployees.remove(person);
                 MavBuyTest.foutput.println("Relesase Employee " + person.getfName()+" "+person.getlName().toLowerCase());
                 return;
            }
            }
            for(Person person : myEmployees)
                MavBuyTest.foutput.println(person);
        }

  }
    /**
     * This will calculate monthly salary for each employees
     * @param inputArray this is menu input
     */
        public void computeSalary(String[] inputArray){
              int empId =Integer.parseInt(inputArray[ONEI]);
              int value = Integer.parseInt(inputArray[TWOI]);
              for(Person person : myEmployees){
                  if(empId == person.getPersonId()){
                      if(empId ==curWD.getEmpId()){
                      MavBuyTest.foutput.println("Month Salary for "+person.getfName()+" "+person.getlName()+" is $ "+curWD.computeSalary(value));
                      }
                      else if(empId ==curAG.getEmpId()){
                      MavBuyTest.foutput.println("Month Salary for "+person.getfName()+" "+person.getlName()+" is $ "+curAG.computeSalary(value));
                      }
                      else{
                      MavBuyTest.foutput.println("Month Salary for "+person.getfName()+" "+person.getlName()+" is $ "+curAC.computeSalary(value));
                      }
                   }
                  else if(empId != person.getPersonId()){
                      MavBuyTest.foutput.println("NOT valid");
                  }
              }   
        }
    
    /**
     * This will list all items sold
     * @param inputArray this is menu input
     */
        public void expenditure(String[] inputArray){
            String itemId = inputArray[ONEI];
            for (Sell purchase : mySales)
                if(itemId.equalsIgnoreCase(purchase.getItemId())){
                   MavBuyTest.foutput.println(purchase.printHistory());
                   }
                else{
                    MavBuyTest.foutput.println("This Item ID is NOT valid");
                }return;
            }
     /**
     * This will determine client status
     * @param inputArray this is menu input
     */
        public void clientStatus(String[] inputArray){
            int custId;
            int year;  
            // Case for all client and all year
            if (inputArray[ONEI].equals("*")){
                if (inputArray[TWOI].equals("*")){
                    for (Customer client : myClients){
                        double total = 0.0;
                    for (Sell purchase : mySales)
                         for(Item item : myItems){
                            if( item.getItemId().equalsIgnoreCase(purchase.getItemId()))
                                total = total +purchase.getTotal() + total;
                                if (total > 2000)
                                        client.setCustTy("GOLD");
                                else
                                    client.setCustTy("Regular");
                            }MavBuyTest.foutput.println("Status of Client " + client.getCId()+" is " + client.getCustTy());
                             return;
                           }
                    for (Customer client : myClients)
                                 MavBuyTest.foutput.println(client);
                }
                
                // case for all client but specific year
                else{
                    year = Integer.parseInt(inputArray[TWOI]);
                    for (Customer client : myClients){
                    double total = 0.0;
                    for (Sell purchase : mySales){
                        for(Item item : myItems){
                            if( item.getItemId().equalsIgnoreCase(purchase.getItemId()) && year == purchase.getDate().getYear())
                                total = total +purchase.getTotal() + total;
                                if (total > 2000)
                                        client.setCustTy("GOLD");
                                else
                                    client.setCustTy("Regular");
                            }
                        }MavBuyTest.foutput.println("Status of Client "+client.getCId()+" is " +client.getCustTy());
                         return;
                    }
                }
            }
            // case for specific client with all year
            else{
                custId = Integer.parseInt(inputArray[ONEI]);
                if(tests.testCustomerStatus(custId, myClients, myItems) == false) //check for client Id
                    return;
                if (inputArray[TWOI].equals("*")){
                    for (Customer client : myClients){
                        double total = 0.0;
                    for (Sell purchase : mySales)
                       if (custId == purchase.getClientId()){
                         for(Item item : myItems){
                            if( item.getItemId().equalsIgnoreCase(purchase.getItemId()))
                                total = total +purchase.getTotal() + total;
                                if (total > 2000)
                                    client.setCustTy("GOLD");
                                else
                                    client.setCustTy("Regular");
                            }
                        } MavBuyTest.foutput.println("Status of Client "+custId+" is GOLD");
                                 return;
                    }
                }
                // case for specific client with specific year
                else{
                    year = Integer.parseInt(inputArray[TWOI]);
                    for (Customer client : myClients){
                        double total = 0.0;
                    for (Sell purchase : mySales)
                    if (custId == purchase.getClientId()){
                        for(Item item : myItems){
                            if( item.getItemId().equalsIgnoreCase(purchase.getItemId()) && year == purchase.getDate().getYear())
                                total = total +purchase.getTotal() + total;
                                if (total > 2000)
                                        client.setCustTy("GOLD");
                                else
                                    client.setCustTy("Regular");
                            }
                        }MavBuyTest.foutput.println("Status of Client "+custId+" is " +client.getCustTy());
                            return;
                    }
                }
            }
        }
    // returns Employee list
    public ArrayList<Person> getMyEmployees() 
    {
        return myEmployees;
    }
    // returns iTEM list
    public ArrayList<Item> getMyItems() 
    {
        return myItems;
    }
    // returns Customer list
    public ArrayList<Customer> getMyClients() 
    {
        return myClients;
    }
    // returns sold list
    public ArrayList<Sell> getMySales() 
    {
        return mySales;
    }
    // returns hash map for sold item
    public Map<String, ArrayList<Sell>> getSaleHash() 
    {
        return saleHash;
    } 
}


