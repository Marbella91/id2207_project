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
		File dataDirectory = new File("data");
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
		
		fromXmlToFile(janet, "data/janet.xml");
		fromXmlToFile(janet, "data/sarah.xml");
		fromXmlToFile(mike, "data/mike.xml");
		fromXmlToFile(alice, "data/alice.xml");
		
	}
	
	public static LinkedList<Employee> generateEmployeeList()
	{
		File dataDirectory = new File("data");
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
	
	
}
