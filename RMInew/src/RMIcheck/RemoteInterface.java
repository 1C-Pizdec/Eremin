package RMIcheck;

//RemoteInterface.java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
	String sayHello() throws RemoteException;
	int calculateSum(int a, int b) throws RemoteException;
	String getServerInfo() throws RemoteException;
}