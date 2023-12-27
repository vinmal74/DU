package Hub;

/** *******************************************************************
 * A concept in a language.
 * It is intended to be used within a vocabulary in a specific language.
 * @author vincenzo.maltese
********************************************************************* */
public class Concept {

	//The identifier
	private int id;

	//The word of the concept in the language
	private String word;

	//The definition of the concept in the language
	private String definition;

	/** It creates a new concept */
	public Concept(int id, String word, String definition) {
		this.id = id;
		this.word = word;
		this.definition = definition;
	}

	/** It returns the identifier of the concept */
	public int getId() {
		return this.id;
	}
	
	/** It returns the word of the concept */
	public String getWord() {
		return this.word;
	}
	
	/** It returns the description of the concept */
	public String getDefinition() {
		return this.definition;
	}
}