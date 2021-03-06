package EventPlanningRequest;

import java.util.HashMap;

public class Application {
	private String department;
	
	//open, in progress or closed
	private String status;
	
	// key = type = decoration or music
	private HashMap<String, Task> tasks;
	private String epr;
	
	public Application(String department, String epr) {
		this.department = department;
		this.status = "open";
		this.tasks =new HashMap<String, Task>();
		this.epr=epr;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HashMap<String, Task> getTasks() {
		return tasks;
	}

	public void setTasks(HashMap<String, Task> tasks) {
		this.tasks = tasks;
	}
	
	public void setEPR(String epr){
		this.epr=epr;
	}
	
	public String getEPR(){
		return this.epr;
	}
	
	
	
	
}
