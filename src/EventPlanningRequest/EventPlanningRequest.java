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

	// unique id, generated first time the request is saved in the database
	private int id;
	
	private String clientRecordRef;
	private String clientName;
	private String eventType;
	private String fromDate;
	private String toDate;
	private int expectedAttendeesNumber;
	private boolean decorationPreference;
	private boolean partiesPreference;
	private boolean photoPreference;
	private boolean foodPreference;
	private boolean drinkPreference;
	private int expectedBudget;
	
	private LinkedList<HiringRequest> hiringRequests;
	private FinancialRequest financialRequest;
	private Application productionApplication;
	
	// status: waiting for a type of comment, approved or rejected
	private EventPlanningRequestStatus status = EventPlanningRequestStatus.PendingSCSOComments;
	
	// comments from various actors
	private String SCSOComments;
	private String financialComments;
	private String administrationComments;
	
	private int finalAttendeesNumber;
	private int finalBudget;
	private String Description;
	
	public EventPlanningRequest(String clientName, String eventType, String fromDate, String toDate,
			int expectedAttendeesNumber, boolean decorationPreference, boolean partiesPreference,
			boolean photoPreference, boolean foodPreference, boolean drinkPreference, int expectedBudget) {
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
		this.hiringRequests = new LinkedList<HiringRequest>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientRecordRef() {
		return clientRecordRef;
	}

	public void setClientRecordRef(String clientRecordRef) {
		this.clientRecordRef = clientRecordRef;
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
	
	public LinkedList<HiringRequest> getHiringRequest() {
		return hiringRequests;
	}

	public void setHiringRequest(LinkedList<HiringRequest> hiringRequests) {
		this.hiringRequests = hiringRequests;
	}

	public FinancialRequest getFinancialRequest() {
		return financialRequest;
	}

	public void setFinancialRequest(FinancialRequest financialRequest) {
		this.financialRequest = financialRequest;
	}

	public Application getProductionApplication() {
		return productionApplication;
	}

	public void setProductionApplication(Application productionApplication) {
		this.productionApplication = productionApplication;
	}
	
	public int getFinalAttendeesNumber() {
		return finalAttendeesNumber;
	}

	public void setFinalAttendeesNumber(int finalAttendeesNumber) {
		this.finalAttendeesNumber = finalAttendeesNumber;
	}

	public int getFinalBudget() {
		return finalBudget;
	}

	public void setFinalBudget(int finalBudget) {
		this.finalBudget = finalBudget;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void fromRequestToXml(){
		File dataDirectory = new File("data/EPRequests");
		int i=dataDirectory.listFiles().length+1;
		this.setId(i);
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("EventPlanningRequest", EventPlanningRequest.class);
		String xml= xstream.toXML(this);
		String fileName="data/EPRequests/request"+i+".xml";
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(xml);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateXml(){
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("EventPlanningRequest", EventPlanningRequest.class);
		String xml= xstream.toXML(this);
		int i = this.getId();
		String fileName="data/EPRequests/request"+i+".xml";
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(xml);
			bw.close();
		} catch (IOException e) {
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
			case ProductionManager:
				return (this.status.equals(EventPlanningRequestStatus.Approved));
			default:
				return false;
		}
	}
	
	public static LinkedList<EventPlanningRequest> generateAuthorizedEPRequestsList(Employee e)
	{
		File dataDirectory = new File("data/EPRequests");
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
				ex.printStackTrace();
			}
		}
		return EPRequestsList;
	}
	
	public static LinkedList<EventPlanningRequest> generateEPRequestsWithoutClientList()
	{
		File dataDirectory = new File("data/EPRequests");
		File[] fileList = dataDirectory.listFiles(); 
		LinkedList<EventPlanningRequest>  EPRequestsWithoutClientList = new LinkedList<EventPlanningRequest>();
		
		for (int i = 0; i < fileList.length; i++)
		{
			FileReader fr;
			try {
				fr = new FileReader(fileList[i]);
				BufferedReader br = new BufferedReader(fr);
				String requestXml = br.readLine();
				EventPlanningRequest request = fromXmlToRequest(requestXml);

				if (request.getClientRecordRef() == null)
					EPRequestsWithoutClientList.add(request);
				br.close();
				fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return EPRequestsWithoutClientList;
	}

	
	@Override
	public String toString() {
		return this.eventType + " for " + this.clientName;
	}
	
	public static EventPlanningRequest fromXmlIdToRequest(int id)
	{
		File file = new File("data/EPRequests/request" + id + ".xml");
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String requestXml = br.readLine();
			EventPlanningRequest request = fromXmlToRequest(requestXml);
			br.close();
			fr.close();
			return request;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
}
