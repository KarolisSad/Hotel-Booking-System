package mediator;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Receiving messages from server and sending them to the client
 * 2022-05-08
 * @author Group 5
 */

public class HotelClientReader implements Runnable{

    private BufferedReader in;
    private HotelClient hotelClient;

    /**
     * Initializing client and BufferedReader objects
     *
     * @param client client
     * @param in inputStream connection to a server
     */
    public HotelClientReader(HotelClient client, BufferedReader in)
    {
        this.hotelClient = client;
        this.in = in;
    }

    /**
     * Receives server messages and sends them to the client
     */
    @Override
    public void run() {
        while (true)
        {
            try {
                hotelClient.receive(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
