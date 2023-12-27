package Hub;

import java.util.ArrayList;
import java.util.HashMap;

/** *******************************************************************
 * A collection of entities of a certain type
 * @author vincenzo.maltese
********************************************************************* */
public class Entitybase {

	//The type of the entities in the entitybase
	private int type;
	
	//The entities in the entitybase
	private HashMap<Integer, Entity> items;

	/** It creates a new empty entitybase for entities of the specified type
	 * @param	type	the type of the entities in the entitybase
	 * @param	capacity	the expected capacity of the entitybase */
	public Entitybase(int type, int capacity) {
		this.type = type;
		this.items = new HashMap<Integer, Entity>(capacity);
	}
	
	/** @return the type of the entitybase */
	public int getType() {
		return this.type;
	}
	
	/** It creates a new entity in the entitybase.
	 * @return null if the entity is not of the expected type */
	public Entity load(Entity e) {

		//Check if the entity is null
		if (e == null) return null;
		
		//Check if the entity is of the right type
		if (e.getType() != this.type) return null;
				
		//Gets the key
		int key = e.getId().hashCode();
		
		//Case A: the entity is already in the entitybase (merge the two entities)
		Entity entityOld = this.items.get(key);
		if (entityOld != null) {
			ArrayList<Attribute> attributes = entityOld.getAttributes();
			for(int i = 0; i < attributes.size(); i++) e.addAttribute(attributes.get(i));
		}
		
		//Case B: the entity is not yet in the entitybase (or the entity needs to be updated)
		this.items.put(key, e);
		
		return e;
	}
	
	/** @return the entity with the specified identifier, or null if the entry is not in the entitybase */
	public Entity get(String identifier) {
		int key = identifier.hashCode();
		return this.items.get(key);
	}
	
	/** @return true if the entitybase contains an entity with the same key */
	public boolean contains(String identifier) {
				
		//Gets the key
		int key = identifier.hashCode();
		
		//Gets the entity
		if (this.items.containsKey(key)) return true;
		else return false;
	}
	
	/** It removes the entity with the specified identifier.
	 * @return the removed entity, or null if the entry is not in the vocabulary */
	public Entity remove(String identifier) {
		int key = identifier.hashCode();
		return this.items.remove(key);
	}
	
	/** @return the number of entities in the entitybase */
	public int size() {
		return this.items.size();
	}
	
}