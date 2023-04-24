package JSON;

import java.util.ArrayList;

public class Team {

    private String id;
    private String name;
    private League league;


    public Team(String id, String name, League league) {
        this.id = id;
        this.name = name;
        this.league = league;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
