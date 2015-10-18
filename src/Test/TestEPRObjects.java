package Test;

import EventPlanningRequest.Application;
import EventPlanningRequest.FinancialRequest;
import EventPlanningRequest.HiringRequest;
import EventPlanningRequest.Task;
import Login.Employee;

public class TestEPRObjects {

	public static void testHiringRequest()
	{
		HiringRequest hr = new HiringRequest("department", "contractType", 1, "jobTitle",
				"jobDescription");
		if (hr.getDepartment().equals("department") &&
				hr.getContractType().equals("contractType") &&
				hr.getExperience() == 1 &&
				hr.getJobTitle().equals("jobTitle") &&
				hr.getJobDescription().equals("jobDescription") &&
				hr.getStatus().equals("open")){
			System.out.println("Hiring request creation ok");
		} else {
			System.out.println("Error in creation of hiring request");
		}
	}
	
	public static void testFinancialRequest()
	{
		FinancialRequest fr = new FinancialRequest("department", 100, "reason");
		if (fr.getDepartment().equals("department") &&
				fr.getAmount() == 100 &&
				fr.getReason().equals("reason") &&
				fr.getStatus().equals("open")){
			System.out.println("Financial request creation ok");
		} else {
			System.out.println("Error in creation of financial request");
		}
	}
	
	public static void testApplication()
	{
		Application application = new Application("department");
		if (application.getDepartment().equals("department") &&
				application.getStatus().equals("open")){
			System.out.println("Application creation ok");
		} else {
			System.out.println("Error in creation of application");
		}
	}
	
	public static void testTask()
	{
		Employee sender = new Employee("sender", null, null, null);
		Employee assignedTo = new Employee("assignedTo", null, null, null);
		Task task = new Task(sender, "description", assignedTo, "priority");
		if (task.getSender().getName().equals("sender") &&
				task.getDescription().equals("description") &&
				task.getAssignedTo().getName().equals("assignedTo") &&
				task.getPriority().equals("priority") &&
				task.getSenderName().equals("sender") &&
				task.getEmployeeName().equals("assignedTo")){
			System.out.println("Task creation ok");
		} else {
			System.out.println("Error in creation of task");
		}
	}
}
