package Tracker;

import java.util.Date;

public class TrainingSession {
    private Date date;
    private int duration;

    public TrainingSession(Date date, int duration) {
        this.date = date;
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }


}
