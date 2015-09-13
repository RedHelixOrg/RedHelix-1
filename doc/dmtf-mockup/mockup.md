##RedHelix Mockup Version 0.1
Version 0.1 is limited to using the Redfish mockup files in [DSP2043_0.99.0](http://www.dmtf.org/sites/default/files/standards/documents/DSP2043_0.99.0a.zip).
and display only the Redfish Chassis information. The Redhelix is compact.
Here is the source for reading all the chassis information from a Redfish server. The majority of the source code is error checking and there
are only two lines that do the heavy lifting of reading the Chassis information. In the source code

1. [RedHxChassisPathCollectionReader.java](../../redhx-server-util/src/main/java/org/redhelix/server/main/RedHelixClientReport.java#L112)
2. [RedHxChassisCollectionReader.java](../../redhx-server-util/src/main/java/org/redhelix/server/main/RedHelixClientReport.java#L117)
and to read all the Redfish Computer Systems
1. [RedHxComputerSystemCollectionReader.java](../../redhx-server-util/src/main/java/org/redhelix/server/main/RedHelixClientReport.java#L125)

The rest of the source code is error checking. The output from this program, chassis information only, when used with the DMTF mockup files is

* [Chassis 1](Chassis1.txt)
* [Blade 1](Blade1.txt)
* [Blade 2](Blade2.txt)
* [Enc 1](Enc1.txt)

