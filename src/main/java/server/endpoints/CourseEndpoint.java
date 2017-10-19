package server.endpoints;
import com.google.gson.Gson;
import server.Controllers.CourseController;
import server.security.XORController;
import server.models.Course;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;


@Path("/Courses")
public class CourseEndpoint {

    String demoJson = new Gson().toJson("Courses");
    @GET
    public Response getCourses() throws IOException, ClassNotFoundException {
        CourseController courseController = new  CourseController();
        ArrayList<Course> courses = courseController.getCourses();
        String output = new Gson().toJson(courses);
        String encryptedOutput = XORController.encryptDecryptXOR(output);
        encryptedOutput = new Gson().toJson(encryptedOutput);

        return Response
                .status(200)
                .type("application/json")
                .entity(encryptedOutput)
                .build();
    }
}

