package server.Controllers;

import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Choice;
import server.endpoints.ChoiceEndpoint;
import server.models.Question;

import java.util.ArrayList;

public class ChoiceController {

        Question test;
        Gson gson;
        DBWrapper db = new DBWrapper();

        public ChoiceController() {
            this.gson = gson;
        }

        public ArrayList<Choice> getChoice(){
            ArrayList<Choice> choices = db.getChoices(test);

            return choices;

        }









    }











