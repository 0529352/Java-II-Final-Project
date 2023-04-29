/*
Author: Jonathan Mestel
Date: 4/29/2023
Java II Final Project
Purpose: This file contains the code to load and save the data from the save file
*/

package FileIO;

import java.io.*;
import java.util.ArrayList;
import javafx.scene.control.Alert;

public class SaveData {
    private static final String FILENAME = "SAVEDATA.txt";
    
    /**
     * Written By Jonathan Mestel on 4/29/2023
     * This method returns the save data found within the SAVEDATA.txt file
     * @return An arraylist of string objects that contain the names of the DB files
     */
    public static ArrayList<String> getSaveData() {
        ArrayList<String> saves = new ArrayList<>();
        
        try (BufferedReader in = new BufferedReader(
                                 new FileReader(FILENAME))) {
            
            String line = in.readLine();
            while (line != null) {
                saves.add(line);
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            //if file not found, create the file then try again
            try (PrintWriter out = new PrintWriter(
                                   new BufferedWriter(
                                   new FileWriter(FILENAME)))) {
            } catch (IOException err) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("FILE ERROR");
                alert.setHeaderText("An IOException occured:");
                alert.setContentText(err.getMessage());
                alert.showAndWait();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FILE ERROR");
            alert.setHeaderText("An IOException occured:");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        
        return saves;
    }
    
    /**
     * Written By Jonathan Mestel on 4/29/2023
     * adds a new DB name to the list of saves within the saves file
     * @param SaveName a string containing the name of the DB file
     * @return returns true if successful and false if an exception occurred
     */
    public static boolean upLoadSave(String SaveName) {
        ArrayList<String> saves = getSaveData();
        saves.add(SaveName);
        
        try (PrintWriter out = new PrintWriter(
                               new BufferedWriter(
                               new FileWriter(FILENAME)))) {
            for(String name : saves) {
                out.println(name);
            }
        } catch (IOException err) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FILE ERROR");
            alert.setHeaderText("An IOException occured:");
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }
        return true;
    }
    
    /**
     * Written By Jonathan Mestel on 4/29/2023
     * updates the current list for saves
     * @param saves an arraylist of strings containing the names of the DB files
     * @return returns true if successful and false if an exception occurred
     */
    public static boolean updateSave(ArrayList<String> saves) {

        try (PrintWriter out = new PrintWriter(
                               new BufferedWriter(
                               new FileWriter(FILENAME)))) {
            for(String name : saves) {
                out.println(name);
            }
        } catch (IOException err) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FILE ERROR");
            alert.setHeaderText("An IOException occured:");
            alert.setContentText(err.getMessage());
            alert.showAndWait();
        }
        return true;
    }
}
