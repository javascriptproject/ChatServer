/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import chatserver.Advertise;
import chatserver.ServerImplementation;
import chatserver.User;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.HomeFrame;
import view.ServerUI;


/**
 *
 * @author abdotalaat
 */
public class ServerController {
    
    ServerImplementation model;
    ServerUI view;
    public  Advertise advertise = new Advertise();

    public Advertise getAdvertise() {
        return advertise;
    }

    public void setAdvertise(Advertise advertise) {
        this.advertise = advertise;
    }
    
    
    

    public ServerImplementation getModel() {
        return model;
    }

    public void setModel(ServerImplementation model) {
        this.model = model;
    }

    public ServerUI getView() {
        return view;
    }

    public void setView(ServerUI view) {
        this.view = view;
    }
    
    
    
    
    
    
    
    
    
    
    public void serverStart()
    {
        try {
            Registry registry = LocateRegistry.createRegistry(5555);
            registry.rebind("chat", model);
        } 
        catch(ExportException es)
        {
            JOptionPane.showMessageDialog(view, "the port taken");
            System.exit(0);
        }
        catch (RemoteException ex) {
            
            
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  
    
    
     //Kareem 5/2
      public ArrayList<User> getAllUsers() throws RemoteException {
        
        return model.getAllUsers();
    }
      
      public String getStatusOfUsers(){
        String status = "";
        try {
            
            status =  model.getStatusReport();
        } catch (RemoteException ex) {

            System.out.println("Problem in receiving status");
        }
        return status;
    }
    
    
    
}
