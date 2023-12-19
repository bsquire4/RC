package com.example.rc;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.When;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.*;
import java.awt.*;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    public HBox HB;
    @FXML
    private Button Btn1;
    @FXML
    public Button Btn2;
    @FXML
    public Button Btn3;
    @FXML
    public Button Btn4;
    @FXML
    public Button Btn5;
    @FXML
    public ImageView ImageBox;
    @FXML
    public Button StartBtn;


    private static double width;
    private static double height;
    private static int mapCounter = 0;

    private static Maps Map;
    private static int percentageBehind;

    @FXML
    public void onHelloBtnClick() {
        System.out.println("STARTED");
        DisableBtn(StartBtn);
        try {
            onStart();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @FXML
    protected void onChoiceBtn1Click() {
        System.out.println("Btn1 Pressed -" + Map.getColours()[0]);
        float temp = (float) (Map.getDistances()[0]) / (float) (Map.getDistances()[Map.getCorrectChoice() - 1]);
        Choices tempchoice = new Choices(Map.id, temp);
        System.out.println(tempchoice.toString());
        HelloApplication.choicesList.add(tempchoice);
        setButtons();
    }

    @FXML
    protected void onChoiceBtn2Click() {
        System.out.println("Btn2 Pressed -" + Map.getColours()[1]);
        float temp = (float) (Map.getDistances()[1]) / (float) (Map.getDistances()[Map.getCorrectChoice() - 1]);
        Choices tempchoice = new Choices(Map.id, temp);
        System.out.println(tempchoice.toString());
        HelloApplication.choicesList.add(tempchoice);
        setButtons();
    }

    @FXML
    protected void onChoiceBtn3Click() {
        System.out.println("Btn3 Pressed -" + Map.getColours()[2]);
        float temp = (float) (Map.getDistances()[2]) / (float) (Map.getDistances()[Map.getCorrectChoice() - 1]);
        Choices tempchoice = new Choices(Map.id, temp);
        System.out.println(tempchoice.toString());
        HelloApplication.choicesList.add(tempchoice);
        setButtons();
    }

    @FXML
    protected void onChoiceBtn4Click() {
        System.out.println("Btn4 Pressed -" + Map.getColours()[2]);
        float temp = (float) (Map.getDistances()[3]) / (float) (Map.getDistances()[Map.getCorrectChoice() - 1]);
        Choices tempchoice = new Choices(Map.id, temp);
        System.out.println(tempchoice.toString());
        HelloApplication.choicesList.add(tempchoice);
        setButtons();
    }

    @FXML
    protected void onChoiceBtn5Click() {
        System.out.println("Btn5 Pressed - Other");
        Choices tempchoice = new Choices(Map.id, Float.NaN);
        System.out.println(tempchoice.toString());
        HelloApplication.choicesList.add(tempchoice);
        setButtons();
    }

    @FXML
    public static void SetStart(Stage stage) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth() * 0.8;
        height = screenSize.getHeight() * 0.8;
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setX((screenSize.getWidth() - width) / 2);
        stage.setY((screenSize.getHeight() - height) / 2);
    }

    public void onStart() throws FileNotFoundException {
        System.out.println(HelloApplication.gameModeSettings.toString());

        while (mapCounter < HelloApplication.gameModeSettings.getNumRouteChoices() && mapCounter < HelloApplication.importedMaps.size()) {
            Map = HelloApplication.importedMaps.get(mapCounter);
            mapCounter++;
            DisableBtn(Btn1);
            DisableBtn(Btn2);
            DisableBtn(Btn3);
            DisableBtn(Btn4);

            HB.setPrefWidth(width);
            HBox.setHgrow(Btn5, Priority.ALWAYS);
            counter = 0;

            switch (Map.getNumChoices()) {
                case 2:
                    //Btn1,2,5 in use
                    EnableBtn(Btn1);
                    EnableBtn(Btn2);
                    DisableBtn(Btn3);
                    DisableBtn(Btn4);

                    break;
                case 3:
                    //Btn1,2,3,5 in use
                    EnableBtn(Btn1);
                    EnableBtn(Btn2);
                    EnableBtn(Btn3);
                    DisableBtn(Btn4);

                    break;
                case 4:
                    //Btn 1,2,3,4,5 in use
                    EnableBtn(Btn1);
                    EnableBtn(Btn2);
                    EnableBtn(Btn3);
                    EnableBtn(Btn4);

                    break;
            }
        }

        ImageBox.setFitHeight(height - Btn1.getPrefHeight() - 50);

        // Play the timeline
        System.out.println(HelloApplication.gameModeSettings.getTimeLength());

        switch (HelloApplication.gameModeSettings.getGameMode()) {
            case "SingleViewMode":
                System.out.println("SVM");
                timeline = new Timeline(new KeyFrame(Duration.seconds(0), event -> {
                    try {
                        changeImage();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }), new KeyFrame(Duration.seconds(HelloApplication.gameModeSettings.getTimeLength()), event -> {
                    try {
                        changeImage();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }));
                timeline.setCycleCount(1);


                break;
            case "GlanceViewMode":
                timeline = new Timeline(new KeyFrame(Duration.seconds(0), event -> {
                    try {
                        changeImage();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }), new KeyFrame(Duration.seconds(HelloApplication.gameModeSettings.getGlanceTimeLength()), event -> {
                    try {
                        changeImage();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }), new KeyFrame(Duration.seconds(HelloApplication.gameModeSettings.getGlanceTimeLength() * 2)));
                timeline.setCycleCount(HelloApplication.gameModeSettings.getNumGlances());

                break;
            case "RandomViewMode":
                timeline = new Timeline(new KeyFrame(Duration.seconds(0), event -> {
                    try {
                        changeImage();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }), new KeyFrame(Duration.millis((500 + new java.util.Random().nextInt(1000))), event -> {
                    try {
                        changeImage();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }), new KeyFrame(Duration.millis(((500 + new java.util.Random().nextInt(1000)) * 3))));
                timeline.setCycleCount(HelloApplication.gameModeSettings.getNumGlances());

                break;
            default:
                System.out.println("ERROR setting Game Mode");
                break;
        } timeline.play();

    }

    public Timeline timeline;
    private int counter = 0;

    @FXML
    public void setButtons() {
        if (HelloApplication.importedMaps.size() == mapCounter) {
            System.out.println("RAN OUT OF MAPS!!!!!!!");
            timeline.stop();
            //alert saying ran out of maps
            //go to Finish Screen
        } else {


        }
    }

    public void DisableBtn(Button Btn) {
        Btn.setDisable(true);
        Btn.setVisible(false);
        HB.getChildren().remove(Btn);
        HBox.setHgrow(Btn, Priority.NEVER);
    }


    public void EnableBtn(Button Btn) {
        Btn.setDisable(false);
        Btn.setVisible(true);
        HB.getChildren().add(Btn);
        HBox.setHgrow(Btn, Priority.ALWAYS);
        Btn.setText(Map.getColours()[counter]);
        counter++;
    }

    private Boolean currentMap = false;

    private void changeImage() throws FileNotFoundException {
        System.out.println("CHANGE IMAGE CALLED");
        if (currentMap == true) {
            InputStream steam2 = new FileInputStream("C:/Users/Ben04/Downloads/EM104989.jpg");
            Image otherImage = new Image(steam2);
            ImageBox.setImage(otherImage);
        } else {
            InputStream stream;
            if (HelloApplication.gameModeSettings.isMapRoute()) {
                stream = new FileInputStream(Map.getRouteLocation());
            } else {
                stream = new FileInputStream(Map.getBlanklocation());
            }

            Image image = new Image(stream);
            ImageBox.setImage(image);
        }
        currentMap = !currentMap;

    }


}
