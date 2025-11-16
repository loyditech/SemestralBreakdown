package game.events;

import game.Player;

public class ExamWeekEvent extends Event {
    @Override
    public String getName() {
        return "Exam Week";
    }

    @Override
    public void apply(Player player) {
        player.modifyStress(7);
        if (player.getEnergy() >= 60) {
            int boost = 3;
            player.modifyGrade(boost);
            System.out.println("Well prepared! +" + boost + " Grade");
        } else {
            int penalty = 5;
            player.modifyGrade(-penalty);
            System.out.println("Underprepared! -" + penalty + " Grade");
        }
        player.modifyEnergy(-10);
    }
}
