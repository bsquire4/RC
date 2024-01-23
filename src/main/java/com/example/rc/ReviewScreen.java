package com.example.rc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ReviewScreen {
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
            if (imageFiles[imageCounter] == null) {
                stream = new FileInputStream("C:/Users/Ben04/Downloads/EM104989.jpg");
            } else {
                stream = new FileInputStream(imageFiles[imageCounter]);
            }
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
    public void nextMap() {
        if(choiceCounter < HelloApplication.choicesList.size())
        {
            currentChoice = HelloApplication.choicesList.get(choiceCounter);
            choiceCounter++;
            imageCounter = 0;
            if (reviewAll == false && currentChoice.getPercentageBehind() == 1) {
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
                    //work out which chocice the user made using the percentage behind
                    reviewText.setText("YOU DID NOT GET THIS ONE RIGHT. YOU WERE " + ((currentChoice.getPercentageBehind() * 100) - 100) + " PERCENT BEHIND");
                }
            }
        }else {
            System.out.println("RAN OUT OF CHOICES");
        }

    }

    public void flagBtn(ActionEvent event) {
    }
}
