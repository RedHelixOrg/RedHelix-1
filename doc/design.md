# RedHelix Software Architecture

### Java package org.redhelix.core.*
The classes in the Java package org.redhelix.core.* are immutable thread safe 
implementations of the Redfish JSON messages. This package does not implement Java threads. As of version 0.1 there are 107 classes with and 
the most interesting interface is org.redhelix.core.chassis.RedHxChassis. No class in org.redhelix.core.* depend on the Olingo library. 

### Java package org.redhelix.server.*
The classes in the Java package org.redhelix.server.* are responsible for the Java Threads that interface with the Redfish enabled servers,
the in memory database and the AngularJS web clients. For this version 0.1 there is nothing exciting in this package. The Java Main method is called
and then it exits. Take a look at the source code and the output of reading the chassis using [RedHelixClientReport](./doc/dmtf-mockup/mockup.md).

## Architecture Risk
While desirable to scale the single JVM to handle more than 40,000 servers as comparable IPMI Java implementation it is not clear if 
HTTP and Olingo can scale. The issue that needs to be addressed is once a HTTP Get is sent by RedHelix does a Java Thread then block waiting on the TCP 
socket for a response. If so, the JVM will run out of Threads. Under IPMI this was not an issue due to the connectionless nature of UDP
10 threads could handle in all incomming traffic from the 40,000+ servers.


![RedHelix Architecture](https://rawgit.com/RedHelixOrg/RedHelix-1/master/doc/redhelix-toplevel-architecture-1.svg)