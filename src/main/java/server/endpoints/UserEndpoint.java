package server.endpoints;

import com.google.gson.Gson;
import server.DBWrapper;
import server.Controllers.UserController;
import server.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/User")
public class UserEndpoint {
    UserController uController = new UserController();

    @GET
    public Response get() {
        return Response.status(200).entity("User").build();
    }
        ArrayList<User> users = uController.getUsers();

        return Response.status(200).entity(new Gson().toJson(users)).build();

    @Path("/login/{username}/{password}")
    @POST
    public Response authorizeUser(@PathParam("username") String username, @PathParam("password") String password) throws Exception {
        System.out.printf("IS login hit?");
        User user = DBWrapper.authorizeUser(username, password);
        System.out.println("test1");
        System.out.println("userID: "+ user.getId());
        if (user != null) {
            return Response.status(200).entity("SUCESS!").build();
        } else {
            return Response.status(400).entity("Failure :-(").build();
        }
    }


}