package Hub;

/** *******************************************************************
 * A Relational attribute.
 * @author vincenzo.maltese
********************************************************************* */
public class RelationalAttribute extends Attribute {
	
	/** It creates a new Relational Attribute 
	 * @param	name	is the the of the attribute
	 * @param	value	it is the related Entity
	 * */
	public RelationalAttribute(String name, EntityValue value) {
		this.setName(name);
		this.setValue(value);
	}
	
}