##RedHelix version 0.1
Version 0.1 is limited to using the Redfish mockup files in [DSP2043_0.99.0](http://www.dmtf.org/sites/default/files/standards/documents/DSP2043_0.99.0a.zip).
and display only the Redfish Chassis information. Redhelix is compact.
Here is the source for reading all the chassis information from a Redfish server. The majority of the source code is error checking and there
are only two lines that do the heavy lifting of reading the Chassis information.
[RedMatrixServerDb.java](../src/main/java/org/redhelix/server/main/RedHxServerConnectionContext.java)
