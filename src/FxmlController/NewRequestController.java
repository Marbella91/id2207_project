package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.ClientRecord;
import EventPlanningRequest.EventPlanningRequest;
import Login.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewRequestController  implements Initializable{
	private Employee employee;
	private ClientRecord clientRecord;
	private String clientName;
	private String eventType;
	private boolean decorationPreference;
	private boolean partiesPreference;
	private boolean photoPreference;
	private boolean foodPreference;
	private boolean drinkPreference;
	private int expectedAttendeesNumber;
	private int expectedBudget;
	@FXML private Label labelLogin;
	@FXML private ChoiceBox<ClientRecord> clientRefBox;
	@FXML private TextField clientNameText;
	@FXML private TextField eventTypeText;
	@FXML private Button buttonSubmit;
	@FXML private Button buttonLogout;
	@FXML private TextField fromText;
	@FXML private TextField toText;
	@FXML private TextField expectedAttendeesText;
	@FXML private CheckBox decorations;
	@FXML private CheckBox parties;
	@FXML private CheckBox food;
	@FXML private CheckBox drinks;
	@FXML private CheckBox photos;
	@FXML private TextField expectedBudgetText;
	
	
	public NewRequestController(Employee employee){
		this.employee=employee;
	}
	
	public ClientRecord getClientRecord() {
		return clientRecord;
	}

	public void setClientRecord(ClientRecord clientRecord) {
		this.clientRecord = clientRecord;
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
	
	
	
	public void setLabelLogin(String userName){
		labelLogin.setText(userName);
	}
	
	public String getLabelLogin(){
		return labelLogin.getText();
	}
	
	

	@FXML
	public void handleSubmit(ActionEvent event){
		setClientRecord(this.clientRefBox.getValue());
				
		if(this.clientNameText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please enter the client name!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(this.eventTypeText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please enter the event type!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(this.fromText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please enter the starting date!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(this.toText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please enter the end date!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		setClientName(this.clientNameText.getText());
		setEventType(this.eventTypeText.getText());
		if (this.decorations.isDisabled()){
			this.decorationPreference=false;
		}
		else {
			this.decorationPreference=true;
		}
		if (this.parties.isDisabled()){
			this.partiesPreference=false;
		}
		else {
			this.partiesPreference=true;
		}
		if (this.food.isDisabled()){
			this.foodPreference=false;
		}
		else {
			this.foodPreference=true;
		}
		if (this.drinks.isDisabled()){
			this.drinkPreference=false;
		}
		else {
			this.drinkPreference=true;
		}
		if (this.photos.isDisabled()){
			this.photoPreference=false;
		}
		else {
			this.foodPreference=true;
		}
		
		String expectedAttendees = this.expectedAttendeesText.getText();
		if (expectedAttendees.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the expected number of attendees!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		} else try {  
			this.expectedAttendeesNumber = Integer.parseInt(expectedAttendees);
		} catch(NumberFormatException nfe) {  
			JOptionPane.showMessageDialog(null, "The expected number of attendees should be a number!",
					"", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		
		String expectedBudget = this.expectedBudgetText.getText();
		if (expectedBudget.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the expected budget!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		} else try {  
			this.expectedBudget = Integer.parseInt(expectedBudget);
		} catch(NumberFormatException nfe) {  
			JOptionPane.showMessageDialog(null, "The expected budget should be a number!",
					"", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		
		EventPlanningRequest request=new EventPlanningRequest(this.clientName,
				this.eventType,this.fromText.getText(),this.toText.getText(),this.expectedAttendeesNumber,
				this.decorationPreference,this.partiesPreference,photoPreference,foodPreference,this.drinkPreference,
				this.expectedBudget);
				request.fromRequestToXmlFile();
				
		//if a client has been selected, update the client record with the new request
		// and update the new request with the client ref
		if (clientRecord != null)
		{	
			request.setClientRecordRef(clientRecord.getRecordRef());
			request.updateXml();
			clientRecord.getEventsIds().add(request.getId());
			clientRecord.updateXml();
		}
		
		
		
		JOptionPane.showMessageDialog(null, "The event planning request has been "
				+ "created successfully!",
				"", JOptionPane.INFORMATION_MESSAGE);
		
		Stage currentStage= (Stage) this.buttonSubmit.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/NewRequest.fxml"));
        NewRequestController controller = new  NewRequestController(employee);
        loader.setController(controller); 
        Parent root;
		
			try {
				root = (Parent) loader.load();
				Scene scene = new Scene(root);
		        currentStage.setScene(scene);
		        currentStage.setTitle("Initialize a new Request"); 
		        currentStage.setHeight(800);
		        currentStage.setWidth(600);
		        currentStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if(option == JOptionPane.OK_OPTION){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/LoginInterface.fxml"));
	        LoginController controller = new  LoginController();
	        loader.setController(controller); 
	        Parent root = (Parent) loader.load();
	        Stage primaryStage=(Stage) buttonLogout.getScene().getWindow();
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Login"); 
	        primaryStage.setHeight(250);
	        primaryStage.setWidth(400);
	        primaryStage.show();
	    }
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
		this.clientRefBox.setItems(FXCollections.observableList(ClientRecord.generateClientRecordList()));
		this.clientRefBox.getItems().add(null);
	}
	
}
