package ETL;

import Hub.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EntityStore {

	//Vocabulary entries
	static final int voc_person 		= 118;		//Class Person
	static final int voc_role 			= 118247;	//Class Role
	static final int voc_org 			= 43544;	//Class Organization
	static final int voc_course 		= 4553;		//Class Course
	
	static final int voc_male 			= 90013;	//Gender
	static final int voc_female 		= 90019;	//Gender
	
	//Expected capacities (recommended initial size should be the at least the double of expected entities divided by 0.75)
	static final int personCapacity 		= 100;	//People 
	static final int organizationCapacity 	= 100;	//Organizations 
	static final int roleCapacity 			= 300;	//Roles
	static final int courseCapacity 		= 100;	//Courses
	
	//Entitybases
	private static final int size = 4;			//The number of entitybases
	public static final int personType 			= 0;
	public static final int organizationType 	= 1;
	public static final int roleType 			= 2;
	public static final int courseType 			= 3;

	public static Entitybase[] EB = new Entitybase[size];
	
	public EntityStore() {
		//Entitybase for People
		EB[personType] = new Entitybase(personType, personCapacity);
		
		//Entitybase for Organizations
		EB[organizationType] = new Entitybase(organizationType, organizationCapacity);
			
		//Entitybase for Roles
		EB[roleType] = new Entitybase(roleType, roleCapacity);
		
		//Entitybase for Courses
		EB[courseType] = new Entitybase(courseType, courseCapacity);
	}
	
	/** It converts the entity into a String
	 *  @param id		the ID of the entity to be converted
	 *  @param type		the type of the entity to be converted
	 *  @param v		the vocabulary to be used to translate concept attributes
	 *  @param depth	the number of relational attributes to be followed
	 * 	@return a string representation of the entity
	 */
	public static String toString(String id, int type, Vocabulary v, int depth) {
		
		//Depth must be >= 0
		if (depth < 0) return "";
		
		//It gets the Entitybase of the specified type
		Entitybase base = EB[type];
		if (base == null) return "";
		
		//It gets the entity
		Entity e = base.get(id);
		if (e == null) return "";
		
		//It computes the string
        String text = "The entity ID is: " + e.getId() + "\n";
        
        ArrayList<Attribute> attributes = e.getAttributes();
		for(int i = 0; i < attributes.size(); i++) { 
			Attribute a = attributes.get(i);
			if (a instanceof ConceptAttribute) {			
				ConceptValue value = (ConceptValue)a.getValue();
				int target = value.getId();
				Concept c = v.get(target);
				if (c != null)
					text += "\t[CONCEPT] " + a.getName() + ": " +  c.getWord() + "\n";
				else 
					text += "\t[CONCEPT] " + a.getName() + ": <" + target + ">\n";				
			}
			else if (a instanceof RelationalAttribute) {
				EntityValue value = (EntityValue)a.getValue();
				int targetType = value.getType();
				String targetValue = value.getId();
				text += "\t[RELATIONAL <" + targetType + ">] " + a.getName() + ": " + targetValue + "\n";
				
				if (depth > 0)
					text += "\t{" + toString(targetValue, targetType,  v, depth-1) + "\t}\n";
			}
			else
				text += "\t" + a.getName() + ": " + a.getValue() + "\n";
		}  
		
		return text;
	}
	
	/** It converts the entity into a JSON object
	 *  @param id		the ID of the entity to be converted
	 *  @param type		the type of the entity to be converted
	 *  @param v		the vocabulary to be used to translate concept attributes
	 *  @param depth	the number of relational attributes to be followed
	 * 	@return a JSON representation of the entity
	 */
	public static JSONObject toJSON(String id, int type, Vocabulary v, int depth) {

		//Depth must be >= 0
		if (depth < 0) return null;
		
		//It gets the Entitybase of the specified type
		Entitybase base = EB[type];
		if (base == null) return null;
		
		//It gets the entity
		Entity e = base.get(id);
		if (e == null) return null;
			
		//It computes the string
		JSONObject obj = new JSONObject();
	    obj.put("Identifier", e.getId());
	            
	    JSONArray attArray = new JSONArray();
        ArrayList<Attribute> attributes = e.getAttributes();
		for(int i = 0; i < attributes.size(); i++) { 
			Attribute a = attributes.get(i);
			JSONObject att = new JSONObject();
			
			if (a instanceof ConceptAttribute) {			
				ConceptValue value = (ConceptValue)a.getValue();
				int target = value.getId();
				Concept c = v.get(target);
			    
				att.put("Type", "Concept");
				att.put("Name", a.getName());
				
				if (c != null) {
					att.put("Value", c.getWord());
					att.put("Target", target);
				}
				else {
					att.put("Value", "");
					att.put("Target", target);
				}		
			}
			else if (a instanceof RelationalAttribute) {
				EntityValue value = (EntityValue)a.getValue();
				int targetType = value.getType();
				String targetValue = value.getId();

				att.put("Type", "Relational");
				att.put("EType", targetType);
				att.put("Name", a.getName());
				att.put("Target", targetValue);
				
				if (depth > 0)
					att.put("Value", toJSON(targetValue, targetType,  v, depth-1));
			}
			else {
				att.put("Type", "String");
				att.put("Name", a.getName());
				att.put("Value",a.getValue());
			}
			
			attArray.add(att);
		}  
		obj.put("Attributes", attArray);
		
		return obj;
	}
	
}