package Model;

import java.util.GregorianCalendar;

public class PresentTime {

    private final GregorianCalendar calendar = new GregorianCalendar();

    public int getPresentTime() {

        String bufferTime[] = calendar.getTime().toString().split("\\s|\\:");
        String time = bufferTime[3];
        int presentTime = Integer.parseInt(time);
        return presentTime;
    }
}