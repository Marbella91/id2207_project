package FxmlController;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.FinancialRequest;
import Login.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class NewFinancialRequestController   implements Initializable{
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button buttonMenu;
	@FXML private Button buttonBackEpr;
	@FXML private Button buttonSubmit;
	@FXML private Label eventLabel;
	@FXML private RadioButton adminButton;
	@FXML private RadioButton servicesButton;
	@FXML private RadioButton prodButton;
	@FXML private RadioButton financialButton;
	@FXML private TextField ProjReferenceText;
	@FXML private TextField amountText;
	@FXML private TextField reasonText;
	private Employee employee;
	private EventPlanningRequest epr;
	private ToggleGroup department;
	
	public NewFinancialRequestController(Employee employee,EventPlanningRequest epr){
		this.employee=employee;
		this.epr=epr;
			
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		Controller.logout(this, buttonLogout);
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
	public void handleSubmit(ActionEvent event){
		
		String amountString = this.amountText.getText();
		int amount = 0;
		if (amountString.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the financial amount!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		} else try {  
			amount = Integer.parseInt(amountString);
		} catch(NumberFormatException nfe) {  
			JOptionPane.showMessageDialog(null, "The financial amount should be a number!",
					"", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		
		FinancialRequest financialRequest=new FinancialRequest(
				department.getSelectedToggle().getUserData().toString(),
				amount, this.reasonText.getText(), epr.getId());
		
		this.epr.setFinancialRequest(financialRequest);
		epr.updateXml();
		
		JOptionPane.showMessageDialog(null, "The financial request has been "
				+ "created successfully!",
				"", JOptionPane.INFORMATION_MESSAGE);
		
		Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
		Controller.generateInterface(this,
				new ViewEventPlanningRequestController(this.employee, this.epr),
				"../Fxml/ViewEventPlanningRequest.fxml", currentStage,
				"View Event Planning Request");
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
		this.eventLabel.setText(this.epr.toString());
		
		department = new ToggleGroup();
		this.adminButton.setToggleGroup(department);
		this.adminButton.setUserData("administration");
		this.prodButton.setToggleGroup(department);
		this.prodButton.setUserData("production");
		this.servicesButton.setToggleGroup(department);
		this.servicesButton.setUserData("services");
		this.financialButton.setToggleGroup(department);
		this.financialButton.setUserData("financial");
		
		this.prodButton.setSelected(true);
	}
	
	
	
	

}
