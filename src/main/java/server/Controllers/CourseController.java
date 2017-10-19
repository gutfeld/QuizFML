package server.Controllers;

import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Course;

import java.util.ArrayList;

public class CourseController {
    Gson gson;



    public ArrayList<Course> getCourses(){
         DBWrapper db = new DBWrapper();

        ArrayList<Course> c = db.getCourses();
        return c;
    }
}
