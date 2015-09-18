#RedHelix GitHub Repository
Date September 17, 2015, Version 0.2
## Major Implementation change.
On September 12,2015 the maven projects were renamed and the main method has moved. See  [RedHelix Development Status](./doc/dev-status.md)

##Overview
RedHelix is a Java library and a server to monitor hardware using the [DMTF Redfish](http://www.dmtf.org/standards/redfish) specification. 
The RedHelix server uses the Redfish specification to monitor multiple devices. It builds a in memory database representing the inventory
and status of the devices and provides a Restfull API to query the history of the devices.

## License
All RedHelix software is under the Apache 2.0 license.

## Redfish vs. RedHelix 
While both Redfish and RedHelix manage servers using a RESTful interface they differ on the Graphics User Interface, RESTful protocol and how
concisely hardware is defined. For more detail look at [Redfish vs. RedHelix](./doc/redfish-vs-redhelix.md) 

## Development Status
1. [RedHelix Development Status](./doc/dev-status.md)
2. [RedHelix Development Guidelines](./doc/dev-guidelines.md)

##Roadmap
As of September 13, 2015 RedHelix is developing with the DMTF mockup files found in DSP2043_0.99.0. Development will proceed in this order.

1. A browsers interface to RedHelixDb using AngularJs.
2. Implement the Java threads used to monitor a single Redfish server and store the chassis information in the RedHelixDb database.
3. Standalone server called RedHelixDb containing an in memory databases of the Chassis and Computer System.

## Architecture
RedHelix is designed to run in a single Java Virtual Machine(JVM). The connection to manage servers implementing Redfish is accomplished using
the Apache Olingo  [Olingo](http://olingo.apache.org/doc/odata4/index.html). library for version 4 of the OData protocol. The connection to the Web Browsers will use Google's AngularJS, an implemntation of JavaScript.
As of RedHelix 0.1 this has not been implemented. 
![RedHelix Architecture](https://rawgit.com/RedHelixOrg/RedHelix-1/master/doc/redhelix-toplevel-architecture-2.svg)
<!-- perment cached CDN comment. https://cdn.rawgit.com/RedHelixOrg/RedHelix-1/master/doc/redhelix-toplevel-architecture-2.svg -->
An overview of the architecture is found at [RedHelix Design](./doc/design.md)

## OEM Extensions
Redfish allows OEM extensions to the Chassis, Computer System, etc. It's not clear how these will be implemented. If you are implementing an
OEM extension, even if it is not yet working, please contact me. I'll try and build a framework to allow it. 

## RedHelix Mockup
The output of the test program RedHelixClientReport when used with Redfish mockup file can be viewed  [How to read](./doc/dmtf-mockup/mockup.md) 

## Building
From the dir RedHelix-1/redhx-build-all run the command 

mvn package 

## Running
After building the software in can run with the Redfish mock server using the command. From the dir redhx-build-all run:

1. java -Dparam_protocol="http" -Dparam_hostname="localhost" -Dparam_port="9080" -jar ./redhx-server-util/target/redhx-server-util-0.1-SNAPSHOT.jar
If the executable throws and exception indicating a premature End of File when parsing the JSON messages and a single line to the Redfish file server.js.
The line to be inserted at line number 104 is

line 103: data = JSON.stringify(data, null, '  ');

line 104:  data = data +"  "; // a hack to so that the last char is read by function head() that is below.





