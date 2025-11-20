package game.events;

import game.Player;

public class PowerOutageEvent extends Event {
    @Override
    public String getName() {
        return "Power Outage";
    }

    @Override
    public void apply(Player player) {
        player.modifyEnergy(-5);
        player.modifyStress(-3);
        System.out.println("Power outage! Energy -5%, Stress -3%");
    }
}