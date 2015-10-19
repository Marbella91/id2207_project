package EventPlanningRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;


public class ClientRecord {
	private String recordRef;
	private String clientName;
	private String Descritpion;
	
	LinkedList<Integer> eventsIds;

	public ClientRecord(String recordRef) {
		this.recordRef = recordRef;
		this.eventsIds = new LinkedList<Integer>();
	}

	public String getRecordRef() {
		return recordRef;
	}

	public void setRecordRef(String recordRef) {
		this.recordRef = recordRef;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDescritpion() {
		return Descritpion;
	}

	public void setDescritpion(String descritpion) {
		Descritpion = descritpion;
	}

	public LinkedList<Integer> getEventsIds() {
		return eventsIds;
	}

	public void setEventsIds(LinkedList<Integer> eventsIds) {
		this.eventsIds = eventsIds;
	}
	
	public void fromRecordToXml(){
		File dataDirectory = new File("data/Clients");
		int i=dataDirectory.listFiles().length+1;
		this.recordRef = String.valueOf(i) + this.recordRef;
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("ClientRecord", ClientRecord.class);
		String xml= xstream.toXML(this);
		String fileName="data/Clients/"+this.recordRef+".xml";
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
	
	public void updateXml(){
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("ClientRecord", ClientRecord.class);
		String xml= xstream.toXML(this);
		String fileName="data/Clients/"+this.recordRef+".xml";
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
	
	public static ClientRecord fromXmlToRecord(String xml){
		XStream xstream = new XStream(new StaxDriver());
		xstream.processAnnotations(ClientRecord.class);
		xstream.alias("ClientRecord", ClientRecord.class);
		return (ClientRecord) xstream.fromXML(xml);
	}
	
	public static LinkedList<ClientRecord> generateClientRecordList()
	{
		File dataDirectory = new File("data/Clients");
		File[] fileList = dataDirectory.listFiles(); 
		
		LinkedList<ClientRecord>  clientRecordsList = new LinkedList<ClientRecord>();
		
		for (int i = 0; i < fileList.length; i++)
		{
			FileReader fr;
			try {
				fr = new FileReader(fileList[i]);
				BufferedReader br = new BufferedReader(fr);
				String requestXml = br.readLine();
				ClientRecord record = fromXmlToRecord(requestXml);
				clientRecordsList.add(record);
				br.close();
				fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return clientRecordsList;
	}
	
	@Override
	public String toString() {
		return this.recordRef;
	}
	
}
