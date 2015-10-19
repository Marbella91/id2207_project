package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.ClientRecord;
import EventPlanningRequest.EventPlanningRequest;
import Login.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SCSOInterfaceController  implements Initializable{
	private Employee employee;
	private LinkedList<EventPlanningRequest> EPRequests;
	private LinkedList<EventPlanningRequest> pendingSCSORequests;
	private LinkedList<EventPlanningRequest> pendingFinancialRequests;
	private LinkedList<EventPlanningRequest> pendingAdministrationRequests;
	private LinkedList<EventPlanningRequest> approvedRequests;
	private LinkedList<EventPlanningRequest> rejectedRequests;
	private LinkedList<ClientRecord> clientRecords;
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	
	@FXML private TableView<EventPlanningRequest> tablePendingSCSOCommentsRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingSCSO_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingSCSO_EventType;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingSCSO_From;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingSCSO_To;
	
	@FXML private TableView<EventPlanningRequest> tablePendingFinancialCommentsRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingFinancial_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingFinancial_EventType;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingFinancial_From;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingFinancial_To;
	
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
	
	@FXML private TableView<EventPlanningRequest> tableRejectedRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnRejected_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnRejected_EventType;
	@FXML private TableColumn<EventPlanningRequest, String> columnRejected_From;
	@FXML private TableColumn<EventPlanningRequest, String> columnRejected_To;
	
	@FXML private TableView<ClientRecord> tableClientRecords;
	@FXML private TableColumn<ClientRecord, String> columnRecordReference;
	@FXML private TableColumn<ClientRecord, String> columnClientName;
	@FXML private Button buttonCreateClient;
	
	public SCSOInterfaceController(Employee employee){
		this.employee=employee;
		this.clientRecords =ClientRecord.generateClientRecordList();
		this.EPRequests = EventPlanningRequest.generateAuthorizedEPRequestsList(employee);
		
		this.pendingSCSORequests = new LinkedList<EventPlanningRequest>();
		this.pendingFinancialRequests = new LinkedList<EventPlanningRequest>();
		this.pendingAdministrationRequests = new LinkedList<EventPlanningRequest>();
		this.approvedRequests = new LinkedList<EventPlanningRequest>();
		this.rejectedRequests = new LinkedList<EventPlanningRequest>();
		
		for (int i=0; i<this.EPRequests.size(); i++){
			EventPlanningRequest request = this.EPRequests.get(i);
			switch(request.getStatus()){
				case PendingSCSOComments:
					this.pendingSCSORequests.add(request);
					break;
				case PendingFinancialComments:
					this.pendingFinancialRequests.add(request);
					break;
				case PendingAdministrationComments:
					this.pendingAdministrationRequests.add(request);
					break;
				case Approved:
					this.approvedRequests.add(request);
					break;
				case Rejected:
					this.rejectedRequests.add(request);
					break;
				default:
					break;
			}
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
		
		//initialize tables of epr
		Controller.initializeEventPlanningRequestsTable(this, this.employee,
				this.tablePendingSCSOCommentsRequests,
				this.columnPendingSCSO_ClientName,
				this.columnPendingSCSO_EventType,
				this.columnPendingSCSO_From,
				this.columnPendingSCSO_To,
				this.pendingSCSORequests);
		
		Controller.initializeEventPlanningRequestsTable(this, this.employee,
				this.tablePendingFinancialCommentsRequests,
				this.columnPendingFinancial_ClientName,
				this.columnPendingFinancial_EventType,
				this.columnPendingFinancial_From,
				this.columnPendingFinancial_To,
				this.pendingFinancialRequests);
		
		Controller.initializeEventPlanningRequestsTable(this, this.employee,
				this.tablePendingAdministrationCommentsRequests,
				this.columnPendingAdministration_ClientName,
				this.columnPendingAdministration_EventType,
				this.columnPendingAdministration_From,
				this.columnPendingAdministration_To,
				this.pendingAdministrationRequests);
		
		Controller.initializeEventPlanningRequestsTable(this, this.employee,
				this.tableApprovedRequests,
				this.columnApproved_ClientName,
				this.columnApproved_EventType,
				this.columnApproved_From,
				this.columnApproved_To,
				this.approvedRequests);
		
		Controller.initializeEventPlanningRequestsTable(this, this.employee,
				this.tableRejectedRequests,
				this.columnRejected_ClientName,
				this.columnRejected_EventType,
				this.columnRejected_From,
				this.columnRejected_To,
				this.rejectedRequests);
		
		// client records table
		columnRecordReference.setCellValueFactory(new PropertyValueFactory<ClientRecord, String>("recordRef"));
		columnClientName.setCellValueFactory(new PropertyValueFactory<ClientRecord, String>("clientName"));
		tableClientRecords.setItems(FXCollections.observableList(this.clientRecords));
					
		tableClientRecords.setRowFactory( tv -> {
		    TableRow<ClientRecord> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty()) {
		        	Controller.generateInterface(this,
		    				new  ViewClientRecordController(this.employee, row.getItem()),
		    				"../Fxml/ViewClientRecord.fxml",
		    				(Stage) this.tableClientRecords.getScene().getWindow(),
		    				"View Client Record");
		        }
		    });
		    return row ;
		});
	}
	
	@FXML
	public void handleCreateClient(ActionEvent event) throws IOException{
		String clientRecordRef = JOptionPane.showInputDialog("Enter the new client record reference","clientname");
		if(clientRecordRef != null){
    		if (clientRecordRef.equals("")) {
    		JOptionPane.showMessageDialog(null, "Please enter the reference!",
					"", JOptionPane.ERROR_MESSAGE);
    		return;
    		}
    		ClientRecord record = new ClientRecord(clientRecordRef);
    		record.fromRecordToXml();
    		JOptionPane.showMessageDialog(null, "The client record has been "
    				+ "created successfully!",
    				"", JOptionPane.INFORMATION_MESSAGE);
    		Controller.generateInterface(this,
    				new  ViewClientRecordController(this.employee, record),
    				"../Fxml/ViewClientRecord.fxml",
    				(Stage) this.buttonCreateClient.getScene().getWindow(),
    				"View Client Record");
    	}
    }
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		Controller.logout(this, buttonLogout);
	}

	    
	
}
