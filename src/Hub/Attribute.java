package Hub;

/** *******************************************************************
 * A generic attribute.
 * @author vincenzo.maltese
********************************************************************* */
public abstract class Attribute {
	
	//The name
	private String name;

	//The value
	private Object value;
	
	/** It creates a new attribute with no name and no value */
	public Attribute() {
		this.name = null;
		this.value = null;
	}
	
	/** It creates a new attribute with a name and value */
	public Attribute(String name, Object value) {
	  this.name = name;
	  this.value = value;
	}
	
	/** It returns the name of the attribute */
	public String getName() {
		return this.name;
	}
	
	/** It returns the value of the attribute */
	public Object getValue() {
		return this.value;
	}
	
	/** It sets the name of the attribute */
	public void setName(String name) {
		this.name = name;
	}
	
	/** It sets the name of the attribute */
	public void setValue(Object value) {
		this.value = value;
	}
	
	/** It checks if two attributes have same name and value */
	public boolean equals(Object obj) { 
		if (obj == this) return true; 
		if (obj == null || obj.getClass() != this.getClass()) return false;
		Attribute att = (Attribute) obj; 
		if (att.getName().equals(this.name) && att.getValue().equals(this.value)) return true;
		else return false;
	}

}
