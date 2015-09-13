# RedHelix Software Development Guidelines
The RedHelix software is developed under these guidelines.
1. Java source adhears to the [Google Styleguide](http://google.github.io/styleguide/javaguide.html) 
*  You can download the Eclipse code formmating configuration from [Google Style Eclipse](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml)
2. All Java Enums have a file name that ends in Enum.java
3. The logging system is Apache [commons-logging](http://commons.apache.org/proper/commons-logging)
4. Redfish uses the OData protocol version 4 specification which is implemented by the Apache Olingo v4 Java library. This is used in the RedHelix database server.
5. Maven is the build system
7. the maven project redhx-core-api contains only Java interfaces and exceptions. There are no other class files.
8. the maven project redhx-core-impl contains only Java classes that implement the interfaces found in the redhx-core-api.  All classes are 
marked final except classes with the name "Abstract" in them.
9. RedHelix implements distinct classes for resources that Redfish uses the identical enum but with different resource names. For example
Redfish has a Status class used to identify both "Operating Health" and "Operating HealthRollup". The Java Compiler can not distinguis between the two
and a programmer could easily interchange the two leading to run time bugs. RedHelix implements two different classes  RedHxOperatingStatus 
RedHxOperatingHealthEnum and  RedHxOperatingHealthRollupEnum allowing the Java compiler to issue errors, and stop compiling, if the two Status are interchanged.

