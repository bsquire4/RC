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
    private HBox HB;
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

    @FXML
    protected void onHelloButtonClick() {
        setButtons(2);
    }

    @FXML
    public static void SetStart(Stage stage) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth() *0.8;
        height = screenSize.getHeight() *0.8;

        VBox layout = new VBox(10);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setX((screenSize.getWidth() - width) / 2);
        stage.setY((screenSize.getHeight() - height) / 2);

    }

    @FXML
    public  void setButtons(int choices)  {





        HB.setPrefWidth(width);
        DisableBtn(Btn1);
        DisableBtn(Btn2);
        DisableBtn(Btn3);
        DisableBtn(Btn4);
        DisableBtn(Btn5);
        switch (choices)
        {
            case 2:
                //Btn1,2,3 in use
                EnableBtn(Btn1);
                EnableBtn(Btn2);
                EnableBtn(Btn3);
                DisableBtn(Btn4);
                DisableBtn(Btn5);
                break;
            case 3:
                EnableBtn(Btn1);
                EnableBtn(Btn2);
                EnableBtn(Btn3);
                EnableBtn(Btn4);
                DisableBtn(Btn5);
                break;
            case 4:
                EnableBtn(Btn1);
                EnableBtn(Btn2);
                EnableBtn(Btn3);
                EnableBtn(Btn4);
                EnableBtn(Btn5);
                break;


        }
        try{
            InputStream stream = new FileInputStream("C:/Users/Ben04/Pictures/20230528_192718.jpg");
            Image image = new Image(stream);
            ImageBox.setImage(image);
            ImageBox.setFitHeight(height - Btn1.getPrefHeight() - 50);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println(HB.getHeight());

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
    }


}
