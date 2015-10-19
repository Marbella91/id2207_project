package FxmlController;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
	public static void generateInterface(Object thisClass, Object controller, String resource,
			Stage currentStage, String title)
	{
		FXMLLoader loader = new FXMLLoader(thisClass.getClass().getResource(resource));
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
	
	
	public static void logout(Object thisClass, Button buttonLogout)
	{
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(option == JOptionPane.OK_OPTION){
			FXMLLoader loader = new FXMLLoader(thisClass.getClass().getResource("../Main/LoginInterface.fxml"));
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
}
