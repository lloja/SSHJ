# Package Name : SSHJ
This is a webMethods package and requires a webMethods Integration Server to host it. Package versioning and configuration can be found in the package [manifest](./SSHJ/manifest.v3) file. Service and API documentation is available on the package's home page http://&lt;server&gt;:&lt;port&gt;/&lt;packagename>.

This package wraps and includes the SSHJ implementation and libraries from https://github.com/hierynomus/sshj. 

webMethods Integration Server doesn't provide an SSH client capability hence the reason for this package.
The package includes just set of few services and it doesn't attempt to map or explore all the options of SSHJ libraries as webMethods services.

Download this repository as a zip file and install the zip file as an Integration Server package.

After the package installation.
Generate a SSH public/private key par using for example ssh-keygen.

Copy and paste the private key into the file \<Installation Directory\>\IntegrationServer\instances\default\packages\SSHJ\config\.ssh\id_rsa .

The public key should go into the file \<Installation Directory\>\IntegrationServer\instances\default\packages\SSHJ\config\.ssh\id_rsa.pub .

Add all the public keys and one per line for each remote server that you are connecting to and onto the file \<Installation Directory\>\IntegrationServer\instances\default\packages\SSHJ\config\.ssh\known_hosts .


