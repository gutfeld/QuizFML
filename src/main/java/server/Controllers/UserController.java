package server.Controllers;

import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Login;
import server.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {

    Log log = new Log();
    ArrayList<User> users;
    Gson gson;
    DBWrapper db = new DBWrapper();

    public UserController() {
        this.gson = gson;
    }

    public ArrayList<User> getUsers() {
        log.writeLog(this.getClass().getName(), this, "We are now getting users", 0);
        ArrayList<User> users = db.getUsers();
        return users;
    }

    public User login(String data) throws Exception {
        log.writeLog(this.getClass().getName(), this, "User trying to log in", 2);
        System.out.println(data);
        Login login = null;
        try {
            login = new Gson().fromJson(data, Login.class);
        } catch (Exception e) {
            log.writeLog(this.getClass().getName(), this, "Catching an exception", 1);
            e.printStackTrace();
        }
        System.out.println(login.getUsername());
        User userFound = DBWrapper.authorizeUser(login.getUsername(), login.getPassword());
        return userFound;
    }

    //public User getUser (int userId){
    public User createUser(User user) throws Exception {

        log.writeLog(this.getClass().getName(), this, "We are now creating a user", 0);
        User newUser =  db.createUser(user);
        newUser.setCreatedTime();
        /* String hashedPassword = Digester.hashWIthSalt(u.getPassword()); Her kan der hashes og tilføjes salt til password
        u.setPassword(hashedPassword);
        */
        return newUser;

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

















