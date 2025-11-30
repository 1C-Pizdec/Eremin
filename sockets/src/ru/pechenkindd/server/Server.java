package ru.pechenkindd.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.pechenkindd.core.*;

public class Server {
    final Logger LOGGER = Logger.getLogger(Server.class.getName());

    // void registerService()

    void listenAndServe(int port) {
        try (ServerSocket sso = new ServerSocket(port)) {
            LOGGER.log(
                Level.INFO, 
                String.format("Сервер слушает порт %d...", port)
            );

            while (true) {
                new ClientThread(sso.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientThread extends Thread implements OrderBookObserver {
        private Socket clientSocket;
        private PrintWriter out;

        ClientThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(this.clientSocket.getInputStream()));

                PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            ) {
                this.out = out;

                OrderBook.getInstance().addObserver(this);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {

                    Command cmd = CommandFactory.createCommand(inputLine);
                    String result = cmd.execute(OrderBook.getInstance());

                    out.println(result);
                }

            } catch (Exception e) {
                LOGGER.warning("Клиент отключился: " + e.getMessage());
                return;
            } finally {

                OrderBook.getInstance().removeObserver(this);

                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onOrderBookUpdate(String snapshot) {
            this.out.println(snapshot);
        }

        @Override
        public void onTrade(String tradeMessage) {
            this.out.println(tradeMessage);
        }
    }

    public static void main(String[] main) {
        Server s = new Server();
        s.listenAndServe(8080);
    }
}
