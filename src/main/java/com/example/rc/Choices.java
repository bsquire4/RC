package com.example.rc;

public class Choices {
    private final int id;
    private final boolean correctChoice;
    private final Float percentageBehind;
    private int numGlances;
    private long timeTaken;

    public Choices(int id, Float percentageBehind, int numGlances) {
        this.id = id;
        this.percentageBehind = percentageBehind;
        this.numGlances = numGlances;
        correctChoice = this.percentageBehind == 1;
    }

    public Choices(int id, Float percentageBehind, long timeTaken) {
        this.id = id;
        this.percentageBehind = percentageBehind;
        this.timeTaken = timeTaken;
        correctChoice = this.percentageBehind == 1;
    }

    @Override
    public String toString() {
        return "Choices{" +
                "id=" + id +
                ", correctChoice=" + correctChoice +
                ", percentageBehind=" + percentageBehind +
                ", numGlances=" + numGlances +
                ", timeTaken=" + timeTaken +
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

    public int getNumGlances() {
        return numGlances;
    }

    public long getTimeTaken() {
        return timeTaken;
    }
}
