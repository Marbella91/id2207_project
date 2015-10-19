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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ViewClientRecordController  implements Initializable{
	private Employee employee;
	private ClientRecord record;
	private LinkedList<EventPlanningRequest> events;
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button buttonMenu;
	
	@FXML private Label labelRecordReference;
	@FXML private TextField textClientName;
	@FXML private TextArea textDescription;
	@FXML private TableView<EventPlanningRequest> tableEvents;
	@FXML private TableColumn<EventPlanningRequest, String> columnEventType;
	@FXML private TableColumn<EventPlanningRequest, String> columnFrom;
	@FXML private TableColumn<EventPlanningRequest, String> columnTo;
	@FXML private TableColumn<EventPlanningRequest, String> columnStatus;
	@FXML private ChoiceBox<EventPlanningRequest> boxAddEvent;
	@FXML private Button buttonUpdate;
	
	public ViewClientRecordController(Employee employee, ClientRecord record){
		this.employee=employee;
		this.record = record;
		this.events = new LinkedList<EventPlanningRequest>();
		for (int i = 0; i < record.getEventsIds().size(); i++){
			this.events.add(EventPlanningRequest.fromXmlIdToRequest(record.getEventsIds().get(i)));
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
		this.labelRecordReference.setText(this.record.getRecordRef());
		this.textClientName.setText(this.record.getClientName());
		this.textDescription.setText(this.record.getDescritpion());
		
		columnEventType.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("eventType"));
		columnFrom.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("fromDate"));
		columnTo.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("toDate"));
		columnStatus.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("status"));
		tableEvents.setItems(FXCollections.observableList(this.events));
		
		tableEvents.setRowFactory( tv -> {
		    TableRow<EventPlanningRequest> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty()) {
		        	Controller.generateInterface(this,
		        			new  ViewEventController(this.employee, row.getItem(), this.record),
		        			"../Fxml/ViewEvent.fxml",
		        			(Stage) this.tableEvents.getScene().getWindow(),
		        			"View Event");
		        }
		    });
		    return row ;
		});
		
		this.boxAddEvent.setItems(FXCollections.observableList(EventPlanningRequest.generateEPRequestsWithoutClientList()));
		boxAddEvent.getItems().add(null);
	}
	
	@FXML
	public void handleUpdate(ActionEvent event){
		this.record.setClientName(this.textClientName.getText());
		this.record.setDescritpion(this.textDescription.getText());
		EventPlanningRequest addedEvent = this.boxAddEvent.getValue();
		if (addedEvent != null){
			this.record.getEventsIds().add(addedEvent.getId());
			addedEvent.setClientRecordRef(this.record.getRecordRef());
			addedEvent.updateXml();
		}
		record.updateXml();
		JOptionPane.showMessageDialog(null, "The client record has been "
				+ "updated successfully!",
				"", JOptionPane.INFORMATION_MESSAGE);
		
		
		// reinitialize interface
		Controller.generateInterface(this,
				new  ViewClientRecordController(this.employee, this.record),
				"../Fxml/ViewClientRecord.fxml",
				(Stage) buttonUpdate.getScene().getWindow(),
				"View Client Record");
	}
	
	
	@FXML
	public void handleMenu(ActionEvent event){
		Stage currentStage = (Stage) this.buttonMenu.getScene().getWindow();
		this.employee.generateInterface(currentStage);
	}
	
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		Controller.logout(this, buttonLogout);
		
	}

	    
	
}
