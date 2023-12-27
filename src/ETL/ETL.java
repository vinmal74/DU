package ETL;

import java.sql.Timestamp;
import Hub.Vocabulary;

public class ETL {
	
	public static void main(String[] args) {
	
		//It creates a new vocabulary
		Vocabulary english = new Vocabulary("English");
		String path = "C:\\Users\\vincenzo.maltese\\eclipse-workspace\\EntityHub\\src\\data\\english.txt";
		boolean read = english.load(path);
		if (!read) {
			System.out.println("DEBUG: Vocabuary has not been read correctly!");
			System.exit(0);
		}
		
		//It creates a new vocabulary
		Vocabulary italian = new Vocabulary("Italian");
		path = "C:\\Users\\vincenzo.maltese\\eclipse-workspace\\EntityHub\\src\\data\\italian.txt";
		read = italian.load(path);
		if (!read) {
			System.out.println("DEBUG: Vocabuary has not been read correctly!");
			System.exit(0);
		}
		
		//It crease the EntityStore with all the necessary Entitybases
		EntityStore ES = new EntityStore();
		
		//It processes the various Data Sources
		Units.process();
		People.process();
		Positions.process();
		Courses.process();

		//It gets entities with a certain identifier
		System.out.println("An example of generated String in English");	
		System.out.println(EntityStore.toString("92075", EntityStore.courseType, english, 2));
		
		System.out.println("An example of generated JSON in English");
		System.out.println(ES.toJSON("92075", EntityStore.courseType, english, 4).toJSONString());
		
		System.out.println("An example of generated JSON in Italian");
		System.out.println(ES.toJSON("92075", EntityStore.courseType, italian, 4).toJSONString());
	}
}