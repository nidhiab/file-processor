# file-processor
File Processor Application

This application can be run to process a default file called records.cvs that is inside the jar.<br/>
This application can also be run to process a file that you have on your system by giving it's absolute path.

When you run the file using the following command

java -jar file-processor.jar

it will read the file inside the jar and produce output for that file.

If you run the file with an absolute path, it will process that file.

java -jar file-processor.jar "c://records.csv"

This file should have records as specified in the challenge brief.

If the file has fewer columns than 6 it throws a runtime exception.<br/>
If the file has more than 6 columns in a line, it ignores anything after first 6 columns.<br/>
If the build duration does not follow the pattern of a String with all digits ending with s, the application throws an exception.

You should have jdk installed on your machine. Set JAVA_HOME to your jdk directory.

Please use mvn to build the application.
I have set maven.compiler.release property to 11.  Please change version to whatever you would like.

To build use: 
mvn install

As a result of above command two jars get created in target folder. You would want to use the executable jar called file-processor.jar for testing purposes.

When we run the jar we see the following output:

2021-08-14 16:02:33 INFO FileProcessorApplication...<br/>
2021-08-14 16:02:33 INFO Come to read file records.csv<br/>
2021-08-14 16:02:33 INFO Come to process records<br/>
2021-08-14 16:02:33 INFO ------------------------------------------------<br/>
2021-08-14 16:02:33 INFO Going to find unique customers per contract<br/>
2021-08-14 16:02:33 INFO Contract 2346 has 2 unique customers<br/>
2021-08-14 16:02:33 INFO Contract 2345 has 3 unique customers<br/>
2021-08-14 16:02:33 INFO ------------------------------------------------<br/>
2021-08-14 16:02:33 INFO Going to find unique customers count per geozone<br/>
2021-08-14 16:02:33 INFO Geozone eu_west has 2 unique customers<br/>
2021-08-14 16:02:33 INFO Geozone us_west has 2 unique customers<br/>
2021-08-14 16:02:33 INFO Geozone us_east has 1 unique customers<br/>
2021-08-14 16:02:33 INFO ------------------------------------------------<br/>
2021-08-14 16:02:33 INFO Going to find averages<br/>
2021-08-14 16:02:33 INFO Geozone eu_west average build duration 4,222<br/>
2021-08-14 16:02:33 INFO Geozone us_west average build duration 2,216<br/>
2021-08-14 16:02:33 INFO Geozone us_east average build duration 3,445<br/>
2021-08-14 16:02:33 INFO ------------------------------------------------<br/>
2021-08-14 16:02:33 INFO Going to find unique customers per geozone<br/>
2021-08-14 16:02:33 INFO Geozone eu_west has unique customers [3244132, 3244332]<br/>
2021-08-14 16:02:33 INFO Geozone us_west has unique customers [1233456, 1223456]<br/>
2021-08-14 16:02:33 INFO Geozone us_east has unique customers [2343225]<br/>



The repository has a folder called apidocs, that has generated javadocs.
