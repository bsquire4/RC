package com.example.rc;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.lang.Object;


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
        DisableBtn(StartBtn);
        setButtons();

    }

    @FXML
    protected void onChoiceBtn1Click()
    {
        System.out.println("Btn1 Pressed -" + Map.getColours()[0]);
        float temp = (float)(Map.getDistances()[0]) / (float)(Map.getDistances()[Map.getCorrectChoice()-1]);
        Choices tempchoice = new Choices(Map.id, temp);
        System.out.println(tempchoice.toString());
        HelloApplication.choicesList.add(tempchoice);
        setButtons();
    }

    @FXML
    protected void onChoiceBtn2Click()
    {
        System.out.println("Btn2 Pressed -" + Map.getColours()[1]);
        float temp = (float)(Map.getDistances()[1]) / (float)(Map.getDistances()[Map.getCorrectChoice()-1]);
        Choices tempchoice = new Choices(Map.id, temp);
        System.out.println(tempchoice.toString());
        HelloApplication.choicesList.add(tempchoice);
        setButtons();
    }

    @FXML
    protected void onChoiceBtn3Click()
    {
        System.out.println("Btn3 Pressed -" + Map.getColours()[2]);
        float temp = (float)(Map.getDistances()[2]) / (float)(Map.getDistances()[Map.getCorrectChoice()-1]);
        Choices tempchoice = new Choices(Map.id, temp);
        System.out.println(tempchoice.toString());
        HelloApplication.choicesList.add(tempchoice);
        setButtons();
    }

    @FXML
    protected void onChoiceBtn4Click()
    {
        System.out.println("Btn4 Pressed -" + Map.getColours()[2]);
        float temp = (float)(Map.getDistances()[3]) / (float)(Map.getDistances()[Map.getCorrectChoice()-1]);
        Choices tempchoice = new Choices(Map.id, temp);
        System.out.println(tempchoice.toString());
        HelloApplication.choicesList.add(tempchoice);
        setButtons();
    }

    @FXML
    protected void onChoiceBtn5Click()
    {
        System.out.println("Btn5 Pressed - Other");
        Choices tempchoice = new Choices(Map.id, Float.NaN);
        System.out.println(tempchoice.toString());
        HelloApplication.choicesList.add(tempchoice);
        setButtons();
    }

    @FXML
    public static void SetStart(Stage stage) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth() *0.8;
        height = screenSize.getHeight() *0.8;
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setX((screenSize.getWidth() - width) / 2);
        stage.setY((screenSize.getHeight() - height) / 2);
    }

    private int counter = 0;

    @FXML
    public void setButtons()  {

        Map = HelloApplication.importedMaps.get(mapCounter);
        System.out.println(Map);
        mapCounter++;


        System.out.println(Btn1.getHeight());

        DisableBtn(Btn1);
        DisableBtn(Btn2);
        DisableBtn(Btn3);
        DisableBtn(Btn4);

        HB.setPrefWidth(width);
        HBox.setHgrow(Btn5,Priority.ALWAYS);
        counter = 0;

        switch (Map.getNumChoices())
        {
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
        try{
            InputStream stream = new FileInputStream(Map.getLocation());
            Image image = new Image(stream);
            ImageBox.setImage(image);
            ImageBox.setFitHeight(height - Btn1.getPrefHeight() - 50);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    
    public void DisableBtn(Button Btn)
    {
        Btn.setDisable(true);
        Btn.setVisible(false);
        HB.getChildren().remove(Btn);
        HBox.setHgrow(Btn,Priority.NEVER);

    }
    public void EnableBtn(Button Btn)
    {
        Btn.setDisable(false);
        Btn.setVisible(true);
        HB.getChildren().add(Btn);
        HBox.setHgrow(Btn,Priority.ALWAYS);
        Btn.setText(Map.getColours()[counter]);
        counter ++;
    }


}
