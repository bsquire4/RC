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
    @Override
    public void start(Stage stage) throws IOException {

//        importedMaps.add(new Maps(0,"/C:/Users/Ben04/Documents/JK analysis/leg_03_80_C.png",3, 1,new String[]{"Purple", "Green", "Red", "Other"},   new Integer[]{237,246,249}));

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
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
        try
        {
            Scanner scanner = new Scanner(new File("C:/Users/Ben04/IdeaProjects/RC/src/main/resources/MapsFile.csv"));
            scanner.useDelimiter(",");
            Scanner scanner2 = new Scanner(new File("C:/Users/Ben04/IdeaProjects/RC/src/main/resources/Counter.csv"));
            scanner2.useDelimiter(",");
            int counter = Integer.parseInt(scanner2.next());
            System.out.println("NUMBER OF MAPS SAVED: " + counter);
            int temp_id;
            String temp_location;
            int temp_numChoices;
            List<String> temp_colours = new ArrayList<>();
            int temp_correctchoice;
            List<Integer> temp_distances = new ArrayList<>();

            while(scanner.hasNext())
            {
                temp_id = Integer.parseInt(scanner.next());
                System.out.println(temp_id);
                temp_location = scanner.next();
                System.out.println(temp_location);
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

                importedMaps.add(new Maps(temp_id,temp_location,temp_numChoices, temp_correctchoice,tempA_colours,tempA_distances));
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR READING FILE");
            System.out.println(e);
        }

    }
}

