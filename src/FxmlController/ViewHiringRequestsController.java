package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.Application;
import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.HiringRequest;
import Login.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ViewHiringRequestsController  implements Initializable{
	private Employee employee;
	private EventPlanningRequest epr;
	private LinkedList<HiringRequest> approvedRequests;
	private LinkedList<HiringRequest> openRequests;
	private LinkedList<HiringRequest> rejectedRequests;

	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private TableView<HiringRequest> tableApprovedRequests;
	@FXML private TableColumn<HiringRequest, String> columnApproved_Department;
	@FXML private TableColumn<HiringRequest, String> columnApproved_ContractType;
	@FXML private TableColumn<HiringRequest, String> columnApproved_Experience;
	@FXML private TableColumn<HiringRequest, String> columnApproved_JobTitle;
	@FXML private TableColumn<HiringRequest, String> columnApproved_JobDescription;
	@FXML private TableView<HiringRequest> tableOpenRequests;
	@FXML private TableColumn<HiringRequest, String> columnOpen_Department;
	@FXML private TableColumn<HiringRequest, String> columnOpen_ContractType;
	@FXML private TableColumn<HiringRequest, String> columnOpen_Experience;
	@FXML private TableColumn<HiringRequest, String> columnOpen_JobTitle;
	@FXML private TableColumn<HiringRequest, String> columnOpen_JobDescription;
	@FXML private TableView<HiringRequest> tableRejectedRequests;
	@FXML private TableColumn<HiringRequest, String> columnRejected_Department;
	@FXML private TableColumn<HiringRequest, String> columnRejected_ContractType;
	@FXML private TableColumn<HiringRequest, String> columnRejected_Experience;
	@FXML private TableColumn<HiringRequest, String> columnRejected_JobTitle;
	@FXML private TableColumn<HiringRequest, String> columnRejected_JobDescription;
	
	
	
	
	public ViewHiringRequestsController(Employee employee, EventPlanningRequest epr){
		this.employee=employee;
		this.epr=epr;
		this.approvedRequests = new LinkedList<HiringRequest>();
		this.openRequests = new LinkedList<HiringRequest>();
		this.rejectedRequests = new LinkedList<HiringRequest>();
		for (int i=0; i<this.epr.getHiringRequest().size();i++){
			if(this.epr.getHiringRequest().get(i).getStatus().equals("approved")){
				this.approvedRequests.add(this.epr.getHiringRequest().get(i));
			}
			else if (this.epr.getHiringRequest().get(i).getStatus().equals("open")){
				this.openRequests.add(this.epr.getHiringRequest().get(i));
			}
			else if (this.epr.getHiringRequest().get(i).getStatus().equals("rejected")){
				this.rejectedRequests.add(this.epr.getHiringRequest().get(i));
			}
			
		}
		
		
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
		
		// Approved Requests table
				columnApproved_Department.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("department"));
				columnApproved_ContractType.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("contractType"));
				columnApproved_Experience.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("experience"));
				columnApproved_JobTitle.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobTitle"));
				columnApproved_JobDescription.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobDescription"));
				tableApprovedRequests.setItems(FXCollections.observableList(this.approvedRequests));
							
				tableApprovedRequests.setRowFactory( tv -> {
				    TableRow<HiringRequest> row = new TableRow<>();
				    return row ;
				});
				
				// Open Requests table
				columnOpen_Department.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("department"));
				columnOpen_ContractType.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("contractType"));
				columnOpen_Experience.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("experience"));
				columnOpen_JobTitle.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobTitle"));
				columnOpen_JobDescription.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobDescription"));
				tableOpenRequests.setItems(FXCollections.observableList(this.openRequests));
							
				tableOpenRequests.setRowFactory( tv -> {
				    TableRow<HiringRequest> row = new TableRow<>();
				    return row ;
				});
				
				// Rejected Requests table
				columnRejected_Department.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("department"));
				columnRejected_ContractType.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("contractType"));
				columnRejected_Experience.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("experience"));
				columnRejected_JobTitle.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobTitle"));
				columnRejected_JobDescription.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobDescription"));
				tableRejectedRequests.setItems(FXCollections.observableList(this.rejectedRequests));
							
				tableRejectedRequests.setRowFactory( tv -> {
				    TableRow<HiringRequest> row = new TableRow<>();
				    return row ;
				});
	}    
	
	
	
    
}   
















