vbox-browser
============

Simple web based interface for browsing, searching, and doing simple tasks across multiple <a href="https://www.virtualbox.org/">Oracle Virtualbox</a> hosts.

Project consists of HTML5 client and JavaEE7 REST service.

****Client*****

A very simple Bootstrap & Angular HTML5 UI.  Client allows for searching across hosts, pausing vms, and starting VMs.  


****Server*****

JavaEE7 Jersey REST service that wraps the VirtualBox vboxjws Java API.  As a pratice, I am not a fan of wrapping a webservice with another web service...  The services VirtualBox provides are very complete and SOAP/Axis based.  Axis does provide the ability to expose services as both SOAP and REST, but IMO Axis URL conventions are not ideal.

If you are looking for a full featured interface have a look at <a href="http://sourceforge.net/projects/phpvirtualbox/" >PHPVirtualBox</a>


Fetures looking to implement:
  - automating the shutdown -> clone -> startup process for Linux and Windows hosts.
  - browse clones on local/mounted file system.
  - ability to "move" a clone to different host.
