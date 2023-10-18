package com.example.rc;

import java.time.LocalDate;

public class Choices {
    private int id;
    private boolean correctChoice;
    private Float percentageBehind;
    private LocalDate date;

    public Choices(int id, Float percentageBehind) {
        this.id = id;
        this.percentageBehind = percentageBehind;
        if(this.percentageBehind == 1)
        {
            correctChoice = true;
        }
        else
        {
            correctChoice = false;
        }
        this.date = java.time.LocalDate.now();
    }

    @Override
    public String toString() {
        return "Choices{" +
                "id=" + id +
                ", correctChoice=" + correctChoice +
                ", percentageBehind=" + percentageBehind +
                ", date=" + date +
                '}';
    }
}
