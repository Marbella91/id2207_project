package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.HiringRequest;
import Login.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
		
		LinkedList<EventPlanningRequest> list= EventPlanningRequest.generateEPRequestList();
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
		Controller.logout(this, buttonLogout);
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
								int idHiring=row.getItem().getidHiring();
								int id=row.getItem().getIDEPR();
								EventPlanningRequest eventPla=EventPlanningRequest.fromXmlIdToRequest(id);
								eventPla.getHiringRequest().get(idHiring).setStatus("approved");
								eventPla.updateXml();
								
			
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
								int idHiring=row.getItem().getidHiring();
								int id=row.getItem().getIDEPR();
								EventPlanningRequest eventPla=EventPlanningRequest.fromXmlIdToRequest(id);
								eventPla.getHiringRequest().get(idHiring).setStatus("rejected");
								eventPla.updateXml();
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
		this.labelLogin.setText(this.employee.getLogin());
		 approveButton.setVisible(false);
	     rejectButton.setVisible(false);
	     traceTable();
	
	
	}

}
