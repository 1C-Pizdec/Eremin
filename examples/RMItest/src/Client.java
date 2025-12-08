import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry re = LocateRegistry.getRegistry("127.0.0.1", 9001);
		IServer s = (IServer) re.lookup("server");
		s.m();

	}

}
