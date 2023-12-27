package Hub;

/** *******************************************************************
 * A Concept attribute.
 * @author vincenzo.maltese
********************************************************************* */
public class ConceptAttribute extends Attribute {
	
	/** It creates a new concept attribute 
	 * @param	name	is the the of the attribute
	 * @param	value	it is the identifier of the concept
	 * */
	public ConceptAttribute(String name, ConceptValue value) {
		this.setName(name);
		this.setValue(value);
	}
	
}
