import java.util.Comparator;

/**
 * compares by java comparator to find oldest
 * @author 
 */
public class PersonComparator implements Comparator<Person>{
    @Override
    public int compare(Person emp1, Person emp2){
        return emp1.getJoined().compareTo(emp2.getJoined());
    }
}
