package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.Application;
import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.EventPlanningRequestStatus;
import EventPlanningRequest.HiringRequest;
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
		// TODO Auto-generated method stub
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
								FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewApplication.fxml"));
						        ViewApplicationController controller = new ViewApplicationController(employee, request);
						        loader.setController(controller); 
						        Parent root;
								try {
								 Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
									root = (Parent) loader.load();
									Scene scene = new Scene(root);
								       currentStage.setScene(scene);
								       currentStage.setTitle("View Application"); 
								       currentStage.setHeight(800);
								       currentStage.setWidth(600);
								       currentStage.show();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
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
								FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewApplication.fxml"));
						        ViewApplicationController controller = new ViewApplicationController(employee, request);
						        loader.setController(controller); 
						        Parent root;
								
								try {
								 Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
									root = (Parent) loader.load();
									Scene scene = new Scene(root);
								       currentStage.setScene(scene);
								       currentStage.setTitle("View Application"); 
								       currentStage.setHeight(800);
								       currentStage.setWidth(600);
								       currentStage.show();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
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
								FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/NewHiringRequest.fxml"));
						        NewHiringRequestController controller = new NewHiringRequestController(employee, request);
						        loader.setController(controller); 
						        Parent root;
								
								try {
								 Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
									root = (Parent) loader.load();
									Scene scene = new Scene(root);
								       currentStage.setScene(scene);
								       currentStage.setTitle("New Hiring Request"); 
								       currentStage.setHeight(800);
								       currentStage.setWidth(600);
								       currentStage.show();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
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
								FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewHiringRequests.fxml"));
						        ViewHiringRequestsController controller = new ViewHiringRequestsController(employee, request);
						        loader.setController(controller); 
						        Parent root;
								
								try {
								 Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
									root = (Parent) loader.load();
									Scene scene = new Scene(root);
								       currentStage.setScene(scene);
								       currentStage.setTitle("Hiring request list"); 
								       currentStage.setHeight(800);
								       currentStage.setWidth(600);
								       currentStage.show();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
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
								FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/NewFinancialRequest.fxml"));
						        NewFinancialRequestController controller = new NewFinancialRequestController(employee, request);
						        loader.setController(controller); 
						        Parent root;
								
								try {
								 Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
									root = (Parent) loader.load();
									Scene scene = new Scene(root);
								       currentStage.setScene(scene);
								       currentStage.setTitle("New Financial Request"); 
								       currentStage.setHeight(800);
								       currentStage.setWidth(600);
								       currentStage.show();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
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
								FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewFinancialRequests.fxml"));
						        ViewFinancialRequestController controller = new ViewFinancialRequestController(employee, request.getFinancialRequest());
						        loader.setController(controller); 
						        Parent root;
								
								try {
								 Stage currentStage= (Stage) buttonLogout.getScene().getWindow();
									root = (Parent) loader.load();
									Scene scene = new Scene(root);
								       currentStage.setScene(scene);
								       currentStage.setTitle("View Financial Request"); 
								       currentStage.setHeight(800);
								       currentStage.setWidth(600);
								       currentStage.show();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
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
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if(option == JOptionPane.OK_OPTION){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/LoginInterface.fxml"));
	        LoginController controller = new  LoginController();
	        loader.setController(controller); 
	        Parent root = (Parent) loader.load();
	        Stage primaryStage=(Stage) buttonLogout.getScene().getWindow();
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Login"); 
	        primaryStage.setHeight(250);
	        primaryStage.setWidth(400);
	        primaryStage.show();
	    }
	}

	    
	
}
