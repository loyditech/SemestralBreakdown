package game;

public class Player {
    private double money;
    private int energy, stress, grade;
    private int studyCount, workCount, hangoutCount, restCount;
    public static final int STUDY_LIMIT = 4, WORK_LIMIT = 3, REST_LIMIT = 3, HANGOUT_LIMIT = 3;

    public Player(double money, int energy, int stress, int grade) {
        this.money = money;
        this.energy = energy;
        this.stress = stress;
        this.grade = grade;
    }

    public double getMoney() { return money; }
    public void setMoney(double money) { this.money = money; }
    public int getEnergy() { return energy; }
    public void setEnergy(int energy) { this.energy = energy; }
    public int getStress() { return stress; }
    public void setStress(int stress) { this.stress = stress; }
    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }

    public void modifyMoney(double delta) { this.money += delta; }
    public void modifyEnergy(int delta) { this.energy += delta; }
    public void modifyStress(int delta) { this.stress += delta; }
    public void modifyGrade(int delta) { this.grade += delta; }

    public void incrementStudy() { studyCount++; }
    public void incrementWork() { workCount++; }
    public void incrementRest() { restCount++; }
    public void incrementHangout() { hangoutCount++; }

    public boolean canStudy() { return studyCount < STUDY_LIMIT; }
    public boolean canWork() { return workCount < WORK_LIMIT; }
    public boolean canRest() { return restCount < REST_LIMIT; }
    public boolean canHangout() { return hangoutCount < HANGOUT_LIMIT; }

    public void resetWeeklyCounters() {
        studyCount = workCount = hangoutCount = restCount = 0;
        System.out.println("New week! Activity counters reset.");
    }

    public void showWeeklyProgress() {
        System.out.printf("Weekly:| Study %d/%d | Work %d/%d | Rest %d/%d | Hangout %d/%d | \n",
            studyCount, STUDY_LIMIT, workCount, WORK_LIMIT, restCount, REST_LIMIT, hangoutCount, HANGOUT_LIMIT);
    }
}