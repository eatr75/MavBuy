/**
 * Programmer: Ji yeon Jeong
 * Language:	Java
 * Date:      10/23/2014
 * Purpose:   Create Abstract class Person to be overriden
 */
public abstract class Person {
    private int personId;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthDate; private Date joinDate;
    private int age;
    private double salary;
    
    public Person(int id, String fName, String lName, String gend, Date bDate, Date jDate){
        this.personId = id; this.firstName = fName;
        this.lastName = lName; this.gender = gend;
        this.birthDate = bDate; this.joinDate = jDate;
    } // end four-args Person constructor

    // get & set method 
    public double getSalary() 
    {
        return salary;
    }
  
    public void setSalary(double salary) 
    {
        this.salary = salary;
    }
    
    public void setPersonId(int personId) 
    {
        this.personId = personId;
    }

    public void setfName(String fName) 
    {
        this.firstName = fName;
    }

    public void setlName(String lName) 
    {
        this.lastName = lName;
    }

    public String getfName() 
    {
        return firstName;
    }

    public String getlName() 
    {
        return lastName;
    }
    
    public void setDob(Date dob) 
    {
        this.birthDate = dob;
    }

    public Date getDob() 
    {
        return birthDate ;
    }

    public int getPersonId() 
    {
        return personId;
    }
        
    public String getGender() 
    {
        return gender;
    }

    public int getAge() 
    {
        return age;
    }

    public void setAge(int age) 
    {
        this.age = age;
    }
    
    public void setJoined(Date jDate)
    {
        joinDate = jDate;
    }
    public Date getJoined()
    {
        return joinDate;
    }
    public abstract double computeSalary(int hours);
    public abstract void age();
    
    @Override
    public String toString(){
        return String.format("ID: %d, Name: %s %s Gender: %s, Birth Date: %s, "
                + "Join Date: %s", personId, firstName, lastName, 
                gender, birthDate, joinDate);
    }
}
