# file-processor
File Processor Application

This application can be run to process a default file called records.cvs that is inside the jar.<br/>
This application can also be run to process a file that you have on your system by giving it's absolute path.

# get code
git clone https://github.com/nidhiab/file-processor.git

# pre-requisites
You should have jdk installed on your machine. Anything later than 9 would work.<br/>

Set JAVA_HOME to your jdk directory. <br/>
Set PATH variable to have %JAVA_HOME%\bin at the beginning of the PATH.<br/>

You also need maven on your machine <br/>
https://maven.apache.org/download.cgi

Please follow instructions as specified in  <br/>
https://maven.apache.org/users/index.html

# align versions
In the project pom.xml, maven.compiler.release property is set to 15. <br/> 
Change maven.compiler.release version corresponding to JAVA_HOME above <br/>

# build instructions
To build run: 
mvn install

# running the jar
As a result of above command two jars get created in target folder. You would want to use the executable jar called file-processor.jar for testing purposes.

When you run the file using the following command

java -jar file-processor.jar

it will read the records.csv file inside the jar and produce output for that file.

If you run the file with an absolute path, it will process that file. <br/>
Please note that this name would change according to your file name. Just pass the absolute file path to the program. <br/>

java -jar file-processor.jar "c://outside_records.csv"   

# file validations
This file should have records as specified in the brief.

If the file has fewer columns than 6 it throws a runtime exception.<br/>
If the file has more than 6 columns in a line, it ignores anything after first 6 columns.<br/>
If the build duration does not follow the pattern of a String with all digits ending with s, the application throws an exception.

# api docs
The repository has a folder called apidocs, that has generated javadoc.
