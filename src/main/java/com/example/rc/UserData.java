package com.example.rc;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class UserData {
    private int id;
    private LocalDate date;
    private int numCorrect;
    private int numIncorrect;
    private float avgPercentage;

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
                '}';
    }

    public UserData(int id, LocalDate date, int numCorrect, int numIncorrect, float avgPercentage) {
        this.id = id;
        this.date = date;
        this.numCorrect = numCorrect;
        this.numIncorrect = numIncorrect;
        this.avgPercentage = avgPercentage;
    }

    public UserData(LocalDate date, int numCorrect, int numIncorrect, float avgPercentage) {
        this.id = id;
        this.date = date;
        this.numCorrect = numCorrect;
        this.numIncorrect = numIncorrect;
        this.avgPercentage = avgPercentage;

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

        System.out.println("WRITING: " + this.toString());
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
            fileWriter.write(Float.toString(this.avgPercentage));
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
}
