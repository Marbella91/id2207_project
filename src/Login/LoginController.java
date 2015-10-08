package Login;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	@FXML private TextField labelLogin;
	@FXML private TextField labelPass;
	@FXML private Button buttonLogin;
	
	@FXML
	private void handleButtonLogin(ActionEvent event){
		String printedLogin = labelLogin.getText();
		String printedPassword = labelPass.getText();
		
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
	    			Stage currentStage = (Stage) buttonLogin.getScene().getWindow();
	    			currentStage.close();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}