package EventPlanningRequest;

public class HiringRequest {
	
	private String department;
	private String contractType;
	private int experience;
	private String jobTitle;
	private String jobDescription;
	// status can be open when the request is created , approved or rejected
	private String status; 
	
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public HiringRequest(String department, String contractType, int experience, String jobTitle,
			String jobDescription) {
		super();
		this.department = department;
		this.contractType = contractType;
		this.experience = experience;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.status="open";
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getContractType() {
		return contractType;
	}


	public void setContractType(String contractType) {
		this.contractType = contractType;
	}


	public int getExperience() {
		return experience;
	}


	public void setExperience(int experience) {
		this.experience = experience;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public String getJobDescription() {
		return jobDescription;
	}


	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

}
