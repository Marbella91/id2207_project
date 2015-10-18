package Test;

import Login.Employee;
import Login.Job;

public class TestEmployee {
	
	public static void testEmployee()
	{
		Employee e = new Employee("name", "login", "password", Job.CustomerServiceOfficer);
		if (e.getName().equals("name") &&
				e.getLogin().equals("login") &&
				e.getPassword().equals("password") &&
				e.getJob().equals(Job.CustomerServiceOfficer)){
			System.out.println("Employee creation ok");
		} else {
			System.out.println("Error in creation of employee");
		}
	}
	
	public static void testEmployeeToString()
	{
		Employee e = new Employee("name", "login", "password", Job.CustomerServiceOfficer);
		if (e.toString().equals("name"))
			System.out.println("Employee function toString() ok");
		else
			System.out.println("Error in Employee function toString()");
	}
	
	public static void testToFromXml()
	{
		Employee employee = new Employee("name", "login", "password", Job.CustomerServiceOfficer );
		String employeeXML = Employee.toXml(employee);
		Employee employeebis = Employee.fromXml(employeeXML);
		if (employeebis.getName().equals(employee.getName()) &&
				employeebis.getLogin().equals(employee.getLogin()) &&
				employeebis.getPassword().equals(employee.getPassword()) &&
				employeebis.getJob().equals(employee.getJob())){
			System.out.println("Employee to/from xml functions ok");
		} else {
			System.out.println("Error in Employee to/from xml functions");
		}
	}
	
}
