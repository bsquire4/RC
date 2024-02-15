package com.example.rc;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class UserData {
    private int id;
    private final LocalDate date;
    private final int numCorrect;
    private final int numIncorrect;
    private final float avgPercentage;
    private String gameMode;
    private int avgNumGlances;
    private long avgTimeTaken;

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dtf = dtf.withLocale( Locale.ENGLISH );

        return "UserData{" +
                "id=" + id +
                ", date=" + date.format(dtf) +
                ", numCorrect=" + numCorrect +
                ", numIncorrect=" + numIncorrect +
                ", avgPercentage=" + avgPercentage +
                ", gameMode='" + gameMode + '\'' +
                ", avgNumGlances=" + avgNumGlances +
                ", avgTimeTaken=" + avgTimeTaken +
                '}';
    }

    public UserData(int id, LocalDate date, int numCorrect, int numIncorrect, float avgPercentage, String gameMode, int avgNumGlances) {
        this.id = id;
        this.date = date;
        this.numCorrect = numCorrect;
        this.numIncorrect = numIncorrect;
        this.avgPercentage = avgPercentage;
        this.gameMode = gameMode;
        this.avgNumGlances = avgNumGlances;
    }
    public UserData(int id, LocalDate date, int numCorrect, int numIncorrect, float avgPercentage, String gameMode, long avgTimeTaken) {
        this.id = id;
        this.date = date;
        this.numCorrect = numCorrect;
        this.numIncorrect = numIncorrect;
        this.avgPercentage = avgPercentage;
        this.gameMode = gameMode;
        this.avgTimeTaken = avgTimeTaken;
    }

    public UserData(LocalDate date, int numCorrect, int numIncorrect, float avgPercentage, String gameMode, long avgTimeTaken) {
        this.id = id;
        this.date = date;
        this.numCorrect = numCorrect;
        this.numIncorrect = numIncorrect;
        this.avgPercentage = avgPercentage;
        this.gameMode = gameMode;
        this.avgTimeTaken = avgTimeTaken;

        int othervariable = 0;
        try {
            Scanner counterScanner = new Scanner(new File("../Counter.csv"));
            counterScanner.useDelimiter(",");
            othervariable = Integer.parseInt(counterScanner.next());
            System.out.println("MAP COUNTER: " + othervariable);
            this.id = Integer.parseInt(counterScanner.next());
            this.id++;
            System.out.println("ID: " + this.id);
            counterScanner.close();
        } catch (Exception e) {
            System.out.println("ERROR READING COUNTER FILE");
            System.out.println(e);
        }

        try {
            FileWriter fileWriter1 = new FileWriter("../Counter.csv", false);
            fileWriter1.write(String.valueOf(othervariable));
            fileWriter1.write(",");
            fileWriter1.write(String.valueOf(this.id));
            fileWriter1.write(",");
            fileWriter1.flush();
            fileWriter1.close();
        } catch (Exception e) {
            System.out.println("FAILED TO WRITE TO COUNTER FILE");
            System.out.println(e);
        }
    }

    public UserData(LocalDate date, int numCorrect, int numIncorrect, float avgPercentage, String gameMode, int avgNumGlances) {
        this.date = date;
        this.numCorrect = numCorrect;
        this.numIncorrect = numIncorrect;
        this.avgPercentage = avgPercentage;
        this.gameMode = gameMode;
        this.avgNumGlances = avgNumGlances;

        int othervariable = 0;
        try {
            Scanner counterScanner = new Scanner(new File("../Counter.csv"));
            counterScanner.useDelimiter(",");

            othervariable = Integer.parseInt(counterScanner.next());
            System.out.println("MAP COUNTER: " + othervariable);
            this.id = Integer.parseInt(counterScanner.next());
            this.id++;
            System.out.println("ID: " + this.id);
            counterScanner.close();
        } catch (Exception e) {
            System.out.println("ERROR READING COUNTER FILE");
            System.out.println(e);
        }

        try {
            FileWriter fileWriter1 = new FileWriter("../Counter.csv", false);
            fileWriter1.write(String.valueOf(othervariable));
            fileWriter1.write(",");
            fileWriter1.write(String.valueOf(this.id));
            fileWriter1.write(",");
            fileWriter1.flush();
            fileWriter1.close();
        } catch (Exception e) {
            System.out.println("FAILED TO WRITE TO COUNTER FILE");
            System.out.println(e);
        }
    }


    public void toFile() {

        System.out.println("WRITING: " + this);
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            dtf = dtf.withLocale( Locale.ENGLISH );
            FileWriter fileWriter = new FileWriter(new File("../UserData.csv"), true);
            fileWriter.write(String.valueOf(this.id));
            fileWriter.write(",");
            fileWriter.write(this.date.format(dtf));
            fileWriter.write(",");
            fileWriter.write(String.valueOf(this.numCorrect));
            fileWriter.write(",");
            fileWriter.write(String.valueOf(this.numIncorrect));
            fileWriter.write(",");
            fileWriter.write(String.valueOf(this.avgPercentage));
            fileWriter.write(",");
            fileWriter.write(this.gameMode);
            fileWriter.write(",");
            if(Objects.equals(this.gameMode, "GlanceViewMode"))
            {
                fileWriter.write(String.valueOf(this.avgNumGlances));
            }else
            {
                fileWriter.write(String.valueOf(this.avgTimeTaken));
            }
            fileWriter.write(",");
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("FAILED TO WRITE TO USERDATA FILE");
            System.out.println(e);
        }
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public int getNumIncorrect() {
        return numIncorrect;
    }

    public float getAvgPercentage() {
        return avgPercentage;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getAvgNumGlances() {
        return avgNumGlances;
    }

    public void setAvgNumGlances(int avgNumGlances) {
        this.avgNumGlances = avgNumGlances;
    }

    public long getAvgTimeTaken() {
        return avgTimeTaken;
    }

    public void setAvgTimeTaken(long avgTimeTaken) {
        this.avgTimeTaken = avgTimeTaken;
    }
}
