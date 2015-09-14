###  Redfish vs. RedHelix 
Redfish and RedHelix differ on the type of RESTful interface and how the data is presented to the end user.
RedHelix also attempts to provide a consistent interface to hardware.

#### Consistent Interface
Redfish, in an effort to maximize the hardware platforms it supports, makes most identifying data optional. For example, it allows both
Part Number and Model Number for servers, chassis and power supplies. Some vendors supply both, some only one and some none. It also does not
mandate a UUID for each piece of hardware and is makes no demands on the the UUID remaining constant when firmware or hardware is upgraded.
In a Data Center if you upgrade hardware you don't want the history of the hardware to disappear due to a firmware upgrade. To allow hardware
vendors or Data Center operators to change the UUID without losing the devices history RedHelix assigns it's own UUID to each device.

Redfish does not constrain the number of characters in strings such as model number, manufacture name, sensor name or hardware description. 
And it should not. The design of Redfish is to maximize it's adoption for the hardware layer. 

In contrast RedHelix includes a GUI and limits have to placed on these strings to have consistent user interface for table, data entry fields
 etc. In most places the limit of 50 characters has been enforced. An exception is for the hardware description which is 1000 characters. 

If any limit that is a problem, this open source and fork or other change will happen. Search the GitHub repository for the string 
RED_HELIX_DEFINED to see all the limits which are static integers. There are currently 57 of them and all constrained to the maven project 
redhx-core-api which contains only Java interfaces and enumerations. By design the single maven project could be forked and the limits changed 
without modifying any Java classes.
 

#### RESTful Interface
Redfish provides the HTTP protocol and JSON messages to monitor a single implementation of Redfish at a single HTTP connection to a TCP port number.
RedHelix allows a single HTTP connection to a TCP port number to monitor servers a multiple physical data centers, rooms and racks. 
RedHelix allows queries to be filtered by any combination of:

 * siteName
 * roomName
 * rackName
 * redfishHardwareType
 * manufacturerName
 * redfishSchemaVersion
 * poweredBy
 * cooledBy
 * poweredOnState
 * operatingState
 
These are examples of how to get the list of UUID, assigned by RedHelix, of the devices in the RedHelix database.


1. To get all devices managed by a RedHelix implementation call the RESTful URL
   * http://localhost:8080/RedHelix.svc/v1/hardwareId
2. To get all devices powered off use the query poweredOff from the above list to make this URL
   * http://localhost:8080/RedHelix.svc/v1/hardwareId?$poweredOnState=off
3. To get all devices manufactured by Acme Computer at the Data Center in Hong Kong use the URL
   * http://localhost:8080/RedHelix.svc/v1/hardwareId?$manufacturerName="Acme Computer"$siteName="Hong Kong"
4. To get all power supplys in room DataHall-17
   * http://localhost:8080/RedHelix.svc/v1/hardwareId?$redfishHardwareType="PowerSupply"$roomName="DataHall-17"

RedHelix provides a database contaning multiple servers that implement Redfish and the history for
each server. At present the database is only in limited to what is in memory and when the Java Virtual Machine shuts down all history is destroyed.

#### Discovery of the RESTful interface
Redfish has an optional protocol called "SimpleService Discovery Protocol" (SSDP), that allows discovery of Redfish enabled servers. RedHelix servers
can not be discovered using SSDP.
Prior knowledge of the IP address and TCP port number, and possibly account name and number, are required to access a RedHelix server. The user of
RedHelix can define how Redfish servers are found. It may be a scan of IP address, a fixed set IPs or enabling the use of SSDP. 

#### Graphics User Interface
RedHelix will contain a Graphic User Interface based on [AngularJS](https://angularjs.org). This is under development in the GitHub branch labled
version 0.2.



