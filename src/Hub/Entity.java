package Hub;

import java.util.ArrayList;

/** *******************************************************************
 * An entity
 * @author vincenzo.maltese
********************************************************************* */
public class Entity {

	//The type of the entity
	private int type;
	
	//The identifier of the entity
	private String id;
		  
	//The list of attributes of the entity
	private ArrayList<Attribute> attributes;
	
	/** It creates a new entity
	 * @param	type	the type of the entity
	 * @param	id		the identifier of the entity
	 *  */
	public Entity(int type, String id) {
	  this.type = type;
	  this.id = id;
	  this.attributes = new ArrayList<Attribute>();
	}
	
	/** It returns the type of the entity */
	public int getType() {
		return this.type;
	}
	
	/** It returns the id of the entity */
	public String getId() {
		return this.id;
	}
	
	/** It returns the attributes of the entity */
	public ArrayList<Attribute> getAttributes() {
		return this.attributes;
	}
	
	/** It adds the attribute of the entity.
	 * @return	false if the attribute was already in the list
	 * @return	true if the attribute is correctly inserted in the list
	 * */
	public boolean addAttribute(Attribute att) {
		if (this.attributes.contains(att)) return false;
		else return this.attributes.add(att);
	}
	
	/** It removes the attribute of the entity.
	 * @return	false if the attribute was not in the list
	 * @return	true if the attribute is correctly removed from the list
	 * */
	public boolean removeAttribute(Attribute att) {
		if (!this.attributes.contains(att)) return false;
		else return this.attributes.remove(att);
	}
}
