package Main;

import FxmlController.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchProgram extends Application {

	@Override
	public void init() throws Exception{
		super.init();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginInterface.fxml"));
	        LoginController controller = new  LoginController();
	        loader.setController(controller); 
	        Parent root = (Parent) loader.load();
	       
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Login"); 
	        primaryStage.setMinHeight(700);
	        primaryStage.setMinWidth(1100);
	        primaryStage.show();
	         
	      
	}
	
	
    public static void main(String[] args) {
       
        launch(args);
       
       
    }           
	       
	       
}
