package server.Controllers;

import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Choice;
import server.endpoints.ChoiceEndpoint;
import server.models.Question;

import java.io.IOException;
import java.util.ArrayList;

public class ChoiceController {


        Gson gson;
        DBWrapper db = new DBWrapper();

        public ChoiceController() {
            this.gson = gson;
        }

        public ArrayList<Choice> getChoices(int questionID) throws IOException {
            ArrayList<Choice> choices = db.getChoices(questionID);
            return choices;
        }


        public void createChoice(String choice)throws Exception{
            Choice newChoice = new Gson() .fromJson(choice, Choice.class);
            db.createChoice(newChoice);
        }



}











