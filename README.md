THE DIGITAL UNIVERSITY OPEN SOURCE CODE

We provide the complete code of a simple and very efficient framework 
that can be employed by organizations to develop their own knowledge graph, 
offering a comprehensive picture of the strategic data of the organization, 
such that it can be consistently exploited by different digital services.
It is released as an Eclipse Java project.

It supports data engineers in the creation of a multilingual knowledge graph 
that is built from data extracted from multiple sources by means of ETL facilities. 
In terms of complexity the entity matching and integration algorithm is linear 
in the number of entities to be integrated, i.e. it is O(n). 
It makes use of very efficient data structures (hash tables) and runs entirely on RAM memory.

This program is free software: you can redistribute it and/or modify it under 
the terms of the GNU General Public License as published by the Free Software Foundation, 
either version 3 of the License, or (at your option) any later version. 
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details. Please always cite in your 
code information about the original author: 
Vincenzo Maltese (vincenzo.maltese@unitn.it), University of Trento, Italy.

A toy example is provided. For sake of simplicity, we assume that data sources have been 
already pre-processed (for instance, as a result of a job that runs daily in order to get
up-to-date information) and that the result is available as CSV files:

•	people.csv contains the people affiliated to the University, and in particular each row contains the unique identifier of the person, the surname, the name, the gender (M for male and F for female), the email address and the phone number;
•	units.csv contains administrative units of the University, where each row contains the unique identifier of the unit, the identifier of the type of unit, the unit name, the identifier of the parent administrative unit on which it depends (in order to reconstruct the organization chart), the email address and the phone number of the unit;
•	types_of_units.csv contains information about the types of administrative units of the University, and in particular each row contains the identifier of the type of unit, the name of the type and the identifier of the corresponding concept in the vocabularies (for instance as a result of a manual or automatic mapping);
•	positions.csv contains information about the affiliations of each person, and in particular each row contains the identifier of the person, the identifier of the type of position and the identifier of the administrative unit;
•	types_of_positions.csv contains information about the types of positions that can be appointed to people in the administrative Units of the University, and in particular each row contains the identifier of the type of position, the name of the type and the identifier of the corresponding concept in the vocabularies (for instance as a result of a manual or automatic mapping);
•	courses.csv contains information about the courses offered by the University, and in particular each row contains the unique identifier of the degree program, the degree program name, the program type, the identifier of the course, the name of the course, the identifier of the department (an administrative unit), the identifier of the person who teaches the course, a field that is equal 0 in case the person is a professor and 1 in case the person is an assistant.

The two vocabularies, in English and Italian, are stored in TXT files. Each row contains the identifier of the concept, the label and the definition in the corresponding language. They can be extended as needed.

Selected papers:

Maltese, V., & Giunchiglia, F. (2017). Foundations of Digital Universities. 
Cataloging & Classification Quarterly, 55(1), 26-50. http://dx.doi.org/10.1080/01639374.2016.1245231 

Maltese, V (2018). Digital transformation challenges for universities: 
Ensuring information consistency across digital services. 
Cataloging & Classification Quarterly, 56, 592-606. http://dx.doi.org/10.1080/01639374.2018.1504847 

Maltese, V (2024). Addressing digital transformation in universities: 
how to effectively govern, trust and value your data. Soon in: 
Journal of Telecommunications and the Digital Economy, Special Issue on
Emerging Technologies and Innovation for Digital Economy and Transformation

