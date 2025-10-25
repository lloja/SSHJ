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

# SSH Services

The SSHJ.Services:remoteExecutionSession service opens a SSH connnection and session and then issues a command statement before closing both the session and the connection in the same service execution.  

<img width="561" height="32" alt="image" src="https://github.com/user-attachments/assets/e103b9d6-272c-4df9-ae21-513b9884e625" />  

The inputs for this service includes the user name, its password, the remote hostname or address, the SSH port if not using the default port number, a command with its arguments. Notice, no password is required if the public key for the remote server is present in the known_hosts file.  

<img width="1535" height="320" alt="image" src="https://github.com/user-attachments/assets/54193aea-123c-4e58-abf2-00b49e468701" />  


Multiple commands can be executed in the same connection by first opening a connection with the service SSHJ.Services:openRemoteExecutionClient and then issuing multiple times the service SSHJ.Services:commandRemoteExecutionSession and for each command before closing the connection with service SSHJ.Services:closeRemoteExecutionClient.  

<img width="794" height="397" alt="image" src="https://github.com/user-attachments/assets/fd5a605b-636c-4c92-be48-bd4e59323fe5" />  







