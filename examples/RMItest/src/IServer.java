import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	public void m() throws RemoteException;
}
