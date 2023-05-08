/*
Author: Jonathan Mestel
Date: 5/8/2023
Purpose: This file creates a report file based off the input recieved
*/
package FileIO;

import javafx.scene.control.Alert;
import java.io.*;
import java.util.ArrayList;

public class ReportOutput {
    /**
     * creates a report based off the input arraylist
     * @param arr the string of information to be displayed
     * @param Filename The name of the file to be created
     */
    public static void MakeReport(ArrayList<String> arr, String Filename) {
        //create file
        try (PrintWriter out = new PrintWriter(
                               new BufferedWriter(
                               new FileWriter(Filename)))) {
            
            //create each row of file then close file after closing file alert the user of success
            for (String line : arr) {
                //if the line contains *, split the line into an array else treat it as its own line
                if (line.contains("*")) {
                    String[] items = line.split("\\*");
                    out.print(toLength(items[0], 20));
                    for (int i = 1; i < items.length; i++) {
                        out.print(fromLength(items[i], 6));
                    }
                    out.println();
                } else {
                    out.println(line);
                }
            }
            
            out.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success!");
            alert.setContentText("File saved as: " + Filename);
            alert.showAndWait();
            
        } catch (IOException e) {
            //if there is an exception, alert the user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("An Error Has Occured:");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    public static String toLength(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        } else {
            while (str.length() < len) {
                str = str + " ";
            }
            return str;
        }
    }
    
    public static String fromLength(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        } else {
            while (str.length() < len) {
                str = " " + str;
            }
            return str;
        }
    }
}
