
This folder contains the XKMS (XML Key Management Service) implementation of 
Apache CXF. It contains:


xkms-client - The XKMS client and invoker implementations
xkms-common - Common functionality, XML schemas, generated code
xkms-service - The XKMS core service implementation
xkms-x509-handlers - The implementation of pluggable commands for X509 keys.
xkms-features - Karaf features for XKMS client and service
xkms-itests - Integration tests
xkms-osgi - OSGi blueprint configuration for OSGi deployment
xkms-war - Web spring configuration for Web depoyment

Installation
------------

features:addurl mvn:org.apache.cxf.services.xkms/cxf-services-xkms-features/2.7.7-SNAPSHOT/xml
features:install cxf-xkms-service cxf-xkms-client
