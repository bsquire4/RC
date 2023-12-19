package com.example.rc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloApplication extends Application {
    public static List<Maps> importedMaps = new ArrayList<>();
    public static List<Choices> choicesList = new ArrayList<Choices>();
    public static GameModeSettings gameModeSettings = new GameModeSettings();
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Welcome Screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        getMaps();
        HelloController.SetStart(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public void getMaps()
    {
        try {
            Scanner scanner2 = new Scanner(new File("../Counter.csv"));
            scanner2.useDelimiter(",");
            int counter = Integer.parseInt(scanner2.next());
            System.out.println("NUMBER OF MAPS SAVED: " + counter);
            scanner2.close();
        }catch (Exception e) {
            System.out.println("ERROR READING COUNTER FILE");
            System.out.println(e);
        }
        try{
            Scanner scanner = new Scanner(new File("../MapsFile.csv"));
            scanner.useDelimiter(",");

            int temp_id;
            String temp_Blanklocation;
            String temp_Routelocation;
            String temp_Distancelocation;
            int temp_numChoices;
            List<String> temp_colours = new ArrayList<>();
            int temp_correctchoice;
            List<Integer> temp_distances = new ArrayList<>();

            while(scanner.hasNext())
            {
                temp_id = Integer.parseInt(scanner.next());
                System.out.println(temp_id);
                temp_Blanklocation = scanner.next();
                System.out.println(temp_Blanklocation);
                temp_Routelocation = scanner.next();
                temp_Distancelocation= scanner.next();
                temp_numChoices = Integer.parseInt(scanner.next());
                temp_correctchoice = Integer.parseInt(scanner.next());
                for(int i = 0; i<temp_numChoices; i++)
                {
                    temp_colours.add(scanner.next());
                }
                for (int i = 0; i<temp_numChoices;i++)
                {
                    temp_distances.add(Integer.parseInt(scanner.next()));
                }
                System.out.println("CYCLE COMPLETE");

                String[] tempA_colours = temp_colours.toArray(new String[0]);
//                Integer[] tempA_distances = ((Integer[]) temp_distances.toArray());
                int[] tempA_distances = temp_distances.stream().mapToInt(i -> i).toArray();

                importedMaps.add(new Maps(temp_id,temp_Blanklocation,temp_Routelocation, temp_Distancelocation, temp_numChoices, temp_correctchoice,tempA_colours,tempA_distances));
            }
            scanner.close();
        }
        catch (Exception e)
        {
            System.out.println("ERROR READING FILE");
            System.out.println(e);
        }

    }
}

