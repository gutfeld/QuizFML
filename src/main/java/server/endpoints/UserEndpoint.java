package server.endpoints;

import com.google.gson.Gson;
import server.Controllers.Log;
import server.DBWrapper;
import server.Controllers.UserController;
import server.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/user")


public class UserEndpoint {

    Log log = new Log();
    UserController controller = new UserController();


    @GET
    public Response getUsers() {

        log.writeLog(this.getClass().getName(), this, "We are now getting users", 2);

        ArrayList<User> users = controller.getUsers();

        return Response.status(200)
                .type("application/json")
                .entity(new Gson().toJson(users))
                .build();

    }

    @GET
    @Path("{id}")

    public Response getUserById(@PathParam("id") int UserId) {

        log.writeLog(this.getClass().getName(), this, "We are now getting user by Id", 2);

        // User foundUser

        return Response
            .status(200)
            .type("application/json")
            .entity(new Gson().toJson("foundUser"))
            .build();
    }

    @POST
    public Response createUser(String user) throws Exception {

        log.writeLog(this.getClass().getName(), this, "We are now creating user", 2);

        controller.createUser(user);


        return Response
                .status(200)
                .type("application/json")
                .entity("{\"userCreated\":\"true\"}")
                .build();
    }



    @Path("/login")
    @POST
    public Response authorizeUser(String data) throws Exception {

        log.writeLog(this.getClass().getName(), this, "We are now authorizing user for login", 2);

        User u = controller.login(data);
        if (u != null) {
            log.writeLog(this.getClass().getName(), this, "User logged in", 2);
            return Response.status(200).entity(new Gson().toJson(u)).build();
        } else {
            log.writeLog(this.getClass().getName(), this, "User not logged in because of failure", 1);
            return Response.status(400).entity("failure!").build();
        }

    }


}