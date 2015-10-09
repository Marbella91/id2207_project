package Test;

import Login.Employee;

public class MainTest {

	public static void main(String[] args) {
		//Employee.generateDataFiles();
		//TestEmployee.testGenerateEmployeeList();
		//TestEventPlanningRequest.testCreateEventPlanningRequest();
		TestEventPlanningRequest t=new TestEventPlanningRequest();
		t.testCreateXmlFile();
	}

}
