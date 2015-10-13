package EventPlanningRequest;

public class Application {
	private String department;
	
	//open, in progress or closed
	private String status;
	
	public Application(String department) {
		this.department = department;
		this.status = "open";
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
	
	
	
	
}
