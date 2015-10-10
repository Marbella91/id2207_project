package FxmlController;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.EventPlanningRequest;
import Login.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SCSOInterfaceController  implements Initializable{
	private Employee employee;
	private LinkedList<EventPlanningRequest> EPRequestsList;
	@FXML private Label labelLogin;
	@FXML private ListView labelPendingCommentsRequests;
	
	public SCSOInterfaceController(Employee employee){
		this.employee=employee;
		this.EPRequestsList = EventPlanningRequest.generateAuthorizedEPRequestsList(employee);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
		//List<String> values = Arrays.asList("one", "two", "three");
		//labelPendingCommentsRequests.setItems(FXCollections.observableList(values));
	}
	
	 

	    
	
}
