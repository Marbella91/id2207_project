package FxmlController;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import EventPlanningRequest.Application;
import EventPlanningRequest.EventPlanningRequest;
import EventPlanningRequest.Task;
import Login.Employee;
import Login.Job;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViewApplicationController implements Initializable{
	private Employee employee;
	private EventPlanningRequest epr;
	private Application application;
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button buttonMenu;
	@FXML private Button buttonBackEpr;
	@FXML private Label labelEvent;
	
	@FXML private Label labelMusicExistingTask;
	@FXML private HBox boxMusicDescription;
	@FXML private HBox boxMusicAssign;
	@FXML private HBox boxMusicPriority;
	@FXML private HBox boxMusicButton;
	
	@FXML private Label labelDecorationExistingTask;
	@FXML private HBox boxDecorationDescription;
	@FXML private HBox boxDecorationAssign;
	@FXML private HBox boxDecorationPriority;
	@FXML private HBox boxDecorationButton;
	
	public ViewApplicationController(Employee employee, EventPlanningRequest epr){
		this.employee=employee;
		this.epr = epr;
		this.application = epr.getProductionApplication();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.labelLogin.setText(this.employee.getLogin());
		this.labelEvent.setText(this.epr.toString());
		
		// if the music task already exists
		if (this.application.getTasks().containsKey("music"))
		{
			this.labelMusicExistingTask.setText("A task has already been assigned for the music.");
			Task task = this.application.getTasks().get("music");
			this.boxMusicDescription.getChildren().add(new Label(task.getDescription()));
			this.boxMusicAssign.getChildren().add(new Label(task.getAssignedTo().toString()));
			this.boxMusicPriority.getChildren().add(new Label(task.getPriority()));
		}
		
		// else if the application's status is open, possibility to create a task
		else if (this.application.getStatus().equals("open")){
			this.labelMusicExistingTask.setText("No task has been assigned for the music.");
			LinkedList<Employee> employeeList = Employee.generateEmployeeList();
			LinkedList<Employee> musicEmployees = new LinkedList<Employee>();
			for (int i = 0; i < employeeList.size(); i++){
				Employee e = employeeList.get(i);
				if (e.getJob().equals(Job.MusicEmployee))
					musicEmployees.add(e);
			}
			
			TextArea musicDescriptionArea = new TextArea();
			this.boxMusicDescription.getChildren().add(musicDescriptionArea);
			
			ChoiceBox<Employee> musicAssignBox = new ChoiceBox<Employee>();
			musicAssignBox.setItems(FXCollections.observableList(musicEmployees));
			this.boxMusicAssign.getChildren().add(musicAssignBox);
			
			ChoiceBox<String> musicPriorityBox = new ChoiceBox<String>();
			musicPriorityBox.getItems().addAll("High", "Medium");
			this.boxMusicPriority.getChildren().add(musicPriorityBox);
			
			
			
			Button  button = new Button("Send Task");
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					String description = musicDescriptionArea.getText();
					Employee assign = musicAssignBox.getValue();
					String priority = musicPriorityBox.getValue();
					if (description.equals("")){
						JOptionPane.showMessageDialog(null, "Please enter a description!",
								"", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (assign == null){
						JOptionPane.showMessageDialog(null, "Please choose an employee to assign the task!",
								"", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (priority == null){
						JOptionPane.showMessageDialog(null, "Please select a priority!",
								"", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Task task = new Task(employee, description, assign, priority,application.getEPR());
					application.getTasks().put("music", task);
					epr.updateXml();
					JOptionPane.showMessageDialog(null, "The task has been "
							+ "created successfully!",
							"", JOptionPane.INFORMATION_MESSAGE);
					Stage stage=(Stage) button.getScene().getWindow();
					reinitializeInterface(stage);
				}
			});
			this.boxMusicButton.getChildren().add(button);
		}
		
		// else no more task can be created
		else {
			this.labelMusicExistingTask.setText("No task has been assigned for the music.");
		}
		
		// if the decoration task already exists
		if (this.application.getTasks().containsKey("decoration"))
		{
			this.labelDecorationExistingTask.setText("A task has already been assigned for the decorations.");
			Task task = this.application.getTasks().get("decoration");
			this.boxDecorationDescription.getChildren().add(new Label(task.getDescription()));
			this.boxDecorationAssign.getChildren().add(new Label(task.getAssignedTo().toString()));
			this.boxDecorationPriority.getChildren().add(new Label(task.getPriority()));
		}
		
		// else if the application's status is open, possibility to create a task
		else if (this.application.getStatus().equals("open")){
			this.labelDecorationExistingTask.setText("No task has been assigned for the decorations.");
			LinkedList<Employee> employeeList = Employee.generateEmployeeList();
			LinkedList<Employee> decorationEmployees = new LinkedList<Employee>();
			for (int i = 0; i < employeeList.size(); i++){
				Employee e = employeeList.get(i);
				if (e.getJob().equals(Job.DecorationEmployee))
					decorationEmployees.add(e);
			}
					
			TextArea decorationDescriptionArea = new TextArea();
			this.boxDecorationDescription.getChildren().add(decorationDescriptionArea);
					
			ChoiceBox<Employee> decorationAssignBox = new ChoiceBox<Employee>();
			decorationAssignBox.setItems(FXCollections.observableList(decorationEmployees));
			this.boxDecorationAssign.getChildren().add(decorationAssignBox);
					
			ChoiceBox<String> decorationPriorityBox = new ChoiceBox<String>();
			decorationPriorityBox.getItems().addAll("High", "Medium");
			this.boxDecorationPriority.getChildren().add(decorationPriorityBox);
					
					
					
			Button  button = new Button("Send Task");
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					String description = decorationDescriptionArea.getText();
					Employee assign = decorationAssignBox.getValue();
					String priority = decorationPriorityBox.getValue();
					if (description.equals("")){
						JOptionPane.showMessageDialog(null, "Please enter a description!",
								"", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (assign == null){
						JOptionPane.showMessageDialog(null, "Please choose an employee to assign the task!",
								"", JOptionPane.ERROR_MESSAGE);
						return;
					}	
					if (priority == null){
						JOptionPane.showMessageDialog(null, "Please select a priority!",
								"", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Task task = new Task(employee, description, assign, priority, application.getEPR());
					application.getTasks().put("decoration", task);
					epr.updateXml();
					JOptionPane.showMessageDialog(null, "The task has been "
							+ "created successfully!",
							"", JOptionPane.INFORMATION_MESSAGE);
					Stage stage=(Stage) button.getScene().getWindow();
					reinitializeInterface(stage);
				}
			});
			this.boxDecorationButton.getChildren().add(button);
		}
		
		// else no more task can be created
		else {
			this.labelDecorationExistingTask.setText("No task has been assigned for the decorations.");
		}
	}	
		
	@FXML
	public void handleLogOut(ActionEvent event) throws IOException{
		Controller.logout(this, buttonLogout);
	}
	
	@FXML
	public void handleMenu(ActionEvent event){
		Stage currentStage = (Stage) this.buttonMenu.getScene().getWindow();
		this.employee.generateInterface(currentStage);
	}
	
	@FXML
	public void handleBackEPR(ActionEvent event){
		Stage currentStage = (Stage) this.buttonBackEpr.getScene().getWindow();
		Controller.generateInterface(this,
				new ViewEventPlanningRequestController(this.employee, this.epr),
				"../Fxml/ViewEventPlanningRequest.fxml", currentStage,
				"View Event Planning Request");
	}
	
	public void reinitializeInterface(Stage currentStage)
	{
		Controller.generateInterface(this,
				new ViewApplicationController(this.employee, this.epr),
				"../Fxml/ViewApplication.fxml", currentStage,
				"View Application");
	}
}
