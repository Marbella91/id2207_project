package EventPlanningRequest;

public class FinancialRequest {
	private String department;
	private int amount;
	private String reason;
	// status can be open when the request is created , approved or rejected
	private String status;
	private int idEPR;
	
	public FinancialRequest(String department, int amount, String reason, int idEPR) {
		this.department = department;
		this.amount = amount;
		this.reason = reason;
		this.status = "open";
		this.idEPR=idEPR;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setIdEPR(int id){
		this.idEPR=id;
	}
	
	public int getIdEPR(){
		return this.idEPR;
	}

}
