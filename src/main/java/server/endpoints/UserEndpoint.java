package server.endpoints;

import com.google.gson.Gson;
import server.DBWrapper;
import server.Controllers.UserController;
import server.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/user")


public class UserEndpoint {

    UserController controller = new UserController();


    @GET
    public Response getUsers() {

        ArrayList<User> users = controller.getUsers();

        return Response.status(200)
                .type("application/json")
                .entity(new Gson().toJson(users))
                .build();

    }

    @GET
    @Path("{id}")

    public Response getUserById(@PathParam("id") int UserId) {

        // User foundUser

        return Response
            .status(200)
            .type("application/json")
            .entity(new Gson().toJson("foundUser"))
            .build();
    }

    @POST
    public Response createUser(String user) throws Exception {
        User u = controller.createUser(user);


        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson(u) )
                .build();
    }



    @Path("/login")
    @POST
    public Response authorizeUser(String data) throws Exception {
        User u = controller.login(data);
        if (u != null) {
            return Response.status(200).entity(new Gson().toJson(u)).build();
        } else {
            return Response.status(400).entity("failure!").build();
        }

    }


}