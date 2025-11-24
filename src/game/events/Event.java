package game.events;

import game.Player;

public abstract class Event {
    public abstract String getName();
    public abstract void apply(Player player);
}
