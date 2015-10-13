package Login;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import FxmlController.AdministrationManagerInterfaceController;
import FxmlController.FinancialManagerInterfaceController;
import FxmlController.NewRequestController;
import FxmlController.ProductionManagerInterfaceController;
import FxmlController.SCSOInterfaceController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Employee {
	private String name;
	private String login;
	private String password;
	private Job job;
	
	public Employee(String name, String login, String password, Job job) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	public static String toXml(Employee data)
	{
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("Employee", Employee.class);
		return xstream.toXML(data);
	}
	
	public static Employee fromXml(String xml)
	{
		XStream xstream = new XStream(new StaxDriver());
		xstream.processAnnotations(Employee.class);
		xstream.alias("Employee", Employee.class);
		return (Employee) xstream.fromXML(xml);
	}
	
	public static void fromXmlToFile(String xml, String fileName)
	{
		File file = new File(fileName);
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(xml);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generateDataFiles()
	{
		File dataDirectory = new File("data/Employees");
		try{
	        dataDirectory.mkdir();
		} catch(SecurityException se){
	        //handle it
			se.printStackTrace();
	    }
		
		String janet = toXml(new Employee("Janet", "janet", "janetpassword", Job.SeniorCustomerServiceOfficer));
		String sarah = toXml(new Employee("Sarah", "sarah", "sarahpassword", Job.CustomerServiceOfficer));
		String mike = toXml(new Employee("Mike", "mike", "mikepassword",Job.AdministrationManager));
		String alice = toXml(new Employee("Alice", "alice", "alicepassword",Job.FinancialManager));
		String jack = toXml(new Employee("Jack", "jack", "jackpassword",Job.ProductionManager));
		String antony = toXml(new Employee("Antony", "antony", "antonypassword",Job.MusicEmployee));
		String adam = toXml(new Employee("Adam", "adam", "adampassword",Job.MusicEmployee));
		String magy = toXml(new Employee("Magy", "magy", "magypassword",Job.DecorationEmployee));
		String angelina = toXml(new Employee("Angelina", "angelina", "angelinapassword",Job.DecorationEmployee));
		String dom = toXml(new Employee("Dom", "dom", "dompassword",Job.DecorationEmployee));
		String tom = toXml(new Employee("Tom", "tom", "tompassword",Job.DecorationEmployee));
		
		fromXmlToFile(janet, "data/Employees/janet.xml");
		fromXmlToFile(sarah, "data/Employees/sarah.xml");
		fromXmlToFile(mike, "data/Employees/mike.xml");
		fromXmlToFile(alice, "data/Employees/alice.xml");
		fromXmlToFile(jack, "data/Employees/jack.xml");
		fromXmlToFile(antony, "data/Employees/antony.xml");
		fromXmlToFile(adam, "data/Employees/adam.xml");
		fromXmlToFile(magy, "data/Employees/magy.xml");
		fromXmlToFile(angelina, "data/Employees/angelina.xml");
		fromXmlToFile(dom, "data/Employees/dom.xml");
		fromXmlToFile(tom, "data/Employees/tom.xml");
		
	}
	
	public static LinkedList<Employee> generateEmployeeList()
	{
		File dataDirectory = new File("data/Employees");
		File[] fileList = dataDirectory.listFiles(); 
		
		LinkedList<Employee>  employeeList = new LinkedList<Employee>();
		
		for (int i = 0; i < fileList.length; i++)
		{
			FileReader fr;
			try {
				fr = new FileReader(fileList[i]);
				BufferedReader br = new BufferedReader(fr);
				String employeeXml = br.readLine();
				employeeList.add(fromXml(employeeXml));
				br.close();
				fr.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return employeeList;
	}
	
	public void generateInterface(Stage currentStage){
		FXMLLoader loader;
		Parent root;
		Scene scene;
		try {
			switch (this.job) {
	        	case CustomerServiceOfficer: 
	        		loader = new FXMLLoader(getClass().getResource("../Fxml/NewRequest.fxml"));
	    	        NewRequestController CSOcontroller = new  NewRequestController(this);
	    	        loader.setController(CSOcontroller);
	    	        root = (Parent) loader.load();
					scene = new Scene(root);
	    	        currentStage.setScene(scene);
	    	        currentStage.setTitle("Initialize a new Request"); 
	    	        currentStage.setHeight(800);
	    	        currentStage.setWidth(600);
	    	        currentStage.show();
	        		break;
	        	
	        	case SeniorCustomerServiceOfficer: 
	        		loader = new FXMLLoader(getClass().getResource("../Fxml/SCSOInterface.fxml"));
	        		SCSOInterfaceController SCSOcontroller = new  SCSOInterfaceController(this);
	    	        loader.setController(SCSOcontroller);
	    	        root = (Parent) loader.load();
					scene = new Scene(root);
	    	        currentStage.setScene(scene);
	    	        currentStage.setTitle("Senior Customer Service Officer Interface"); 
	    	        currentStage.setMinHeight(700);
	    	        currentStage.setMinWidth(1100);
	    	        currentStage.show();
	        		break;
	        	
	        	case FinancialManager: 
	        		loader = new FXMLLoader(getClass().getResource("../Fxml/FinancialManagerInterface.fxml"));
	        		FinancialManagerInterfaceController financialManagercontroller =
	        				new  FinancialManagerInterfaceController(this);
	    	        loader.setController(financialManagercontroller);
	    	        root = (Parent) loader.load();
					scene = new Scene(root);
	    	        currentStage.setScene(scene);
	    	        currentStage.setTitle("Financial Manager Interface"); 
	    	        currentStage.setMinHeight(700);
	    	        currentStage.setMinWidth(1100);
	    	        currentStage.show();
	        		break;
	        	
	        	case AdministrationManager: 
	        		loader = new FXMLLoader(getClass().getResource("../Fxml/AdministrationManagerInterface.fxml"));
	        		AdministrationManagerInterfaceController administrationManagercontroller =
	        				new  AdministrationManagerInterfaceController(this);
	    	        loader.setController(administrationManagercontroller);
	    	        root = (Parent) loader.load();
					scene = new Scene(root);
	    	        currentStage.setScene(scene);
	    	        currentStage.setTitle("Administration Manager Interface"); 
	    	        currentStage.setMinHeight(700);
	    	        currentStage.setMinWidth(1100);
	    	        currentStage.show();
	        		break;
	        	
	        	case ProductionManager: 
	        		loader = new FXMLLoader(getClass().getResource("../Fxml/ProductionManagerInterface.fxml"));
	        		ProductionManagerInterfaceController productionManagercontroller =
	        				new  ProductionManagerInterfaceController(this);
	    	        loader.setController(productionManagercontroller);
	    	        root = (Parent) loader.load();
					scene = new Scene(root);
	    	        currentStage.setScene(scene);
	    	        currentStage.setTitle("Production Manager Interface"); 
	    	        currentStage.setMinHeight(700);
	    	        currentStage.setMinWidth(1100);
	    	        currentStage.show();
	        		break;
	        	
	        	default:
	        		System.out.println("Cas pas encore traité");
	        		currentStage.close();
	        		break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
