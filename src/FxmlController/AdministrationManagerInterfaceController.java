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
	@FXML private TableColumn<EventPlanningRequest, Calendar> columnPendingAdministration_From;
	@FXML private TableColumn<EventPlanningRequest, Calendar> columnPendingAdministration_To;
	
	@FXML private TableView<EventPlanningRequest> tableApprovedRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_EventType;
	@FXML private TableColumn<EventPlanningRequest, Calendar> columnApproved_From;
	@FXML private TableColumn<EventPlanningRequest, Calendar> columnApproved_To;
	
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
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
		
		
		// Pending Administration Comments Requests table
		columnPendingAdministration_ClientName.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("clientName"));
		columnPendingAdministration_EventType.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("eventType"));
		columnPendingAdministration_From.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, Calendar>("fromDate"));
		columnPendingAdministration_To.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, Calendar>("toDate"));
		tablePendingAdministrationCommentsRequests.setItems(FXCollections.observableList(this.pendingAdministrationRequests));
					
		tablePendingAdministrationCommentsRequests.setRowFactory( tv -> {
		    TableRow<EventPlanningRequest> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty()) {
		        	Stage currentStage= (Stage) this.tablePendingAdministrationCommentsRequests.getScene().getWindow();
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
