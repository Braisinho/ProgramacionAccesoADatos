package JSON;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(getTeams().get(0).getName());
    }

    public static List<Team> getTeams(){
        List<Team> teams;
        File fileName = new File("src/main/java/JSON/teams.json");

        try {
            String jsonString = null;

            jsonString = Files.readString(Paths.get(String.valueOf(fileName)));


            Type teamListType = new TypeToken<List<Team>>() {
            }.getType();

            teams = new Gson().fromJson(jsonString, teamListType);
        } catch (JsonSyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return teams;
    }
}
