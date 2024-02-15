package com.example.rc;

import java.util.Arrays;

public class Maps {
    private final String Blanklocation;
    private final String RouteLocation;
    private final String DistanceLocation;

    private final int numChoices;
    private final String[] colours;
    private final int correctChoice;
    protected int id;
    private final int [] distances;

    public Maps(int id, String Blanklocation,String RouteLocation, String DistanceLocation, int numChoices, int correctChoice, String[] colours, int[] distances) {
        this.Blanklocation = Blanklocation;
        this.RouteLocation = RouteLocation;
        this.DistanceLocation = DistanceLocation;
        this.numChoices = numChoices;
        this.colours = colours;
        this.correctChoice = correctChoice;
        this.id = id;
        this.distances = distances;
    }

    @Override
    public String toString() {
        return "Maps{" +
                "Blanklocation='" + Blanklocation + '\'' +
                ", RouteLocation='" + RouteLocation + '\'' +
                ", DistanceLocation='" + DistanceLocation + '\'' +
                ", numChoices=" + numChoices +
                ", colours=" + Arrays.toString(colours) +
                ", correctChoice=" + correctChoice +
                ", id=" + id +
                ", distances=" + Arrays.toString(distances) +
                '}';
    }

    public String getBlanklocation() {
        return Blanklocation;
    }


    public String getRouteLocation() {
        return RouteLocation;
    }


    public String getDistanceLocation() {
        return DistanceLocation;
    }


    public int getNumChoices() {
        return numChoices;
    }


    public String[] getColours() {
        return colours;
    }


    public int getCorrectChoice() {
        return correctChoice;
    }


    public int getId() {
        return id;
    }

    public int[] getDistances() {
        return distances;
    }


    public void getDistancesString()
    {
        for (int i:distances) {
            System.out.println(i);
        }
    }

}
