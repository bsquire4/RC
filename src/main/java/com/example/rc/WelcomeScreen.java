package com.example.rc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WelcomeScreen {
    public ChoiceBox SVBmapchoiceBox;
    public ChoiceBox SVBtimechoiceBox;
    public ChoiceBox SVBnumberchoiceBox;
    public VBox SingleViewBox;
    public VBox BlinkViewBox;
    public VBox RandomVewBox;
    public ChoiceBox BVBmapchoiceBox;
    public ChoiceBox BVBnumberchoiceBox;
    public ChoiceBox RVBmapchoiceBox;
    public ChoiceBox RVBnumberchoiceBox;
    public ChoiceBox RVBglancechoiceBox;
    public ChoiceBox BVBtimelengthchoiceBox;
    public ChoiceBox BVBglancechoiceBox;

    public void start()
    {
        SingleViewBox.setDisable(true);
        BlinkViewBox.setDisable(true);
        RandomVewBox.setDisable(true);


        ObservableList<String> tempList = FXCollections.observableArrayList();;
        tempList.add("Blank Map");
        tempList.add("Route Map");
        SVBmapchoiceBox.setItems(tempList);
        BVBmapchoiceBox.setItems(tempList);
        RVBmapchoiceBox.setItems(tempList);

        ObservableList<String> tempLst2 = FXCollections.observableArrayList();;
        tempLst2.add("2");
        tempLst2.add("4");
        tempLst2.add("8");
        tempLst2.add("16");
        tempLst2.add("Till space pressed");
        SVBtimechoiceBox.setItems(tempLst2);
        BVBglancechoiceBox.setItems(tempLst2);


        ObservableList<String> tempList3 = FXCollections.observableArrayList();;
        tempList3.add("5");
        tempList3.add("10");
        tempList3.add("20");
        tempList3.add("30");
        tempList3.add("Till Esc pressed");
        SVBnumberchoiceBox.setItems(tempList3);
        RVBnumberchoiceBox.setItems(tempList3);
        BVBnumberchoiceBox.setItems(tempList3);

        ObservableList<String> tempList4 = FXCollections.observableArrayList();;
        tempList4.add("1");
        tempList4.add("2");
        tempList4.add("4");
        tempList4.add("6");
        tempList4.add("Till space pressed");
        RVBglancechoiceBox.setItems(tempList4);

        ObservableList<String> tempList5 = FXCollections.observableArrayList();;
        tempList5.add("0.5");
        tempList5.add("1");
        tempList5.add("1.5");
        tempList5.add("2");
        tempList5.add("Till space pressed");
        BVBtimelengthchoiceBox.setItems(tempList5);

    }

    public void onSingleBtnPress(ActionEvent event) {
        SingleViewBox.setDisable(false);
        SingleViewBox.setVisible(true);
        RandomVewBox.setDisable(true);
        RandomVewBox.setVisible(false);
        BlinkViewBox.setDisable(true);
        BlinkViewBox.setVisible(false);
    }

    public void onGlanceBtnPress(ActionEvent event) {
        SingleViewBox.setDisable(true);
        SingleViewBox.setVisible(false);
        RandomVewBox.setDisable(true);
        RandomVewBox.setVisible(false);
        BlinkViewBox.setDisable(false);
        BlinkViewBox.setVisible(true);

    }

    public void onRandomBtnPress(ActionEvent event) {
        SingleViewBox.setDisable(true);
        SingleViewBox.setVisible(false);
        RandomVewBox.setDisable(false);
        RandomVewBox.setVisible(true);
        BlinkViewBox.setDisable(true);
        BlinkViewBox.setVisible(false);

    }

    public void onStartBtnPress() {
        boolean isValid = true;
        if(!SingleViewBox.isDisabled())
        {
            boolean tempMapRoute = false;
            //set singleView stuff
            if(SVBmapchoiceBox.getValue() == "Route Map")
            {
                tempMapRoute = true;
            }else if (SVBmapchoiceBox.getValue() == "Blank Map")
            {
                tempMapRoute = false;
            }else {
                isValid = false;
            }

            int tempNumRC = 0;
            if(SVBnumberchoiceBox.getValue() == "Till Esc pressed")
            {
                tempNumRC = 0;
            } else if (SVBnumberchoiceBox.getValue() == null) {
                isValid = false;
            }
            else
            {
                tempNumRC = Integer.parseInt((String) SVBnumberchoiceBox.getValue());
            }

            int tempTimeLength = 0;
            if(SVBtimechoiceBox.getValue() == "Till space pressed")
            {
                tempTimeLength = 0;
            } else if (SVBtimechoiceBox.getValue() == null) {
                isValid = false;
            }else {
                tempTimeLength = Integer.parseInt((String) SVBtimechoiceBox.getValue());
            }

            if(isValid)
            {
                HelloApplication.gameModeSettings.SingleViewMode(tempMapRoute,tempNumRC, tempTimeLength);
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Not chosen all your settings");
                alert.showAndWait();
            }

        } else if (!RandomVewBox.isDisabled()) {
            // set randomView stuff
            boolean tempMapRoute = false;
            //set singleView stuff
            if(RVBmapchoiceBox.getValue() == "Route Map")
            {
                tempMapRoute = true;
            }else if (RVBmapchoiceBox.getValue() == "Blank Map")
            {
                tempMapRoute = false;
            }else {
                isValid = false;
            }

            int tempNumRC = 0;
            if(RVBnumberchoiceBox.getValue() == "Till Esc pressed")
            {
                tempNumRC = 0;
            } else if (RVBnumberchoiceBox.getValue() == null) {
                isValid = false;
            }
            else
            {
                tempNumRC = Integer.parseInt((String) RVBnumberchoiceBox.getValue());
            }

            int tempnumglances = 0;
            if(RVBglancechoiceBox.getValue() == "Till space pressed")
            {
                tempnumglances = 0;
            } else if (RVBglancechoiceBox.getValue() == null) {
                isValid = false;
            }else {
                tempnumglances = Integer.parseInt((String) RVBglancechoiceBox.getValue());
            }

            if(isValid)
            {
                HelloApplication.gameModeSettings.RandomViewMode(tempMapRoute,tempNumRC,tempnumglances);
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Not chosen all your settings");
                alert.showAndWait();
            }
        }
        else if (!BlinkViewBox.isDisabled()){
            // set GlanceView stuff
            boolean tempMapRoute = false;
            //set singleView stuff
            if(BVBmapchoiceBox.getValue() == "Route Map")
            {
                tempMapRoute = true;
            }else if (BVBmapchoiceBox.getValue() == "Blank Map")
            {
                tempMapRoute = false;
            }else {
                isValid = false;
            }

            int tempNumRC = 0;
            if(BVBnumberchoiceBox.getValue() == "Till Esc pressed")
            {
                tempNumRC = 0;
            } else if (BVBnumberchoiceBox.getValue() == null) {
                isValid = false;
            }
            else
            {
                tempNumRC = Integer.parseInt((String) BVBnumberchoiceBox.getValue());
            }

            float tempTimeLength = 0f;
            if(BVBtimelengthchoiceBox.getValue() == "Till space pressed")
            {
                tempTimeLength = 0f;
            } else if (BVBtimelengthchoiceBox.getValue() == null) {
                isValid = false;
            }else {
                tempTimeLength = Float.parseFloat((String) BVBtimelengthchoiceBox.getValue()) ;
            }

            int tempNumGlances = 0;
            if(BVBglancechoiceBox.getValue() == "Till space pressed")
            {
                tempNumGlances = 0;
            } else if (BVBglancechoiceBox.getValue() == null) {
                isValid = false;
            }else {
                tempNumGlances = Integer.parseInt((String) BVBglancechoiceBox.getValue());
            }

            if(isValid)
            {
                HelloApplication.gameModeSettings.GlanceViewMode(tempMapRoute,tempNumRC,tempTimeLength,tempNumGlances);
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Not chosen all your settings");
                alert.showAndWait();
            }
        }

        if(isValid)
        {
            try
            {
                Stage stage = new Stage();
                Scene currentScene = SVBnumberchoiceBox.getScene();
                Stage currentStage = (Stage) currentScene.getWindow();
                currentStage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.show();
                HelloController.SetStart(stage);

            }catch (Exception e)
            {
                System.out.println(e);
            }

        }
    }

}
