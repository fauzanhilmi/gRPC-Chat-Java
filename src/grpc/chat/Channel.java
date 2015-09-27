/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grpc.chat;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tegar
 */
public class Channel {
    public List<User> activeUser;
    
    private String name;
            
    public void addActiveUser(String user)
    {
        User u = new User(user);
        activeUser.add(u);
    }

    public void removeActiveUser(String user)
    {
        System.out.println(user+" "+activeUser.indexOf(user));
        boolean found = false;
        int i = 0;
        while (!found && i<activeUser.size())
        {
            if (activeUser.get(i).getName()==user)
            {
                found = true;
            }
            else
            {
                i++;
            }
        }
    }

    public Channel(String _name)
    {
        activeUser = new ArrayList<User>();
        name = new String(_name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
