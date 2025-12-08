import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject 
					implements IServer {
	private static final long serialVersionUID = 1L;

	protected Server() throws RemoteException {	}

	public void m() throws RemoteException {
		System.out.println("Hey dude!");		
	}
	
	public static void main(String[] args) throws RemoteException {
		System.out.println("Server started...");
		Server s = new Server();
		Registry re = LocateRegistry.createRegistry(9001);
		re.rebind("server", s);

	}



}
