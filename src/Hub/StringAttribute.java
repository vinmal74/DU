package Hub;

/** *******************************************************************
 * A String attribute.
 * @author vincenzo.maltese
********************************************************************* */
public class StringAttribute extends Attribute {
	
	/** It creates a new String Attribute 
	 * @param	name	is the the of the attribute
	 * @param	value	it is the value og the attribute
	 * */
	public StringAttribute(String name, String value) {
		this.setName(name);
		this.setValue(value);
	}
	
}