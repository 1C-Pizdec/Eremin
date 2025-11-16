package RMIcheck;

//RemoteImplementation.java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class RemoteImplementation extends UnicastRemoteObject implements RemoteInterface {
 
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


 protected RemoteImplementation() throws RemoteException {
     super();
 }
 
 @Override
 public String sayHello() throws RemoteException {
     return "Привет от RMI сервера! Готов выполнять вычисления!";
 }
 
 @Override
 public int calculateSum(int a, int b) throws RemoteException {
   
     int result = performAddition(a, b);
     System.out.println("Сервер: Вычислена сумма " + a + " + " + b + " = " + result);
     return result;
 }
 
 @Override
 public String getServerInfo() throws RemoteException {
     return "Калькулятор сервера запущен: " + new Date();
 }
 

 private int performAddition(int a, int b) {
     System.out.println("Сервер: Выполняется сложение чисел " + a + " и " + b);
     
    
     try {
         Thread.sleep(1000);
     } catch (InterruptedException e) {
         System.out.println("Сервер: Прервано выполнение сложения");
     }
     
     int result = a + b;
     System.out.println("Сервер: Сложение завершено, результат: " + result);
     return result;
 }
}
