package SSHJ;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.File;
import java.io.IOException;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.userauth.keyprovider.KeyProvider;
// --- <<IS-END-IMPORTS>> ---

public final class Services

{
	// ---( internal utility methods )---

	final static Services _instance = new Services();

	static Services _newInstance() { return new Services(); }

	static Services _cast(Object o) { return (Services)o; }

	// ---( server methods )---




	public static final void closeRemoteExecutionClient (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(closeRemoteExecutionClient)>> ---
		// @sigtype java 3.5
		// [i] object:0:required sshClient
		// [o] field:0:required error
		IDataCursor pipelineCursor = pipeline.getCursor();
		SSHClient ssh = (SSHClient) IDataUtil.get( pipelineCursor, "sshClient" );
		
		try {
			if (ssh != null && ssh.isConnected()) {
				ssh.disconnect();
				IDataUtil.remove(pipelineCursor, "sshClient");
			} else {
				IDataUtil.put( pipelineCursor, "error", "No client connection given.");
			}
			pipelineCursor.destroy();
		} catch (Exception e)
		{
			System.out.println(e.toString());
			IDataUtil.put( pipelineCursor, "error", e.toString() );
			IDataUtil.remove(pipelineCursor, "sshClient");
			pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void commandRemoteExecutionSession (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(commandRemoteExecutionSession)>> ---
		// @sigtype java 3.5
		// [i] object:0:required sshClient
		// [i] field:0:required command
		// [i] field:0:required arguments
		// [o] field:0:required output
		// [o] field:0:required error
		IDataCursor pipelineCursor = pipeline.getCursor();
		SSHClient ssh = (SSHClient) IDataUtil.get( pipelineCursor, "sshClient" );
		String	command = IDataUtil.getString( pipelineCursor, "command" );
		String	arguments = IDataUtil.getString( pipelineCursor, "arguments" );
		
		
		try {
			if (ssh != null && ssh.isConnected()) {
				if (command != null && !command.isEmpty()) {
					String commandline = command;
					if (arguments != null && !arguments.isEmpty())
						commandline.concat(" "+arguments);
					Session session = ssh.startSession();
					final Command cmd = session.exec(commandline);
					IDataUtil.put( pipelineCursor, "output", IOUtils.readFully(cmd.getInputStream()).toString());
					session.close();
				} else {
					IDataUtil.put( pipelineCursor, "error", "No input commands given.");
				}	
			} else {
				IDataUtil.put( pipelineCursor, "error", "No client connection given.");
			}
			pipelineCursor.destroy();
		} catch (Exception e)
		{
			System.out.println(e.toString());
			IDataUtil.put( pipelineCursor, "error", e.toString() );
			pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void openRemoteExecutionClient (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(openRemoteExecutionClient)>> ---
		// @sigtype java 3.5
		// [i] field:0:required user
		// [i] field:0:required password
		// [i] field:0:required hostname
		// [i] field:0:required port
		// [o] object:0:required sshClient
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	user = IDataUtil.getString( pipelineCursor, "user" );
		String	password = IDataUtil.getString( pipelineCursor, "password" );
		String	hostname = IDataUtil.getString( pipelineCursor, "hostname" );
		String	port = IDataUtil.getString( pipelineCursor, "port" );
		
		File file = new File(".\\packages\\SSHJ\\config\\.ssh\\known_hosts");
		File privateKey = new File(".\\packages\\SSHJ\\config\\.ssh\\id_rsa");
		
		final SSHClient ssh = new SSHClient();
		
		try {
			ssh.loadKnownHosts(file);
			if (port !=null && !port.isEmpty()) {
				int sshPort = Integer.parseInt(port);
				ssh.connect(hostname, sshPort);
			} else {
				ssh.connect(hostname);
			}
			if (password != null && !password.isEmpty()) {
				ssh.authPassword(user,password);
			} else {
				KeyProvider keys = ssh.loadKeys(privateKey.getPath());
				ssh.authPublickey(user,keys);
			}
			IDataUtil.put( pipelineCursor, "sshClient", ssh);
			pipelineCursor.destroy();
		} catch (Exception e)
		{
			System.out.println(e.toString());
			IDataUtil.put( pipelineCursor, "error", e.toString() );
			pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void remoteExecutionSession (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remoteExecutionSession)>> ---
		// @sigtype java 3.5
		// [i] field:0:required user
		// [i] field:0:required password
		// [i] field:0:required hostname
		// [i] field:0:required port
		// [i] field:0:required command
		// [i] field:0:required arguments
		// [o] field:0:required output
		// [o] field:0:required error
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	user = IDataUtil.getString( pipelineCursor, "user" );
		String	password = IDataUtil.getString( pipelineCursor, "password" );
		String	hostname = IDataUtil.getString( pipelineCursor, "hostname" );
		String	port = IDataUtil.getString( pipelineCursor, "port" );
		String	command = IDataUtil.getString( pipelineCursor, "command" );
		String	arguments = IDataUtil.getString( pipelineCursor, "arguments" );
		
		File file = new File(".\\packages\\SSHJ\\config\\.ssh\\known_hosts");
		File privateKey = new File(".\\packages\\SSHJ\\config\\.ssh\\id_rsa");
		
		final SSHClient ssh = new SSHClient();
		Session session = null;
		
		try {
			ssh.loadKnownHosts(file);
			if (port !=null && !port.isEmpty()) {
				int sshPort = Integer.parseInt(port);
				ssh.connect(hostname, sshPort);
			} else {
				ssh.connect(hostname);
			}
			if (password != null && !password.isEmpty()) {
				ssh.authPassword(user,password);
			} else {
				KeyProvider keys = ssh.loadKeys(privateKey.getPath());
				ssh.authPublickey(user,keys);
			}
			session = ssh.startSession();
			if (command != null && !command.isEmpty()) {
				String commandline = command;
				if (arguments != null && !arguments.isEmpty())
					commandline.concat(" "+arguments);
				final Command cmd = session.exec(commandline);
				IDataUtil.put( pipelineCursor, "output", IOUtils.readFully(cmd.getInputStream()).toString());
			} else {
				IDataUtil.put( pipelineCursor, "error", "No input commands given.");
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
			IDataUtil.put( pipelineCursor, "error", e.toString() );
		}finally {
			try {
				if (session != null) {
					session.close();
				}
				ssh.disconnect();
				pipelineCursor.destroy();
			} catch (IOException e) {
				e.printStackTrace();  
			}		    
		}
		// --- <<IS-END>> ---

                
	}
}

