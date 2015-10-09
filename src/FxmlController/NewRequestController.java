package FxmlController;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewRequestController  implements Initializable{
	private String userName;
	private double recordNumber;
	private String clientName;
	private String eventType;
	@FXML private Label labelLogin;
	@FXML private TextField recordNumberText;
	@FXML private TextField clientNameText;
	@FXML private TextField eventTypeText;
	@FXML private Button buttonSubmit;
	
	
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
	
	public NewRequestController(String userName){
		this.userName=userName;
		//setLabelLogin(userName);
	}

	@FXML
	public void handleSubmit(ActionEvent event){
	//	setRecordNumber(this.recordNumberText.getText());
		setClientName(this.clientNameText.getText());
		setEventType(this.eventTypeText.getText());
		System.out.println("submit ");
		
		JOptionPane.showMessageDialog(null, "the event planning request has been submited successfully",
				"", JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
