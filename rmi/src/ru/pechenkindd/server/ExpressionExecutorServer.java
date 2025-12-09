package ru.pechenkindd.server;

import java.rmi.RemoteException;

import ru.pechenkindd.rmi.contract.ExpressionExecutor;
import ru.pechenkindd.rmi.contract.OperationDTO;

public class ExpressionExecutorServer implements ExpressionExecutor {

    @Override
    public Double execStep(OperationDTO operation) throws RemoteException {
        double A = operation.a;
        double B = operation.b;

        return switch (operation.op) {
            case "add": 
                yield A + B;
            case "sub": 
                yield A - B;
            case "mul": 
                yield A * B;
            case "div": {
                if (B == 0) {
                    throw new RemoteException("Деление на ноль!");
                }
                yield A / B;
            }
            default:
                throw new RemoteException("Неизвестная операция: " + operation.op);
        };
    }
    
}
