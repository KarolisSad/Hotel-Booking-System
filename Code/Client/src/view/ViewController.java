package view;

import javafx.scene.layout.Region;
import viewModel.ViewModelFactory;

import java.rmi.RemoteException;

/**
 * A class handling all the occurring views in the system.
 *
 * @author Group 5
 * @version 04/05/2022
 */

public abstract class ViewController {
    private Region root;
    private ViewHandler viewHandler;
    ViewModelFactory viewModelFactory;

    /**
     * A none argument, void method initializing instance variables.
     */
    public abstract void init();


    /**
     * A void method that refers to initializing method in ViewController object.
     *
     * @param viewHandler A ViewHandler object is initializing the viewHandler instance variable.
     * @param root A Region object which is initializing the root instance variable.
     * @param viewModelFactory a ViewModelFactory object is initializing the viewModelFactory instance variable.
     */
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root) throws RemoteException {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        init();
    }

    /**
     * An abstract method that in subclasses calls the clear() method from viewModel.
     */
    public abstract void reset();

    /**
     * A getter method returning the Region object.
     *
     * @return A Region object called root.
     */
    public Region getRoot(){
        return root;
    }

    /**
     * A getter method returning the ViewModelFactory object.
     *
     * @return A ViewModelFactory object called viewModelFactory.
     */
    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }

    /**
     * A getter method returning the ViewHandler object.
     *
     * @return A ViewHandler object called viewHandler.
     */
    public ViewHandler getViewHandler(){
        return viewHandler;
    }
}
