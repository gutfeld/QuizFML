package server.endpoints;

import com.google.gson.Gson;
import server.Controllers.UserController;
import server.models.User;
import server.security.XORController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
        User createUser = controller.createUser(user);
        String output = new Gson().toJson(createUser);
        String encryptedOutput = XORController.encryptDecryptXOR(output);
        encryptedOutput = new Gson().toJson(encryptedOutput);

        if(createUser != null) {
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(encryptedOutput)
                    .build();
        } else {
            return Response.status(400).entity("Error").build();
        }

    }



    @Path("/login")
    @POST
    public Response authorizeUser(String data) throws Exception {
        User u = controller.login(data);
        String output = new Gson().toJson(u);
        String encryptedOutput = XORController.encryptDecryptXOR(output);
        encryptedOutput = new Gson().toJson(encryptedOutput);
        if (u != null) {
            return Response.status(200).entity(new Gson().toJson(encryptedOutput)).build();
        } else {
            return Response.status(400).entity("failure!").build();
        }

    }


}