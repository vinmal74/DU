package ETL;

import Hub.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Courses {
	
	//Data files
	private static final String courses = "C:\\Users\\vincenzo.maltese\\eclipse-workspace\\EntityHub Open\\src\\data\\courses.csv";
	private static final String splitBy = ";";
	
	public static void process() {
				
		//Readers for data
		BufferedReader bufferCourses = null;
		try {
			bufferCourses = new BufferedReader(new FileReader(courses));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = "";

    	try {
			//Extract Courses
			while ((line = bufferCourses.readLine()) != null) {
				
				//Attributes of a Course
			    String[] course = line.split(splitBy, -1);
			    String programName 	= course[1];
			    String programType 	= course[2];
			    String courseID		= course[3];
			    String courseName	= course[4];
			    String departmentID	= course[5];
			    String personID		= course[6];
			    int assistant		= Integer.parseInt(course[7]);
    		
				//Prepare the course entity with the specified ID
				Entity e = new Entity(EntityStore.courseType, courseID);
				
				//Class
				e.addAttribute(new ConceptAttribute("Class", new ConceptValue(EntityStore.voc_course)));
							
				//Course name
				e.addAttribute(new StringAttribute("Name", courseName));
				
				//Degree program
				String completeProgramName = programName + "(" + programType + ")";
				e.addAttribute(new StringAttribute("Degree program", completeProgramName));
				
				//Prepare the Department entity (we just get the ID)
				Entity org = new Entity(EntityStore.organizationType,departmentID);
				EntityStore.EB[EntityStore.organizationType].load(org);
					
				//Add the relational attribute to the course
				e.addAttribute(new RelationalAttribute("Department", new EntityValue(EntityStore.organizationType, departmentID)));
				
				//Prepare the Person entity (we just get the ID)
				Entity person = new Entity(EntityStore.personType,personID);
				EntityStore.EB[EntityStore.personType].load(person);
					
				//Add the relational attribute to the course
				if (assistant == 1)
					e.addAttribute(new RelationalAttribute("Assistant", new EntityValue(EntityStore.personType,personID)));
				else
					e.addAttribute(new RelationalAttribute("Professor", new EntityValue(EntityStore.personType,personID)));
				
				//Load the entity into the entitybase
				EntityStore.EB[EntityStore.courseType].load(e);
	        }	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}