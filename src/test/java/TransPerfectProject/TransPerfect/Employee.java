package TransPerfectProject.TransPerfect;

import java.util.Objects;

public class Employee {
	
	String name;
    String jobTitle;
    String phoneNumber;
    String address;
    
    //konstruktor
    public Employee(String name, String jobTitle, String phoneNumber, String address)
    {
        this.name = name;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    @Override
    public boolean equals(Object o) 
    {
    	if(this==o) 
    	{
    		return true;
    	}
    	if(o==null || getClass()!=o.getClass()) 
    	{
    		return false;
    	}
    	
    	Employee employee = (Employee) o;
    	return name.equals(employee.name) &&
                jobTitle.equals(employee.jobTitle) &&
                phoneNumber.equals(employee.phoneNumber) &&
                address.equals(employee.address);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, jobTitle, phoneNumber, address);
    }

}
