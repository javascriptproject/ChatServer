/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import chaltclient.ClientInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author abdotalaat
 */
public class FirstConnect implements Serializable{
    
    ArrayList<User> myUser ;
    HashMap<Integer, ClientInterface>  userConnected;
    User user;
    Advertise advertise;
    ArrayList<Message> messages;
    ArrayList<FriendRequests> requestses;

    public ArrayList<FriendRequests> getRequestses() {
        return requestses;
    }

    public void setRequestses(ArrayList<FriendRequests> requestses) {
        this.requestses = requestses;
    }
    
    

    public Advertise getAdvertise() {
        return advertise;
    }

    public void setAdvertise(Advertise advertise) {
        this.advertise = advertise;
    }
    
    
    
    
    public FirstConnect()
    {
        myUser = new ArrayList<>();
        userConnected = new HashMap<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    public ArrayList<User> getMyUser() {
        return myUser;
    }

    public void setMyUser(ArrayList<User> myUser) {
        this.myUser = myUser;
    }

    public HashMap<Integer, ClientInterface> getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(HashMap<Integer, ClientInterface> userConnected) {
        this.userConnected = userConnected;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
    
    
    
}
