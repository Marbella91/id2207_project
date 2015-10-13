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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewHiringRequestController  implements Initializable{
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button buttonSubmit;
	@FXML private CheckBox fullTimeBox;
	@FXML private CheckBox partTimeBox;
	@FXML private CheckBox adminBox;
	@FXML private CheckBox servicesBox;
	@FXML private CheckBox prodBox;
	@FXML private CheckBox financialBox;
	@FXML private TextField yearsExpText;
	@FXML private TextField jobTitleText;
	@FXML private TextField jobDescriptionText;
	private Employee employee;
	private EventPlanningRequest epr;
	
	public NewHiringRequestController(Employee employee,EventPlanningRequest epr){
		this.employee=employee;
		this.epr=epr;
			
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if(option == JOptionPane.OK_OPTION){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/LoginInterface.fxml"));
	        LoginController controller = new  LoginController();
	        loader.setController(controller); 
	        Parent root = (Parent) loader.load();
	        Stage primaryStage= (Stage) buttonLogout.getScene().getWindow();
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Login"); 
	        primaryStage.setHeight(250);
	        primaryStage.setWidth(400);
	        primaryStage.show();
	         
		}
	}
	
	@FXML
	public void handleSubmit(ActionEvent event){
		String contractType="";
		if (fullTimeBox.isSelected()){
			contractType="fullTime";
		}
		else if (partTimeBox.isSelected()) {
			contractType="partTime";
		}
		else {
			JOptionPane.showMessageDialog(null, "Please choose a contract type!",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
	
		String department="";
		if (adminBox.isSelected()){
			department="administration";
		}
		else if (servicesBox.isSelected()){
			department="services";
			}
		else if (prodBox.isSelected()){
			department="production";
		}
		else if (financialBox.isSelected()){
			department="financial";
		}
		else {
				JOptionPane.showMessageDialog(null, "Please choose a department!",
						"", JOptionPane.ERROR_MESSAGE);
				return;
			
		}
		if (this.yearsExpText.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please enter the experience year number !",
					"", JOptionPane.ERROR_MESSAGE);
			return;
		}
	
		
	
		int years=Integer.parseInt(this.yearsExpText.getText());
		
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
		
		HiringRequest hiringRequest=new HiringRequest(department,contractType,
				years,this.jobTitleText.getText(),
				this.jobDescriptionText.getText());
		
		this.epr.getHiringRequest().add(hiringRequest);
		this.epr.updateXml();
		

		JOptionPane.showMessageDialog(null, "The hiring request has been "
				+ "created successfully!",
				"", JOptionPane.INFORMATION_MESSAGE);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewEventPlanningRequest.fxml"));
        ViewEventPlanningRequestController controller = new  ViewEventPlanningRequestController(this.employee, this.epr);
        loader.setController(controller); 
        Parent root;
		
		try {
		 Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
			root = (Parent) loader.load();
			Scene scene = new Scene(root);
		       currentStage.setScene(scene);
		       currentStage.setTitle("View Event Planning Request"); 
		       currentStage.setHeight(800);
		       currentStage.setWidth(600);
		       currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
	}
	

}
