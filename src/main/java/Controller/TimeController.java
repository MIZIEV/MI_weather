package Controller;

import Model.PresentTime;

/**
 * class description:
 * this controller class is needed to define a day or night,
 * and return this value as a string with getDayOrNight() method
 */
public class TimeController {

    private final PresentTime presentTime;

    public TimeController(PresentTime time) {
        this.presentTime = time;
    }

    public String getDayOrNight() {
        if (presentTime.getPresentTime() > 4 & presentTime.getPresentTime() < 18) return "day";
        else return "night";
    }
}