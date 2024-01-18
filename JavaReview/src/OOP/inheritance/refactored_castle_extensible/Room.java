package OOP.inheritance.refactored_castle_extensible;

import java.util.HashMap;

/**
 * Room updated to be extensible using polymorphism
 */
public class Room {
    private String description;

    private HashMap<String, Room> exits = new HashMap<>();

    public Room(String description) {
        this.description = description;
    }

    public String getExitDescription() {
        StringBuffer sb = new StringBuffer(); // mutable and synchronized
        for (String direction : exits.keySet()) {
            sb.append(direction);
            sb.append(" ");
        }
        return sb.toString();
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void setExits(String direction, Room room) {
        exits.put(direction, room);
    }

    @Override
    public String toString() {
        return description;
    }
}
