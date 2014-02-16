/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import chaltclient.ClientInterface;
import controller.ServerController;
import database.DataBaseOpeartions;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author abdotalaat
 */
public class ServerImplementation extends UnicastRemoteObject implements ServerInt {

    ServerController controller;
    database.DataBaseOpeartions dataBaseOpeartions;
    // for testing
    //this hashmap to know the state of user offline or online
    //this hash map to know how connect to the server
    HashMap<Integer, ClientInterface> connectedUsers = new HashMap<>();
    ArrayList<User> users = new ArrayList<>();

    public ServerImplementation(ServerController controller) throws RemoteException {
        this.controller = controller;
        dataBaseOpeartions = new DataBaseOpeartions();
        dataBaseOpeartions.getConnection();
    }

    @Override
    public FirstConnect login(String eMail, String passWord, ClientInterface client) throws RemoteException {


        User user = dataBaseOpeartions.checkUser(eMail, passWord);

        FirstConnect firstConnect = new FirstConnect();
        if (user != null) {
            try {
                if(!users.contains(user))
                users.add(user);
                dataBaseOpeartions.changeStatues(user.getId(), "online");
                user.setStatus("online");
                connectedUsers.put(user.getId(), client);
                ArrayList<User> myFreiends = getContacts(user.getId());
                HashMap<Integer, ClientInterface> myOnlineUser = new HashMap<>();
                for (User user1 : myFreiends) {

                    if (connectedUsers.containsKey(user1.getId())) {
                        myOnlineUser.put(user1.getId(), connectedUsers.get(user1.getId()));
                    }

                }

                Iterator onlineuser = myOnlineUser.keySet().iterator();
                while (onlineuser.hasNext()) {
                    Object object = onlineuser.next();
                    ClientInterface clients = myOnlineUser.get(object);
                    clients.userNotification(user.id, client, user.getStatus()); //get online statues away buzy 

                }

                firstConnect.setRequestses(dataBaseOpeartions.getfrFriendRequestses(user.id));
                firstConnect.userConnected = myOnlineUser;
                firstConnect.myUser = myFreiends;
                firstConnect.user = user;
                firstConnect.advertise = controller.advertise;
                firstConnect.messages = dataBaseOpeartions.getOfflineMessage(user.id);
            } catch (SQLException ex) {
                Logger.getLogger(ServerImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            return null;
        }

        //  JOptionPane.showMessageDialog(null, "abdo");
        return firstConnect;

    }

    @Override
    public void logout(int id) throws RemoteException {

        dataBaseOpeartions.changeStatues(id, "offline");//online statues
        connectedUsers.remove(id);
        ArrayList<User> myFreiends = getContacts(id);
        HashMap<Integer, ClientInterface> myOnlineUser = new HashMap<>();

        for (User user1 : myFreiends) {

            if (connectedUsers.containsKey(user1.getId())) {
                myOnlineUser.put(user1.getId(), connectedUsers.get(user1.getId()));
            }

        }

        Iterator onlineuser = myOnlineUser.keySet().iterator();
        while (onlineuser.hasNext()) {
            Object object = onlineuser.next();
            ClientInterface clients = myOnlineUser.get(object);
            clients.userNotificationLogOut(id);

        }



    }

    @Override
    public boolean Register(User user) throws RemoteException {
        boolean isUser = false;
        isUser = dataBaseOpeartions.isUser(user.email);
        System.out.println("after quiery " + isUser);
        if (isUser) {
            System.out.println("Register : " + isUser);
            return isUser;
        } else {
            dataBaseOpeartions.insertUser(user.LName, user.fName, user.BOD, user.Country, user.email, user.gendar, user.age, user.password);
            return isUser;
        }
    }

    //return all my friends
    @Override
    public ArrayList<User> getContacts(int id) throws RemoteException {

        return dataBaseOpeartions.getMyFrined(id);
    }

    @Override
    public void changeStatus(String eMail, int status) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//karemm

    @Override
    public boolean addContact(int user_Id, String eMail) throws RemoteException {
        boolean done = dataBaseOpeartions.addList(user_Id, eMail);




        User user = dataBaseOpeartions.getUser(eMail);
        User user1 = dataBaseOpeartions.getUser(user_Id);
        
        

            
            ClientInterface ci = connectedUsers.get(user.getId());

            ClientInterface me = connectedUsers.get(user_Id);
            if(connectedUsers.containsKey(user.getId()))
            ci.userNotificationadd(user1, me, user1.getStatus());
            if(connectedUsers.containsKey(user_Id))
            me.userNotificationadd(user, ci, user.getStatus());



        return done;
    }

    @Override
    public void removeContact(String eMail) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void groupChating(String msg) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientInterface getUser(String name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fileTransfer(byte[] file, String senderName, String recieverName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeOnlineStatus(int id, int status) throws RemoteException {
        dataBaseOpeartions.changeonlineStatues(id, status);

        ArrayList<User> myFreiends = getContacts(id);
        HashMap<Integer, ClientInterface> myOnlineUser = new HashMap<>();

        for (User user1 : myFreiends) {

            if (connectedUsers.containsKey(user1.getId())) {
                myOnlineUser.put(user1.getId(), connectedUsers.get(user1.getId()));
            }

        }

        Iterator onlineuser = myOnlineUser.keySet().iterator();
        while (onlineuser.hasNext()) {
            Object object = onlineuser.next();
            ClientInterface clients = myOnlineUser.get(object);
            clients.userNotificationstatues(id, status);

        }



    }

    public void sentAdvertise(Advertise advertise) {
        Iterator it = connectedUsers.keySet().iterator();
        while (it.hasNext()) {
            try {
                Object object = it.next();
                ClientInterface client = connectedUsers.get(object);
                client.receiveAdvertis(advertise);
            } catch (RemoteException ex) {
                Logger.getLogger(ServerImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void sendofflinemessage(int from, int to, String message) throws RemoteException {

       dataBaseOpeartions.sendofflinemessage(from, to, message);
        
        System.out.println(message);
    }

    @Override
    public void receivechatfromGroupingChatt(int id, int groupid, String message) throws RemoteException {
        ClientInterface ci = connectedUsers.get(id);
        ci.recivegroupChatFromServer(groupid, message);
    }
    int chatid = 0;

    @Override
    public int getChatId() throws RemoteException {
        chatid++;
        return chatid;
    }

    @Override
    public void leaveGroupt(int me, int userid, int chatgroupid) throws RemoteException {
        ClientInterface ci = connectedUsers.get(userid);
        ci.leaveGroupt(me, chatgroupid);
    }

    @Override
    public boolean checkAddContact(int user_Id, String eMail) throws RemoteException {
        
        User user = dataBaseOpeartions.getUser(eMail);
        User user1 = dataBaseOpeartions.getUser(user_Id);
        
        if(user != null)
        {
        if(connectedUsers.containsKey(user.getId()))
        {
            System.out.println("abdosooasosoos");
            ClientInterface ci = connectedUsers.get(user.getId());
            ci.acceptFriend(user1.getfName(), user1.getEmail());
            return true;
            
        }
        else
        {
            System.out.println("abdo offline");
            dataBaseOpeartions.addRequestFriend(user.getId(), user1.getfName(), user1.getEmail());
            return true;
        }
        }
        else
        {
            return false;
        }
       
        
    }
    
    
    // Kareem 5/2
    @Override
    public ArrayList<User> getAllUsers() throws RemoteException {
        
        return dataBaseOpeartions.getAllUsers();
    }
    
    @Override
    public String getStatusReport() throws RemoteException {
        
        return dataBaseOpeartions.getAllUsersStatus();
    }
    
}
