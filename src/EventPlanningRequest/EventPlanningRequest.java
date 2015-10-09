package EventPlanningRequest;

import java.util.Calendar;

public class EventPlanningRequest {

	private int clientRecordNumber; // set to 0 if no client record
	private String clientName;
	private String eventType;
	private Calendar fromDate;
	private Calendar toDate;
	private int expectedAttendeesNumber;
	private boolean decorationPreference;
	private boolean partiesPreference;
	private boolean photoPreference;
	private boolean foodPreference;
	private boolean drinkPreference;
	private int expectedBudget;
	
	// status: waiting for a type of comment, approved or rejected
	private EventPlanningRequestStatus status = EventPlanningRequestStatus.PendingSCSOComments;
	
	// comments from various actors
	private String SCSOComments;
	private String financialComments;
	private String administrationComments;
	
	public EventPlanningRequest(int clientRecordNumber, String clientName, String eventType, Calendar fromDate,
			Calendar toDate, int expectedAttendeesNumber, boolean decorationPreference, boolean partiesPreference,
			boolean photoPreference, boolean foodPreference, boolean drinkPreference, int expectedBudget) {
		this.clientRecordNumber = clientRecordNumber;
		this.clientName = clientName;
		this.eventType = eventType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.expectedAttendeesNumber = expectedAttendeesNumber;
		this.decorationPreference = decorationPreference;
		this.partiesPreference = partiesPreference;
		this.photoPreference = photoPreference;
		this.foodPreference = foodPreference;
		this.drinkPreference = drinkPreference;
		this.expectedBudget = expectedBudget;
	}

	public EventPlanningRequest(String clientName, String eventType, Calendar fromDate, Calendar toDate,
			int expectedAttendeesNumber, boolean decorationPreference, boolean partiesPreference,
			boolean photoPreference, boolean foodPreference, boolean drinkPreference, int expectedBudget) {
		this.clientRecordNumber = 0;
		this.clientName = clientName;
		this.eventType = eventType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.expectedAttendeesNumber = expectedAttendeesNumber;
		this.decorationPreference = decorationPreference;
		this.partiesPreference = partiesPreference;
		this.photoPreference = photoPreference;
		this.foodPreference = foodPreference;
		this.drinkPreference = drinkPreference;
		this.expectedBudget = expectedBudget;
	}

	public int getClientRecordNumber() {
		return clientRecordNumber;
	}

	public void setClientRecordNumber(int clientRecordNumber) {
		this.clientRecordNumber = clientRecordNumber;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Calendar getFromDate() {
		return fromDate;
	}

	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}

	public Calendar getToDate() {
		return toDate;
	}

	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}

	public int getExpectedAttendeesNumber() {
		return expectedAttendeesNumber;
	}

	public void setExpectedAttendeesNumber(int expectedAttendeesNumber) {
		this.expectedAttendeesNumber = expectedAttendeesNumber;
	}

	public boolean isDecorationPreference() {
		return decorationPreference;
	}

	public void setDecorationPreference(boolean decorationPreference) {
		this.decorationPreference = decorationPreference;
	}

	public boolean isPartiesPreference() {
		return partiesPreference;
	}

	public void setPartiesPreference(boolean partiesPreference) {
		this.partiesPreference = partiesPreference;
	}

	public boolean isPhotoPreference() {
		return photoPreference;
	}

	public void setPhotoPreference(boolean photoPreference) {
		this.photoPreference = photoPreference;
	}

	public boolean isFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(boolean foodPreference) {
		this.foodPreference = foodPreference;
	}

	public boolean isDrinkPreference() {
		return drinkPreference;
	}

	public void setDrinkPreference(boolean drinkPreference) {
		this.drinkPreference = drinkPreference;
	}

	public int getExpectedBudget() {
		return expectedBudget;
	}

	public void setExpectedBudget(int expectedBudget) {
		this.expectedBudget = expectedBudget;
	}

	public EventPlanningRequestStatus getStatus() {
		return status;
	}

	public void setStatus(EventPlanningRequestStatus status) {
		this.status = status;
	}

	public String getSCSOComments() {
		return SCSOComments;
	}

	public void setSCSOComments(String sCSOComments) {
		SCSOComments = sCSOComments;
	}

	public String getFinancialComments() {
		return financialComments;
	}

	public void setFinancialComments(String financialComments) {
		this.financialComments = financialComments;
	}

	public String getAdministrationComments() {
		return administrationComments;
	}

	public void setAdministrationComments(String administrationComments) {
		this.administrationComments = administrationComments;
	}
	
	
	
	
	
	
}
