package server.Controllers;

import server.DBWrapper;
import server.models.User;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class MainController {

    DBWrapper authorizeUser = new DBWrapper();
    public User login(String username, String password) {
        //User user = authorizeUser.authorizeUser(username, password);

        //return user;
        return null;
    }
}
