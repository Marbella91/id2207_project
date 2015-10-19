package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.Task;
import Login.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductionManagerInterfaceController  implements Initializable{
	private Employee employee;
	private LinkedList<EventPlanningRequest> EPRequests;
	private LinkedList<Task>  taskList;
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	
	@FXML private TableView<EventPlanningRequest> tableApprovedRequests;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_EventType;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_From;
	@FXML private TableColumn<EventPlanningRequest, String> columnApproved_To;
	
	@FXML private TableView<Task> table;
	@FXML private TableColumn<Task, String> employeeColumn;
	@FXML private TableColumn<Task, String> sender;
	@FXML private TableColumn<Task, String> priority;
	@FXML private TableColumn<Task, String> description;
	@FXML private TableColumn<Task, String> event;
	
	public ProductionManagerInterfaceController(Employee employee){
		this.employee=employee;
		this.EPRequests = EventPlanningRequest.generateAuthorizedEPRequestsList(employee);
		this.taskList=new LinkedList<Task>();
		for(int i=0;i<EPRequests.size();i++){
			if(EPRequests.get(i).getProductionApplication() !=null){
				if(EPRequests.get(i).getProductionApplication().getTasks().size()!=0){
					if(EPRequests.get(i).getProductionApplication().getTasks().get("music")!=null){
						this.taskList.add(EPRequests.get(i).getProductionApplication().getTasks().get("music"));
					}
					if(EPRequests.get(i).getProductionApplication().getTasks().get("decoration")!=null){
						this.taskList.add(EPRequests.get(i).getProductionApplication().getTasks().get("decoration"));
					}	
				}
			}
			
		}
		
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
				
		// Approved Requests table
		Controller.initializeEventPlanningRequestsTable(this, this.employee,
				this.tableApprovedRequests,
				this.columnApproved_ClientName,
				this.columnApproved_EventType,
				this.columnApproved_From,
				this.columnApproved_To,
				this.EPRequests);
				
		// Task table
		employeeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("employeeName"));
		sender.setCellValueFactory(new PropertyValueFactory<Task, String>("senderName"));
		priority.setCellValueFactory(new PropertyValueFactory<Task, String>("priority"));
		description.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
		event.setCellValueFactory(new PropertyValueFactory<Task, String>("EPR"));
		table.setItems(FXCollections.observableList(this.taskList));
		
	}
	
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		Controller.logout(this, buttonLogout);
	}

	    
	
}
