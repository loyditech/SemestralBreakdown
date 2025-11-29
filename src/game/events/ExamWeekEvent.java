package game.events;

import game.Player;

/**
 * Exam Week Event - Player's performance depends on their energy level
 */
public class ExamWeekEvent extends Event {
    @Override
    public String getName() {
        return "Exam Week:\n" +
       "- When Energy is 60% or higher, Grade Boost (+3)\n" + 
       "- When Energy is below 60%, Grade Penalty (-5)\n" +
       "(Caution) In all cases, Energy decreases by 10.";
    }

    @Override
    public void apply(Player player) {
        player.modifyStress(7);
        if (player.getEnergy() >= 60) {
            int boost = 3;
            player.modifyGrade(boost);
            System.out.println("\nWell prepared! +" + boost + " Grade");
        } else {
            int penalty = 5;
            player.modifyGrade(-penalty);
            System.out.println("\nUnderprepared! -" + penalty + " Grade");
        }
        player.modifyEnergy(-10);
    }
}
