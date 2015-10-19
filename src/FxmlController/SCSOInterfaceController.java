package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.ClientRecord;
import EventPlanningRequest.EventPlanningRequest;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
		        	Stage currentStage= (Stage) this.tableClientRecords.getScene().getWindow();
		        	generateViewRecord(currentStage, row.getItem());
		        }
		    });
		    return row ;
		});
	}
	
	
	public void generateViewRequest(Stage currentStage, EventPlanningRequest request)
	{
		Controller.generateInterface(this,
				new  ViewEventPlanningRequestController(this.employee, request),
				"../Fxml/ViewEventPlanningRequest.fxml", currentStage,
				"View Event Planning Request");
		/*
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewEventPlanningRequest.fxml"));
        ViewEventPlanningRequestController controller = new  ViewEventPlanningRequestController(this.employee, request);
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
		*/
	}
	
	public void generateViewRecord(Stage currentStage, ClientRecord record)
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewClientRecord.fxml"));
        ViewClientRecordController controller = new  ViewClientRecordController(this.employee, record);
        loader.setController(controller); 
        Parent root;
		
		try {
			root = (Parent) loader.load();
			Scene scene = new Scene(root);
		       currentStage.setScene(scene);
		       currentStage.setTitle("View Client Record"); 
		       currentStage.setHeight(800);
		       currentStage.setWidth(600);
		       currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleCreateClient(ActionEvent event) throws IOException{
		String clientRecordRef = JOptionPane.showInputDialog("Enter the new client record reference");
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
    		Stage currentStage= (Stage) this.buttonCreateClient.getScene().getWindow();
    		generateViewRecord(currentStage, record);
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

	    
	
}
