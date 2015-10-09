package FxmlController;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.EventPlanningRequest;
import Login.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewRequestController  implements Initializable{
	private Employee employee;
	private double recordNumber;
	private String clientName;
	private String eventType;
	@FXML private Label labelLogin;
	@FXML private TextField recordNumberText;
	@FXML private TextField clientNameText;
	@FXML private TextField eventTypeText;
	@FXML private Button buttonSubmit;
	
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
		setClientName(this.clientNameText.getText());
		setEventType(this.eventTypeText.getText());
		//EventPlanningRequest request=new EventPlanningRequest("",this.clientName,
			//	this.eventType,);
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
	}
	
}
