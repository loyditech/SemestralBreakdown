package game.events;
import game.Player;

/**
 * Power Outage Event - Demonstrates polymorphism through method overriding
 * Shows how different events can have unique effects on player stats
 */
public class PowerOutageEvent extends Event {
    
   
    @Override
    public String getName() {
        return "Power Outage";
    }
    /**
     * Applies event effects - demonstrates polymorphism
     * Unique effect: reduces energy but also reduces stress
     */
    @Override
    public void apply(Player player) {
        player.modifyEnergy(-5);
        player.modifyStress(-3);
        System.out.println("Power outage! Energy -5%, Stress -3%");
    }
}
