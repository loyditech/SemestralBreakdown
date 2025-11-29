package game;
/**
 * Manages player stats and weekly activity limits.
 **/
public class Player {
    // Player attributes
    private double money;
    private int energy;
    private int stress;
    private int grade;
    
    // Weekly activity counters
    private int studyCount;
    private int workCount;
    private int hangoutCount;
    private int restCount;
    
    // Weekly activity limits
    public static final int STUDY_LIMIT = 4;
    public static final int WORK_LIMIT = 3;
    public static final int REST_LIMIT = 3;
    public static final int HANGOUT_LIMIT = 3;

    /**
     * Constructs a new Player with initial stats.
     */
    public Player(double money, int energy, int stress, int grade) {
        this.money = money;
        this.energy = energy;
        this.stress = stress;
        this.grade = grade;
    }

    // Getter methods
    public double getMoney() { return money; }
    public int getEnergy() { return energy; }
    public int getStress() { return stress; }
    public int getGrade() { return grade; }

    // Setter methods
    public void setMoney(double money) { this.money = money; }
    public void setEnergy(int energy) { this.energy = energy; }
    public void setStress(int stress) { this.stress = stress; }
    public void setGrade(int grade) { this.grade = grade; }

    // Modifier methods for incremental changes
    public void modifyMoney(double delta) { this.money += delta; }
    public void modifyEnergy(int delta) { this.energy += delta; }
    public void modifyStress(int delta) { this.stress += delta; }
    public void modifyGrade(int delta) { this.grade += delta; }

    // Activity counter methods
    public void incrementStudy() { studyCount++; }
    public void incrementWork() { workCount++; }
    public void incrementRest() { restCount++; }
    public void incrementHangout() { hangoutCount++; }

    // Activity limit check methods
    public boolean canStudy() { return studyCount < STUDY_LIMIT; }
    public boolean canWork() { return workCount < WORK_LIMIT; }
    public boolean canRest() { return restCount < REST_LIMIT; }
    public boolean canHangout() { return hangoutCount < HANGOUT_LIMIT; }

    /**
     * Resets weekly activity counters at the start of a new week.
     */
    public void resetWeeklyCounters() {
        studyCount = workCount = hangoutCount = restCount = 0;
        System.out.println("New week! Activity counters reset.");
    }

    /**
     * Displays current weekly activity progress.
     */
    public void showWeeklyProgress() {
        System.out.printf("Weekly:| Study %d/%d | Work %d/%d | Rest %d/%d | Hangout %d/%d | \n",
            studyCount, STUDY_LIMIT, workCount, WORK_LIMIT, restCount, REST_LIMIT, hangoutCount, HANGOUT_LIMIT);
    }
}
