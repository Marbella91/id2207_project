package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.EventPlanningRequest;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class HRInterfaceController implements Initializable{
	private Employee employee;
	private LinkedList<HiringRequest> approvedRequests;
	private LinkedList<HiringRequest> rejectedRequests;
	private LinkedList<HiringRequest> openRequests;
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button approveButton;
	@FXML private Button rejectButton;
	@FXML private TableView<HiringRequest> tableApproved;
	@FXML private TableColumn<HiringRequest, String> departmentApproved;
	@FXML private TableColumn<HiringRequest, String> contractApproved;
	@FXML private TableColumn<HiringRequest, String> experienceApproved;
	@FXML private TableColumn<HiringRequest, String> jobTitleApproved;
	@FXML private TableColumn<HiringRequest, String> jobDescriptionApproved;
	
	@FXML private TableView<HiringRequest> tableOpen;
	@FXML private TableColumn<HiringRequest, String> departmentOpen;
	@FXML private TableColumn<HiringRequest, String> contractOpen;
	@FXML private TableColumn<HiringRequest, String> experienceOpen;
	@FXML private TableColumn<HiringRequest, String> jobTitleOpen;
	@FXML private TableColumn<HiringRequest, String> jobDescriptionOpen;
	
	@FXML private TableView<HiringRequest> tableRejected;
	@FXML private TableColumn<HiringRequest, String> departmentRejected;
	@FXML private TableColumn<HiringRequest, String> contractRejected;
	@FXML private TableColumn<HiringRequest, String> experienceRejected;
	@FXML private TableColumn<HiringRequest, String> jobTitleRejected;
	@FXML private TableColumn<HiringRequest, String> jobDescriptionRejected;
	
	public HRInterfaceController(Employee employee){
		this.employee=employee;
		this.approvedRequests = new LinkedList<HiringRequest>();
		this.openRequests = new LinkedList<HiringRequest>();
		this.rejectedRequests = new LinkedList<HiringRequest>();
		
		LinkedList<EventPlanningRequest> list= EventPlanningRequest.generateEPRequestsList();
		for(int j=0; j<list.size();j++){
			EventPlanningRequest epr=list.get(j);
			for (int i=0; i<epr.getHiringRequest().size();i++){
				if(epr.getHiringRequest().get(i).getStatus().equals("approved")){
					this.approvedRequests.add(epr.getHiringRequest().get(i));
				}
				else if (epr.getHiringRequest().get(i).getStatus().equals("open")){
					this.openRequests.add(epr.getHiringRequest().get(i));
				}
				else if (epr.getHiringRequest().get(i).getStatus().equals("rejected")){
					this.rejectedRequests.add(epr.getHiringRequest().get(i));
				}
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
	
	public void traceTable(){
		// Approved Requests table
		departmentApproved.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("department"));
		contractApproved.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("contractType"));
		experienceApproved.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("experience"));
		jobTitleApproved.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobTitle"));
		jobDescriptionApproved.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobDescription"));
		tableApproved.setItems(FXCollections.observableList(this.approvedRequests));
					
		tableApproved.setRowFactory( tv -> {
		    TableRow<HiringRequest> row = new TableRow<>();
		    return row ;
		});
		
		// Open Requests table
				departmentOpen.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("department"));
				contractOpen.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("contractType"));
				experienceOpen.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("experience"));
				jobTitleOpen.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobTitle"));
				jobDescriptionOpen.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobDescription"));
				tableOpen.setItems(FXCollections.observableList(this.openRequests));
							
				tableOpen.setRowFactory( tv -> {
				    TableRow<HiringRequest> row = new TableRow<>();
				    row.setOnMouseClicked(event -> {
				    	System.out.println("clicked");
				        if (! row.isEmpty()) {
				        approveButton.setVisible(true);
				        rejectButton.setVisible(true);
				        		approveButton.setOnAction(new EventHandler<ActionEvent>() {
				        		@Override
								public void handle(ActionEvent e) {
								approvedRequests.add(row.getItem());
								openRequests.remove(row.getItem());
								approveButton.setVisible(false);
						        rejectButton.setVisible(false);
								traceTable();
			
								}
				        	}
				        	);
				        	
				        	rejectButton.setOnAction(new EventHandler<ActionEvent>() {
				        		@Override
								public void handle(ActionEvent e) {
								rejectedRequests.add(row.getItem());
								openRequests.remove(row.getItem());
								approveButton.setVisible(false);
						        rejectButton.setVisible(false);
								traceTable();
								}
				        	}
				        	);
				        	
				        }
				        	
				      
				    });
				    return row ;
				});
				
				// Rejected Requests table
				departmentRejected.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("department"));
				contractRejected.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("contractType"));
				experienceRejected.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("experience"));
				jobTitleRejected.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobTitle"));
				jobDescriptionRejected.setCellValueFactory(new PropertyValueFactory<HiringRequest, String>("jobDescription"));
				tableRejected.setItems(FXCollections.observableList(this.rejectedRequests));
							
				tableRejected.setRowFactory( tv -> {
				    TableRow<HiringRequest> row = new TableRow<>();
				    return row ;
				});
				
		
			   
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	   	this.labelLogin.setText(this.employee.getLogin());
		 approveButton.setVisible(false);
	     rejectButton.setVisible(false);
	     traceTable();
	
	
	}

}
