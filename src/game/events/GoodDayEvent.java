package game.events;

import game.Player;

/**
 * Good Day Event - Player gets bonus money and reduced stress
 */
public class GoodDayEvent extends Event {
    @Override
    public String getName() {
        return "Good Day";
    }

    @Override
    public void apply(Player player) {
        player.modifyMoney(150);
        player.modifyStress(-5);
        System.out.println("Great day! Money +150, Stress -5%");
    }
}
