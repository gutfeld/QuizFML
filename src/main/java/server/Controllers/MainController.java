package server.Controllers;

import server.DBWrapper;
import server.models.User;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class MainController {

    DBWrapper authorizeUser = new DBWrapper();
    Log log = new Log();

    public User login(String username, String password) {
        //User user = authorizeUser.authorizeUser(username, password);

        log.writeLog(this.getClass().getName(), this, "We are now logging in a user", 0);

        //return user;
        return null;
    }
}