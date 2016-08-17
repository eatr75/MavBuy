/**
 * Programmer: Ji yeon Jeong
 * Language:	Java
 * Date:      10/23/2014
 * Purpose:   Create concrete class Agent to overide Abstract class Person
 */
public class Agent extends Person implements Proj3Interface{
    private double baseSalary; 
    private double overRate;
    private Date joinDate; private int empId;
    private int overHour; private String fName; private String lName;
    
    public Agent(int id, String fName, String lName, Date bDate, String gend,
           Date jDate, double bSalary, double oRate) {
        super(id, fName, lName, gend, bDate, jDate);
        this.fName = fName; this.lName = lName;
        this.baseSalary = bSalary; this.overRate = oRate;
        this.joinDate = jDate;
    }

     public String getName(){
        return fName + " "+ lName;
    }
    public int getEmpId(){
        return empId;
    }
    public double getBaseSalary(){
        return baseSalary;
    }
    public double getOverRate(){
        return overRate;
    }
    public Date getJoinDate(){
        return joinDate;
    }
    
    @Override
    public double computeSalary(int overHr) {
        this.overHour = overHr;
        double salary = baseSalary + overRate * overHour;
        return salary;
    }
    @Override
    public String toString(){
        return String.format("%s\n Salary: $ %,.2f\n", super.toString(),
                computeSalary(overHour));
    } //same as WebDesinger

    @Override
    public void age() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
