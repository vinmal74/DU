package ETL;

import Hub.*;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Units {

	//Data files
	private static final String unitTypes = "C:\\Users\\vincenzo.maltese\\eclipse-workspace\\EntityHub Open\\src\\data\\types_of_units.csv";
	private static final String units = "C:\\Users\\vincenzo.maltese\\eclipse-workspace\\EntityHub Open\\src\\data\\units.csv";
	private static final String splitBy = ";";
	
	//Expected capacities (recommended initial size should be the at least the double of expected entities divided by 0.75)
	private static final int orgTypesCapacity = 100;		//Types of organizations
	
	public static void process() {
		
		//Readers for data
		BufferedReader bufferTypes = null;
		BufferedReader bufferUnits = null;
		try {
			bufferTypes = new BufferedReader(new FileReader(unitTypes));
			bufferUnits = new BufferedReader(new FileReader(units));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = "";
		
    	try {
			//Extract Unit Types: prepares HUB IDs of the organization types	
			HashMap<String, String> orgTypes = new HashMap<String, String>(orgTypesCapacity);		
			while ((line = bufferTypes.readLine()) != null) {
			    String[] unitType = line.split(splitBy, -1);
			    String typeID 		= unitType[0];
			    String conceptID 	= unitType[2];
			    orgTypes.put(typeID, conceptID);
			}
			
			//Extract Units
			//---------------------------------------------------------------------------
			while ((line = bufferUnits.readLine()) != null) {
	
				//Attributes of a Unit
			    String[] unit = line.split(splitBy, -1);
			    String unitID 		= unit[0];
			    String unitType 	= unit[1];
			    String unitName 	= unit[2];
			    String unitParentID	= unit[3];
			    String unitEmail	= unit[4];
			    String unitPhone	= unit[5];
			    
				//Prepare the organization entity with the specified ID
				Entity e = new Entity(EntityStore.organizationType, unitID);
				
				//Class
				Integer conceptID = Integer.parseInt(orgTypes.get(unitType));
				e.addAttribute(new ConceptAttribute("Class", new ConceptValue(conceptID)));
								
				//Name
				e.addAttribute(new StringAttribute("Name", unitName));
				
				//Email
				if ((unitEmail != null) && (unitEmail.length() > 0)) 
					e.addAttribute(new StringAttribute("Email", unitEmail));	
						
				//Phone
				if ((unitPhone != null) && (unitPhone.length() > 0))
					e.addAttribute(new StringAttribute("Phone number", unitPhone));
				
				//Parent unit entity
				if (unitParentID.length() != 0) {
					Entity parent = new Entity(EntityStore.organizationType, unitParentID);
					EntityStore.EB[EntityStore.organizationType].load(parent);
					e.addAttribute(new RelationalAttribute("Part of", new EntityValue(EntityStore.organizationType,unitParentID)));
				}			
				
				//Load the entity into the entitybase
				EntityStore.EB[EntityStore.organizationType].load(e);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}