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
import EventPlanningRequest.FinancialRequest;
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
	private LinkedList<FinancialRequest> approvedFinancialRequests;
	private LinkedList<FinancialRequest> openFinancialRequests;
	private LinkedList<FinancialRequest> rejectedFinancialRequests;
	
	@FXML private Label labelLogin;
	@FXML private Button buttonLogout;
	@FXML private Button approveButton;
	@FXML private Button rejectButton;
	
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
	
	@FXML private TableView<FinancialRequest> tableOpen;
	@FXML private TableColumn<FinancialRequest, String> departmentOpen;
	@FXML private TableColumn<FinancialRequest, String> amountOpen;
	@FXML private TableColumn<FinancialRequest, Calendar> reasonOpen;
	
	@FXML private TableView<FinancialRequest> tableRejected;
	@FXML private TableColumn<FinancialRequest, String> departmentRejected;
	@FXML private TableColumn<FinancialRequest, String> amountRejected;
	@FXML private TableColumn<FinancialRequest, Calendar> reasonRejected;
	
	@FXML private TableView<FinancialRequest> tableApproved;
	@FXML private TableColumn<FinancialRequest, String> departmentColumn;
	@FXML private TableColumn<FinancialRequest, String> amountColumn;
	@FXML private TableColumn<FinancialRequest, Calendar> reasonColumn;
	
	
	
	public FinancialManagerInterfaceController(Employee employee){
		this.employee=employee;
		this.EPRequests = EventPlanningRequest.generateAuthorizedEPRequestsList(employee);
		
		this.pendingFinancialRequests = new LinkedList<EventPlanningRequest>();
		this.approvedRequests = new LinkedList<EventPlanningRequest>();
		approvedFinancialRequests=new LinkedList<FinancialRequest> ();
		openFinancialRequests=new LinkedList<FinancialRequest> ();
		rejectedFinancialRequests=new LinkedList<FinancialRequest> ();
		
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
			FinancialRequest fRequest=EPRequests.get(i).getFinancialRequest();
			if(fRequest !=null){
				switch (fRequest.getStatus()){
				case "open":
					this.openFinancialRequests.add(fRequest); break;
				case "approved":
					this.approvedFinancialRequests.add(fRequest);break;
				case "rejected":
					this.rejectedFinancialRequests.add(fRequest);break;
			}
			}
			
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.labelLogin.setText(this.employee.getLogin());
		 approveButton.setVisible(false);
	     rejectButton.setVisible(false);
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
	     traceTable();
	}
	
	public void traceTable(){
			
				// Approved Financial Requests table
				departmentColumn.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("department"));
				amountColumn.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("amount"));
				reasonColumn.setCellValueFactory(new PropertyValueFactory<FinancialRequest, Calendar>("reason"));
				tableApproved.setItems(FXCollections.observableList(this.approvedFinancialRequests));
							
			
				
				
				// Open Financial Requests table
				departmentOpen.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("department"));
				amountOpen.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("amount"));
				reasonOpen.setCellValueFactory(new PropertyValueFactory<FinancialRequest, Calendar>("reason"));
				tableOpen.setItems(FXCollections.observableList(this.openFinancialRequests));
							
				tableOpen.setRowFactory( tv -> {
				    TableRow<FinancialRequest> row = new TableRow<>();
				    row.setOnMouseClicked(event -> {
				    	 if (! row.isEmpty()) {
				    		 approveButton.setVisible(true);
						     rejectButton.setVisible(true);
						        		approveButton.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent arg0) {
												approvedFinancialRequests.add(row.getItem());
												openFinancialRequests.remove(row.getItem());
												approveButton.setVisible(false);
										        rejectButton.setVisible(false);
												traceTable();
												int id=row.getItem().getIdEPR();
												EventPlanningRequest eventPla=EventPlanningRequest.fromXmlIdToRequest(id);
												eventPla.getFinancialRequest().setStatus("approved");
												eventPla.updateXml();
												
											}
						        			
						        		});
						        		rejectButton.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent arg0) {
												rejectedFinancialRequests.add(row.getItem());
												openFinancialRequests.remove(row.getItem());
												approveButton.setVisible(false);
										        rejectButton.setVisible(false);
												traceTable();
												int id=row.getItem().getIdEPR();
												EventPlanningRequest eventPla=EventPlanningRequest.fromXmlIdToRequest(id);
												eventPla.getFinancialRequest().setStatus("rejected");
												eventPla.updateXml();
											
												
											}
						        			
						        		});
						        		
				    		 
				    	 }
				    });
				    return row ;
				});
				// Rejected Financial Requests table
				departmentRejected.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("department"));
				amountRejected.setCellValueFactory(new PropertyValueFactory<FinancialRequest, String>("amount"));
				reasonRejected.setCellValueFactory(new PropertyValueFactory<FinancialRequest, Calendar>("reason"));
				tableRejected.setItems(FXCollections.observableList(this.rejectedFinancialRequests));
							
				
		
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
