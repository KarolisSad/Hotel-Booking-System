package mediator;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Get connections from clients and start them as new threads
 *
 * 2020-05-08
 * @author Group5
 */

public class HotelServer implements Runnable{

    private Model model;
    private int PORT = 2917;
    private ServerSocket welcomeSocket;

    /**
     * Constructor initializing instance variables.
     * @param model
     */
    public HotelServer(Model model)
    {
        this.model = model;
    }

    /**
     * When new client trys to connect to the server it creates new Thread
     *  and runs it
     */
    @Override
    public void run() {
        System.out.println("Starting server...");
        try {
            welcomeSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server started.");

        while (true)
        {
            try {
                Socket client = welcomeSocket.accept();
                HotelClientHandler hotelClientHandler = new HotelClientHandler(client, model);
                Thread newClientThread = new Thread(hotelClientHandler);
                newClientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
