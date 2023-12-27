package Hub;

import java.util.HashMap;
import java.io.File;  
import java.io.FileNotFoundException;
import java.util.Scanner;

/** *******************************************************************
 * A vocabulary in a language.
 * @author vincenzo.maltese
********************************************************************* */
public class Vocabulary {
  
	//The language of the vocabulary
	private String language;
  
	//The concepts in the vocabulary
	private HashMap<Integer, Concept> items;

	/** It creates a new vocabulary in the specified language */
	public Vocabulary(String lang) {
	  this.language = lang;
	  this.items = new HashMap<Integer, Concept>();
	}
	
	/** @return the language of the vocabulary */
	public String getLanguage() {
		return this.language;
	}
	
	/** It creates new entries in the vocabulary taken from a file
	 * @param path the path of the file with the vocabulary entries
	 * @return true if the file is read correctly */
	public boolean load(String path) {
	    try {
	        File myObj = new File(path);
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          
	          //Selects the elements of the vocabulary item
	          String[] entry = data.split("\\|");

	          //Note: if any element is missing or malformed, it throws an Exception 
	          if ((entry[0] != null) && (entry[1] != null)) {
	        	  int key = Integer.parseInt(entry[0].replace("\t", "").trim());
	        	  String word = entry[1].replace("\t", "").trim();
	        	  String definition = "";
	        	  if (entry[2] != null) definition = entry[2].replace("\t", "").trim();
	        	  this.put(new Concept(key, word, definition));
	          }
	        }
	        myReader.close();
	      } catch (Exception e) {
	    	  return false;
	      }
	    return true;
	}
	
	/** It creates a new entry in the vocabulary.
	 * @return null if the entry is already in the vocabulary */
	public Concept put(Concept c) {
		if (c != null) {
			int key = c.getId();
			if (this.items.containsKey(key)) return null;
			this.items.put(key, c);
		}
	  return c;
	}
	
	/** @return the concept with the specified identifier.
	 * @return null if the entry is not in the vocabulary */
	public Concept get(int identifier) {
		return this.items.get(identifier);
	}
	
	/** It removes the concept with the specified identifier.
	 * @return the removed concept, or null if the entry is not in the vocabulary */
	public Concept remove(int identifier) {
		return this.items.remove(identifier);
	}
  
}
