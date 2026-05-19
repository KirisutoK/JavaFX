package Classes.Enemies;

// Creation Date: May 19, 2026. at 1:52 AM
// Last Modified: May 19, 2026. at  2:06 AM

public class Enemy {
    //=======VARIABLES=======//
    private String Name;
    private String EnemyType;

    //=======CONSTRUCTOR=======// NOTE: IN ORDER TO USE THIS FILES WE NEED A CONSTRUCTOR TO CREATE INSTANCES FROM OTHER FILES
    public Enemy(String Name, String EnemyType) {
        this.Name = Name;
        this.EnemyType = EnemyType;
    }

    //==========GETTERS==========\\ NOTE: TO ACCESS THE PRIVATE VARIABLES AND USE IT TO OTHER FILES
    public String getEnemyType() {
        return EnemyType;
    }
    public String getName() {
        return Name;
    }

    //==========SETTERS==========\\ NOTE: CHANGES THE VARIABLES ON THIS FILE
    public void setEnemyType(String enemyType) {
        EnemyType = enemyType;
    }

    //===========METHODS===========\\ NOTE: THIS ARE THE SPECIFIC PROCESS IN ORDER TO MEET THE DESIRED RESULTS
    public void Jumpcare(String filepath) {
        // display like some instant crazy pictures for no reason IDK
    }
    public void Kill() {
        // Show game over or whatever type shi
    }
}
