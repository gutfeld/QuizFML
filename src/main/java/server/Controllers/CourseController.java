package server.Controllers;

import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Course;

import java.util.ArrayList;

public class CourseController {
    Gson gson;



    public ArrayList<Course> getCourses(){
         DBWrapper db = new DBWrapper();

      /* for(int i = 0; i < courses.size(); i++)
       System.out.println(courses.get(i));
       return courses;
       */

        ArrayList<Course> c = db.getCourses();
        return c;
    }
}
