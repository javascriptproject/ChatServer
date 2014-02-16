/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import chatserver.FriendRequests;
import chatserver.Message;
import chatserver.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author abdotalaat
 */
public class DataBaseOpeartions {

    ResultSet user, contact;
    int insert, delete, update;
    Statement stmt;
    Connection con;

    public void getConnection() {
        try {
            DriverManager.registerDriver(new OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "admin2", "java");
            System.out.println("connect");
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public DataBaseOpeartions (){
       
        
    }

//this method return the details of user when this user founded in database \
//and return null if this user not found in the database
    public User checkUser(String email, String pass) {
        try {
            String queryString = "select * from users where user_email like '" + email + "' and user_password like '" + pass + "'";
            ResultSet info = stmt.executeQuery(queryString);
            User user = new User();
            while (info.next()) {
                user.setId(info.getInt(1));
                user.setLName(info.getString(2));
                user.setfName(info.getString(3));
                user.setEmail(info.getString(4));
                user.setPassword(info.getString(5));
                user.setBOD(info.getString(6));
                user.setAge(info.getInt(7));
                user.setGendar(info.getString(8));
                user.setCountry(info.getString(9));
                user.setStatus(info.getString(10));
                user.setOnlinestatus(info.getInt(11));
                System.out.println("goo");
                return user;
                
            }
        } catch (SQLException ex) {

            return null;
        }
        return null;

    }
    
    
    public ArrayList<User> getMyFrined(int id)
    {
        ArrayList<User> friends = new ArrayList<>();
        try {
            String query = "select USERS.* from USERS where USERS.USER_ID in(select CONTACT_LIST.FRIEND_ID from CONTACT_LIST where CONTACT_LIST.USER_ID="+id+")";
            ResultSet rs = stmt.executeQuery(query);
          
            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setLName(rs.getString(2));
                user.setfName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setBOD(rs.getString(6));
                user.setAge(rs.getInt(7));
                user.setGendar(rs.getString(8));
                user.setCountry(rs.getString(9));
                user.setStatus(rs.getString(10));
                user.setOnlinestatus(rs.getInt(11));
                System.out.println("kareem");
                friends.add(user);
                
            }
             String query1 = "select USERS.* from USERS where USERS.USER_ID in(select CONTACT_LIST.USER_ID from CONTACT_LIST where FRIEND_ID="+id+")";
           ResultSet rs1 = stmt.executeQuery(query1);
            while (rs1.next()) {
                User user = new User();
                user.setId(rs1.getInt(1));
                user.setLName(rs1.getString(2));
                user.setfName(rs1.getString(3));
                user.setEmail(rs1.getString(4));
                user.setPassword(rs1.getString(5));
                user.setBOD(rs1.getString(6));
                user.setAge(rs1.getInt(7));
                user.setGendar(rs1.getString(8));
                user.setCountry(rs1.getString(9));
                user.setStatus(rs1.getString(10));
                user.setOnlinestatus(rs1.getInt(11));
                System.out.println("lamia");
                friends.add(user);
                
            }
            
//            
//            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return  friends;
    }
    
    
    
    
    
    
    
    
 public void changeStatues(int id,String status)   
 {
        try {
            String query = "update users set status ='" + status + "'where user_id =" + id + "";
            stmt.executeUpdate(query);
            System.out.println(update + "updated");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
        }
     
 }
    
  
 
 public void changeonlineStatues(int id,int status)   
 {
        try {
            String query = "update users set ONLINESTATE =" + status + "where user_id =" + id + "";
            stmt.executeUpdate(query);
            System.out.println(update + "updated");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
        }
     
 }
 
 
 
 public void insertUser(String lname, String fname, String bd, String country, String email, String gender, int age, String pass) {

        try {
            String queryString1 = "insert into users (user_id , user_lname , user_fname , user_email , user_bd , user_password , user_age , user_gender , user_country,STATUS,ONLINESTATE ) values (id_seq.nextval " + ",'" + lname + "','" + fname + "','" + email + "','" + bd + "','" + pass + "'," + age+ ",'" + gender + "','" + country + "','offline',1)";
            insert = stmt.executeUpdate(queryString1);
            System.out.println(insert + " inserted");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
 
 //Kareem ....
    
        public boolean isUser(String email) {
        try {
            String queryString = "select USER_EMAIL from users where user_email = '" + email + "'";
            ResultSet info = stmt.executeQuery(queryString);
            while (info.next()) {
                //JOptionPane.showMessageDialog(null, "Founded");
                System.out.println(info.getString("user_email"));
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in isUser Method");
            //return null;
        }
        return false;
    }
        
        
        
        public boolean isFriend(int id, int freindID) {
        try {
            String queryString = "select Friend_id from contact_list where user_id = " + id;
            System.out.println(queryString);
            ResultSet info = stmt.executeQuery(queryString);
            System.out.println("hereeeeeeeeeeeee");
            while (info.next()) {
                //JOptionPane.showMessageDialog(null, "Founded");
                
              //  System.out.println(info.getString("userId"));
                if(info.getInt(1) == freindID)
                    return true;
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in isUser Method");
            //return null;
        }
        return false;
    }
    
    
        
            public boolean addList(int user_Id, String friend_Email) {
             ResultSet list1, list2;
             String userId ="", friendId="" ;
             int friend;
            try {
           /* String queryString = "select user_id from users where user_email like '" + user_email + "'";
            list1 = stmt.executeQuery(queryString);
            while (list1.next()) {
                userId = list1.getString(1);
            }*/
            String queryString1 = "select user_id from users where user_email = '" + friend_Email + "'";
            list2 = stmt.executeQuery(queryString1);
            while (list2.next()) {
                friendId = list2.getString(1);
            }
                System.out.println(friendId);
            String queryString2 = "insert into contact_list (user_id , friend_id ) values (" + user_Id + "," + friendId + ")";
                System.out.println(queryString2);
            friend = stmt.executeUpdate(queryString2);
            System.out.println(friend + " inserted");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("addList");
        }
            return false;
    }
            
            
            
             public User getUser(String email) {
        try {
            String queryString = "select * from users where user_email = '" + email + "'";
            ResultSet info = stmt.executeQuery(queryString);
            User user = new User();
            while (info.next()) {
                user.setId(info.getInt(1));
                user.setLName(info.getString(2));
                user.setfName(info.getString(3));
                user.setEmail(info.getString(4));
                user.setPassword(info.getString(5));
                user.setBOD(info.getString(6));
                user.setAge(info.getInt(7));
                user.setGendar(info.getString(8));
                user.setCountry(info.getString(9));
                user.setStatus(info.getString(10));
                System.out.println("goo");
                return user;
                
            }
        } catch (SQLException ex) {

            return null;
        }
        return null;

    }
    
             
             public User getUser(int id) {
        try {
            String queryString = "select * from users where user_id ="+id;
            ResultSet info = stmt.executeQuery(queryString);
            User user = new User();
            while (info.next()) {
                user.setId(info.getInt(1));
                user.setLName(info.getString(2));
                user.setfName(info.getString(3));
                user.setEmail(info.getString(4));
                user.setPassword(info.getString(5));
                user.setBOD(info.getString(6));
                user.setAge(info.getInt(7));
                user.setGendar(info.getString(8));
                user.setCountry(info.getString(9));
                user.setStatus(info.getString(10));
                System.out.println("goo");
                return user;
                
            }
        } catch (SQLException ex) {

            return null;
        }
        return null;

    }
             
    
    
             
             
 public void sendofflinemessage(int from, int to, String message)
 {
        try {
            String query = "insert INTO MESSAGE VALUES(id_seq.nextval,"+from+","+to+",'"+message+"')";
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
     
 
 public ArrayList<Message> getOfflineMessage(int id)
 {
     
     ArrayList<Message> messages = new ArrayList<>();
        try {
            String query = "select SENDER_ID,CONTENT from MESSAGE WHERE RECEIVER_ID ="+id+"";
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next())
            {
                Message message = new Message();
                message.setFrom(resultSet.getInt(1));
                message.setContent(resultSet.getString(2));
                messages.add(message);
                
            }
            
           deleteOFFlineMessage(id);
              return messages;
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
            return messages;
        }
     
 }
             
    
 public void deleteOFFlineMessage(int id)
 {
        try {
            String query = "delete from  MESSAGE WHERE RECEIVER_ID = "+id+"";
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
        }
     
 }
 
 
 public void addRequestFriend(int id,String name,String email)
 {
        try {
            String query = "insert INTO FRIENDREQUEST VALUES(id_seq.nextval,"+id+",'"+name+"','"+email+"')";
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
    
 
 public ArrayList<FriendRequests> getfrFriendRequestses(int id) throws SQLException
 {
     ArrayList<FriendRequests> requestses = new ArrayList<>();
     String query = "select * from ADMIN2.FRIENDREQUEST WHERE ID="+id+"";
     ResultSet resultSet = stmt.executeQuery(query);
     
     while(resultSet.next())
     {
         FriendRequests request = new FriendRequests();
         request.setName(resultSet.getString(3));
         request.setEmail(resultSet.getString(4));
         requestses.add(request);
     }
     deleteFrined(id);
     return requestses;
     
 }
 
 
 
 
 
 public void deleteFrined(int id)
 {
        try {
            String query ="DELETE FROM FRIENDREQUEST WHERE ID="+id+"";
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
 
 
 
 
 
 // Kareem 5/2
            public ArrayList<User> getAllUsers(){
                
                ArrayList<User> allUseres = new ArrayList<User>();
                try {
                    String queryString = "select * from users";
                    ResultSet info = stmt.executeQuery(queryString);
                   // User user = new User();
                    while (info.next()) {
                         User user = new User();
                        user.setId(info.getInt(1));
                        user.setLName(info.getString(2));
                      //  System.out.println("I'm "+info.getString(2));
                        user.setfName(info.getString(3));
                        user.setEmail(info.getString(4));
                        user.setPassword(info.getString(5));
                        user.setBOD(info.getString(6));
                        user.setAge(info.getInt(7));
                        user.setGendar(info.getString(8));
                        user.setCountry(info.getString(9));
                        user.setStatus(info.getString(10));
                       //System.out.println("goo");
                      
                       allUseres.add(user);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error in getting all users");
                    return null;
                }
                for (User user1 : allUseres) {
                     System.out.println(user1.getfName()+" "+user1.getLName());

                }
                    return allUseres;
                
            }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

            
            
            
            
            
            public String getAllUsersStatus() {
        String status = new String();
        int online = 0;                                      
        int offline = 0;
        int away = 0;
        int busy = 0;
        try {
                    String queryString = "select onlinestate from users Where status = 'online'";
                    ResultSet info = stmt.executeQuery(queryString);
                  

                   // User user = new User();
                    while (info.next()) {
                         if(info.getInt(1) == 1)
                             online++;
                         else if(info.getInt(1) == 2)
                             away++;
                         else if(info.getInt(1) == 3)
                             busy++;
                         else if(info.getInt(1) == 4)
                             offline++;
                      
                      
                    }
                } catch (SQLException ex) {
                    System.out.println("Error in getting all users");
                    return null;
                }
        status = "Online Users : " + online + "\n"+"Away Users : " + away + "\n"+"Busy Users : " + busy + "\n"+"Invisiable Users : " + offline ;
        return status;
    }
                
            
            
            
            
            
            
            
    public static void main(String[] args) {
        try {
            DataBaseOpeartions dbo =new DataBaseOpeartions();
            dbo.getConnection();        
            System.out.println( dbo.getfrFriendRequestses(12).size());
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOpeartions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
        
        
       

    }
}
