package server.Controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Config {

    private static String DATABASE_HOST;
    private static Integer DATABASE_PORT;
    private static String DATABASE_USERNAME;
    private static String DATABASE_PASSWORD;

public void initConfig() throws IOException {

    //Initialiserer variabler
    JsonObject json = new JsonObject();
    JsonParser parser = new JsonParser();

    //Referer til config.json filen i resources
    InputStream input = this.getClass().getResourceAsStream("/config.json");
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

    //Initialiser String variabler
    StringBuffer stringBuffer = new StringBuffer();
    String str = "";

    //Denne metode kombinerer flere strings og laver dem til én ny string
    while((str = reader.readLine()) != null) {
        stringBuffer.append(str);
    }

    //Konverterer json til variabler
    json = (JsonObject) parser.parse(stringBuffer.toString());

    //Sætter klassens variabler
    DATABASE_HOST = json.get("DATABASE_HOST").toString().replace("\"", "");
    DATABASE_PORT = Integer.parseInt(json.get("DATABASE_PORT").toString().replace("\"", ""));
    DATABASE_USERNAME = json.get("DATABASE_USERNAME").toString().replace("\"", "");
    DATABASE_PASSWORD = json.get("DATABASE_PASSWORD").toString().replace("\"", "");

}

    public static String getDatabaseHost() {
        return DATABASE_HOST;
    }

    public static Integer getDatabasePort() {
      return DATABASE_PORT;
    }

    public static String getDatabaseUsername() {
     return DATABASE_USERNAME;
    }

    public static String getDatabasePassword() {
     return DATABASE_PASSWORD;
    }


}
