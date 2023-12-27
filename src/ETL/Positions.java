package ETL;

import Hub.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Positions {
		
	//Data files
	private static final String positionTypes = "C:\\Users\\vincenzo.maltese\\eclipse-workspace\\EntityHub Open\\src\\data\\types_of_positions.csv";
	private static final String positions = "C:\\Users\\vincenzo.maltese\\eclipse-workspace\\EntityHub Open\\src\\data\\positions.csv";
	private static final String splitBy = ";";
	
	//Expected capacities (recommended initial size should be the at least the double of expected entities divided by 0.75)
	private static final int positionTypesCapacity = 50;		//Types of organizations
	
	public static void process() {
		
		//Readers for data
		BufferedReader bufferTypes = null;
		BufferedReader bufferPosit = null;
		try {
			bufferTypes = new BufferedReader(new FileReader(positionTypes));
			bufferPosit = new BufferedReader(new FileReader(positions));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = "";

    	try {
			//Extract Position Types: prepares HUB IDs of the position types	
			HashMap<String, String> positionTypes = new HashMap<String, String>(positionTypesCapacity);		
			while ((line = bufferTypes.readLine()) != null) {
			    String[] posType = line.split(splitBy, -1);
			    String typeID 		= posType[0];
			    String conceptID 	= posType[2];
			    positionTypes.put(typeID, conceptID);
			}
			
			//Extract Positions
			//---------------------------------------------------------------------------
			while ((line = bufferPosit.readLine()) != null) {
			    String[] unit = line.split(splitBy, -1);
			    String personID 	= unit[0];
			    String positionType = unit[1];
			    String unitID 		= unit[2];
			    				
				//Prepare the person entity with the specified ID
				Entity e = new Entity(EntityStore.personType, personID);
				
				//Prepare the organization entity with the specified ID adds it to the entitybase
				EntityStore.EB[EntityStore.organizationType].load(new Entity(EntityStore.organizationType,unitID));
				
				//Computes the ID of the Role
				String roleID = unitID + "_" + positionType;
				
				//Prepares the role entity
				Entity r = new Entity(EntityStore.roleType, roleID);
				
				//Adds the role attributes
				Integer conceptID = Integer.parseInt(positionTypes.get(positionType));
				r.addAttribute(new ConceptAttribute("Class", new ConceptValue(conceptID)));
				r.addAttribute(new RelationalAttribute("Organization", new EntityValue(EntityStore.organizationType,unitID)));
					
				//Load the role entity into the entitybase
				EntityStore.EB[EntityStore.roleType].load(r);
					
				//Adds the relational attribute to the person
				e.addAttribute(new RelationalAttribute("Occupies Role", new EntityValue(EntityStore.roleType,roleID)));
				
				//Load the person entity into the entitybase
				EntityStore.EB[EntityStore.personType].load(e);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}