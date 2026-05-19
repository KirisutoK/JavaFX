package Classes;

// Creation Date: May 19, 2026. at 1:43 AM
// Last Modified: May 19, 2026. at  1:57 AM

public class Player {
    //=======VARIABLES=======//
    private String Name;
    private int Score;

    //=======CONSTRUCTOR=======// NOTE: IN ORDER TO USE THIS FILES WE NEED A CONSTRUCTOR TO CREATE INSTANCES FROM OTHER FILES
    public Player(String Name) {
        this.Name = Name;
        this.Score = 0;
    }
    public Player() {
        this.Name = "Guest";
        this.Score = 0;
    }

    //==========GETTERS==========\\ NOTE: TO ACCESS THE PRIVATE VARIABLES AND USE IT TO OTHER FILES
    public String getName() {
        return Name;
    }
    public int getScore() {
        return Score;
    }

    //==========SETTERS==========\\ NOTE: CHANGES THE VARIABLES ON THIS FILE
    public void setName(String Name) {
        this.Name = Name;
    }
    public void addScore(int s) {
        Score = Score + s;
    }
    public void subtractScore(int s) {
        Score = Score - s;
    }

    //===========METHODS===========\\ NOTE: THIS ARE THE SPECIFIC PROCESS IN ORDER TO MEET THE DESIRED RESULTS
}
