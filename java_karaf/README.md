# Tutorials for Apache Karaf
This tutorials is based on [Karaf Tutorials](https://karaf.apache.org/manual/latest/) and 
[Introduction to OSGi](https://www.baeldung.com/osgi).

Apache Karaf is a modern polymorphic application container and it is also defined as a 
framework for OSGi. OSGi stands for **Open Service Gateway Initiative**, which is a 
specification defining a Java-based component system.

In OSGi, a single component is called a bundle. A bundle is a piece of functionality that
has an independent lifecycle, meaning it can be started, stopped and removed independently. 
Usually, a bundle is a **.jar** with a **MANIFEST.MF** containing some OSGi-specific headers.

## Installation & First application
| Karaf Version | Java Version Support |
|--|--|
| 4.4.x | 11+ | 
| 4.3.x | 11+ | 
| 4.2.x | 8/9/10/11 |

For this learning we use 4.2.16, which the latest version supporting java 8. 
To start karaf you can use _bin/karaf start_.

## Step to deploy an application on Karaf
1. BundleActivator is an interface provided by OSGi that has to be implemented by
classes that are entry points for a bundle. 
2. building a bundle -- See pom.xml
3. install a bundle into karaf -- **bundle:install mvn:\<groupId>/\<artifactId>/\<versionId>/**