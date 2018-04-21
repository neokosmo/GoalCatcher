package kosmo.neo.goalcatcher;

public class Goal {
    private String goalName;
    private String goalDate;
    private String goalTime;


    Goal(String goalName, String goalDate, String goalTime){
        this.goalName = goalName;
        this.goalDate = goalDate;
        this.goalTime = goalTime;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(String goalDate) {
        this.goalDate = goalDate;
    }

    public String getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(String goalTime) {
        this.goalTime = goalTime;
    }
}
