package game.events;

import game.Player;

public class RainyDayEvent extends Event {
    @Override
    public String getName() {
        return "Rainy Day";
    }

    @Override
    public void apply(Player player) {
        player.modifyEnergy(-8);
        player.modifyStress(3);
        System.out.println("Rainy day! Stuck inside. Energy -8%, Stress +3%");
    }
}
