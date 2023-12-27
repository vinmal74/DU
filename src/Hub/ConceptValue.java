package Hub;

/** *******************************************************************
 * A concept Value.
 * It is intended to be used as value of a Concept Attribute.
 * @author vincenzo.maltese
********************************************************************* */
public class ConceptValue {

	//The value (identifier of the target concept)
	private int id;

	/** It creates a new concept value */
	public ConceptValue(int id) {
		this.id = id;
	}

	/** It returns the value of the concept value */
	public int getId() {
		return this.id;
	}
	
	/** It checks if two values are the same */
	public boolean equals(Object obj) { 
		if (obj == this) return true; 
		if (obj == null || obj.getClass() != this.getClass()) return false;
		ConceptValue value = (ConceptValue) obj; 
		if (value.getId() == this.id) return true;
		else return false;
	}
}
