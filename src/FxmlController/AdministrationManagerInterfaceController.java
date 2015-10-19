package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import EventPlanningRequest.EventPlanningRequest;
import Login.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdministrationManagerInterfaceController  implements Initializable{
	private Employee employee;
	private LinkedList<EventPlanningRequest> EPRequests;
	private LinkedList<EventPlanningRequest> pendingAdministrationRequests;
	private LinkedList<EventPlanningRequest> approvedRequests;
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	
	@FXML private TableView<EventPlanningRequest> tablePendingAdministrationCommentsRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingAdministration_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingAdministration_EventType;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingAdministration_From;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingAdministration_To;
	
	@FXML private TableView<EventPlanningRequest> tableApprovedRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_EventType;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_From;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_To;
	
	public AdministrationManagerInterfaceController(Employee employee){
		this.employee=employee;
		this.EPRequests = EventPlanningRequest.generateAuthorizedEPRequestsList(employee);
		
		this.pendingAdministrationRequests = new LinkedList<EventPlanningRequest>();
		this.approvedRequests = new LinkedList<EventPlanningRequest>();
		
		for (int i=0; i<this.EPRequests.size(); i++){
			EventPlanningRequest request = this.EPRequests.get(i);
			switch(request.getStatus()){
				case PendingAdministrationComments:
					this.pendingAdministrationRequests.add(request);
					break;
				case Approved:
					this.approvedRequests.add(request);
					break;
				default:
					break;
			}
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
		
		// Pending Administration Comments Requests table
		Controller.initializeEventPlanningRequestsTable(this, this.employee,
				this.tablePendingAdministrationCommentsRequests,
				this.columnPendingAdministration_ClientName,
				this.columnPendingAdministration_EventType,
				this.columnPendingAdministration_From,
				this.columnPendingAdministration_To,
				this.pendingAdministrationRequests);
		
		// Approved Requests table
		Controller.initializeEventPlanningRequestsTable(this, this.employee,
				this.tableApprovedRequests,
				this.columnApproved_ClientName,
				this.columnApproved_EventType,
				this.columnApproved_From,
				this.columnApproved_To,
				this.approvedRequests);
	}		
		
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		Controller.logout(this, buttonLogout);
	}

	    
	
}
