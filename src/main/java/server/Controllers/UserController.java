package server.Controllers;

import com.google.gson.Gson;
import server.DBWrapper;
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
        User user = new Gson().fromJson(data, User.class);
        User userFound = DBWrapper.authorizeUser(user.getUsername(), user.getPassword());
        return userFound;
    }

    //public User getUser (int userId){
    public void createUser(User createUser) throws Exception {

        /* String hashedPassword = Digester.hashWIthSalt(u.getPassword()); Her kan der hashes og tilføjes salt til password
        u.setPassword(hashedPassword);
        */

        db.createUser(createUser);

    }

    public void createAdmin(User createAdmin) throws Exception {

        db.createAdmin(createAdmin);
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

















