package prog.controller;

/**
 * class description:
 * this class is needed to determinate the wind direction
 *
 * getDirection(int windDegrees) method takes wind direction value in degrees (meteorological),
 * then return string with wind direction value
 */
public class WindDirectControl {

    private final static String NORTH_DIRECTION = "north";
    private final static String NORTH_EAST_DIRECTION = "north_east";
    private final static String NORTH_WEST_DIRECTION = "north_west";
    private final static String EAST_DIRECTION = "east";
    private final static String SOUTH_EAST_DIRECTION = "south_east";
    private final static String SOUTH_DIRECTION = "south";
    private final static String SOUTH_WEST_DIRECTION = "south_west";
    private final static String WEST_DIRECTION = "west";

    public String getDirection(int windDegrees) {

        if (windDegrees > 340 & windDegrees < 20) return NORTH_DIRECTION;
        else if (windDegrees > 20 & windDegrees < 65) return NORTH_EAST_DIRECTION;
        else if (windDegrees > 65 & windDegrees < 110) return EAST_DIRECTION;
        else if (windDegrees > 110 & windDegrees < 155) return SOUTH_EAST_DIRECTION;
        else if (windDegrees > 155 & windDegrees < 200) return SOUTH_DIRECTION;
        else if (windDegrees > 200 & windDegrees < 245) return SOUTH_WEST_DIRECTION;
        else if (windDegrees > 245 & windDegrees < 290) return WEST_DIRECTION;
        else  return NORTH_WEST_DIRECTION;
    }
}