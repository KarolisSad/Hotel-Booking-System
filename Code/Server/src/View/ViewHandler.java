package View;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class handling all the occurring views in the system.
 *
 * @author Group 5
 * @version 04/05/2022
 */

public class ViewHandler extends ViewCreator{

    private Scene currentScene;
    private Stage primaryStage;
    private ViewModelFactory viewModelFactory;

    /**
     * A one argument constructor intializing ViewModelFactory object.
     *
     * @param viewModelFactory A ViewModelFactory object which is used to set ViewHandler's viewModelFactory instance variable.
     */
    public ViewHandler(ViewModelFactory viewModelFactory){
        this.viewModelFactory = viewModelFactory;
    }

    /**
     * A void method starting the system and setting the primary stage variable to given primaryStage,
     * setting a currentScene variable and calling the openView(String id) method.
     * @param primaryStage a Stage object which is used to set ViewHandler's primaryStage variable.
     */
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.currentScene = new Scene(new Region());

        // TODO Delete and un-comment!
        openView("RoomListView.fxml");
        //openView("ReservationView.fxml");

    }

    /**
     * A void method searching for a controller with a
     *  with the given id and opening the view.
     *
     * @param id a String object used to create or find a specific ViewController object.
     */
    public void openView(String id) throws IOException {
        Region root = getViewController(id).getRoot();

        currentScene.setRoot(root);
        String title = "";
        if(root.getUserData() != null){
            title += root.getUserData();
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }


    /**
     * A protected, void method that refers to initializing method in ViewController object.
     *
     * @param viewController A ViewController object which is being initialized
     * @param root A Region object which sets the Region object in ViewController
     */
    @Override
    protected void initViewController(ViewController viewController, Region root) throws RemoteException {
        viewController.init(this, viewModelFactory, root);
    }

}
