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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SCSOInterfaceController  implements Initializable{
	private Employee employee;
	private double recordNumber;
	private String clientName;
	private String eventType;
	@FXML private Label labelLogin;
	@FXML private ListView listView;
	
	public SCSOInterfaceController(Employee employee){
		this.employee=employee;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
		//List<String> values = Arrays.asList("one", "two", "three");
		//listView.setItems(FXCollections.observableList(values));
	}
	
	 

	    
	
}
