package FxmlController;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Login.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	@FXML private TextField labelLogin;
	@FXML private TextField labelPass;
	@FXML private Button buttonLogin;
	private Employee employee; 
	
	@FXML
	private void handleButtonLogin(ActionEvent event){
		String printedLogin = labelLogin.getText();
		String printedPassword = labelPass.getText();
		
		if (printedLogin.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please enter a login",
    				"", JOptionPane.ERROR_MESSAGE);
		}
		else if (printedPassword.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please enter a password",
    				"", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			LinkedList<Employee> employeeList = Employee.generateEmployeeList();
		    boolean employeeFound = false;
		    int i = 0;
		    while (!employeeFound && i < employeeList.size()){
		    	Employee employee = employeeList.get(i);
		    	if (employee.getLogin().equals(printedLogin))
		    	{
		    		employeeFound = true;
		    		if (employee.getPassword().equals(printedPassword))
		    		{
		    			this.employee=employee;
		    			Stage currentStage = (Stage) buttonLogin.getScene().getWindow();
		    			//currentStage.close();
		    			
		    			employee.generateInterface(currentStage);
		    			/*
		    			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/NewRequest.fxml"));
		    	        NewRequestController controller = new  NewRequestController(employee);
		    	        loader.setController(controller); 
		    	        Parent root;
						try {
							root = (Parent) loader.load();
							Scene scene = new Scene(root);
			    	        currentStage.setScene(scene);
			    	        currentStage.setTitle("Initialize a new Request"); 
			    	        currentStage.setMinHeight(700);
			    	        currentStage.setMinWidth(1100);
			    	        currentStage.show();
			    	        
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						*/
		    	       
		    	        
		    		}
		    		else
		    		{
		    			JOptionPane.showMessageDialog(null, "Wrong password!",
			    				"", JOptionPane.ERROR_MESSAGE);
		    		}
		    	}
		    	i++;
		    }
		    if (!employeeFound)
		    {
		    	JOptionPane.showMessageDialog(null,
		    				"This login does not exist!",
		    				"", JOptionPane.ERROR_MESSAGE);
		    }
		}
	    
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}