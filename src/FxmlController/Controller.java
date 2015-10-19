package FxmlController;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import EventPlanningRequest.EventPlanningRequest;
import Login.Employee;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Controller {
	public static void generateInterface(Object thisObject, Object controller, String resource,
			Stage currentStage, String title)
	{
		FXMLLoader loader = new FXMLLoader(thisObject.getClass().getResource(resource));
        loader.setController(controller);
        try {
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
	        currentStage.setScene(scene);
	        currentStage.setTitle(title); 
	        currentStage.setHeight(700);
	        currentStage.setWidth(600);
	        currentStage.centerOnScreen();
	        currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void logout(Object thisObject, Button buttonLogout)
	{
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(option == JOptionPane.OK_OPTION){
			FXMLLoader loader = new FXMLLoader(thisObject.getClass().getResource("../Main/LoginInterface.fxml"));
	        LoginController controller = new  LoginController();
	        loader.setController(controller); 
	        Parent root;
			try {
				root = (Parent) loader.load();
				 Stage primaryStage=(Stage) buttonLogout.getScene().getWindow();
				 Scene scene = new Scene(root);
				 primaryStage.setScene(scene);
				 primaryStage.setTitle("Login"); 
				 primaryStage.setHeight(250);
				 primaryStage.setWidth(400);
				 primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	    }
	}
	
	public static void generateViewEPRequest(Object thisObject,
			Employee employee, Stage currentStage, EventPlanningRequest request)
	{
		Controller.generateInterface(thisObject,
				new  ViewEventPlanningRequestController(employee, request),
				"../Fxml/ViewEventPlanningRequest.fxml", currentStage,
				"View Event Planning Request");
	}
	
	public static void initializeEventPlanningRequestsTable(
			Object thisObject, Employee employee,
			TableView<EventPlanningRequest> table,
			TableColumn<EventPlanningRequest,String> columnClientName,
			TableColumn<EventPlanningRequest,String> columnEventType,
			TableColumn<EventPlanningRequest,String> columnFrom,
			TableColumn<EventPlanningRequest,String> columnTo,
			LinkedList<EventPlanningRequest> eprList)
	{
		columnClientName.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("clientName"));
		columnEventType.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("eventType"));
		columnFrom.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("fromDate"));
		columnTo.setCellValueFactory(new PropertyValueFactory<EventPlanningRequest, String>("toDate"));
		table.setItems(FXCollections.observableList(eprList));
		
		table.setRowFactory( tv -> {
		    TableRow<EventPlanningRequest> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty()) {
		        	Stage currentStage= (Stage) table.getScene().getWindow();
		        	generateViewEPRequest(thisObject, employee, currentStage, row.getItem());
		        }
		    });
		    return row ;
		});
	}
	
}
