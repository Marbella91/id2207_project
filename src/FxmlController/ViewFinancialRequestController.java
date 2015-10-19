package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.FinancialRequest;
import Login.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewFinancialRequestController  implements Initializable{
	
	private Employee employee;
	private EventPlanningRequest epr;
	private FinancialRequest request;
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button buttonMenu;
	@FXML private Button buttonBackEpr;
	@FXML private Label labelEvent;
	@FXML private Label labelDepartment;
	@FXML private Label labelAmount;
	@FXML private Label labelReason;
	@FXML private Label labelStatus;
	
	public ViewFinancialRequestController(Employee employee, EventPlanningRequest epr){
		this.employee=employee;
		this.epr = epr;
		this.request=epr.getFinancialRequest();
	}
	
	
	@FXML
	public void handleMenu(ActionEvent event){
		Stage currentStage = (Stage) this.buttonMenu.getScene().getWindow();
		this.employee.generateInterface(currentStage);
	}
	
	@FXML
	public void handleBackEPR(ActionEvent event){
		Stage currentStage = (Stage) this.buttonBackEpr.getScene().getWindow();
		Controller.generateInterface(this,
				new ViewEventPlanningRequestController(this.employee, this.epr),
				"../Fxml/ViewEventPlanningRequest.fxml", currentStage,
				"View Event Planning Request");
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		Controller.logout(this, buttonLogout);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelEvent.setText(this.epr.toString());
		this.labelLogin.setText(this.employee.getLogin());
		this.labelDepartment.setText(this.request.getDepartment());
		this.labelAmount.setText(Integer.toString(this.request.getAmount()));
		this.labelReason.setText(this.request.getReason());
		this.labelStatus.setText(this.request.getStatus());
	}

}
