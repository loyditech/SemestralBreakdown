package game.events;

import game.Player;

public interface GameEvent {
    String getName();
    void apply(Player player);
    String getDescription();
}