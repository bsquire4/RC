package com.example.rc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeScreen implements Initializable {
    public ChoiceBox SVBmapchoiceBox;
    public ChoiceBox SVBtimechoiceBox;
    public ChoiceBox SVBnumberchoiceBox;
    public VBox SingleViewBox;
    public VBox GlanceViewBox;
    public VBox RandomVewBox;
    public ChoiceBox GVBmapchoiceBox;
    public ChoiceBox GVBnumberchoiceBox;
    public ChoiceBox RVBmapchoiceBox;
    public ChoiceBox RVBnumberchoiceBox;
    public ChoiceBox RVBglancechoiceBox;
    public ChoiceBox BVBtimelengthchoiceBox;
    public ChoiceBox GVBglancechoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SingleViewBox.setDisable(true);
        GlanceViewBox.setDisable(true);
        RandomVewBox.setDisable(true);

        ObservableList<String> tempList = FXCollections.observableArrayList();
        ;
        tempList.add("Blank Map");
        tempList.add("Route Map");
        SVBmapchoiceBox.setItems(tempList);
        SVBmapchoiceBox.setValue("Route Map");
        GVBmapchoiceBox.setItems(tempList);
        GVBmapchoiceBox.setValue("Route Map");
        RVBmapchoiceBox.setItems(tempList);
        RVBmapchoiceBox.setValue("Route Map");


        ObservableList<String> tempLst2 = FXCollections.observableArrayList();
        ;
        tempLst2.add("2");
        tempLst2.add("4");
        tempLst2.add("8");
        tempLst2.add("16");
        tempLst2.add("Unlimited");
        SVBtimechoiceBox.setItems(tempLst2);
        SVBtimechoiceBox.setValue("4");
        GVBglancechoiceBox.setItems(tempLst2);
        GVBglancechoiceBox.setValue("Unlimited");


        ObservableList<String> tempList3 = FXCollections.observableArrayList();
        ;
        tempList3.add("5");
        tempList3.add("10");
        tempList3.add("20");
        tempList3.add("30");
        tempList3.add("Till Esc pressed");
        SVBnumberchoiceBox.setItems(tempList3);
        SVBnumberchoiceBox.setValue("10");
        RVBnumberchoiceBox.setItems(tempList3);
        RVBnumberchoiceBox.setValue("10");
        GVBnumberchoiceBox.setItems(tempList3);
        GVBnumberchoiceBox.setValue("10");

        ObservableList<String> tempList4 = FXCollections.observableArrayList();
        ;
        tempList4.add("1");
        tempList4.add("2");
        tempList4.add("4");
        tempList4.add("6");
        tempList4.add("Unlimited");
        RVBglancechoiceBox.setItems(tempList4);
        RVBglancechoiceBox.setValue("4");

        ObservableList<String> tempList5 = FXCollections.observableArrayList();
        ;
        tempList5.add("0.5");
        tempList5.add("1");
        tempList5.add("1.5");
        tempList5.add("2");
        tempList5.add("Toggle view with 'q'");
        BVBtimelengthchoiceBox.setItems(tempList5);
        BVBtimelengthchoiceBox.setValue("Toggle view with 'q'");

        onSingleBtnPress(new ActionEvent());
    }

    public void onSingleBtnPress(ActionEvent event) {
        SingleViewBox.setDisable(false);
        SingleViewBox.setVisible(true);
        RandomVewBox.setDisable(true);
        RandomVewBox.setVisible(false);
        GlanceViewBox.setDisable(true);
        GlanceViewBox.setVisible(false);
    }

    public void onGlanceBtnPress(ActionEvent event) {
        SingleViewBox.setDisable(true);
        SingleViewBox.setVisible(false);
        RandomVewBox.setDisable(true);
        RandomVewBox.setVisible(false);
        GlanceViewBox.setDisable(false);
        GlanceViewBox.setVisible(true);
    }

    public void onRandomBtnPress(ActionEvent event) {
        SingleViewBox.setDisable(true);
        SingleViewBox.setVisible(false);
        RandomVewBox.setDisable(false);
        RandomVewBox.setVisible(true);
        GlanceViewBox.setDisable(true);
        GlanceViewBox.setVisible(false);
    }

    public void onStartBtnPress() {
        boolean isValid = true;
        if (!SingleViewBox.isDisabled()) {
            //set singleView stuff
            boolean tempMapRoute = false;
            if (SVBmapchoiceBox.getValue() == "Route Map") {
                tempMapRoute = true;
            } else if (SVBmapchoiceBox.getValue() == "Blank Map") {
                tempMapRoute = false;
            } else {
                isValid = false;
            }

            int tempNumRC = 0;
            if (SVBnumberchoiceBox.getValue() == "Till Esc pressed") {
                tempNumRC = 1000;
            } else if (SVBnumberchoiceBox.getValue() == null) {
                isValid = false;
            } else {
                tempNumRC = Integer.parseInt((String) SVBnumberchoiceBox.getValue());
            }

            int tempTimeLength = 0;
            if (SVBtimechoiceBox.getValue() == "Unlimited") {
                tempTimeLength = 0;
            } else if (SVBtimechoiceBox.getValue() == null) {
                isValid = false;
            } else {
                tempTimeLength = Integer.parseInt((String) SVBtimechoiceBox.getValue());
            }

            if (isValid) {
                HelloApplication.gameModeSettings.SingleViewMode(tempMapRoute, tempNumRC, tempTimeLength);
            } else {
                alert();
            }

        } else if (!RandomVewBox.isDisabled()) {
            // set randomView stuff
            boolean tempMapRoute = false;
            if (RVBmapchoiceBox.getValue() == "Route Map") {
                tempMapRoute = true;
            } else if (RVBmapchoiceBox.getValue() == "Blank Map") {
                tempMapRoute = false;
            } else {
                isValid = false;
            }

            int tempNumRC = 0;
            if (RVBnumberchoiceBox.getValue() == "Till Esc pressed") {
                tempNumRC = 1000;
            } else if (RVBnumberchoiceBox.getValue() == null) {
                isValid = false;
            } else {
                tempNumRC = Integer.parseInt((String) RVBnumberchoiceBox.getValue());
            }

            int tempnumglances = 0;
            if (RVBglancechoiceBox.getValue() == "Unlimited") {
                tempnumglances = 0;
            } else if (RVBglancechoiceBox.getValue() == null) {
                isValid = false;
            } else {
                tempnumglances = Integer.parseInt((String) RVBglancechoiceBox.getValue());
            }

            if (isValid) {
                HelloApplication.gameModeSettings.RandomViewMode(tempMapRoute, tempNumRC, tempnumglances);
            } else {
                alert();
            }
        } else if (!GlanceViewBox.isDisabled()) {
            // set GlanceView stuff
            boolean tempMapRoute = false;
            //set singleView stuff
            if (GVBmapchoiceBox.getValue() == "Route Map") {
                tempMapRoute = true;
            } else if (GVBmapchoiceBox.getValue() == "Blank Map") {
                tempMapRoute = false;
            } else {
                isValid = false;
            }

            int tempNumRC = 0;
            if (GVBnumberchoiceBox.getValue() == "Till Esc pressed") {
                tempNumRC = 1000;
            } else if (GVBnumberchoiceBox.getValue() == null) {
                isValid = false;
            } else {
                tempNumRC = Integer.parseInt((String) GVBnumberchoiceBox.getValue());
            }

            float tempTimeLength = 0f;
            if (BVBtimelengthchoiceBox.getValue() == "Toggle view with 'q'") {
                tempTimeLength = 0f;
            } else if (BVBtimelengthchoiceBox.getValue() == null) {
                isValid = false;
            } else {
                tempTimeLength = Float.parseFloat((String) BVBtimelengthchoiceBox.getValue());
            }

            int tempNumGlances = 0;
            if (GVBglancechoiceBox.getValue() == "Unlimited") {
                tempNumGlances = 1000000;
            } else if (GVBglancechoiceBox.getValue() == null) {
                isValid = false;
            } else {
                tempNumGlances = Integer.parseInt((String) GVBglancechoiceBox.getValue());
            }

            if (isValid) {
                HelloApplication.gameModeSettings.GlanceViewMode(tempMapRoute, tempNumRC, tempTimeLength, tempNumGlances);
            } else {
                alert();
            }
        }

        if (isValid) {
            try {
                Stage stage = new Stage();
                Scene currentScene = SVBnumberchoiceBox.getScene();
                Stage currentStage = (Stage) currentScene.getWindow();
                currentStage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GameScreen.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                stage.setTitle("Hello!");
                stage.setScene(scene);
                MainScreen.SetStage(stage);
                stage.show();
            } catch (Exception e) { System.out.println(e);}
        }
    }

    private static void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Not chosen all your settings");
        alert.showAndWait();
    }
}
