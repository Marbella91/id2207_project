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

public class FinancialManagerInterfaceController  implements Initializable{
	private Employee employee;
	private LinkedList<EventPlanningRequest> EPRequests;
	private LinkedList<EventPlanningRequest> pendingFinancialRequests;
	private LinkedList<EventPlanningRequest> approvedRequests;
	
	@FXML private Label labelLogin;
	
	@FXML private TableView<EventPlanningRequest> tablePendingFinancialCommentsRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingFinancial_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnPendingFinancial_EventType;
	@FXML private TableColumn<EventPlanningRequest, Calendar> columnPendingFinancial_From;
	@FXML private TableColumn<EventPlanningRequest, Calendar> columnPendingFinancial_To;
	
	@FXML private TableView<EventPlanningRequest> tableApprovedRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_EventType;
	@FXML private TableColumn<EventPlanningRequest, Calendar> columnApproved_From;
	@FXML private TableColumn<EventPlanningRequest, Calendar> columnApproved_To;
	
	public FinancialManagerInterfaceController(Employee employee){
		this.employee=employee;
		this.EPRequests = EventPlanningRequest.generateAuthorizedEPRequestsList(employee);
		
		this.pendingFinancialRequests = new LinkedList<EventPlanningRequest>();
		this.approvedRequests = new LinkedList<EventPlanningRequest>();
		
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
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
		
		
		// Pending Financial Comments Requests table
		columnPendingFinancial_ClientName.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("clientName"));
		columnPendingFinancial_EventType.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("eventType"));
		columnPendingFinancial_From.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, Calendar>("fromDate"));
		columnPendingFinancial_To.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, Calendar>("toDate"));
		tablePendingFinancialCommentsRequests.setItems(FXCollections.observableList(this.pendingFinancialRequests));
				
		tablePendingFinancialCommentsRequests.setRowFactory( tv -> {
		    TableRow<EventPlanningRequest> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty()) {
		        	Stage currentStage= (Stage) this.tablePendingFinancialCommentsRequests.getScene().getWindow();
		        	generateViewRequest(currentStage, row.getItem());
		        }
		    });
		    return row ;
		});
		
		// Approved Requests table
		columnApproved_ClientName.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("clientName"));
		columnApproved_EventType.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("eventType"));
		columnApproved_From.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, Calendar>("fromDate"));
		columnApproved_To.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, Calendar>("toDate"));
		tableApprovedRequests.setItems(FXCollections.observableList(this.approvedRequests));
					
		tableApprovedRequests.setRowFactory( tv -> {
		    TableRow<EventPlanningRequest> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty()) {
		        	Stage currentStage= (Stage) this.tableApprovedRequests.getScene().getWindow();
		        	generateViewRequest(currentStage, row.getItem());
		        }
		    });
		    return row ;
		});
		
	}
	
	
	public void generateViewRequest(Stage currentStage, EventPlanningRequest request)
	{
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
	}

	    
	
}
