package com.example.rc;

public class GameModeSettings {
    public String gameMode;
    private boolean mapRoute;
    private int timeLength;
    private float glanceTimeLength;
    private int numGlances;
    private int numRouteChoices;

    public String getGameMode() {
        return gameMode;
    }

    public boolean isMapRoute() {
        return mapRoute;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public int getNumGlances() {
        return numGlances;
    }

    public int getNumRouteChoices() {return numRouteChoices; }

    public float getGlanceTimeLength() { return glanceTimeLength; }

    public void SingleViewMode(boolean mapRoute, int numRouteChoices, int timeLength) {
        this.gameMode = "SingleViewMode";
        //TRUE - Map has routes on, FALSE - map is blank
        this.mapRoute = mapRoute;
        //Number of Routechoice Images shown
        this.numRouteChoices = numRouteChoices;
        //Length of the viewing of the image
        this.timeLength = timeLength;
    }

    public void GlanceViewMode(boolean mapRoute, int numRouteChoices, float glanceTimeLength, int numGlances) {
        this.gameMode = "GlanceViewMode";
        //TRUE - Map has routes on, FALSE - map is blank
        this.mapRoute = mapRoute;
        //Number of Routechoice Images shown
        this.numRouteChoices = numRouteChoices;
        //Length of time of each glance
        this.glanceTimeLength = glanceTimeLength;
        //number of glances for each map
        this.numGlances = numGlances;
    }

    public void RandomViewMode(boolean mapRoute, int numRouteChoices, int numGlances) {
        this.gameMode = "RandomViewMode";
        //TRUE - Map has routes on, FALSE - map is blank
        this.mapRoute = mapRoute;
        //Number of Routechoice Images shown
        this.numRouteChoices = numRouteChoices;
        //Number of Glances
        this.numGlances = numGlances;
    }

    @Override
    public String toString() {
        return "GameModeSettings{" +
                "gameMode='" + gameMode + '\'' +
                ", mapRoute=" + mapRoute +
                ", timeLength=" + timeLength +
                ", glanceTimeLength=" + glanceTimeLength +
                ", numGlances=" + numGlances +
                ", numRouteChoices=" + numRouteChoices +
                '}';
    }
}