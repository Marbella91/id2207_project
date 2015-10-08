package Test;

import java.util.Iterator;
import java.util.LinkedList;

import Login.Employee;
import Login.Job;

public class TestEmployee {
	public static void testToXml()
	{
		Employee employee = new Employee("Janet", "janet", "password", Job.SeniorCustomerServiceOfficer );
		System.out.println(Employee.toXml(employee));
	}
	
	public static void testToFromXml()
	{
		Employee employee = new Employee("Janet", "janet", "password", Job.SeniorCustomerServiceOfficer );
		String employeeXML = Employee.toXml(employee);
		Employee employeebis = Employee.fromXml(employeeXML);
		System.out.println("Name: " + employeebis.getName());
		System.out.println("Login: " + employeebis.getLogin());
		System.out.println("Password: " + employeebis.getPassword());
		System.out.println("Job: " + employeebis.getJob().toString());
	}
	
	public static void testGenerateEmployeeList()
	{
		LinkedList<Employee> employeeList = Employee.generateEmployeeList();
		for (int i = 0; i < employeeList.size(); i++){ 
			Employee employee = employeeList.get(i);
			System.out.println("Name: " + employee.getName());
			System.out.println("Login: " + employee.getLogin());
			System.out.println("Password: " + employee.getPassword());
			System.out.println("Job: " + employee.getJob().toString());
		}
	}
	
}
