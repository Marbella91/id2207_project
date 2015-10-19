package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ResourceBundle;

import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.FinancialRequest;
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

public class FinancialManagerInterfaceController  implements Initializable{
	private Employee employee;
	private LinkedList<EventPlanningRequest> EPRequests;
	private LinkedList<EventPlanningRequest> pendingFinancialRequests;
	private LinkedList<EventPlanningRequest> approvedRequests;
	private LinkedList<FinancialRequest> approvedFinancialRequests;
	private LinkedList<FinancialRequest> openFinancialRequests;
	private LinkedList<FinancialRequest> rejectedFinancialRequests;
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button approveButton;
	@FXML private Button rejectButton;
	
	@FXML private TableView<EventPlanningRequest> tablePendingFinancialCommentsRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingFinancial_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingFinancial_EventType;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingFinancial_From;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingFinancial_To;
	
	@FXML private TableView<EventPlanningRequest> tableApprovedRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_EventType;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_From;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_To;
	
	@FXML private TableView<FinancialRequest> tableOpen;
	@FXML private TableColumn<FinancialRequest, String> departmentOpen;
	@FXML private TableColumn<FinancialRequest, String> amountOpen;
	@FXML private TableColumn<FinancialRequest, Calendar> reasonOpen;
	
	@FXML private TableView<FinancialRequest> tableRejected;
	@FXML private TableColumn<FinancialRequest, String> departmentRejected;
	@FXML private TableColumn<FinancialRequest, String> amountRejected;
	@FXML private TableColumn<FinancialRequest, Calendar> reasonRejected;
	
	@FXML private TableView<FinancialRequest> tableApproved;
	@FXML private TableColumn<FinancialRequest, String> departmentColumn;
	@FXML private TableColumn<FinancialRequest, String> amountColumn;
	@FXML private TableColumn<FinancialRequest, Calendar> reasonColumn;
	
	
	
	public FinancialManagerInterfaceController(Employee employee){
		this.employee=employee;
		this.EPRequests = EventPlanningRequest.generateEPRequestList();
		
		this.pendingFinancialRequests = new LinkedList<EventPlanningRequest>();
		this.approvedRequests = new LinkedList<EventPlanningRequest>();
		approvedFinancialRequests=new LinkedList<FinancialRequest> ();
		openFinancialRequests=new LinkedList<FinancialRequest> ();
		rejectedFinancialRequests=new LinkedList<FinancialRequest> ();
		
		for (int i=0; i<this.EPRequests.size(); i++){
			EventPlanningRequest request = this.EPRequests.get(i);
			switch(request.getStatus()){
				case PendingFinancialComments:
					this.pendingFinancialRequests.add(request);
					break;
				case Approved:
					this.approvedRequests.add(request);
					break;
				default:
					break;
			}
			FinancialRequest fRequest=EPRequests.get(i).getFinancialRequest();
			if(fRequest !=null){
				switch (fRequest.getStatus()){
				case "open":
					this.openFinancialRequests.add(fRequest); break;
				case "approved":
					this.approvedFinancialRequests.add(fRequest);break;
				case "rejected":
					this.rejectedFinancialRequests.add(fRequest);break;
			}
			}
			
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
		 approveButton.setVisible(false);
	     rejectButton.setVisible(false);
	     
	     // Pending Financial Comments Requests table
	     Controller.initializeEventPlanningRequestsTable(this, this.employee,
					this.tablePendingFinancialCommentsRequests,
					this.columnPendingFinancial_ClientName,
					this.columnPendingFinancial_EventType,
					this.columnPendingFinancial_From,
					this.columnPendingFinancial_To,
					this.pendingFinancialRequests);
	     
	     // Approved Requests table
	     Controller.initializeEventPlanningRequestsTable(this, this.employee,
					this.tableApprovedRequests,
					this.columnApproved_ClientName,
					this.columnApproved_EventType,
					this.columnApproved_From,
					this.columnApproved_To,
					this.approvedRequests);
			
		traceTable();
	}
	
	public void traceTable(){
		// Approved Financial Requests table
		departmentColumn.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("department"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("amount"));
		reasonColumn.setCellValueFactory(new PropertyValueFactory<FinancialRequest, Calendar>("reason"));
		tableApproved.setItems(FXCollections.observableList(this.approvedFinancialRequests));
							
		// Open Financial Requests table
		departmentOpen.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("department"));
		amountOpen.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("amount"));
		reasonOpen.setCellValueFactory(new PropertyValueFactory<FinancialRequest, Calendar>("reason"));
		tableOpen.setItems(FXCollections.observableList(this.openFinancialRequests));
							
		tableOpen.setRowFactory( tv -> {
			TableRow<FinancialRequest> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (! row.isEmpty()) {
					approveButton.setVisible(true);
					rejectButton.setVisible(true);
					approveButton.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
							approvedFinancialRequests.add(row.getItem());
							openFinancialRequests.remove(row.getItem());
							approveButton.setVisible(false);
							rejectButton.setVisible(false);
							traceTable();
							int id=row.getItem().getIdEPR();
							EventPlanningRequest eventPla=EventPlanningRequest.fromXmlIdToRequest(id);
							eventPla.getFinancialRequest().setStatus("approved");
							eventPla.updateXml();
						}
						        			
					});
					
					rejectButton.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							rejectedFinancialRequests.add(row.getItem());
							openFinancialRequests.remove(row.getItem());
							approveButton.setVisible(false);
							rejectButton.setVisible(false);
							traceTable();
							int id=row.getItem().getIdEPR();
							EventPlanningRequest eventPla=EventPlanningRequest.fromXmlIdToRequest(id);
							eventPla.getFinancialRequest().setStatus("rejected");
							eventPla.updateXml();
							
						}
					});
						        		
				    		 
				}
			});
			return row ;
		});
		
		// Rejected Financial Requests table
		departmentRejected.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("department"));
		amountRejected.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("amount"));
		reasonRejected.setCellValueFactory(new PropertyValueFactory<FinancialRequest, Calendar>("reason"));
		tableRejected.setItems(FXCollections.observableList(this.rejectedFinancialRequests));
							
	}
	
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		Controller.logout(this, buttonLogout);
	}

	    
	
}
