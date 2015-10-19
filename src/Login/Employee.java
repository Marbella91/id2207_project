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
import FxmlController.Controller;
import FxmlController.FinancialManagerInterfaceController;
import FxmlController.HRInterfaceController;
import FxmlController.NewRequestController;
import FxmlController.ProductionEmployeeInterfaceController;
import FxmlController.ProductionManagerInterfaceController;
import FxmlController.SCSOInterfaceController;
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
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(xml);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generateEmployeesDataFiles()
	{
		File dataDirectory = new File("data/Employees");
		try{
	        dataDirectory.mkdir();
		} catch(SecurityException se){
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
		String simon = toXml(new Employee("Simon", "simon", "simonpassword",Job.HR));
		
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
		fromXmlToFile(simon,"data/Employees/simon.xml");
		
		System.out.println("Employees data files generated\n");
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
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return employeeList;
	}
	
	public void generateInterface(Stage currentStage){
		switch (this.job) {
			case CustomerServiceOfficer: 
				Controller.generateInterface(this, new  NewRequestController(this),
						"../Fxml/NewRequest.fxml", currentStage,
						"Initialize a new Request");
	        	break;
	        case SeniorCustomerServiceOfficer:
	        	Controller.generateInterface(this, new SCSOInterfaceController(this),
	        			"../Fxml/SCSOInterface.fxml", currentStage,
	        			"Senior Customer Service Officer Interface");
	        	break;
	        case FinancialManager:
	        	Controller.generateInterface(this, new FinancialManagerInterfaceController(this),
	        			"../Fxml/FinancialManagerInterface.fxml", currentStage,
	        			"Financial Manager Interface");
	        	break;
	        case AdministrationManager:
	        	Controller.generateInterface(this, new AdministrationManagerInterfaceController(this),
	        			"../Fxml/AdministrationManagerInterface.fxml", currentStage,
	        			"Administration Manager Interface");
	        	break;
	        case ProductionManager:
	        	Controller.generateInterface(this, new ProductionManagerInterfaceController(this),
	        			"../Fxml/ProductionManagerInterface.fxml", currentStage,
	        			"Production Manager Interface");
	        	break;
	        case HR:
	        	Controller.generateInterface(this, new HRInterfaceController(this),
	        			"../Fxml/HRInterface.fxml", currentStage,
	        			"Human Ressources Interface");
	        	break;
	        case MusicEmployee:
	        case DecorationEmployee:
	        	Controller.generateInterface(this, new ProductionEmployeeInterfaceController(this),
	        			"../Fxml/ProductionEmployeeInterface.fxml", currentStage,
	        			"Production Employee Interface");
	        	break;
		}
	}
	
	
}
