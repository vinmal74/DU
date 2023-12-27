package Hub;

/** *******************************************************************
 * An Entity Value.
 * It is intended to be used as value of a Relational Attribute.
 * @author vincenzo.maltese
********************************************************************* */
public class EntityValue {
	
	//The type of the target entity
	private int type;
	
	//The identifier of the target entity
	private String id;
	
	/** It creates a new entity value */
	public EntityValue(int type, String id) {
		this.type = type;
		this.id = id;
	}

	/** It returns the type of the target entity */
	public int getType() {
		return this.type;
	}
	
	/** It returns the identifier of the target entity */
	public String getId() {
		return this.id;
	}
	
	/** It checks if two values are the same */
	public boolean equals(Object obj) { 
		if (obj == this) return true; 
		if (obj == null || obj.getClass() != this.getClass()) return false;
		EntityValue value = (EntityValue) obj; 
		if ((value.getType() == this.type) && (value.getId().equals(this.id))) return true;
		else return false;
	}
}
