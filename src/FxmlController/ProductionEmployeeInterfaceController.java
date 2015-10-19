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

public class ProductionEmployeeInterfaceController  implements Initializable{
	private Employee employee;
	private LinkedList<EventPlanningRequest> EPRequests;
	private LinkedList<Task>  taskList;
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	
	@FXML private TableView<Task> table;
	@FXML private TableColumn<Task, String> sender;
	@FXML private TableColumn<Task, String> priority;
	@FXML private TableColumn<Task, String> description;
	@FXML private TableColumn<Task,String> event;
	
	public ProductionEmployeeInterfaceController(Employee employee){
		this.employee=employee;
		this.EPRequests = EventPlanningRequest.generateEPRequestList();
		this.taskList=new LinkedList<Task>();
		// add the employee's tasks to taskList
		for(int i=0;i<EPRequests.size();i++){
			if(EPRequests.get(i).getProductionApplication() !=null){
				if(EPRequests.get(i).getProductionApplication().getTasks().size()!=0){
					if(EPRequests.get(i).getProductionApplication().getTasks().get("music")!=null){
						if (EPRequests.get(i).getProductionApplication().getTasks().get("music").getAssignedTo().getLogin().equals(this.employee.getLogin())){
							this.taskList.add(EPRequests.get(i).getProductionApplication().getTasks().get("music"));
						}
					}
					if(EPRequests.get(i).getProductionApplication().getTasks().get("decoration")!=null){
						if (EPRequests.get(i).getProductionApplication().getTasks().get("decoration").getAssignedTo().getLogin().equals(this.employee.getLogin())){
							this.taskList.add(EPRequests.get(i).getProductionApplication().getTasks().get("decoration"));
						}
					}	
				}
			}
			
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
		
		
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
