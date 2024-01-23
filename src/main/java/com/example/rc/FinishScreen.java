package com.example.rc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FinishScreen implements Initializable {
    @FXML
    public BorderPane borderPane;
    public ImageView ImageBox;
    public TextArea reviewText;
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart lineChart;
    @FXML
    private NumberAxis XnumberAxis;
    @FXML
    public NumberAxis YnumberAxis;

    private List<UserData> userDataList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int correctchoices = 0;
        int incorrectchoices = 0;
        float avgPercent = 0;

        for (Choices i : HelloApplication.choicesList) {
            if (i.isCorrectChoice()) {
                correctchoices++;
            } else {
                incorrectchoices++;
            }
            avgPercent += i.getPercentageBehind();
        }

        avgPercent = avgPercent / HelloApplication.choicesList.size();

        createPieChart(correctchoices, incorrectchoices);
        saveData(correctchoices, incorrectchoices, avgPercent);
        createLineChart();
//        writeToAvailableFile();
        System.out.println("HELLO");
    }

    private void writeToAvailableFile() {
        try {
            FileWriter fileWriter1 = new FileWriter("../UsersMapsNotDone.csv", false);
            while (!HelloApplication.avaliableMaps.isEmpty()) {
                fileWriter1.write(HelloApplication.avaliableMaps.get(0));
                HelloApplication.avaliableMaps.remove(0);
                fileWriter1.write(",");
            }
            fileWriter1.flush();
            fileWriter1.close();
        } catch (Exception e) {
            System.out.println("FAILED TO WRITE TO COUNTER FILE");
            System.out.println(e);
        }

    }


    private void createPieChart(int correctChoices, int incorrectChoices) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Correct", correctChoices),
                        new PieChart.Data("Incorrect", incorrectChoices));
        pieChart.setData(pieChartData);
    }

    private void saveData(int correctChoices, int incorrectChoices, float avgPercentage) {
        UserData userData = new UserData(LocalDate.now(), correctChoices, incorrectChoices, avgPercentage);
        userData.toFile();
        userDataList.add(userData);

        try {
            Scanner scanner = new Scanner(new File("../UserData.csv"));
            scanner.useDelimiter(",");

            int tempID;
            LocalDate tempDate;
            int tempCorrectChoice;
            int temptIncorrectChoice;
            float tempPercent;

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            dtf = dtf.withLocale(Locale.ENGLISH);

            while (scanner.hasNext()) {
                tempID = Integer.parseInt(scanner.next());
                tempDate = LocalDate.parse(scanner.next(), dtf);
                tempCorrectChoice = Integer.parseInt(scanner.next());
                temptIncorrectChoice = Integer.parseInt(scanner.next());
                tempPercent = Float.parseFloat(scanner.next());
                userDataList.add(new UserData(tempID, tempDate, tempCorrectChoice, temptIncorrectChoice, tempPercent));
            }
            scanner.close();

        } catch (Exception e) {
            System.out.println("FAILED TO READ USERDATA FILE");
            System.out.println(e);
        }
    }

    private void createLineChart() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dtf = dtf.withLocale(Locale.ENGLISH);
        int maxid = 1;
        float maxheight = 1;
        try {
            XYChart.Series<Number, Number> series = new XYChart.Series();

            for (UserData i : userDataList) {

                series.getData().add(new XYChart.Data<Number, Number>(i.getId(), i.getAvgPercentage()));
                maxid = i.getId();
                if (i.getAvgPercentage() > maxheight) {
                    maxheight = i.getAvgPercentage();
                }
            }

            NumberAxis YnumberAxis = new NumberAxis(1, maxheight + 0.5, 0.1);
            NumberAxis XnumberAxis = new NumberAxis(0, maxid, 1);
            LineChart lineChart = new LineChart<Number, Number>(XnumberAxis, YnumberAxis);
            lineChart.getData().add(series);
            lineChart.setCreateSymbols(false);
            lineChart.setLegendVisible(false);
            lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.X_AXIS);
            borderPane.setRight(lineChart);
//
//            XnumberAxis.setLowerBound(0);
//            XnumberAxis.setUpperBound(maxid);
//            XnumberAxis.setTickUnit(1);
//            YnumberAxis.setLowerBound(1);
//            YnumberAxis.setUpperBound(2);
//            YnumberAxis.setTickUnit(0.1);

        } catch (Exception e) {
            System.out.println("HERE");
            System.out.println(e);
        }
    }

    private int toDateFormatter(LocalDate localDate) {
        return localDate.getDayOfMonth() + localDate.getMonthValue() * 100 + localDate.getYear() * 10000;
    }

    private String toDateFormatter(int formatted) {
        int year = formatted / 10000;
        int month = formatted / 100 - year * 100;
        int day = formatted - month * 100 - year * 10000;

        LocalDate tempDate = LocalDate.of(year, month, day);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dtf = dtf.withLocale(Locale.ENGLISH);
        return tempDate.format(dtf);
    }

    //REVIEWING BELOW
    boolean reviewAll = true;

    public void reviewAllBtnPressed(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Scene currentScene = pieChart.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ReviewScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            MainScreen.SetStage(stage);
            stage.show();
            ReviewScreen.reviewAll = true;
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void reviewIncorrectBtnPressed(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Scene currentScene = pieChart.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ReviewScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            MainScreen.SetStage(stage);
            stage.show();
            ReviewScreen.reviewAll = false;
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
