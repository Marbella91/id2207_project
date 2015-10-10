package EventPlanningRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import Login.Employee;

public class EventPlanningRequest {

	
	private int clientRecordNumber; // set to 0 if no client record
	private String clientName;
	private String eventType;
	//private Calendar fromDate;
	//private Calendar toDate;
	private String fromDate;
	private String toDate;
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
	
	public EventPlanningRequest(int clientRecordNumber, String clientName, String eventType, String fromDate,
			String toDate, int expectedAttendeesNumber, boolean decorationPreference, boolean partiesPreference,
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

	public EventPlanningRequest(String clientName, String eventType, String fromDate, String toDate,
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

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
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
	
	public void fromRequestToXml(){
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("EventPlanningRequest", EventPlanningRequest.class);
		String xml= xstream.toXML(this);
		File dataDirectory = new File("data/Requests/EPRequests");
		int i=dataDirectory.listFiles().length+1;
		String fileName="data/Requests/EPRequests/request"+i+".xml";
		File file = new File(fileName);
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(xml);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static EventPlanningRequest fromXmlToRequest(String xml){
		XStream xstream = new XStream(new StaxDriver());
		xstream.processAnnotations(EventPlanningRequest.class);
		xstream.alias("EventPlanningRequest", EventPlanningRequest.class);
		return (EventPlanningRequest) xstream.fromXML(xml);
	}
	
	
	// true if the employee is authorized to see the event planning request
	public boolean isVisible(Employee e)
	{
		switch (e.getJob())
		{
			case SeniorCustomerServiceOfficer:
				return true;
			case FinancialManager:
				return (this.status.equals(EventPlanningRequestStatus.PendingFinancialComments) ||
						this.status.equals(EventPlanningRequestStatus.Approved));
			case AdministrationManager:
				return (this.status.equals(EventPlanningRequestStatus.PendingAdministrationComments) ||
						this.status.equals(EventPlanningRequestStatus.Approved));
			default:
				return false;
		}
	}
	
	public static LinkedList<EventPlanningRequest> generateAuthorizedEPRequestsList(Employee e)
	{
		File dataDirectory = new File("data/Requests/EPRequests");
		File[] fileList = dataDirectory.listFiles(); 
		
		LinkedList<EventPlanningRequest>  EPRequestsList = new LinkedList<EventPlanningRequest>();
		
		for (int i = 0; i < fileList.length; i++)
		{
			FileReader fr;
			try {
				fr = new FileReader(fileList[i]);
				BufferedReader br = new BufferedReader(fr);
				String requestXml = br.readLine();
				EventPlanningRequest request = fromXmlToRequest(requestXml);
				if (request.isVisible(e))
					EPRequestsList.add(request);
				br.close();
				fr.close();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return EPRequestsList;
	}
	
	
	
	
	
	
	
	
	
}
