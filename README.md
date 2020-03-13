# Securing a Spring Boot Application with TLS

### Configuring a Self-Signed Certificate
We have been developing application with spring boot and able to access the configured unsecured endpoints. 
Most of these applications are running on an insecure HTTP protocol. 
This example enable TLS and let our spring boot application to run on a secured protocol (HTTPS).

In order to do this, we will need to configure a certificate. 

In production-grade applications, we normally use certificates that are issued from renowned Certification 
Authorities (CA) such as Verisign, DigiCert, Entrust and others. 

Certificates issued by renowned CAs ensure that our application is a trusted entity. 
However, as this is a demo application, we will create a Self-Signed Certificate and 
use it in our application.

#### Step 1: Creating the certificate
Java provides the keytool utility to create and manage certificates locally. 
Keytool is available along with other JDK utilities in JDK_HOME/bin directory. 
Let us execute the following keytool command to generate a new certificate:


````
keytool -genkey -keyalg RSA -alias habeebcycle -keystore habeebcycle.jks
 -storepass habeebcycle -validity 365 -keysize 4096 -storetype pkcs12
````
We are generating a certificate along with the following tasks in the above command:

``Using the RSA algorithm`` 

``Providing an alias name as medium``

``Naming the Keystore file s medium.jks``

``A Validity period of one year``

Once you hit this command, it will prompt for a few details and the certificate will be created. 
Copy this certificate in the ``src/main/resources`` directory so that it will be available at classpath.

#### Step 2: Configuring the application with TLS
Now that we are done with the certificate generation, let us add the following information in the 
Spring boot ``application.yaml`` file to enable TLS:

````yaml
server:
  port: 8443
  ssl:
    key-alias: habeebcycle
    key-password: habeebcycle
    key-store: classpath:habeebcycle.jks
    key-store-password: habeebcycle
    key-store-type: pkcs12
````

#### Step 3: Testing the API using https://127.0.0.1:8443
Once we have added the TLS configuration in Spring boot resource folder,
 the application is ready to run in HTTPS. Open Postman/Browser and hit the below URL:
``https://127.0.0.1:8443/v1/books/1``

Many HTTP clients have a check on the Self-Signed certificates and it does not allow accessing 
resources over Self-Signed certificates HTTPS. 
For example, ***curl*** returns the following error. 
````
curl: (77) schannel: next InitializeSecurityContext failed: SEC_E_UNTRUSTED_ROOT (0x80090325) 
- The certificate chain was issued by an authority that is not trusted.
````

