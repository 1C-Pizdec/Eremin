package main.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    private String address;
    private int port;

    private Socket clientSocket;

    private PrintWriter out;
    private BufferedReader in;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException {
        // TODO connect to server
        
        this.clientSocket = new Socket(this.address, this.port);

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] main) {
        Client client = new Client("localhost", 8080);
        try {
            client.connect();
            String resp = client.sendMessage("NEW,123,BUY,150.50,100");
            System.out.println("Res: " + resp);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return;
        }
    }
}
