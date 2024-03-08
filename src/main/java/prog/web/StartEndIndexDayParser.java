package prog.web;

public class StartEndIndexDayParser implements IndexParser {

    private int startDayIndex;
    private int endDayIndex;

    public int getStartDayIndex() {
        return startDayIndex;
    }

    public void setStartDayIndex(int startDayIndex) {
        this.startDayIndex = startDayIndex;
    }

    public int getEndDayIndex() {
        return endDayIndex;
    }

    public void setEndDayIndex(int endDayIndex) {
        this.endDayIndex = endDayIndex;
    }

    @Override
    public String toString() {
        return "start index - " + startDayIndex + " end index - " + endDayIndex;
    }
}
