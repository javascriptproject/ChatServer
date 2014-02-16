/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import controller.ServerController;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.LoginFrame;

/**
 *
 * @author abdotalaat
 */
public class ChatServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            ServerController controller = new ServerController();
            ServerImplementation model = new ServerImplementation(controller);
            controller.setModel(model);
           LoginFrame loginFrame = new  LoginFrame(controller);
           loginFrame.setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
