/*
Author: Jonathan Mestel
Date: 4/29/2023
Java II Final Project
Purpose: This file contains the code to run the help screen menus
*/

package DataHandling;

import javafx.scene.control.Alert;

public class Help {
    
    /**
     * Written by Jonathan Mestel on 4/29/2023
     * This method creates and displays an informational alert that tells the user more about the page they are on
     * @param menuNumber The number corresponding to the GUI menu number
     */
    public static void getHelpMenu(int menuNumber) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        
        String head = "";
        String msg = "";
        
        if (menuNumber == 0) {
            head = "Main Menu Help:";
            msg = "Hello, and welcome to the Pitcher Team Application!\n";
            msg += "\n";
            msg += "The Pitcher Team Application allows you to enter in statistical data for players";
            msg += " and calculate records based off the data entered.\n";
            msg += "\n";
            msg += "If you would like to create a new record of a game, click \"Create Game Record\".\n";
            msg += "\n";
            msg += "If you want to view existing record data, click \"View Game Record(s)\".\n";
            msg += "\n";
            msg += "If you would like to manually add a record file or remove a record file from the ";
            msg += "list, select \"Manage Saved Records\".\n";
        } else if (menuNumber == 1) {
            head = "Creating A Record:";
            msg = "To get started on creating a record, simply enter in the name of the two teams that had played each other.\n";
            msg += "\n";
            msg += "Then select the date that the game took place on in the drop down menu under \"Date of the Game\".\n";
        } else if (menuNumber == 2) {
            head = "Creating A Record";
            msg = "On this screen, enter in the players information and then click the \"Add\" button to add the player and their information to the record.\n";
            msg += "\n";
            msg += "When adding in a players details, no negative numbers can be added and Innings played must end with a 1 2 or 0 for the number of outs.\n";
            msg += "\n";
            msg += "After adding players, if you make a mistake or wish to remove a players record, click on the name of the player from the list then click the \"Remove\" button to remove that player from the list.\n";
            msg += "\n";
            msg += "Once all players information is entered, press the \"Create File\" button. You will then recieve a message saying the name of the save that was created.\n";
        } else if (menuNumber == 3) {
            head = "Viewing A Record";
            msg += "To view a record, click on the name of the record you wish to view. Then click \"Load File\".\n";
            msg += "\n";
            msg += "If you wish to see a combined summary of multiple games, select multiple files to view. To select multiple files, hold down the \"CTRL\" key on your keyboard then press the \"Load File\" button to view the summary.\n";
        } else if (menuNumber == 4) {
            head = "Adding/Removing files manually:";
            msg += "To manually add a file, type the full name of the file and the directory it can be found in.\n";
            msg += "\n";
            msg += "Removing a file from the list does not delete the save record. It simply removes the record from the available list within the program. To delete a save file, locate your save files within the JAVA II FINAL PROJECT folder and then delete the record you wish to get rid of.\n";
        } else if (menuNumber == 5) {
            head = "Reading statistics:";
            msg += "IP - Number of Innings played (decimal represents number of outs)\n";
            msg += "\n";
            msg += "H - Number of Hits a batter had gotten against them\n";
            msg += "\n";
            msg += "R - Number of Runs scored against a pitcher\n";
            msg += "\n";
            msg += "ER - Earned Runs that were scored against a pitcher\n";
            msg += "\n";
            msg += "BB - Number of walks given by the pitcher\n";
            msg += "\n";
            msg += "SO - Number of Strike outs the pitcher had gotten\n";
            msg += "\n";
            msg += "AB - Number of \"At bats\" faced\n";
            msg += "\n";
            msg += "BF - Total Batters Faced\n";
            msg += "\n";
            msg += "NP - Number of pitches\n";
            msg += "\n";
            msg += "EAR - Earned Average Runs\n";
        }
        
        alert.setHeaderText(head);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
