package FxmlController;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.HiringRequest;
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

public class NewHiringRequestController  implements Initializable{
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button buttonMenu;
	@FXML private Button buttonBackEpr;
	@FXML private Button buttonSubmit;
	@FXML private Label eventLabel;
	@FXML private RadioButton fullTimeButton;
	@FXML private RadioButton partTimeButton;
	@FXML private RadioButton adminButton;
	@FXML private RadioButton servicesButton;
	@FXML private RadioButton prodButton;
	@FXML private RadioButton financialButton;
	@FXML private TextField yearsExpText;
	@FXML private TextField jobTitleText;
	@FXML private TextField jobDescriptionText;
	private Employee employee;
	private EventPlanningRequest epr;
	private ToggleGroup contractType;
	private ToggleGroup department;
	
	public NewHiringRequestController(Employee employee,EventPlanningRequest epr){
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
		
		String yearsExpString = this.yearsExpText.getText();
		int yearsExp = 0;
		if (yearsExpString.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter the years of experience number!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		} else try {  
			yearsExp = Integer.parseInt(yearsExpString);
		} catch(NumberFormatException nfe) {  
			JOptionPane.showMessageDialog(null, "The years of experience should be a number!",
					"", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		
		if (this.jobTitleText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please enter a job title !",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (this.jobDescriptionText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please enter a job description !",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		HiringRequest hiringRequest=new HiringRequest(
				department.getSelectedToggle().getUserData().toString(),
				contractType.getSelectedToggle().getUserData().toString(),
				yearsExp,
				this.jobTitleText.getText(),
				this.jobDescriptionText.getText(),epr.getId());
		
		this.epr.getHiringRequest().add(hiringRequest);
		this.epr.updateXml();
		

		JOptionPane.showMessageDialog(null, "The hiring request has been "
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
		
		contractType = new ToggleGroup();
		this.fullTimeButton.setToggleGroup(contractType);
		this.fullTimeButton.setUserData("full time");
		this.partTimeButton.setToggleGroup(contractType);
		this.partTimeButton.setUserData("part time");
		this.fullTimeButton.setSelected(true);
		
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
