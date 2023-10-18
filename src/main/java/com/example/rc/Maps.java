package com.example.rc;

public class Maps {
    private String location;
    private int numChoices;
    private String[] colours;
    private int correctChoice;
    protected int id;
    private int [] distances;

    public Maps(int id, String location, int numChoices, int correctChoice, String[] colours, int[] distances) {
        this.location = location;
        this.numChoices = numChoices;
        this.colours = colours;
        this.correctChoice = correctChoice;
        this.id = id;
        this.distances = distances;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumChoices() {
        return numChoices;
    }

    public void setNumChoices(int numChoices) {
        this.numChoices = numChoices;
    }

    public String[] getColours() {
        return colours;
    }

    public void setColours(String[] colours) {
        this.colours = colours;
    }

    public int getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }

    public int getId() {
        return id;
    }

    public int[] getDistances() {
        return distances;
    }

    public void setDistances(int[] distances) {
        this.distances = distances;
    }

    public void getDistancesString()
    {
        for (int i:distances) {
            System.out.println(i);
        }
    }

}
