/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chaltclient;

import chatserver.User;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author abdotalaat
 */
public class GroupSession  implements Serializable{
    
    
    int id;
    String name;
    ArrayList<User> users = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    
    
    
    
}
