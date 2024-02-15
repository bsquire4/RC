package com.example.rc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FinishScreen implements Initializable {
    @FXML
    public BorderPane borderPane;
    public HBox linechartHolder;
    @FXML
    private PieChart pieChart;
    private final List<UserData> userDataList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int correctchoices = 0;
        int incorrectchoices = 0;
        float avgPercent = 0;
        int avgNumGlances = 0;
        long avgTimeTaken = 0;

        for (Choices i : HelloApplication.choicesList) {
            System.out.println(i.toString());
            if (i.isCorrectChoice()) {
                correctchoices++;
            } else {
                incorrectchoices++;
            }
            avgPercent += i.getPercentageBehind();
            if (Objects.equals(HelloApplication.gameModeSettings.getGameMode(), "GlanceViewMode")) {
                avgNumGlances += i.getNumGlances();
            } else {
                avgTimeTaken += i.getTimeTaken();
            }
        }

        avgPercent = avgPercent / HelloApplication.choicesList.size();

        if (Objects.equals(HelloApplication.gameModeSettings.getGameMode(), "GlanceViewMode")) {
            avgNumGlances = avgNumGlances / HelloApplication.choicesList.size();
        } else {
            avgTimeTaken = avgTimeTaken / HelloApplication.choicesList.size();
        }
        createPieChart(correctchoices, incorrectchoices);
        if (Objects.equals(HelloApplication.gameModeSettings.getGameMode(), "GlanceViewMode")) {
            saveData(correctchoices, incorrectchoices, avgPercent, avgNumGlances);
        } else {
            saveData(correctchoices, incorrectchoices, avgPercent, avgTimeTaken);
        }
        onLCpercentBtnPress(new ActionEvent());
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

    private void saveData(int correctChoices, int incorrectChoices, float avgPercentage, long avgtimeTaken) {
        UserData userData = new UserData(LocalDate.now(), correctChoices, incorrectChoices, avgPercentage, HelloApplication.gameModeSettings.getGameMode(), avgtimeTaken);
        userData.toFile();
        userDataList.add(userData);
        readData();
    }

    private void saveData(int correctChoices, int incorrectChoices, float avgPercentage, int avgNumGlances) {
        UserData userData = new UserData(LocalDate.now(), correctChoices, incorrectChoices, avgPercentage, HelloApplication.gameModeSettings.getGameMode(), avgNumGlances);
        userData.toFile();
        userDataList.add(userData);
        readData();
    }

    public void readData() {
        try {
            Scanner scanner = new Scanner(new File("../UserData.csv"));
            scanner.useDelimiter(",");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            dtf = dtf.withLocale(Locale.ENGLISH);

            while (scanner.hasNext()) {
                int tempID = Integer.parseInt(scanner.next());
                LocalDate tempDate = LocalDate.parse(scanner.next(), dtf);
                int tempCorrectChoice = Integer.parseInt(scanner.next());
                int temptIncorrectChoice = Integer.parseInt(scanner.next());
                float tempPercent = Float.parseFloat(scanner.next());
                String tempGameMode = scanner.next();
                if (Objects.equals(tempGameMode, "GlanceViewMode")) {
                    int tempNumGlances = Integer.parseInt(scanner.next());
                    userDataList.add(new UserData(tempID, tempDate, tempCorrectChoice, temptIncorrectChoice, tempPercent, tempGameMode, tempNumGlances));
                } else {
                    long tempTimeTaken = Long.parseLong(scanner.next());
                    userDataList.add(new UserData(tempID, tempDate, tempCorrectChoice, temptIncorrectChoice, tempPercent, tempGameMode, tempTimeTaken));
                }
            }
            scanner.close();

        } catch (Exception e) {
            System.out.println("FAILED TO READ USERDATA FILE");
            System.out.println(e);
        }
    }

    private XYChart.Series<Number, Number> series = new XYChart.Series();
    private int maxid = 1;
    private float maxheight = 1;
    private float minheight = 0;
    private float tickHeight = 0.1F;

    public void onLCpercentBtnPress(ActionEvent actionEvent) {
        XYChart.Series<Number, Number> tempSeries = new XYChart.Series();
        minheight = 0.9F;
        maxheight = 1;
        for (UserData i : userDataList) {
            tempSeries.getData().add(new XYChart.Data<Number, Number>(i.getId(), i.getAvgPercentage()));
            maxid = i.getId();
            if (i.getAvgPercentage() > maxheight) {
                maxheight = i.getAvgPercentage();
            }
        }
        tickHeight = 0.1F;
        maxheight = (float) Math.round((maxheight * 1.1) * 10)/10;
        series = tempSeries;
        createLineChart();
    }

    public void onLCtimeBtnPress(ActionEvent actionEvent) {
        XYChart.Series<Number, Number> tempSeries = new XYChart.Series();
        minheight = 0;
        maxheight = 1;
        for (UserData i : userDataList) {
            if(i.getAvgTimeTaken()>0)
            {
                tempSeries.getData().add(new XYChart.Data<Number, Number>(i.getId(), i.getAvgTimeTaken()));

            }
            maxid = i.getId();
            if (i.getAvgTimeTaken() > maxheight) {
                maxheight = i.getAvgTimeTaken();
            }
        }
        tickHeight = Math.round((maxheight / 10) / 10) * 10;
        maxheight = (float) Math.round((maxheight * 1.1) /10) * 10;
        series = tempSeries;
        createLineChart();
    }

    public void onLCglancesBtnpress(ActionEvent actionEvent) {
        minheight = 0;
        maxheight = 1;
        XYChart.Series<Number, Number> tempSeries = new XYChart.Series();

        for (UserData i : userDataList) {
            if(i.getAvgNumGlances() > 0)
            {
                tempSeries.getData().add(new XYChart.Data<Number, Number>(i.getId(), i.getAvgNumGlances()));

            }
            maxid = i.getId();
            if (i.getAvgNumGlances() > maxheight) {
                maxheight = i.getAvgNumGlances();
            }
        }
        tickHeight = Math.round((maxheight / 10));
        maxheight = (float) (maxheight * 1.1);
        series = tempSeries;
        createLineChart();
    }

    private void createLineChart() {
        try {
            linechartHolder.getChildren().removeAll();
            linechartHolder.getChildren().clear();

            NumberAxis YnumberAxis = new NumberAxis(minheight, maxheight, tickHeight);
            NumberAxis XnumberAxis = new NumberAxis(0, maxid, 1);
            LineChart<Number, Number> lineChart = new LineChart<Number, Number>(XnumberAxis, YnumberAxis);
            lineChart.getData().add(series);
            lineChart.setCreateSymbols(false);
            lineChart.setLegendVisible(false);
            lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.X_AXIS);
            linechartHolder.getChildren().add(lineChart);
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
            loadReviewPage();
            ReviewScreen.reviewAll = true;
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void loadReviewPage() throws java.io.IOException {
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
    }

    public void reviewIncorrectBtnPressed(ActionEvent event) {
        try {
            loadReviewPage();
            ReviewScreen.reviewAll = false;
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
