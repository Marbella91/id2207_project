package EventPlanningRequest;

import Login.Employee;

public class Task {
	private Employee sender;
	private String description;
	private Employee assgnedTo;
	
	//high or medium
	private String priority;

	public Task(Employee sender, String description, Employee assgnedTo, String priority) {
		this.sender = sender;
		this.description = description;
		this.assgnedTo = assgnedTo;
		this.priority = priority;
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

	public Employee getAssgnedTo() {
		return assgnedTo;
	}

	public void setAssgnedTo(Employee assgnedTo) {
		this.assgnedTo = assgnedTo;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	
	
}
