/*
Author: Jonathan Mestel
Date: 5/3/2023
Java II Final Project
Purpose: This is to handle an input from the user to create a multiple game summary based on an input of game file names, then output a summary of all of those games as an arraylist of strings
*/

package FileIO;

// --- Imports --- //
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

import Classes.Pitcher;


public class MultiInput {

    public static ArrayList<String> getSummary(ArrayList<String> Filenames) {
        
        //create the main arrayList for the players objects and their teams
        ArrayList<Pitcher> Players = new ArrayList<>();
        HashMap<String,Integer> Teams = new HashMap<>();
        ArrayList<String> TeamNames = new ArrayList<>();
        
        //start for loop to load information from each file
        for(int i = 0; i < Filenames.size(); i++) {
            //connect to file
            try (Connection con = getConnection(Filenames.get(i))) {
                //search file for each team name
                Statement statement = con.createStatement();
                ResultSet teamname = statement.executeQuery("SELECT TeamName FROM StatsTable GROUP BY TeamName");

                while (teamname.next()) {
                    String Team = teamname.getString(1).toLowerCase();
                    if (Teams.containsKey(Team)) {
                        //if Teams already has the team name add 1 to the counter
                        Teams.put(Team, Teams.get(Team) + 1);
                    } else {
                        //if Teams does not, add the team name to it and set counter to 1
                        Teams.put(Team, 1);
                        TeamNames.add(Team);
                    }
                }
                
                //search file for each player
                ResultSet pitcherInfo = statement.executeQuery("SELECT * FROM StatsTable");
                
                while (pitcherInfo.next()) {
                    Pitcher p = new Pitcher();
                    p.setLastName(pitcherInfo.getString(1));
                    p.setFirstName(pitcherInfo.getString(2));
                    p.setTeamName(pitcherInfo.getString(3));
                    p.setInningsPitched(pitcherInfo.getDouble(4));
                    p.setAtBats(pitcherInfo.getInt(5));
                    p.setHits(pitcherInfo.getInt(6));
                    p.setRunScored(pitcherInfo.getInt(7));
                    p.setEarnedRuns(pitcherInfo.getInt(8));
                    p.setWalks(pitcherInfo.getInt(9));
                    p.setStrikeouts(pitcherInfo.getInt(10));
                    p.setBattersFaced(pitcherInfo.getInt(11));
                    p.setNopitches(pitcherInfo.getInt(12));
                    
                    //if the player is already present in the Players array, add their stats if not, add them to the end of the list
                    boolean found = false;
                    for(Pitcher pi : Players) {
                        if(pi.getFirstName().equalsIgnoreCase(p.getFirstName()) && pi.getLastName().equalsIgnoreCase(p.getLastName()) && pi.getTeamName().equalsIgnoreCase(p.getTeamName())) {
                            found = true;
                            // if the player with the same name and team is found, add their stats together
                            pi.setInningsPitched(combineInnings(pi.getInningsPitched(), p.getInningsPitched()));
                            pi.setAtBats(pi.getAtBats() + p.getAtBats());
                            pi.setHits(pi.getHits() + p.getHits());
                            pi.setRunScored(pi.getRunScored() + p.getRunScored());
                            pi.setEarnedRuns(pi.getEarnedRuns() + p.getEarnedRuns());
                            pi.setWalks(pi.getWalks() + p.getWalks());
                            pi.setStrikeouts(pi.getStrikeouts() + p.getStrikeouts());
                            pi.setBattersFaced(pi.getBattersFaced() + p.getBattersFaced());
                            pi.setNopitches(pi.getNopitches() + p.getNopitches());
                        }
                    }
                    
                    //if player was not found, add a new pitcher object to the arraylist
                    if(!found) {
                        Players.add(p);
                    }
                }
                
                //add each player to the list of players as a player object
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        }
        //create the main Arraylist object to return
        ArrayList<String> Output = new ArrayList<>();
        String Space = "------------------------------------------------------------------------------------";
        //create table
        for (String Name : TeamNames) {
            Output.add(Name + " - " + Teams.get(Name) + " games" +"*IP*AB*H*R*ER*BB*SO*BF*NP");
            Output.add(Space);
            for (Pitcher p : Players) {
                if (p.getTeamName().equalsIgnoreCase(Name)) {
                    String out = p.getLastName() + ", " + p.getFirstName();
                    out += "*" + p.getInningsPitched();
                    out += "*" + p.getAtBats();
                    out += "*" + p.getHits();
                    out += "*" + p.getRunScored();
                    out += "*" + p.getEarnedRuns();
                    out += "*" + p.getWalks();
                    out += "*" + p.getStrikeouts();
                    out += "*" + p.getBattersFaced();
                    out += "*" + p.getNopitches();
                    Output.add(out);
                }
            }
            Output.add("");
        }
        
        
        return Output;
    }
    
    public static Connection getConnection(String Filename) throws SQLException {
        String URL = "jdbc:sqlite:" + Filename;
        Connection connection = DriverManager.getConnection(URL);
        return connection;
    }
    
    public static double combineInnings(double n1, double n2) {
        String num1 = n1 + "";
        String num2 = n2 + "";
        
        String[] first;
        String[] second;
        if (num1.contains(".")) {
            first = num1.split("\\.");
        } else {
            first = new String[2];
            first[0] = num1;
            first[1] = "0";
        }
        
        if (num2.contains(".")) {
            second = num2.split("\\.");
        } else {
            second = new String[2];
            second[0] = num2;
            second[1] = "0";
        }
        
        //add the first two numbers together as integers
        int innings = Integer.parseInt(first[0]) + Integer.parseInt(second[0]);
        
        //add the second two numbers together as integers
        int outs =  Integer.parseInt(first[1]) + Integer.parseInt(second[1]);
        
        //calculate new number of innings based on outs
        int fullOuts = outs/3;
        
        innings = innings + fullOuts;
        
        //the remaining number of outs /3 is the new number of outs
        outs = outs % 3;
        
        String newDouble = innings + "." + outs;
        Double result = Double.parseDouble(newDouble);
        return result;
    }
}
