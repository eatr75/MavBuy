/**
 * Programmer: Ji yeon Jeong
 * Language:	Java
 * Date:      10/23/2014
 * Purpose:   Create concrete class WebDesigner to overide Abstract class Person
 */
public class WebDesigner extends Person implements Proj3Interface{
    private double baseSalary; private String fName;
    private double rateClick; private String lName;
    private Date joinDate;
    private int numClick;
    private int empId;
    
   public WebDesigner(int id, String fName, String lName, Date bDate, String gend,
           Date jDate, double bSalary, double rClick){
        super(id, fName, lName, gend, bDate, jDate); this.empId = id;
        setBaseSalary(bSalary); setRateClick(rClick);
        this.fName = fName; this.lName = lName;
        setJoinDate(jDate);
    }

    public String getName(){
        return fName + " "+ lName;
    }
    public int getEmpId(){
        return empId;
     }
   public void setBaseSalary(double bSalary){
      baseSalary = bSalary;
     }
   public double getBaseSalary(){
       return baseSalary;
   }
   public void setRateClick(double rClick){
       rateClick = rClick;
   }
   public double getRateClick(){
       return rateClick;
   }
   public void setJoinDate(Date jDate){
       joinDate = jDate;
   }
   public Date getJoinDate(){
       return joinDate;
   }
   
    @Override
    public double computeSalary(int nClick){
        this.numClick = nClick;
        double salary = getBaseSalary() + getRateClick() * numClick;
        return salary;
    }
       
    @Override
    public String toString(){
        return String.format("%s \nSalary : $ %,.2f\n", super.toString(), 
                computeSalary(numClick));
    } //how to add computerSalary here?

    @Override
    public void age() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
