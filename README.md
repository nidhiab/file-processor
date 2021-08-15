# file-processor
File Processor Application

This application can be run to process a default file called records.cvs that is inside the jar.<br/>
This application can also be run to process a file that you have on your system by giving it's absolute path.

# build instructions
You should have jdk installed on your machine. 
Set JAVA_HOME to your jdk directory. <br/>

In the accompanying pom.xml, maven.compiler.release property is set to 15. <br/> 
Change maven.compiler.release version corresponding to JAVA_HOME above <br/>

To build use: 
mvn install

# running the jar
As a result of above command two jars get created in target folder. You would want to use the executable jar called file-processor.jar for testing purposes.

When you run the file using the following command

java -jar file-processor.jar

it will read the records.csv file inside the jar and produce output for that file.

If you run the file with an absolute path, it will process that file. <br/>
Please note that this name would change according to your file name. Just pass the absolute file path to the program. <br/>

java -jar file-processor.jar "c://outside_records.csv"   

# file Validations
This file should have records as specified in the brief.

If the file has fewer columns than 6 it throws a runtime exception.<br/>
If the file has more than 6 columns in a line, it ignores anything after first 6 columns.<br/>
If the build duration does not follow the pattern of a String with all digits ending with s, the application throws an exception.

# api docs
The repository has a folder called apidocs, that has generated javadoc.
