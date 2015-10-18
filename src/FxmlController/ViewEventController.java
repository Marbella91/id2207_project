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

public class ViewEventController  implements Initializable{
	private Employee employee;
	private EventPlanningRequest request;
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button buttonMenu;
	
	@FXML private Label labelStatus;
	@FXML private Label labelClientRef;
	@FXML private Label labelClientName;
	@FXML private Label labelEventType;
	@FXML private Label labelFromDate;
	@FXML private Label labelToDate;
	@FXML private TextArea textDescription;
	@FXML private Label labelAttendees;
	@FXML private TextField textFinalAttendees;
	@FXML private Label labelBudget;
	@FXML private TextField textFinalBudget;
	
	@FXML private Label labelDecorations;
	@FXML private Label labelMusic;
	@FXML private Button buttonUpdate;
	
	public ViewEventController(Employee employee, EventPlanningRequest request){
		this.employee=employee;
		this.request = request;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
		this.labelStatus.setText(this.request.getStatus().toString());
		this.labelClientRef.setText(this.request.getClientRecordRef());
		this.labelClientName.setText(this.request.getClientName());
		this.labelEventType.setText(this.request.getEventType());
		this.labelFromDate.setText(this.request.getFromDate());
		this.labelToDate.setText(this.request.getToDate());
		if (this.request.getDescription() != null) {
			this.textDescription.setText(this.request.getDescription());
		}
		this.labelAttendees.setText(String.valueOf(this.request.getExpectedAttendeesNumber()));
		this.textFinalAttendees.setText(String.valueOf(this.request.getFinalAttendeesNumber()));
		this.labelBudget.setText(String.valueOf(this.request.getExpectedBudget()));
		this.textFinalBudget.setText(String.valueOf(this.request.getFinalBudget()));
		
		if (this.request.getProductionApplication() != null &&
				this.request.getProductionApplication().getTasks().containsKey("decoration")){
			this.labelDecorations.setText(
					this.request.getProductionApplication().getTasks().get("decoration").getDescription());
		}
		else {
			this.labelDecorations.setText("There is no decoration task for this event.");
		}
		
		if (this.request.getProductionApplication() != null &&
				this.request.getProductionApplication().getTasks().containsKey("music")){
			this.labelMusic.setText(
					this.request.getProductionApplication().getTasks().get("music").getDescription());
		}
		else {
			this.labelMusic.setText("There is no music task for this event.");
		}
	}
	
	@FXML
	public void handleUpdate(ActionEvent event){
		this.request.setDescription(this.textDescription.getText());
		
		String finalAttendees = this.textFinalAttendees.getText();
		if (finalAttendees.equals(""))
			this.request.setFinalAttendeesNumber(0);
		else try {  
			this.request.setFinalAttendeesNumber(Integer.parseInt(finalAttendees));
		} catch(NumberFormatException nfe) {  
			JOptionPane.showMessageDialog(null, "The final number of attendees should be a number!",
					"", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		
		String finalBudget = this.textFinalBudget.getText();
		if (finalBudget.equals(""))
			this.request.setFinalBudget(0);
		else try {  
			this.request.setFinalBudget(Integer.parseInt(finalBudget));
		} catch(NumberFormatException nfe) {  
			JOptionPane.showMessageDialog(null, "The final budget should be a number!",
					"", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		
		this.request.updateXml();
		
		JOptionPane.showMessageDialog(null, "The event has been "
				+ "updated successfully!",
				"", JOptionPane.INFORMATION_MESSAGE);
		
		
		// reinitialize interface
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/ViewEvent.fxml"));
        ViewEventController controller = new  ViewEventController(this.employee, this.request);
        loader.setController(controller); 
        Parent root;
		try {
			root = (Parent) loader.load();
			Scene scene = new Scene(root);
			Stage currentStage=(Stage) buttonUpdate.getScene().getWindow();
			currentStage.setScene(scene);
			currentStage.setTitle("View Event"); 
			currentStage.setHeight(800);
			currentStage.setWidth(600);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
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
