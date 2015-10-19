package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.Application;
import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.EventPlanningRequestStatus;
import EventPlanningRequest.HiringRequest;
import Login.Employee;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewEventPlanningRequestController  implements Initializable{
	private Employee employee;
	private EventPlanningRequest request;
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button buttonMenu;
	@FXML private Label labelClientRef;
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
	@FXML private VBox vboxButton;
	
	public ViewEventPlanningRequestController(Employee employee, EventPlanningRequest request){
		this.employee=employee;
		this.request = request;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
		this.labelClientRef.setText(this.request.getClientRecordRef());
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
		
		
		// Display existing comments from some actors or warnings
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
			case ProductionManager:
				String warning = "";
				if (this.request.getProductionApplication() == null)
					warning = "Warning: You cannot create an application if there is at least one open hiring request\n" +
							"for this event.\n" +
							"Once the application has been created, it is not possible to create hiring requests anymore.\n";
				else if (this.request.getProductionApplication().getStatus().equals("open"))
					warning = "Warning: You cannot set the application's status to 'in progress' if there is an open\n" +
							"financial request for this event.\n" +
							"Once the application's status has been set to 'in progress', it is not possible to create\n" +
							"a financial request or to add new tasks in the application.";
				this.labelExistingComments.setText(warning);
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
	                this.vboxButton.getChildren().add(button);
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
	                button2.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	request.setStatus(EventPlanningRequestStatus.Rejected);
	                    	request.updateXml();
	                    	Stage stage=(Stage) button2.getScene().getWindow();
	                    	reinitializeInterface(stage);
	                    }
	                });
	                this.vboxButton.getChildren().add(button1);
	                this.vboxButton.getChildren().add(button2);
				}
				break;
			
			case FinancialManager:
				if (this.request.getStatus().equals(EventPlanningRequestStatus.PendingFinancialComments) &&
					this.request.getFinancialComments() == null	){
					Button  button = new Button("Add a comment");
	                button.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	String newComment = JOptionPane.showInputDialog("Write your comment below:", "My comment");
	                    	if(newComment != null)
	                    	{
	                    		request.setFinancialComments(newComment);
	                    		request.updateXml();
	                    		Stage stage=(Stage) button.getScene().getWindow();
	                    		reinitializeInterface(stage);
	                    	}
	                    }
	                });
	                this.vboxButton.getChildren().add(button);
				}
				else if (this.request.getStatus().equals(EventPlanningRequestStatus.PendingFinancialComments) &&
						this.request.getFinancialComments() != null	){
					Button  button = new Button("Send to Administration Manager");
	                button.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	request.setStatus(EventPlanningRequestStatus.PendingAdministrationComments);
	                    	request.updateXml();
	                    	Stage stage=(Stage) button.getScene().getWindow();
	                    	reinitializeInterface(stage);
	                    }
	                });
	                this.vboxButton.getChildren().add(button);
	            }
				break;
			
			case AdministrationManager:
				if (this.request.getStatus().equals(EventPlanningRequestStatus.PendingAdministrationComments) &&
					this.request.getAdministrationComments() == null	){
					Button  button = new Button("Add a comment");
	                button.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	String newComment = JOptionPane.showInputDialog("Write your comment below:", "My comment");
	                    	if(newComment != null)
	                    	{
	                    		request.setAdministrationComments(newComment);
	                    		request.updateXml();
	                    		Stage stage=(Stage) button.getScene().getWindow();
	                    		reinitializeInterface(stage);
	                    	}
	                    }
	                });
	                this.vboxButton.getChildren().add(button);
				}
				else if (this.request.getStatus().equals(EventPlanningRequestStatus.PendingAdministrationComments) &&
						this.request.getAdministrationComments() != null	){
					Button  button1 = new Button("Approve request");
	                button1.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	request.setStatus(EventPlanningRequestStatus.Approved);
	                    	request.updateXml();
	                    	Stage stage=(Stage) button1.getScene().getWindow();
	                    	reinitializeInterface(stage);
	                    }
	                });
	                Button  button2 = new Button("Reject request");
	                button2.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	request.setStatus(EventPlanningRequestStatus.Rejected);
	                    	request.updateXml();
	                    	Stage stage=(Stage) button2.getScene().getWindow();
	                    	reinitializeInterface(stage);
	                    }
	                });
	                this.vboxButton.getChildren().add(button1);
	                this.vboxButton.getChildren().add(button2);
				}
				break;
				
				case ProductionManager:
					// possibility to create application if there is no existing application
					// and there is either no hiring request or only approved or rejected hiring request
					LinkedList<HiringRequest> hiringRequests = this.request.getHiringRequest();
					boolean isNoOpenHiringRequests = true;
					for (int i = 0; i < hiringRequests.size(); i++){
						if (hiringRequests.get(i).getStatus().equals("open")){
							isNoOpenHiringRequests = false;
						}
					}
					if ((this.request.getProductionApplication() == null) &&
							(isNoOpenHiringRequests)){
						Button  button = new Button("Create an Application");
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								request.setProductionApplication(new Application("production", request.toString()));
								request.updateXml();
								JOptionPane.showMessageDialog(null, "The application has been "
										+ "created successfully!",
										"", JOptionPane.INFORMATION_MESSAGE);
								
								// open the application
								Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
								Controller.generateInterface(this,
										new ViewApplicationController(employee, request),
										"../Fxml/ViewApplication.fxml",
										currentStage, "View Application");
							}
						});
						this.vboxButton.getChildren().add(button);
					}
					// see the application if it exists
					if (this.request.getProductionApplication() != null){
						Button  button = new Button("View the Application");
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
								Controller.generateInterface(this,
										new ViewApplicationController(employee, request),
										"../Fxml/ViewApplication.fxml",
										currentStage, "View Application");
							}
						});
						this.vboxButton.getChildren().add(button);
					}
					// possibility to create a hiring request if the application
					// has not been created yet
					if (this.request.getProductionApplication() == null){
						Button  button = new Button("Create a Hiring Request");
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
								Controller.generateInterface(this,
										new NewHiringRequestController(employee, request),
										"../Fxml/NewHiringRequest.fxml", currentStage,
										"New Hiring Request");
							}
						});
						this.vboxButton.getChildren().add(button);
					}
					// see hiring requests if at least one exists
					if (!hiringRequests.isEmpty()){
						Button  button = new Button("View Hiring Requests");
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
								Controller.generateInterface(this,
										new ViewHiringRequestsController(employee, request),
										"../Fxml/ViewHiringRequests.fxml", currentStage,
										"Hiring request list");
							}
						});
						this.vboxButton.getChildren().add(button);
					}
					// possibility to create a financial request if there is no existing
					// financial request and if the application's status is "open"
					if ((this.request.getFinancialRequest() == null) &&
							(this.request.getProductionApplication() != null) &&
							this.request.getProductionApplication().getStatus().equals("open")){
						Button  button = new Button("Create a Financial Request");
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
								Controller.generateInterface(this,
										new NewFinancialRequestController(employee, request),
										"../Fxml/NewFinancialRequest.fxml", currentStage,
										"New Financial Request");
							}
						});
						this.vboxButton.getChildren().add(button);
					}
					// see the financial request if it exists
					if (this.request.getFinancialRequest() != null){
						Button  button = new Button("View the Financial Request");
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
								Controller.generateInterface(this,
										new ViewFinancialRequestController(employee, request.getFinancialRequest()),
												"../Fxml/ViewFinancialRequests.fxml", currentStage,
												"View Financial Request");
							}
						});
						this.vboxButton.getChildren().add(button);
					}
					// possibility to set status from "open" to "in progress" if there is no
					// open financial request
					if ((this.request.getProductionApplication() != null) &&
							(this.request.getProductionApplication().getStatus().equals("open")) &&
							((this.request.getFinancialRequest() == null) ||
									!this.request.getFinancialRequest().getStatus().equals("open"))){
						Button  button = new Button("Set the Application's status to 'in progress'");
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								request.getProductionApplication().setStatus("inprogress");
		                    	request.updateXml();
		                    	Stage stage=(Stage) button.getScene().getWindow();
		                    	reinitializeInterface(stage);
							}
						});
						this.vboxButton.getChildren().add(button);
					}
					break;
					
				
			default:
				break;
		}
		
	}
	
	public void reinitializeInterface(Stage currentStage)
	{
		Controller.generateInterface(this,
				new ViewEventPlanningRequestController(this.employee, this.request),
				"../Fxml/ViewEventPlanningRequest.fxml", currentStage,
				"View Event Planning Request");
	}
	
	@FXML
	public void handleMenu(ActionEvent event){
		Stage currentStage = (Stage) this.buttonMenu.getScene().getWindow();
		this.employee.generateInterface(currentStage);
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		Controller.logout(this, buttonLogout);
	}

	    
	
}
