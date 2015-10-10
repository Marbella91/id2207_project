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

public class SCSOInterfaceController  implements Initializable{
	private Employee employee;
	private LinkedList<EventPlanningRequest> EPRequestsList;
	@FXML private Label labelLogin;
	@FXML private TableView<EventPlanningRequest> labelPendingSCSOCommentsRequests;
	@FXML private TableColumn<EventPlanningRequest, String> labelPendingSCSO_ClientName;
	@FXML private TableColumn<EventPlanningRequest, String> labelPendingSCSO_EventType;
	@FXML private TableColumn<EventPlanningRequest, Calendar> labelPendingSCSO_From;
	@FXML private TableColumn<EventPlanningRequest, Calendar> labelPendingSCSO_To;
	
	public SCSOInterfaceController(Employee employee){
		this.employee=employee;
		this.EPRequestsList = EventPlanningRequest.generateAuthorizedEPRequestsList(employee);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
		
		labelPendingSCSO_ClientName.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("clientName"));
		labelPendingSCSO_EventType.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("eventType"));
		labelPendingSCSO_From.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, Calendar>("fromDate"));
		labelPendingSCSO_To.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, Calendar>("toDate"));
		labelPendingSCSOCommentsRequests.setItems(FXCollections.observableList(this.EPRequestsList));
		
		labelPendingSCSOCommentsRequests.setRowFactory( tv -> {
		    TableRow<EventPlanningRequest> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty()) {
		        	EventPlanningRequest rowData = row.getItem();
		        	
		        	Stage currentStage= (Stage) this.labelPendingSCSOCommentsRequests.getScene().getWindow();
		    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewEventPlanningRequest.fxml"));
		            ViewEventPlanningRequestController controller = new  ViewEventPlanningRequestController(this.employee, rowData);
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
		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
		    			} 
		        }
		    });
		    return row ;
		});
		
		
	}
	
	 

	    
	
}
