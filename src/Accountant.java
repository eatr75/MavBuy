/**
 * Programmer: Ji yeon Jeong
 * Language:	Java
 * Date:      10/23/2014
 * Purpose:   Create concrete class Accountant to overide Abstract class Person
 */
public class Accountant extends Person implements Proj3Interface{
    private double hourRate;
    private Date joinDate; private String fName; private String lName;
    private int hrWork; private int empId;

    public Accountant(int id, String fName, String lName, Date bDate,
            String gend, Date jDate, double bSalary,  double hRate) {
        super(id, fName, lName, gend, bDate, jDate); this.empId = id; 
        this.fName = fName; this.lName = lName;
        this.hourRate = hRate; this.joinDate = jDate;
    }

     public String getName(){
        return fName + " "+ lName;
    }
    public int getEmpId(){
        return empId;
     }
    public double getHourRate(){
        return hourRate;
    }
    public Date getJoinDate(){
        return joinDate;
    }        

    @Override
    public double computeSalary(int hWork){
        this.hrWork = hWork;
        double salary = hourRate * hrWork;
        return salary;
     }
    @Override
    public String toString(){
        return String.format("%s\nHourly: $ %,.2f\n", super.toString(),
                computeSalary(hrWork) );
    } //same as WebDesinger


    @Override
    public void age() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
