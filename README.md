# RedHelix GitHub Repository
Date September 2, 2015, Version 0.1
##Overview
RedHelix is a Java library and a server to monitor hardware using the [DMTF Redfish](http://www.dmtf.org/standards/redfish) specification. 
The RedHelix server uses the Redfish specification to monitor multiple devices. It builds a in memory database representing the inventory
and status of the devices and provides a Restfull API to query the history of the devices.

## License
All RedHelix software is under the Apache 2.0 license.

## Redfish vs. RedHelix 
Redfish provides the HTTP protocol and JSON messages to monitor a single server. RedHelix does not. RedHelix provides a databasecontaning 
multiple servers that implement Redfish and the history for
each server. At present the database is only in limited to what is in memory and when the Java Virtual Machine shuts down all history is destroyed.
From a user perspective RedHelix can have multiple interfaces to a browser. The first will use [AngularJS](https://angularjs.org).

## Development Status
At present, September 2, 2015 there is no hardware implementing Redfish for sale or available to RedHelix for testing. If you have Redfish capable
hardware contact us. The current Java software
is tested with the DMTF Redfish mockup files. This prevents any development of Redfish operations that change the state of the server, for example
to reboot it.  The software is undergoing massive changes. It's pre-alpa and not ready to use but you can see the direction it is going.

##Roadmap
As of September 2, 2015 RedHelix will be developed with the DMTF mockup files found in DSP2043_0.99.0. Development will proceed in this order.

1. HTTP reading of the Chassis messages and saving them in Java classes. Done.
2. Implement the Java threads used to monitor a single Redfish server and store the chassis information in the RedHelixDb database.
3. A browsers interface to RedHelixDb using AngularJs.
4. HTTP reading of the Computer System messages and saving them in Java classes.
5. Standalone server called RedHelixDb containing an in memory databases of the Chassis and Computer System.

## Architecture
RedHelix is designed to run in a single Java Virtual Machine(JVM). The connection to manage servers implementing Redfish is accomplished using
the Apache Olingo library for version 4 of the OData protocol. The connection to the Web Browsers will use Google's AngularJS, an implemntation of JavaScript.
As of RedHelix 0.1 this has not been implemented.
![Image](./doc/redhelix-toplevel-architecture-1.svg?raw=true)

### Java package org.redhelix.core.*
The classes in the Java package org.redhelix.core.* are immutable thread safe 
implementations of the Redfish JSON messages. This package does not implement Java threads. As of version 0.1 there are 107 classes with and 
the most interesting interface is org.redhelix.core.chassis.RedHxChassis. The core class does not depend on the Olingo library. 

## Architecture Risk
While desirable to scale the single JVM to handle more than 40,000 servers,
comparable to the IPMI implementation of [Hemi DC](http://jblade.com/products/hemi/hemiDC/HemiDcOverview.jsf) it is not clear that can be done with
HTTP and Olingo. The issue that needs to be addressed is once a HTTP Get is sent by RedHelix does a Java Thread then block waiting on the TCP 
socket for a response. If so the JVM will run out of Threads. Under IPMI this was not an issue because due to the connectionless nature of UDP
10 threads could handle in all incomming traffic from the 40,000+ servers.


## Other information
1. [RedHelix Development](./doc/development.md)
2. [RedHelix Design](./doc/design.md)

