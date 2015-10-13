package EventPlanningRequest;

import java.util.HashMap;
import java.util.LinkedList;

public class Application {
	private String department;
	
	//open, in progress or closed
	private String status;
	
	// key = type = decoration or music
	private HashMap<String, Task> tasks;
	
	public Application(String department) {
		this.department = department;
		this.status = "open";
		this.tasks =new HashMap<String, Task>();
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
	
	
	
	
}
