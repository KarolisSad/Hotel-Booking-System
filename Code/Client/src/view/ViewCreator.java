package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public abstract class ViewCreator {

    /**
     * A class creating the controllers.
     *
     * @author Group 5
     * @version 04/05/2022
     */
    private Map<String, ViewController> controllers;

    /**
     * A one argument constructor intializing Map<String, ViewController> object.
     */
    public ViewCreator(){
        controllers = new HashMap<String, ViewController>();
    }

    /**
     * A getter method that gets an existing in the HashMap controller
     * or that calls the loadFromFXML method to get and put it
     * in the HashMap.
     *
     * @return A ViewController object called controller.
     */
    public ViewController getViewController(String id) throws IOException {
        ViewController controller = controllers.get(id);
        if(controller == null){
            controller = loadFromFXML(id);
            controllers.put(id, controller);
        }
        else{
            controller.reset();
        }

        return controller;
    }

    /**
     * A getter method that creates a controller.
     *
     * @return A ViewController object called controller.
     */

    private ViewController loadFromFXML(String txtFile) throws IOException{
        ViewController controller = null;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(txtFile));
            Region root = loader.load();
            controller = loader.getController();
            initViewController(controller, root);

            if (controller == null){
                throw new Exception("controller == null");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return controller;
    }

    /**
     * An abstract void method that refers to initializing method in ViewController object.
     *
     * @param viewController A ViewController object which is being initialized
     * @param root A Region object which sets the Region object in ViewController
     */
    protected abstract void initViewController(ViewController viewController, Region root) throws RemoteException;

}
