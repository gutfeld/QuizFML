package server.Controllers;

import com.google.gson.Gson;
import server.DBWrapper;
import server.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    Gson gson;
    DBWrapper db = new DBWrapper();

    public UserController() {
        this.gson = gson;
    }
public static void main(String []args) {
    UserController uController = new UserController();
    uController.getUsers();
}

    public ArrayList<User> getUsers() {
        ArrayList<User> users = db.getUsers();
        return users;
    }

    public User login(String data) throws Exception {
        User user = new Gson().fromJson(data, User.class);
        User userFound = DBWrapper.authorizeUser(user.getUsername(), user.getPassword());
        return userFound;
    }

    //public User getUser (int userId){

        //User user = db.getUsers(userId);

    //}

    //public boolean deleteUser(int id) throws SQLException {

       // DBWrapper db = new DBWrapper();

        //boolean deleteUser = db.deleteUser(id);



        //return deleteUser;

   // }








}


