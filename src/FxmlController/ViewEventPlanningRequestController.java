package FxmlController;

import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ViewEventPlanningRequestController  implements Initializable{
	private Employee employee;
	private EventPlanningRequest request;
	@FXML private Label labelLogin;
	@FXML private Button buttonMenu;
	@FXML private Label labelRecordNumber;
	@FXML private Label labelClientName;
	@FXML private Label labelEventType;
	
	public ViewEventPlanningRequestController(Employee employee, EventPlanningRequest request){
		this.employee=employee;
		this.request = request;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//this.labelLogin.setText(this.employee.getLogin());
		if (this.request.getClientRecordNumber() == 0){
			this.labelRecordNumber.setText("");
		} else {
			this.labelRecordNumber.setText(String.valueOf(this.request.getClientRecordNumber()));
		}
		this.labelClientName.setText(this.request.getClientName());
		this.labelEventType.setText(this.request.getEventType());
	}
	
	
	@FXML
	public void handleMenu(ActionEvent event){
		Stage currentStage = (Stage) this.buttonMenu.getScene().getWindow();
		this.employee.generateInterface(currentStage);
	}
	
	 

	    
	
}
