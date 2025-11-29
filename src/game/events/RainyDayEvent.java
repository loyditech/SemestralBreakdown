package game.events;

import game.Player;

/**
 * Rainy Day Event - Demonstrates polymorphism through method overriding
 * Shows inheritance from Event class with unique weather-related effects
 */
public class RainyDayEvent extends Event {
    
    /**
     * Returns event name - overrides parent class method
     */
    @Override
    public String getName() {
        return "Rainy Day";
    }

    /**
     * Applies rainy day effects - demonstrates polymorphic behavior
     * Unique effect: reduces energy and increases stress from being stuck inside
     */
    @Override
    public void apply(Player player) {
        player.modifyEnergy(-8);
        player.modifyStress(3);
        System.out.println("Rainy day! Stuck inside. Energy -8%, Stress +3%");
    }
}
