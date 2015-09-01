# RedHelix GitHub Repository
##Overview
RedHelix is a Java library and a server to monitor hardware using the [DMTF Redfish](http://www.dmtf.org/standards/redfish) specification. 
The RedHelix server uses the Redfish specification to monitor multiple devices. It builds a in memory database representing the inventory
and status of the devices and provides a Restfull API to query the history of the devices.

## License
All RedHelix software is under the Apache 2.0 license.

## What is the difference between Redfish and RedHelix ?
Redfish provides the HTTP protocol and JSON messages to monitor a single server. RedHelix provides a database of multiple servers and the history for
each server. At present the database is only in limited to what is in memory and when the Java Virtual Machine shuts down all history is destroyed.
From a user perspective RedHelix can have multiple interfaces to a browser. The first will use [AngularJS](https://angularjs.org).

## Development Status
At present, September 2, 2015 there is no hardware implementing Redfish for sale or available to RedHelix for testing. If you have Redfish capable
hardware contact us. The current Java software
is tested with the DMTF Redfish mockup files. This prevents any development of Redfish operations that change the state of the server, for example
to reboot it.  The software is undergoing massive changes. It's pre-alpa and not ready to use but you can see the direction it is going.

##Roadmap
As of September 2, 2015 RedHelix will be developed with the DMTF mockup files found in DSP2043_0.99.0. Development will proceed in this order:
1. HTTP reading of the Chassis messages and saving them in Java classes.
2. HTTP reading of the Computer System messages and saving them in Java classes.
3. Standalone server called RedHelixDb containing an in memory databases of the Chassis and Computer System.
4. Browers interface to RedHelixDb using AngularJs.
