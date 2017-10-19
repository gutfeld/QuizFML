package server.Controllers;

import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Login;
import server.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {

    ArrayList<User> users;
    Gson gson;
    DBWrapper db = new DBWrapper();

    public UserController() {
        this.gson = gson;
    }

    public ArrayList<User> getUsers() {

        ArrayList<User> users = db.getUsers();
        for (int i = 0; i < users.size(); i++) System.out.println(users.get(i));
        return users;
    }

    public User login(String data) throws Exception {
        System.out.println(data);
        Login login = null;
        try {
            login = new Gson().fromJson(data, Login.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(login.getUsername());
        User userFound = DBWrapper.authorizeUser(login.getUsername(), login.getPassword());
        return userFound;
    }

    //public User getUser (int userId){
    public void createUser(String user) throws Exception {
        User newUser = new Gson().fromJson(user, User.class);
        newUser.setCreatedTime();
        /* String hashedPassword = Digester.hashWIthSalt(u.getPassword()); Her kan der hashes og tilføjes salt til password
        u.setPassword(hashedPassword);
        */
        db.createUser(newUser);

    }


    /*public User findById(int id) {

        ArrayList<User> users;
        for (User user; this.users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    } */
}

















