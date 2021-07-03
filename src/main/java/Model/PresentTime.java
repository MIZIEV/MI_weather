package Model;

import java.util.GregorianCalendar;

/**
 * class description:
 * This class is needed to get present time in string and convert it to integer with getPresentTime() method
 */
public class PresentTime {

    private final GregorianCalendar calendar = new GregorianCalendar();

    public int getPresentTime() {

        String bufferTime[] = calendar.getTime().toString().split("\\s|\\:");
        String time = bufferTime[3];
        return Integer.parseInt(time);
    }
}