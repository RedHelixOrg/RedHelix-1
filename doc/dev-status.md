## RedHelix Software Development Status

At present, September 2, 2015 there is no hardware implementing Redfish for sale or available to RedHelix for testing. If you have Redfish capable
hardware contact us. The current Java software
is tested with the DMTF Redfish mockup files. This prevents any development of Redfish operations that change the state of the server, for example
to reboot it.  The software is undergoing massive changes. It's pre-alpa and not ready to use but you can see the direction it is going.

### September 13, 2013
The old maven project redhx-app-server-db has been removed. The Java main method for running Redhelix was in RedHxServerDb and it has moved
to the maven project redhx-server-util as RedHelixClientReport.java. There is more error checking.
In preparation for the web interface two new java projects have been introduced.
1. redhx-server-db The main RESTfull threads that read Redfish JSON messages and use AngularJS to communicate with web browsers.
2. redhx-server-lib This is where all the JSON readers of the Redfish live.
As a result of these changes a new git up brach for version 0.2 has been made.


### September 12, 2013
HP has added HTTPS with Basic Authorization. See scripts  winBuild.cmd  and winRun.cmd. Thanks Dan.
Added processing for Computer System JSON messages. SimpleStorage, Ethernets, Memory status is not yet added.

### September 2, 2013
HTTP reading of the Chassis messages and saving them in Java classes. 