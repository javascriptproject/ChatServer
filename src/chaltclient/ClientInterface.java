/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chaltclient;

import chatserver.Advertise;
import chatserver.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author abdotalaat
 */
public interface ClientInterface extends Remote{
    


public void userNotification(int id, ClientInterface clientInterface,String status) throws RemoteException;
public void userNotificationLogOut(int id) throws RemoteException;
public void userNotificationstatues(int id,int state) throws RemoteException;
public void  receive(String message) throws RemoteException;
public void  ptpChating(String message) throws RemoteException;
public void   receivePTP(String messsage) throws RemoteException;
public void   sendFile(byte [] data, String senderName,String recieverName) throws RemoteException;
public void   recieveFile(byte [] data, String senderName) throws RemoteException;
public void   receiveAdvertis(Advertise advertise) throws RemoteException;
public void    receivePeertoPeer(String message,int id) throws RemoteException;
public void userNotificationadd(User user, ClientInterface clientInterface, String status) throws RemoteException;
public void notifyChatSessionCreated(int id ,GroupSession groupSession) throws RemoteException;
public void recivegroupChatFromServer(int id,String message) throws RemoteException;
public void leaveGroupt(int userid,int chatgroupid) throws RemoteException;
public void  acceptFriend(String name,String email) throws RemoteException;
public boolean acceptFile(String name,int id,String path,long  lenght,String Fname) throws RemoteException;
public int[] getFile(String path,int count) throws RemoteException;

    public void addUserSToGroupChat(int GroupChatSessionID, HashMap<Integer,ClientInterface> users) throws RemoteException;
    public void removeUsersFromGroupChat(int groupChatSessionId,int UserID) throws RemoteException;


}
