package game.events;

import game.Player;

/**
 * AllowanceDelayEvent represents a random event where the player's
 * allowance is delayed, causing financial stress.
 * Demonstrates inheritance from the Event class and method overriding.
 */
public class AllowanceDelayEvent extends Event {
    
    /**
     * Returns the name of this event.
     * @return the event name
     */
    @Override
    public String getName() {
        return "Allowance Delay";
    }

    /**
     * Applies the allowance delay event effects to the player.
     * Reduces money and increases stress.
     */
    @Override
    public void apply(Player player) {
        double lost = 200;
        player.modifyMoney(-lost);
        player.modifyStress(5);
        System.out.printf("Allowance delayed! -$%.0f, +5 Stress\n", lost);
    }
}
