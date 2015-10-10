package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.EventPlanningRequest;
import Login.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewRequestController  implements Initializable{
	private Employee employee;
	private double recordNumber;
	private String clientName;
	private String eventType;
	private boolean decorationPreference;
	private boolean partiesPreference;
	private boolean photoPreference;
	private boolean foodPreference;
	private boolean drinkPreference;
	@FXML private Label labelLogin;
	@FXML private TextField recordNumberText;
	@FXML private TextField clientNameText;
	@FXML private TextField eventTypeText;
	@FXML private Button buttonSubmit;
	@FXML private TextField fromText;
	@FXML private TextField toText;
	@FXML private TextField expectedText;
	@FXML private CheckBox decorations;
	@FXML private CheckBox parties;
	@FXML private CheckBox food;
	@FXML private CheckBox drinks;
	@FXML private CheckBox photos;
	
	public NewRequestController(Employee employee){
		this.employee=employee;
	}
	
	public double getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(double recordNumber) {
		this.recordNumber = recordNumber;
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
	//	setRecordNumber(this.recordNumberText.getText());
		
		
		if(this.clientNameText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please inter the client name!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(this.eventTypeText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please inter the event type!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(this.fromText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please inter the starting date!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
	/*	try {
			fromDate=new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH).parse(fromText.toString());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		if(this.toText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please inter the end date!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(this.expectedText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please inter the expected number of attendees!",
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
	
		
		EventPlanningRequest request=new EventPlanningRequest(this.clientName,
				this.eventType,this.fromText.getText(),this.toText.getText(),0,
				this.decorationPreference,this.partiesPreference,photoPreference,foodPreference,this.drinkPreference,0);
		request.fromRequestToXml();
		
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
	}
	
}
