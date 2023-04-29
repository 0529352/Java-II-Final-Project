/*
Author: Jonathan Mestel
Date: 4/25/2023
Java II Final Project
File purpose: This file will run data validation for user input
*/
package DataHandling;

// --- Imports --- //
import java.time.LocalDate;
import java.lang.Exception;

public class Validation {
    
    /**
     * Written By Jonathan Mestel on 4/25/2023
     * This method validates data and ensures information has been entered
     * @param team1 the name of the first team
     * @param team2 the name of the second team
     * @param date the local date object that contains the date of the game
     * @return returns an empty string if validation is successful, otherwise returns an error message
     */
    public static String getTeamMenuValidation(String team1, String team2, LocalDate date) {
        String msg = "";
        if(team1.isEmpty()) {
            msg += "A name must be entered for Team 1\n";
        }
        if(team2.isEmpty()) {
            msg += "A name must be entered for Team 2\n";
        }
        if(date == null) {
            msg += "A valid date must be entered for the date";
        }
        return msg;
    }
    
    /**
     * Written By Jonathan Mestel on 4/26/2023
     * This method validates the data on the add player screen
     * @param FirstName First name of player
     * @param LastName Last name of player
     * @param Innings Number of innings pitched
     * @param Hits Number of hits
     * @param Runs Number of runs
     * @param EarnedRuns Number of Earned Runs
     * @param Walks Number of Walks
     * @param StrikeOuts Number of StrikeOuts
     * @param AtBats Number of At Bats
     * @param BatFaced Number of batters faced
     * @param NumberPitched Total Number of Pitches
     * @return returns an empty string if successful, otherwise returns an error message
     */
    public static String getAddPlayerValidation(String FirstName, String LastName, String Innings, String Hits, String Runs, String EarnedRuns, String Walks, String StrikeOuts, String AtBats, String BatFaced, String NumberPitched) {
        String msg = "";
        if (FirstName.isEmpty()) {
            msg += "First name cannot be empty!\n";
        }
        
        if (LastName.isEmpty()) {
            msg += "Last name cannot be empty!\n";
        }
        
        //if Innings contains a . then test to make sure it ends with a 0 1 or 2
        if (Innings.contains(".")) {
            String[] temp = Innings.split("\\.");
            if(!(temp[1].equals("2") || temp[1].equals("1") || temp[1].equals("0"))) {
                msg += "Number of Innings can only end with '.0', '.1', or '.2'!\n";
            }
        }
        
        //test Innings
        if (Innings.isEmpty()) {
            msg += "Number of Innings is required!\n";
        } else {
            try {
                double temp = Double.parseDouble(Innings);
                if (temp < 0) {
                    msg += "Can not have negative number for Innings Played!\n";
                }
            } catch (Exception e) {
                msg += "Invalid number for Innings!\n";
            }
        }
        
        //test hits
        if (Hits.isEmpty()) {
            msg += "Number of Hits is required!\n";
        } else {
            try {
                int temp = Integer.parseInt(Hits);
                if (temp < 0) {
                    msg += "Can not have negative number for Hits!\n";
                }
            } catch (Exception e) {
                msg += "Invalid number for Hits!\n";
            }
        }
        
        //test runs
        int run = 0;
        if (Runs.isEmpty()) {
            msg += "Number of Runs is required!\n";
        } else {
            try {
                run = Integer.parseInt(Runs);
                if (run < 0) {
                    msg += "Can not have negative number for Runs!\n";
                }
            } catch (Exception e) {
                msg += "Invalid number for Runs!\n";
            }
        }
        
        //test earned runs
        int Erun = 0;
        if (EarnedRuns.isEmpty()) {
            msg += "Number of Earned Runs is required!\n";
        } else {
            try {
                Erun = Integer.parseInt(EarnedRuns);
                if (Erun < 0) {
                    msg += "Can not have negative number for Earned Runs!\n";
                }
            } catch (Exception e) {
                msg += "Invalid number for Earned Runs!\n";
            }
        }
        
        if (Erun > run) {
            msg += "Can not have more earned runs than runs!\n";
        }
        
        if (Walks.isEmpty()) {
            msg += "Number of Walks is required!\n";
        } else {
            try {
                int temp = Integer.parseInt(Walks);
                if (temp < 0) {
                    msg += "Can not have negative number for Walks!\n";
                }
            } catch (Exception e) {
                msg += "Invalid number for Walks!\n";
            }
        }
        
        if (StrikeOuts.isEmpty()) {
            msg += "Number of Strike Outs is required!\n";
        } else {
            try {
                int temp = Integer.parseInt(StrikeOuts);
                if (temp < 0) {
                    msg += "Can not have negative number for Strike Outs!\n";
                }
            } catch (Exception e) {
                msg += "Invalid number for Strike Outs!\n";
            }
        }
        
        int AB = 0;
        if (AtBats.isEmpty()) {
            msg += "Number of at bats is required!\n";
        } else {
            try {
                AB = Integer.parseInt(AtBats);
                if (AB < 0) {
                    msg += "Can not have negative number for at bats!\n";
                }
            } catch (Exception e) {
                msg += "Invalid number for at bats!\n";
            }
        }
        
        int BF = 0;
        if (BatFaced.isEmpty()) {
            msg += "Number of Batters Faced is required!\n";
        } else {
            try {
                BF = Integer.parseInt(BatFaced);
                if (BF < 0) {
                    msg += "Can not have negative number for Batters Faced!\n";
                }
            } catch (Exception e) {
                msg += "Invalid number for Batters Faced!\n";
            }
        }
        
        if (BF < AB) {
            msg += "Number of Batters Face can't be less than At bats!\n";
        }
        
        if (NumberPitched.isEmpty()) {
            msg += "Number of Walks is required!\n";
        } else {
            try {
                int temp = Integer.parseInt(NumberPitched);
                if (temp < 0) {
                    msg += "Can not have negative number for Walks!\n";
                }
            } catch (Exception e) {
                msg += "Invalid number for Walks!\n";
            }
        }
        
        return msg;
    }
    
    /**
     * Written By Jonathan Mestel on 4/26/2023
     * This method checks for if a string is empty and if not, if the end of it contains .sqlite
     * @return String containing an error message if there is one, otherwise returns an empty string
     */
    public static String getFileValidation(String Filename) {
        String msg = "";
        if (Filename.isEmpty()) {
            msg += "Error: File name is empty!\n";
            //check if the file name contains a .
        } else if (Filename.contains(".")) {
            //split the filename into an array
            String[] temp = Filename.split("\\.");
            //check the last item of the array to see if it contains sqlite for the file type
            if (!(temp[temp.length - 1].equals("sqlite"))) {
                msg += "Error: File name must contain a .sqlite extension";
            }
        } else {
            msg += "Error: File name must contain a .sqlite extension";
        }
        return msg;
    }
}
