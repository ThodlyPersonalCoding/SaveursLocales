package com.example.thodlydugue.saveurslocales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Users implements Serializable {
    private String email;
    private String name;
    private String lastlogin;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }
    public static ArrayList<Users> fromListMap(List<Map> map) {

        ArrayList<Users> users = new ArrayList();

        for(int i = 0; i < map.size(); i++) {
            Users u = new Users();
            u.setEmail((String) map.get(i).get("email"));
            u.setName((String) map.get(i).get("name"));
            u.setLastlogin((String) map.get(i).get("lastlogin"));

            users.add(u);
        }
        return users;
    }

}
