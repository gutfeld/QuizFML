package server.endpoints;

import com.google.gson.Gson;
import server.utility.Log;
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

        log.writeLog(this.getClass().getName(), this, "We are now authorizing user for login", 2);

        User u = controller.login(data);
        String output = new Gson().toJson(u);
        String encryptedOutput = XORController.encryptDecryptXOR(output);
        encryptedOutput = new Gson().toJson(encryptedOutput);
        if (u != null) {
            log.writeLog(this.getClass().getName(), this, "User logged in", 2);

            return Response.status(200).entity(new Gson().toJson(encryptedOutput)).build();
        } else {
            log.writeLog(this.getClass().getName(), this, "User not logged in because of failure", 1);
            return Response.status(400).entity("failure!").build();
        }

    }


}