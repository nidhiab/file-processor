# file-processor
File Processor Application

This application processes a file - records.csv that is placed in the resources folder.
This file has records as specified in the challenge brief.

The repository has a folder called apidocs, that has generated javadocs.

It can be built using mvn

I have set maven.compiler.release property to 11.  

To build please use: 
mvn install

As a result of above command two jars get created in target folder. You would want to use the executable jar called file-processor.jar for testing purposes.

When we run the jar we see the following output:


2021-08-14 16:02:33 INFO FileProcessorApplication...
2021-08-14 16:02:33 INFO Come to read file records.csv
2021-08-14 16:02:33 INFO Come to process records
2021-08-14 16:02:33 INFO ------------------------------------------------
2021-08-14 16:02:33 INFO Going to find unique customers per contract
2021-08-14 16:02:33 INFO Contract 2346 has 2 unique customers
2021-08-14 16:02:33 INFO Contract 2345 has 3 unique customers
2021-08-14 16:02:33 INFO ------------------------------------------------
2021-08-14 16:02:33 INFO Going to find unique customers count per geozone
2021-08-14 16:02:33 INFO Geozone eu_west has 2 unique customers
2021-08-14 16:02:33 INFO Geozone us_west has 2 unique customers
2021-08-14 16:02:33 INFO Geozone us_east has 1 unique customers
2021-08-14 16:02:33 INFO ------------------------------------------------
2021-08-14 16:02:33 INFO Going to find averages
2021-08-14 16:02:33 INFO Geozone eu_west average build duration 4,222
2021-08-14 16:02:33 INFO Geozone us_west average build duration 2,216
2021-08-14 16:02:33 INFO Geozone us_east average build duration 3,445
2021-08-14 16:02:33 INFO ------------------------------------------------
2021-08-14 16:02:33 INFO Going to find unique customers per geozone
2021-08-14 16:02:33 INFO Geozone eu_west has unique customers [3244132, 3244332]
2021-08-14 16:02:33 INFO Geozone us_west has unique customers [1233456, 1223456]
2021-08-14 16:02:33 INFO Geozone us_east has unique customers [2343225]


