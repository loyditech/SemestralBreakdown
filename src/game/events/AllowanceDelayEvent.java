package game.events;

import game.Player;

public class AllowanceDelayEvent extends Event {
    @Override
    public String getName() {
        return "Allowance Delay";
    }

    @Override
    public void apply(Player player) {
        double lost = 200;
        player.modifyMoney(-lost);
        player.modifyStress(5);
        System.out.printf("Allowance delayed! -$%.0f, +5 Stress\n", lost);
    }
}