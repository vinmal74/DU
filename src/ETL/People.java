package ETL;

import Hub.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class People {
		
	//Data files
	private static final String people = "C:\\Users\\vincenzo.maltese\\eclipse-workspace\\EntityHub Open\\src\\data\\people.csv";
	private static final String splitBy = ";";
	
	public static void process() {
				
		//Readers for data
		BufferedReader bufferPeople = null;
		try {
			bufferPeople = new BufferedReader(new FileReader(people));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = "";

    	try {
			//Extract People
			while ((line = bufferPeople.readLine()) != null) {
				
				//Attributes of a Person
			    String[] person = line.split(splitBy, -1);
			    String ID 		= person[0];
			    String surname 	= person[1];
			    String name 	= person[2];
			    String gender	= person[3];
			    String email	= person[4];
			    String phone	= person[5];

				//Prepare the new entity with the specified ID
				Entity e = new Entity(EntityStore.personType, ID);
				
				//Class
				e.addAttribute(new ConceptAttribute("Class", new ConceptValue(EntityStore.voc_person)));
				
				//Surname
				e.addAttribute(new StringAttribute("Surname", surname));
				
				//Name
				e.addAttribute(new StringAttribute("Name", name));
				
				//Gender
				int sex = EntityStore.voc_male;
				if (gender.compareTo("F") == 0) sex = EntityStore.voc_female;
				e.addAttribute(new ConceptAttribute("Gender", new ConceptValue(sex)));
				
				//Email
				if ((email != null) && (email.length() > 0)) 
					e.addAttribute(new StringAttribute("Email", email));

				//Phone
				if ((phone != null) && (phone.length() > 0)) 
					e.addAttribute(new StringAttribute("Phone number", phone));
								
				//Load the entity into the entitybase
				EntityStore.EB[EntityStore.personType].load(e);
	        }	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}