package chatserver;


import chaltclient.ClientInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
ServerInt is an interface that defines all processes will be done
in the server side
*/
public interface ServerInt extends Remote{
    
    //Login method resposible for login process for any user
    FirstConnect login(String eMail, String passWord,ClientInterface client) throws RemoteException;
    
    //Logout method resposible for logout process for any user
    void logout(int id) throws RemoteException;
    
    //Register method resposible for registration process for new users
      boolean Register(User user) throws RemoteException;
    
    
    //getContacts method for retrieving the contact list for any user
    ArrayList<User> getContacts(int id) throws RemoteException;
    
    //changeStatus method for changing the status of the user either online; away; busy or offline
    void changeStatus(String eMail, int status) throws RemoteException;
    void changeOnlineStatus(int id, int status) throws RemoteException;
    
    //addContact method will add new contact for specific user
  boolean addContact(int id, String eMail) throws RemoteException;
    
    //removeContact method will remove a user form specific users' conatct list
    void removeContact(String eMail) throws RemoteException;
    
    //groupChating method resposible for sending a messages for all online users
    void groupChating(String msg) throws RemoteException;
    
    //getUser method resposible for retrieving spcific user
    ClientInterface getUser(String name) throws RemoteException;  
    
    //FileTransfer method resposible for sending files between users
    void fileTransfer(byte[] file, String senderName, String recieverName) throws RemoteException;
            
     public void sendofflinemessage(int from ,int to,String message) throws RemoteException;
     
     
     ////////////////////////////////////////////////////////////////
     public void receivechatfromGroupingChatt(int id,int groupid,String message) throws RemoteException;
     public int getChatId() throws RemoteException;
     public void leaveGroupt(int me,int userid,int chatgroupid) throws RemoteException;
     
     public boolean checkAddContact(int user_Id, String eMail) throws RemoteException;
     public ArrayList<User> getAllUsers() throws RemoteException;
      public String getStatusReport() throws RemoteException;
     
     
     
}
