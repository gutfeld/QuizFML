package server.endpoints;

import com.google.gson.Gson;
import server.Controllers.UserController;
import server.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/user")


public class UserEndpoint {
    UserController uController = new UserController();

    @GET
    public Response get() {
        ArrayList<User> users = uController.getUsers();

        return Response.status(200).entity(new Gson().toJson(users)).build();

    }

}