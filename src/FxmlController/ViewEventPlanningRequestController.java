package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.EventPlanningRequestStatus;
import Login.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViewEventPlanningRequestController  implements Initializable{
	private Employee employee;
	private EventPlanningRequest request;
	@FXML private Label labelLogin;
	@FXML private Button buttonMenu;
	@FXML private Label labelRecordNumber;
	@FXML private Label labelClientName;
	@FXML private Label labelEventType;
	@FXML private Label labelFromDate;
	@FXML private Label labelToDate;
	@FXML private Label labelAttendees;
	@FXML private Label labelDecorations;
	@FXML private Label labelParties;
	@FXML private Label labelFood;
	@FXML private Label labelPhotos;
	@FXML private Label labelDrinks;
	@FXML private Label labelBudget;
	@FXML private Label labelExistingComments;
	@FXML private HBox hboxButton;
	
	public ViewEventPlanningRequestController(Employee employee, EventPlanningRequest request){
		this.employee=employee;
		this.request = request;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
		if (this.request.getClientRecordNumber() == 0){
			this.labelRecordNumber.setText("");
		} else {
			this.labelRecordNumber.setText(String.valueOf(this.request.getClientRecordNumber()));
		}
		this.labelClientName.setText(this.request.getClientName());
		this.labelEventType.setText(this.request.getEventType());
		this.labelFromDate.setText(this.request.getFromDate());
		this.labelToDate.setText(this.request.getToDate());
		this.labelAttendees.setText(String.valueOf(this.request.getExpectedAttendeesNumber()));
		this.labelDecorations.setText(this.request.isDecorationPreference() ? "yes" : "no");
		this.labelParties.setText(this.request.isPartiesPreference() ? "yes" : "no");
		this.labelFood.setText(this.request.isFoodPreference() ? "yes" : "no");
		this.labelPhotos.setText(this.request.isPhotoPreference() ? "yes" : "no");
		this.labelDrinks.setText(this.request.isDrinkPreference() ? "yes" : "no");
		this.labelBudget.setText(String.valueOf(this.request.getExpectedBudget()));
		
		
		// Display existing comments from some actors
		switch(this.employee.getJob()){
			case SeniorCustomerServiceOfficer:
			case FinancialManager:
			case AdministrationManager:
				String comments = "";
				String SCSOComments = this.request.getSCSOComments();
				if (SCSOComments != null)
					comments += "Comments from Senior Customer Service Officer: "
						+ SCSOComments + "\n";
				String financialComments = this.request.getFinancialComments();
				if (financialComments != null)
					comments += "Comments from the Financial Manager: "
						+ financialComments+ "\n";
				String administrationComments = this.request.getAdministrationComments();
				if (administrationComments != null)
					comments += "Comments from Administration Manager: "
						+ administrationComments+ "\n";
				this.labelExistingComments.setText(comments);
				break;
			default:
				this.labelExistingComments.setText("");
				break;
		}
		
		// Display button depending on actor
		switch(this.employee.getJob()){
			case SeniorCustomerServiceOfficer:
				if (this.request.getStatus().equals(EventPlanningRequestStatus.PendingSCSOComments) &&
					this.request.getSCSOComments() == null	){
					Button  button = new Button("Add a comment");
	                button.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	String newComment = JOptionPane.showInputDialog("Write your comment below:", "My comment");
	                    	if(newComment != null)
	                    	{
	                    		request.setSCSOComments(newComment);
	                    		request.updateXml();
	                    		Stage stage=(Stage) button.getScene().getWindow();
	                    		reinitializeInterface(stage);
	                    	}
	                    }
	                });
	                this.hboxButton.getChildren().add(button);
				}
				else if (this.request.getStatus().equals(EventPlanningRequestStatus.PendingSCSOComments) &&
						this.request.getSCSOComments() != null	){
					Button  button1 = new Button("Accept and send to Financial Manager");
	                button1.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	request.setStatus(EventPlanningRequestStatus.PendingFinancialComments);
	                    	request.updateXml();
	                    	Stage stage=(Stage) button1.getScene().getWindow();
	                    	reinitializeInterface(stage);
	                    }
	                });
	                Button  button2 = new Button("Reject request");
	                button1.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	request.setStatus(EventPlanningRequestStatus.Rejected);
	                    	request.updateXml();
	                    	Stage stage=(Stage) button2.getScene().getWindow();
	                    	reinitializeInterface(stage);
	                    }
	                });
	                this.hboxButton.getChildren().add(button1);
	                this.hboxButton.getChildren().add(button2);
				}
				break;
			default:
				break;
		}
		
	}
	
	public void reinitializeInterface(Stage currentStage)
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewEventPlanningRequest.fxml"));
        ViewEventPlanningRequestController controller = new  ViewEventPlanningRequestController(this.employee, this.request);
        loader.setController(controller); 
        Parent root;
		try {
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
	
	@FXML
	public void handleMenu(ActionEvent event){
		Stage currentStage = (Stage) this.buttonMenu.getScene().getWindow();
		this.employee.generateInterface(currentStage);
	}
	
	 

	    
	
}
