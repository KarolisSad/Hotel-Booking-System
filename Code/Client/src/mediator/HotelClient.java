package mediator;

import com.google.gson.Gson;
import model.Guest;
import model.Model;
import model.Room;
import model.RoomType;

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;

/**
 * Sending request to the server and receiving requested infromation
 *
 * 2022-05-08
 * @author Group5
 */

public class HotelClient implements Model {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson json;
    private Model model;
    private String message;

    /**
     *
     * @param model modelManager
     * @throws IOException if Input stream or Output stream is closed while trying to access them
     */

    public HotelClient(Model model) throws IOException {
        this.model = model;
        socket = new Socket("localhost", 2915);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new PrintWriter(socket.getOutputStream()), true);
        json = new Gson();
        message = null;
        startReader();
    }

    /**
     * Creates HotelClientReader object and start separate thread with that object
     */
    private void startReader() {
        HotelClientReader hotelClientReader = new HotelClientReader(this, in);
        Thread thread = new Thread(hotelClientReader);
        thread.start();
    }

    /**
     * When message is received set message instance variable to received message
     *
     * @param message received message from server
     */
    public synchronized void receive(String message) {
        this.message = message;
        notify();
    }

    /**
     * Creates RoomTransfer object, transfer it to Json format and sends it to server
     * while putting itself to wait until message is received from a server
     *
     * @param roomID room unique ID
     * @param type  room type
     * @param numberOfBeds number of beds in a room
     * @return message containing information if room was successfully added
     */
    @Override
    public synchronized RoomTransfer addRoom(String roomID, RoomType type, int numberOfBeds) {
        sendToServerAsJson(new RoomTransfer("addOneRoom", roomID, type, numberOfBeds, null));
        message = null;
        while (message == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return json.fromJson(message, RoomTransfer.class);
    }

    /**
     * Creates RoomTransfer object, transfer it to Json format and sends it to server
     *  while putting itself to wait until message is received from a server
     *
     * @param roomID unique room ID
     * @param type  room type
     * @param nrBeds number of beds in a room
     * @return message containing information if room was successfully edited
     */
    @Override
    public synchronized RoomTransfer editRoomInfo(String roomID, RoomType type, int nrBeds) {
        sendToServerAsJson(new RoomTransfer("edit",roomID,type,nrBeds));
        message = null;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return json.fromJson(message, RoomTransfer.class);
    }

    /**
     * Creates RoomTransfer object, transfer it to Json format and sends it to server
     * while putting itself to wait until message is received from a server
     *
     * @param ID unique room ID
     * @return message containing information if room was successfully removed
     */
    @Override
    public synchronized RoomTransfer removeRoom(String ID) {
        sendToServerAsJson(new RoomTransfer("removeOneRoom", ID, null, null, null));
        message = null;
        while (message == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return json.fromJson(message,RoomTransfer.class);
    }

    /**
     * Creates RoomTransfer object, transfer it to Json format and sends it to server
     * while putting itself to wait until message is received from a server
     *
     * @return arrayList of all rooms
     */
    @Override
    public synchronized RoomTransfer getAllRooms() {
        sendToServerAsJson(new RoomTransfer("getAllRooms"));
        message = null;
        while (message == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      return json.fromJson(message,RoomTransfer.class);
    }

    /**
     * Creates RoomTransfer object, transfer it to Json format and sends it to server
     *  while putting itself to wait until message is received from a server
     *
     * @param startDate startDate in LocalDate Object
     * @param endDate   endDate in LocalDate Object
     * @return  RoomTransfer object which contains available room list
     */
    @Override
    public synchronized RoomTransfer availableRooms(LocalDate startDate, LocalDate endDate) {

        sendToServerAsJson(new RoomTransfer("availableRooms", startDate, endDate));
        message = null;
        while (message == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return json.fromJson(message, RoomTransfer.class);
    }

    /**
     *
     * @param roomId unique room ID
     * @param startDate startDate in LocalDate Object
     * @param endDate   endDate in LocalDate Object
     * @param guest     guest
     * @return  message containing information if booking was successfully created
     */
    @Override
    public synchronized RoomTransfer book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest) {
        sendToServerAsJson(new RoomTransfer("bookOneRoom", roomId, startDate, endDate, guest));
        message = null;
        while (message == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return json.fromJson(message,RoomTransfer.class);
    }


    /**
     * Makes received object into Json format and sends it to a server
     *
     * @param roomTransfer object which is used to transfer information to server
     */
    public synchronized void sendToServerAsJson(RoomTransfer roomTransfer) {
        String jsonString = json.toJson(roomTransfer);
        out.println(jsonString);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(PropertyChangeListener listener) {

    }
}
