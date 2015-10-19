package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.ClientRecord;
import EventPlanningRequest.EventPlanningRequest;
import Login.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ViewEventController  implements Initializable{
	private Employee employee;
	private EventPlanningRequest request;
	private ClientRecord client;
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button buttonMenu;
	@FXML private Button buttonBackClient;
	
	@FXML private Label labelStatus;
	@FXML private Label labelClientRef;
	@FXML private Label labelClientName;
	@FXML private Label labelEventType;
	@FXML private Label labelFromDate;
	@FXML private Label labelToDate;
	@FXML private TextArea textDescription;
	@FXML private Label labelAttendees;
	@FXML private TextField textFinalAttendees;
	@FXML private Label labelBudget;
	@FXML private TextField textFinalBudget;
	
	@FXML private Label labelDecorations;
	@FXML private Label labelMusic;
	@FXML private Button buttonUpdate;
	
	public ViewEventController(Employee employee, EventPlanningRequest request, ClientRecord client){
		this.employee=employee;
		this.request = request;
		this.client = client;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
		this.labelStatus.setText(this.request.getStatus().toString());
		this.labelClientRef.setText(this.request.getClientRecordRef());
		this.labelClientName.setText(this.request.getClientName());
		this.labelEventType.setText(this.request.getEventType());
		this.labelFromDate.setText(this.request.getFromDate());
		this.labelToDate.setText(this.request.getToDate());
		if (this.request.getDescription() != null) {
			this.textDescription.setText(this.request.getDescription());
		}
		this.labelAttendees.setText(String.valueOf(this.request.getExpectedAttendeesNumber()));
		this.textFinalAttendees.setText(String.valueOf(this.request.getFinalAttendeesNumber()));
		this.labelBudget.setText(String.valueOf(this.request.getExpectedBudget()));
		this.textFinalBudget.setText(String.valueOf(this.request.getFinalBudget()));
		
		if (this.request.getProductionApplication() != null &&
				this.request.getProductionApplication().getTasks().containsKey("decoration")){
			this.labelDecorations.setText(
					this.request.getProductionApplication().getTasks().get("decoration").getDescription());
		}
		else {
			this.labelDecorations.setText("There is no decoration task for this event.");
		}
		
		if (this.request.getProductionApplication() != null &&
				this.request.getProductionApplication().getTasks().containsKey("music")){
			this.labelMusic.setText(
					this.request.getProductionApplication().getTasks().get("music").getDescription());
		}
		else {
			this.labelMusic.setText("There is no music task for this event.");
		}
	}
	
	@FXML
	public void handleUpdate(ActionEvent event){
		this.request.setDescription(this.textDescription.getText());
		
		String finalAttendees = this.textFinalAttendees.getText();
		if (finalAttendees.equals(""))
			this.request.setFinalAttendeesNumber(0);
		else try {  
			this.request.setFinalAttendeesNumber(Integer.parseInt(finalAttendees));
		} catch(NumberFormatException nfe) {  
			JOptionPane.showMessageDialog(null, "The final number of attendees should be a number!",
					"", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		
		String finalBudget = this.textFinalBudget.getText();
		if (finalBudget.equals(""))
			this.request.setFinalBudget(0);
		else try {  
			this.request.setFinalBudget(Integer.parseInt(finalBudget));
		} catch(NumberFormatException nfe) {  
			JOptionPane.showMessageDialog(null, "The final budget should be a number!",
					"", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		
		this.request.updateXml();
		
		JOptionPane.showMessageDialog(null, "The event has been "
				+ "updated successfully!",
				"", JOptionPane.INFORMATION_MESSAGE);
		
		
		// reinitialize interface
		Controller.generateInterface(this,
				new ViewEventController(this.employee, this.request, this.client),
				"../Fxml/ViewEvent.fxml",
				(Stage) buttonUpdate.getScene().getWindow(),
				"View Event");
	}
		
	@FXML
	public void handleMenu(ActionEvent event){
		Stage currentStage = (Stage) this.buttonMenu.getScene().getWindow();
		this.employee.generateInterface(currentStage);
	}
	
	@FXML
	public void handleBackClient(ActionEvent event){
		Controller.generateInterface(this,
				new  ViewClientRecordController(this.employee, this.client),
				"../Fxml/ViewClientRecord.fxml",
				(Stage) this.buttonBackClient.getScene().getWindow(),
				"View Client Record");
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		Controller.logout(this, buttonLogout);
	}

	    
	
}
