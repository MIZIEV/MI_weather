package Controller;

public class WindDirectionControl {

    private final static String NORTH_DIRECTION = "north";
    private final static String NORTH_EAST_DIRECTION = "north_east";
    private final static String NORTH_WEST_DIRECTION = "north_west";
    private final static String EAST_DIRECTION = "east";
    private final static String SOUTH_EAST_DIRECTION = "south_east";
    private final static String SOUTH_DIRECTION = "south";
    private final static String SOUTH_WEST_DIRECTION = "south_west";
    private final static String WEST_DIRECTION = "west";

    public String getDirection(int windDirectionInt) {

        if (windDirectionInt > 340 & windDirectionInt < 20) return NORTH_DIRECTION;
        else if (windDirectionInt > 20 & windDirectionInt < 65) return NORTH_EAST_DIRECTION;
        else if (windDirectionInt > 65 & windDirectionInt < 110) return EAST_DIRECTION;
        else if (windDirectionInt > 110 & windDirectionInt < 155) return SOUTH_EAST_DIRECTION;
        else if (windDirectionInt > 155 & windDirectionInt < 200) return SOUTH_DIRECTION;
        else if (windDirectionInt > 200 & windDirectionInt < 245) return SOUTH_WEST_DIRECTION;
        else if (windDirectionInt > 245 & windDirectionInt < 290) return WEST_DIRECTION;
        else  return NORTH_WEST_DIRECTION;
    }
}
