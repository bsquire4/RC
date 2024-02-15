package com.example.rc;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ReviewScreen implements Initializable {
    @FXML
    Label reviewText;
    int imageCounter = 0;
    @FXML
    ImageView ImageBox;

    @FXML


    public void changeImage() {
        File[] imageFiles = {new File(currentMap.getBlanklocation()), new File(currentMap.getRouteLocation()), new File(currentMap.getDistanceLocation())};
        System.out.println("TOGGLE PRESSED");
        if (imageCounter < 2) {
            imageCounter++;
        } else {
            imageCounter = 0;
        }
        try {
            InputStream stream;
            stream = new FileInputStream(imageFiles[imageCounter]);
            Image image = new Image(stream);
            ImageBox.setImage(image);
            System.out.println(("IMAGE SET"));
        } catch (Exception e) {
            System.out.println("ERROR GETTING FILE");
            System.out.println(e);
        }
    }

    public Choices currentChoice;
    public Maps currentMap;
    public int choiceCounter = 0;

    public static boolean reviewAll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nextMap();
    }

    public void nextMap() {
        if (choiceCounter < HelloApplication.choicesList.size()) {
            currentChoice = HelloApplication.choicesList.get(choiceCounter);
            choiceCounter++;
            imageCounter = 0;
            if (!reviewAll && currentChoice.getPercentageBehind() == 1) {
                nextMap();
            } else {
                currentMap = null;
                int mapId = currentChoice.getId();
                for (Maps i : HelloApplication.importedMaps) {
                    if (i.getId() == mapId) {
                        currentMap = i;
                    }
                }
                if (currentMap == null) {
                    System.out.println("ERROR MAP NOT FOUND - THIS SHOULD NEVER HAPPEN");
                }

                changeImage();
                if (currentChoice.isCorrectChoice()) {
                    reviewText.setText("YOU GOT THIS ONE CORRECT. WELL DONE !");
                } else {
                    //work out which choice the user made using the percentage behind
                    reviewText.setText("YOU DID NOT GET THIS ONE RIGHT. YOU WERE " + ((currentChoice.getPercentageBehind() * 100) - 100) + " PERCENT BEHIND. IT TOOK YOU " + currentChoice.getTimeTaken() + " MILLISECONDS TO CHOOSE!");
                }
            }
        } else {
            System.out.println("RAN OUT OF CHOICES");
            backBtn();
        }

    }

    public void flagBtn() {
        System.out.println("FLAGGED");
        //need to add a way to actually note down the flagging. Create an alert and allow the user to input the error then save that to a file for me to view
    }

    public void backBtn() {
        try {
            Stage stage = new Stage();
            Scene currentScene = reviewText.getScene();
            MainScreen.LoadFinishScreen(stage, currentScene);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
