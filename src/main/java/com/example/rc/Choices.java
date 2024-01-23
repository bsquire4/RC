package com.example.rc;

import java.time.LocalDate;

public class Choices {
    private int id;
    private boolean correctChoice;
    private Float percentageBehind;

    public Choices(int id, Float percentageBehind) {
        this.id = id;
        this.percentageBehind = percentageBehind;
        if (this.percentageBehind == 1) {
            correctChoice = true;
        } else {
            correctChoice = false;
        }
    }

    @Override
    public String toString() {
        return "Choices{" +
                "id=" + id +
                ", correctChoice=" + correctChoice +
                ", percentageBehind=" + percentageBehind +
                '}';
    }

    public int getId() {
        return id;
    }

    public boolean isCorrectChoice() {
        return correctChoice;
    }

    public Float getPercentageBehind() {
        return percentageBehind;
    }

}
