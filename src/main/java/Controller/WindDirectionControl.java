package Controller;


public class WindDirectionControl {

    public String getDirection(int windDirectionInt) {

        if (windDirectionInt > 330 & windDirectionInt < 45) return "north";
        else if (windDirectionInt > 45 & windDirectionInt < 135) return "east";
        else if (windDirectionInt > 135 & windDirectionInt < 225) return "south";
        else return "west";
    }
}
