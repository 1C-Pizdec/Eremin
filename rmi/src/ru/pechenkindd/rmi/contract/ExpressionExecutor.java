package ru.pechenkindd.rmi.contract;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ExpressionExecutor extends Remote {

    Double execStep(OperationDTO operation) throws RemoteException;
}
