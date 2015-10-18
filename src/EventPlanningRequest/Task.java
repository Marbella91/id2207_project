package EventPlanningRequest;

import Login.Employee;

public class Task {
	private Employee sender;
	private String description;
	private Employee assignedTo;
	
	//high or medium
	private String priority;
	private String senderName;
	private String employeeName;

	public Task(Employee sender, String description, Employee assignedTo, String priority) {
		this.sender = sender;
		this.description = description;
		this.assignedTo = assignedTo;
		this.priority = priority;
		this.senderName=sender.getName();
		this.employeeName=assignedTo.getName();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getSender() {
		return sender;
	}

	public void setSender(Employee sender) {
		this.sender = sender;
	}

	public Employee getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Employee assgnedTo) {
		this.assignedTo = assgnedTo;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String getSenderName(){
		return this.senderName;
	}
	
	public String getEmployeeName(){
		return this.employeeName;
	}

	
	
}
