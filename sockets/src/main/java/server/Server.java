package main.java.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import main.java.book_service.BookService;
import main.java.book_service.BookServiceInterface;
import main.java.common.CancelOrderCommand;
import main.java.common.NewOrderCommand;
import main.java.common.OrderCommand;

public class Server {
    private int port;

    private ServerSocket serverSocket;

    private BookServiceInterface bookService;


    void registerService(BookServiceInterface bookService) {
        this.bookService = bookService;
    }

    void listenAndServe(int port) throws Exception {
        if (this.bookService == null)
            throw new Exception("dwwk");

        this.port = port;

        this.serverSocket = new ServerSocket(this.port);
        System.out.printf("Сервер слушает порт %d...\n", this.port);

        while (true) {
            new OrderTread(bookService, serverSocket.accept()).start();
        }
    }

    private class OrderTread extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        BookServiceInterface service;

        public OrderTread(BookServiceInterface service, Socket socket) {
            this.service = service;
            this.clientSocket = socket;
        }

        public void run() {
            // try {
            //     out = new PrintWriter(this clientSocket.getOutputStream(), true);
            //     in = new BufferedReader(
            //   new InputStreamReader(clientSocket.getInputStream()));
            
            // String inputLine;
            // while ((inputLine = in.readLine()) != null) {
            //     if (".".equals(inputLine)) {
            //         out.println("bye");
            //         break;
            //     }
            //     out.println(inputLine);
            // }

            // in.close();
            // out.close();
            // clientSocket.close();
            // }

            try {
                this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
                this.in = new BufferedReader(
                       new InputStreamReader(this.clientSocket.getInputStream())
                );

                String inputLine;
                while ((inputLine = this.in.readLine()) != null) {
                    System.out.println(inputLine); // LOG

                    OrderCommand cmd = createCommand(inputLine);

                    cmd.Execute();

                    this.out.println("OK " + inputLine);
                }

            in.close();
            out.close();
            clientSocket.close();

            } catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage());
            }
        }

        private OrderCommand createCommand(String rawCommand) {
            String[] splittedRawCommand = rawCommand.split(",");

            return switch (splittedRawCommand[0]) {
                case "NEW" -> {
                    if (splittedRawCommand[2] == "BUY") {
                        yield new NewOrderCommand(
                            this.service, 
                            NewOrderCommand.Type.BUY, 
                            Integer.parseInt(splittedRawCommand[1]), 
                            MAX_PRIORITY, 
                            Integer.parseInt(splittedRawCommand[1])
                        );
                    } else {
                        yield new NewOrderCommand(
                            this.service, 
                            NewOrderCommand.Type.SELL, 
                            Integer.parseInt(splittedRawCommand[1]), 
                            MAX_PRIORITY, 
                            Integer.parseInt(splittedRawCommand[1])
                        );
                    }
                }

                case "CANSEL" -> new CancelOrderCommand(
                    this.service, Integer.parseInt(splittedRawCommand[1])
                );

                default ->  new CancelOrderCommand(
                    this.service, Integer.parseInt(splittedRawCommand[1])
                );
            };
        }
    }

    public static void main(String[] argv) {
        try {
            Server s = new Server();
            s.registerService(new BookService());

            s.listenAndServe(8080);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
