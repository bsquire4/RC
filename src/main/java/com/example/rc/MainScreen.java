package com.example.rc;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


import javax.swing.*;
import java.awt.event.KeyListener;
import java.io.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class MainScreen {
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
    private static double width;
    private static double height;
    private static int mapCounter = 0;
    private static Maps Map;
    private int glanceCounter = 0;
    @FXML
    private VBox VB;
    private long startTime;

    @FXML
    protected void onChoiceBtn1Click() {
        System.out.println("Btn1 Pressed -" + Map.getColours()[0]);
        float temp = (float) (Map.getDistances()[0]) / (float) (Map.getDistances()[Map.getCorrectChoice() - 1]);
        saveChoice(temp);
    }

    @FXML
    protected void onChoiceBtn2Click() {
        System.out.println("Btn2 Pressed -" + Map.getColours()[1]);
        float temp = (float) (Map.getDistances()[1]) / (float) (Map.getDistances()[Map.getCorrectChoice() - 1]);
        saveChoice(temp);
    }

    @FXML
    protected void onChoiceBtn3Click() {
        System.out.println("Btn3 Pressed -" + Map.getColours()[2]);
        float temp = (float) (Map.getDistances()[2]) / (float) (Map.getDistances()[Map.getCorrectChoice() - 1]);
        saveChoice(temp);
    }

    @FXML
    protected void onChoiceBtn4Click() {
        System.out.println("Btn4 Pressed -" + Map.getColours()[2]);
        float temp = (float) (Map.getDistances()[3]) / (float) (Map.getDistances()[Map.getCorrectChoice() - 1]);
        saveChoice(temp);
    }

    @FXML
    protected void onChoiceBtn5Click() {
        System.out.println("Btn5 Pressed - Other");
        float temp = (float) Arrays.stream(Map.getDistances()).max().getAsInt() / (float) (Map.getDistances()[Map.getCorrectChoice() - 1]);
        saveChoice(temp);
    }

    private void saveChoice(float temp) {
        Choices tempchoice;
        if (Objects.equals(HelloApplication.gameModeSettings.getGameMode(), "GlanceViewMode")) {
            if(glanceCounter == 0)
            {
                glanceCounter = timeline.getCycleCount();
            }
            tempchoice = new Choices(Map.id, temp, glanceCounter);
            glanceCounter = 0;
        } else {
            System.out.println(System.currentTimeMillis() - startTime);
            tempchoice = new Choices(Map.id, temp, System.currentTimeMillis() - startTime);
        }
        HelloApplication.choicesList.add(tempchoice);
        onRoundStart();
    }

    @FXML
    public static void SetStage(Stage stage) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth() * 0.8;
        height = screenSize.getHeight() * 0.8;
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setX((screenSize.getWidth() - width) / 2);
        stage.setY((screenSize.getHeight() - height) / 2);
    }

    public void onRoundStart() {
        currentMap = false;
        System.out.println("HELLO");
        try {
            timeline.stop();
        } catch (Exception e) {
            System.out.println("TIMELINE ALREADY STOPPED/ NEVER A TIMELINE // " + e);
        }

        if (mapCounter < HelloApplication.gameModeSettings.getNumRouteChoices() && HelloApplication.avaliableMaps.size() > 0) {
            try {
                int mapID = HelloApplication.avaliableMaps.get(new java.util.Random().nextInt(HelloApplication.avaliableMaps.size()));

                System.out.println("MAP ID:" + mapID);
                System.out.println("OUT OF LIST OF: " + HelloApplication.avaliableMaps.toString());
                Map = HelloApplication.importedMaps.get(mapID);
                HelloApplication.avaliableMaps.remove(Integer.valueOf(mapID));
                mapCounter++;
                System.out.println(Map.toString());

                System.out.println(HelloApplication.gameModeSettings.toString());
                DisableBtn(Btn1);
                DisableBtn(Btn2);
                DisableBtn(Btn3);
                DisableBtn(Btn4);
                DisableBtn(Btn5);
                counter = 0;

                switch (Map.getNumChoices()) {
                    case 2:
                        //Btn1,2,5 in use
                        EnableBtn(Btn1);
                        Btn1.setStyle("-fx-background-color:" + Btn1.getText() + ";");
                        EnableBtn(Btn2);
                        Btn2.setStyle("-fx-background-color:" + Btn2.getText() + ";");
                        DisableBtn(Btn3);
                        DisableBtn(Btn4);

                        VB.setOnKeyPressed(event -> {
                            keyTypedEvent(event);
                            if (event.getCode() == KeyCode.DIGIT1) {
                                onChoiceBtn1Click();
                            } else if (event.getCode() == KeyCode.DIGIT2) {
                                onChoiceBtn2Click();
                            } else if (event.getCode() == KeyCode.DIGIT3) {
                                onChoiceBtn5Click();
                            }
                        });

                        break;
                    case 3:
                        //Btn1,2,3,5 in use
                        EnableBtn(Btn1);
                        Btn1.setStyle("-fx-background-color:" + Btn1.getText() + ";");
                        EnableBtn(Btn2);
                        Btn2.setStyle("-fx-background-color:" + Btn2.getText() + ";");
                        EnableBtn(Btn3);
                        Btn3.setStyle("-fx-background-color:" + Btn3.getText() + ";");
                        DisableBtn(Btn4);

                        VB.setOnKeyPressed(event -> {
                            keyTypedEvent(event);
                            if (event.getCode() == KeyCode.DIGIT1) {
                                onChoiceBtn1Click();
                            } else if (event.getCode() == KeyCode.DIGIT2) {
                                onChoiceBtn2Click();
                            } else if (event.getCode() == KeyCode.DIGIT3) {
                                onChoiceBtn3Click();
                            } else if (event.getCode() == KeyCode.DIGIT4) {
                                onChoiceBtn5Click();
                            }
                        });

                        break;
                    case 4:
                        //Btn 1,2,3,4,5 in use
                        EnableBtn(Btn1);
                        Btn1.setStyle("-fx-background-color:" + Btn1.getText() + ";");
                        EnableBtn(Btn2);
                        Btn2.setStyle("-fx-background-color:" + Btn2.getText() + ";");
                        EnableBtn(Btn3);
                        Btn3.setStyle("-fx-background-color:" + Btn3.getText() + ";");
                        EnableBtn(Btn4);
                        Btn4.setStyle("-fx-background-color:" + Btn4.getText() + ";");

                        VB.setOnKeyPressed(event -> {
                            keyTypedEvent(event);
                            if (event.getCode() == KeyCode.DIGIT1) {
                                onChoiceBtn1Click();
                            } else if (event.getCode() == KeyCode.DIGIT2) {
                                onChoiceBtn2Click();
                            } else if (event.getCode() == KeyCode.DIGIT3) {
                                onChoiceBtn3Click();
                            } else if (event.getCode() == KeyCode.DIGIT4) {
                                onChoiceBtn4Click();
                            } else if (event.getCode() == KeyCode.DIGIT5) {
                                onChoiceBtn5Click();
                            }
                        });
                        break;
                }

                Btn5.setDisable(false);
                Btn5.setVisible(true);
                Btn5.setStyle("-fx-background-color: #d3d3d3;");
                HB.getChildren().add(Btn5);
                HB.setPrefWidth(width);
                HBox.setHgrow(Btn5, Priority.ALWAYS);

                ImageBox.setFitHeight(height - Btn1.getPrefHeight() - 50);

                // Play the timeline
                System.out.println(HelloApplication.gameModeSettings.getTimeLength());
                startTime = System.currentTimeMillis();
                switch (HelloApplication.gameModeSettings.getGameMode()) {
                    case "SingleViewMode":
                        if (HelloApplication.gameModeSettings.getTimeLength() == 0) {
                            changeImage();
                            // on w pressed change to Route Map if it is currently a blank map
                        } else {
                            System.out.println("SVM");
                            if (!HelloApplication.gameModeSettings.isMapRoute()) {
                                timeline = new Timeline(new KeyFrame(Duration.seconds(0), event -> {
                                    changeImage();
                                }), new KeyFrame(Duration.seconds(HelloApplication.gameModeSettings.getTimeLength()), event -> {
                                    changeImageToRoutes();
                                }));
                            } else {
                                timeline = new Timeline(new KeyFrame(Duration.seconds(0), event -> {
                                    changeImage();
                                }), new KeyFrame(Duration.seconds(HelloApplication.gameModeSettings.getTimeLength()), event -> {
                                    changeImage();
                                }));
                            }

                            timeline.setCycleCount(1);
                            timeline.play();
                            VB.setOnKeyReleased(event ->
                                    {
                                        System.out.println("RELEASED");
                                        if (event.getCode() == KeyCode.W && !HelloApplication.gameModeSettings.isMapRoute()) {
                                            timeline.stop();
                                            changeImageToRoutes();
                                        }

                                    }
                            );

                        }
                        break;
                    case "GlanceViewMode":
                        if (HelloApplication.gameModeSettings.getGlanceTimeLength() == 0) {
                            changeImage();
                            final boolean[] wPressed = {false};

                            VB.setOnKeyReleased(event -> {
                                if (!wPressed[0] && event.getCode() == KeyCode.Q && glanceCounter < 2 * HelloApplication.gameModeSettings.getNumGlances()) {
                                    changeImage();
                                    glanceCounter++;
                                } else if (event.getCode() == KeyCode.W && !HelloApplication.gameModeSettings.isMapRoute()) {
                                    wPressed[0] = true;
                                    changeImageToRoutes();
                                }

                            });
                        } else {
                            timeline = new Timeline(new KeyFrame(Duration.seconds(0), event -> {
                                changeImage();
                            }), new KeyFrame(Duration.seconds(HelloApplication.gameModeSettings.getGlanceTimeLength()), event -> {
                                changeImage();
                            }), new KeyFrame(Duration.seconds(HelloApplication.gameModeSettings.getGlanceTimeLength() * 2)));
                            if (HelloApplication.gameModeSettings.getNumGlances() == 0) {
                                timeline.setCycleCount(Timeline.INDEFINITE);
                                timeline.play();
                                VB.setOnKeyReleased(event ->
                                        {
                                            System.out.println("RELEASED");
                                            if (event.getCode() == KeyCode.W && !HelloApplication.gameModeSettings.isMapRoute()) {
                                                timeline.stop();
                                                changeImageToRoutes();
                                            }

                                        }
                                );
                            } else {
                                timeline.setCycleCount(HelloApplication.gameModeSettings.getNumGlances());
                                timeline.play();
                                timeline.setOnFinished(event -> changeImageToRoutes());

                                VB.setOnKeyReleased(event ->
                                        {
                                            System.out.println("RELEASED");
                                            if (event.getCode() == KeyCode.W && !HelloApplication.gameModeSettings.isMapRoute()) {
                                                timeline.stop();
                                                changeImageToRoutes();
                                            }

                                        }
                                );
                            }
                        }


                        break;
                    case "RandomViewMode":
                        timeline = new Timeline(new KeyFrame(Duration.seconds(0), event -> {
                            changeImage();
                        }), new KeyFrame(Duration.millis((500 + new java.util.Random().nextInt(1000))), event -> {
                            changeImage();
                        }), new KeyFrame(Duration.millis(((500 + new java.util.Random().nextInt(1000)) * 3))));
                        if (HelloApplication.gameModeSettings.getNumGlances() == 0) {
                            timeline.setCycleCount(Timeline.INDEFINITE);
                            timeline.play();
                            VB.setOnKeyReleased(event ->
                                    {
                                        System.out.println("RELEASED");
                                        if (event.getCode() == KeyCode.W && !HelloApplication.gameModeSettings.isMapRoute()) {
                                            timeline.stop();
                                            changeImageToRoutes();
                                        }

                                    }
                            );

                        } else {
                            timeline.setCycleCount(HelloApplication.gameModeSettings.getNumGlances());
                            timeline.play();
                            timeline.setOnFinished(event -> changeImageToRoutes());

                            VB.setOnKeyReleased(event ->
                                    {
                                        System.out.println("RELEASED");
                                        if (event.getCode() == KeyCode.W && !HelloApplication.gameModeSettings.isMapRoute()) {
                                            timeline.stop();
                                            changeImageToRoutes();
                                        }

                                    }
                            );
                        }

                        break;
                    default:
                        System.out.println("ERROR setting Game Mode");
                        break;
                }
            } catch (
                    Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.

                    println("RAN OUT OF MAPS!!!!!!!");

//alert saying ran out of maps
//go to Finish Screen
            Ending();
        }
    }

    public Timeline timeline;
    private int counter = 0;
    private Boolean currentMap = false;
    private boolean started = false;


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


    private void changeImage() {
        try {
            System.out.println("CHANGE IMAGE CALLED");
            if (currentMap) {
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
        } catch (Exception e) {
            System.out.println("IMAGE FILE NOT FOUND // " + e);
        }
    }

    private void changeImageToRoutes() {
        try {
            System.out.println("CHANGE IMAGE TO ROUTES CALLED");
            InputStream stream;
            stream = new FileInputStream(Map.getRouteLocation());
            Image image = new Image(stream);
            ImageBox.setImage(image);
        } catch (Exception e) {
            System.out.println("IMAGE FILE NOT FOUND // " + e);
        }

    }


    private void Ending() {
        try {
            Stage stage = new Stage();
            Scene currentScene = HB.getScene();
            LoadFinishScreen(stage, currentScene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void LoadFinishScreen(Stage stage, Scene currentScene) throws IOException {
        Stage currentStage = (Stage) currentScene.getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FinishScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        MainScreen.SetStage(stage);
        stage.show();
    }

    public void keyTypedEvent(KeyEvent keyEvent) {
        welcomeText.setVisible(false);
        System.out.println("KEY PRESSED: " + keyEvent.getCode());
        if (!started) {
            started = true;
            System.out.println("STARTED");
            onRoundStart();
        } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
            System.out.println("ENDED");
            Ending();
        }
    }
}

