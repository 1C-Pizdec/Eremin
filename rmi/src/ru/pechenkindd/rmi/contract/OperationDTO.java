package ru.pechenkindd.rmi.contract;

import java.io.Serializable;

// Для того, чтобы передавать кастомные объекты удалённо, они должны быть Serializable
public class OperationDTO implements Serializable {
    public String op;
    public Double a;
    public Double b;

    public OperationDTO(String op, Double a, Double b) {
        this.op = op;
        this.a = a;
        this.b = b;
    }
}
