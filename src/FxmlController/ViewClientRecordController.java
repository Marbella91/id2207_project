package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.Application;
import EventPlanningRequest.ClientRecord;
import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.EventPlanningRequestStatus;
import EventPlanningRequest.HiringRequest;
import Login.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
		this.labelRecordReference.setText(this.record.getRecordRef());
		this.textClientName.setText(this.record.getClientName());
		this.textDescription.setText(this.record.getDescritpion());
		
		columnEventType.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("eventType"));
		columnFrom.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("fromDate"));
		columnTo.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("toDate"));
		columnStatus.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("status"));
		tableEvents.setItems(FXCollections.observableList(this.events));
		
		//TODO generate list EPR without clients
	}
	
	//TODO handle button
	/*
	public void reinitializeInterface(Stage currentStage)
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewEventPlanningRequest.fxml"));
        ViewClientRecordController controller = new  ViewClientRecordController(this.employee, this.request);
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
	*/
	@FXML
	public void handleMenu(ActionEvent event){
		Stage currentStage = (Stage) this.buttonMenu.getScene().getWindow();
		this.employee.generateInterface(currentStage);
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
