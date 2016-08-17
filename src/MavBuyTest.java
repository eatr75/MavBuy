/**
 * Programmer: 	Sharma Chakravarthy, Soumyava Das
 * Language:	Java
 * date:        09/10/2012
 * Purpose:		This program uses MavBuyTest class to read data from a text file to initialize
 * 				employees, items, clients, etc.
 * 				
 *              It checks and recovers from some exceptions while reading the input file
 *                 uses a bufferedReader which is different from project 2
 *              NO assumptions on the #employees etc. adjust your code accordingly!
 
 *              also shows how to write to an output file
 * 
 * USAGE:       You need to initialize your data structures as the first step. 
 *              once the values are read into local variables, 
 *              it  is YOUR responsibility to add code at proper places to create objects and manage them!
 *
 *              input and output file names are given as a command line argument 
 *              (e.g, java MavBuyTest dataFile-proj3.txt proj3-output.txt)
 *              for the naming convention used in this file. if you forget to give the data 
 *              files as  command line arguments, it will prompt you for that.	
 *          
 *              you can remove or comment out prints once you are sure it is reading the input correctly
 *
 * MAKE SURE:   your program is case insensitive (for state code, city, gender, employee type etc.)
 */

import java.io.*;
import java.util.*;

/**
 * @param inputfileName
 *            as input data filename contaning input items with : as item separators
 * @param   outputFileName as output file name 
 */

public class MavBuyTest implements DateConstants, Proj3Constants {

	// introduce your (class and instance) attributes for this test class

    private static BufferedReader finput;
    private static Scanner cp;  //this is still command prompt
    public static PrintWriter foutput;

    //define other variables as needed

	//Note that we are using a DIFFERENT method for reading input file;
	/**
	 * @param iFileName is the input data file name
	 */		

    public static BufferedReader openReadFile(String fileName){
        BufferedReader bf = null;
        try{
            bf = new BufferedReader(new FileReader(fileName));
        }     
        catch(FileNotFoundException FNFE){    
          bf = null;
        }
       finally{
          return bf;
       }
    }

/**
* @param oFileName is the input data file name
*/		

    
    public static PrintWriter openWriteFile(String fileName){
        PrintWriter outputFile = null;
        try{
            outputFile = new PrintWriter(new FileWriter(fileName));
        }     
        catch(IOException IOE){    
          outputFile = null;
        }
       finally{
          return outputFile;
       }
    }

	// add here constructors and methods as needed

	/**
	 * @param takes
	 *            fileNames as command line argument. prompts if not given
	 */
	public static void main(String[] args) {

		// declare variables used for input handling
        String enterprisename = DEFAULT_ENTERPRISE_NAME;
        String inputLine = "", ifName = "", ofName = "";

		// determine if correct input file is provided

		cp = new Scanner(System.in);
		if (args.length < 1) {
			System.out.println("Input Data file name was not supplied");
			System.out.printf("Please type input data file name: ");
			ifName = cp.nextLine();//"dataFile-proj3.txt";
		} 
        else if (args.length < 2){
            ifName =  args[ZEROI];       
            System.out.printf("Please type output data file name: ");
            ofName = cp.nextLine();
        } else {
            ifName = args[ZEROI];
            ofName = args[ONEI];
            }

		// See HOW RECOVERY is done (will cover in a few weeks)
		System.out.println("ifName ="+ifName);
		System.out.println("ofName ="+ofName);
		finput = openReadFile(ifName);
		while (finput == null) {
			System.out.println("ifName ="+ifName);
			System.out.println("Error: Input FILE "+ ifName+" does not exist.\nEnter correct file name: ");
			ifName = cp.nextLine();
			finput = openReadFile(ifName);
		}
        foutput = openWriteFile(ofName);
		System.out.println("ofName ="+ofName);
        while (foutput == null){
			System.out.printf("Error: Output FILE %s %s",  ofName,  " does not exist.\nEnter correct file name: ");
            ofName = cp.nextLine();
            foutput = openWriteFile(ofName);
		}  

		/* GET SportsMgmt DETAILS */
		// start reading from data file
		// get name
		try {
			inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) == '/')
				inputLine = finput.readLine();
			String enterpriseName = inputLine;
			System.out.printf("\n%s %s \n", "Enterprise name is: ",
					enterpriseName);

			// add code as needed
                        
                        MavBuy myMav = new MavBuy(enterpriseName);
			/*GET numbers of empoyees, donors, athletes, and number of donations
			inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) == '/')
				inputLine = finput.readLine();

			// reading 4 values from a single line
			String[] chopInputLine = inputLine.split(":");

			int numEmployees = Integer.parseInt(chopInputLine[ZEROI]);
			int numDonors = Integer.parseInt(chopInputLine[ONEI]);
			int numAthletes = Integer.parseInt(chopInputLine[TWOI]);
			int numDonations = Integer.parseInt(chopInputLine[THREEI]);

			System.out.printf("\nnumEmp, numDonors, numAthletes, numDonations: [%5d, %5d, %5d, %5d]\n",
							numEmployees, numDonors, numAthletes, numDonations);
                        
			// add code here: use the above to initialize your arrays, arraylists,
			// and attributes as needed
                        */
           
			/* GET EMPLOYEE DETAILS */

			// reading details for each employee from the data file
			System.out.printf("\nEmployees: \n");
                     int numEmployees = 0;
                     inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) == '/')
					inputLine = finput.readLine();		
			while ( (!inputLine.toLowerCase().equals("end"))){
            	
				String[] chopEmp = inputLine.split(":");

				// fill all fields for a single employee from a single line
				String empType = chopEmp[ZEROI];
				String empFName = chopEmp[ONEI];
                        	String empLName = chopEmp[TWOI];
				String empBDate = chopEmp[THREEI];
				String empGender = chopEmp[FOURI];
				String empHireDate = chopEmp[FIVEI];
                                double empBaseSalary = Double.parseDouble(chopEmp[SIXI]);
                                double empRate = Double.parseDouble(chopEmp[SEVENI]);
                
				// add code here: in order to convert a date string to a Date object,
				// use the following
				// i.e., invoke the appropriate constructor of the Date class
                
				Date dob = new Date(empBDate); //initialize date objects
                                Date hireDate = new Date(empHireDate);
                               myMav.hireEmp(empType,empFName,empLName,dob,empGender,hireDate,empBaseSalary,empRate);
				System.out.printf("(%6s, %10s, %6s, %12s, %10.2f, %4s, %12s, %.2f)\n",
						empFName, empLName, empGender, empHireDate,
						empBaseSalary, empType, dob, empRate);

				// add code here: also, empTYpe is read as a string; if u are using a
				// enum, you need to convert it  using a switch or if statement

                //add this employee to array list
                inputLine = finput.readLine();
				while (inputLine.charAt(BASE_INDEX) == '/')
					inputLine = finput.readLine();	
                numEmployees +=1;
			}
            System.out.printf("#Employees: %d\n", numEmployees);

			// reading details of items from the data file
			System.out.printf("\nItems: \n");

            int numItems =0;
			inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) == '/')
					inputLine = finput.readLine();

            while ( (!inputLine.toLowerCase().equals("end"))){
				String[] chopitem = inputLine.split(":");

				// get all fields of the donor
				String itemId = chopitem[ZEROI];
                                String itemCompanyName = chopitem[ONEI];
                                String itemCondition = chopitem[TWOI];
				double itemShippingCost = Double.parseDouble(chopitem[THREEI]);
                                String itemAvailability = chopitem[FOURI];
				String itemAvailabilityDate = chopitem[FIVEI];
                                double itemShippingDays = Double.parseDouble(chopitem[SIXI]);
				double itemPrice = Double.parseDouble(chopitem[SEVENI]);
				String itemCategory = chopitem[EIGHTI];
				
                   
				System.out.printf("[%s, %s, %s, %f, %s, %s, %f, %f, %s]\n", 
                               itemId, itemCompanyName, itemCondition, itemShippingCost, 
                               itemAvailability, itemAvailabilityDate, itemShippingDays, itemPrice, itemCategory);

				// add code here to add item object to the enterprise
                                myMav.newItems(itemId, itemCompanyName, itemCondition,
            itemShippingCost, itemShippingDays, itemPrice, itemCategory, itemAvailability , itemAvailabilityDate);
                inputLine = finput.readLine();
				while (inputLine.charAt(BASE_INDEX) == '/')
					inputLine = finput.readLine();	
                numItems +=1;
			}
            System.out.printf("#Items: %d\n", numItems);

			/* GET CLIENT DETAILS */

			// reading details for each client from the data file
			System.out.printf("\nClients: \n");
			
                        int numClients =0;
			inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) == '/')
				inputLine = finput.readLine();

            while ( (!inputLine.toLowerCase().equals("end"))){
				String[] chopAthlete = inputLine.split(":");

				// fill all fields for a single client from a single line
				String[] chopClient = inputLine.split(":");
									
				// fill all fields for a single client from a single line 
				int clientId = Integer.parseInt(chopClient[ZEROI]);
				String clientFName = chopClient[ONEI];
                                String clientLName = chopClient[TWOI];
				String clientDOB = chopClient[THREEI];
				String clientGender = chopClient[FOURI];
				String clientMemType = chopClient[FIVEI]; 
				int  clientHouseNum = Integer.parseInt(chopClient[SIXI]);
                                String clientStreet = chopClient[SEVENI];
                                String clientCity = chopClient[EIGHTI];
                                String clientState = chopClient[NINEI];
                
				// add code: construct client object as appropriate
			 Customer address = new Customer(clientHouseNum, clientStreet, clientCity, clientState);
                         Date dob = new Date(clientDOB);
             	myMav.newCustomer(clientId, clientFName, clientLName, dob, clientGender, clientMemType,
                    clientHouseNum, clientStreet, clientCity, clientState);
                  	
                    System.out.printf("{%d, %s, %s, %s, %s, %s, %d, %s, %s, %s} \n",
                    clientId, clientFName, clientLName, clientDOB, clientGender, clientMemType,  
		    clientHouseNum, clientStreet, clientCity, clientState);

                              inputLine = finput.readLine();
				while (inputLine.charAt(BASE_INDEX) == '/')
					inputLine = finput.readLine();
                numClients += 1;
			}
                System.out.printf("#clients: %d\n", numClients);
                
                //process menu from here
         
            while ((inputLine = finput.readLine()) != null){
            if (inputLine.charAt(BASE_INDEX) == '/'){
                System.out.println(inputLine);
                foutput.println(inputLine);
            }
            else {
                   
    //add code: for processing them; use a switch statement after converting the first field
    //you can use foutput.print or foutput.println statements to write to an output file 
            
            // Menu 10
             System.out.println(inputLine);
             String[] inputArray = inputLine.split(":"); //    convert input to array of string
            // System.out.println(Arrays.toString(inputArray));
             int menuItem = Integer.parseInt(inputArray[ZEROI]);
             
                switch (menuItem)
             {
                 case(10): myMav.showProj2menu(); break;
                 case(11): myMav.buyItem(inputArray); break;
                 case(12): myMav.listPurchase(inputArray); break;
                 case(13): myMav.hireNewEmp(inputArray); break;
                 case(14): myMav.releaseEmp(inputArray); break;
                 case(15): myMav.computeSalary(inputArray); break;
                 case(16): myMav.expenditure(inputArray); break;
                 case(17): myMav.clientStatus(inputArray); break;
                 case(0): MyGUI.createAndShowGUI(myMav); break;
             }//   while(menuItem!=0);
                
          }	      
        }   

		} catch (NumberFormatException NFE) {
			System.out.println("I/O Error in File: " + ifName + "\ncheck for: "
					+ NFE.getMessage() + " and correct it in: " + inputLine);
		} catch (RuntimeException RE) {
			System.out.println("Invalid Data error in File: " + ifName
					+ "\nPlease correct " + RE.getMessage() + " in the file!" + inputLine);
		}
        catch(IOException IOE){
        System.out.println("input/output Data error in File: " + ifName + "\nPlease correct " + IOE.getMessage() + " in the file!" + inputLine);
        } 
        finally {
		  foutput.close();
		}
            //       MyGui gui = new MyGui( );
	}
}
