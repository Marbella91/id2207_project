package Test;

import Login.Employee;

public class MainTest {

	public static void main(String[] args) {
		/*
		 * Generate Employees data files
		 */
		Employee.generateEmployeesDataFiles();
		
		/*
		 * TestEmployee
		 */
		System.out.println("Test of Employee's functions:");
		TestEmployee.testEmployee();
		TestEmployee.testEmployeeToString();
		TestEmployee.testToFromXml();
		System.out.println();
		
		/*
		 * TestEventPlanningRequest
		 */
		System.out.println("Test of Event Planning Request's functions:");
		TestEventPlanningRequest.testEventPlanningRequest();
		TestEventPlanningRequest.testEventPlanningRequestToString();
		TestEventPlanningRequest.testToFromXml();
		System.out.println();
		
		/*
		 * TestEPRObjects
		 */
		System.out.println("Test of Event Planning Request's objects functions:");
		TestEPRObjects.testHiringRequest();
		TestEPRObjects.testFinancialRequest();
		TestEPRObjects.testApplication();
		TestEPRObjects.testTask();
		System.out.println();
			
	}

}
