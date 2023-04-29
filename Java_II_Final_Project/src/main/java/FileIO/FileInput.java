/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Classes.Pitcher;



public class FileInput {
    //static String formatLengthName = "%-20.20s"; //controls length of last name and first name
    //static String formatLengthNumber = "%-5.5s"; //controls length of the rest of the data
    private static Connection connection;
    
    
    public static String getPitcherStats(Pitcher p) {
        
        
        //String.format(formatLength,p.getLastName() + ", " + p.getFirstName())
        String stringLine
                = p.getLastName() + ", " + p.getFirstName() + "*"
                + p.getIp() + "*"
                + p.getHits() + "*"
                + p.getRuns() + "*"
                + p.getEarnedRuns() + "*"
                + p.getWalks() + "*"
                + p.getStrikeOuts() + "*"
                + p.getAtBats() + "*"
                + p.getTotalBattersFaced() + "*"
                + p.getNumberOfPitches() + "*"
                + "\n"
                ;

        return stringLine;
    }
    
    
    //order by team----------
    public static ArrayList<String> PitcherStatistics(String SQLFile) {
        
        //String that collects all pitchers info and gets returned.\
        String topRow = "ip*"
                + "h*"
                + "r*"
                + "er*"
                + "bb*"
                + "so*"
                + "ab*"
                + "bf*"
                + "np*"
                +"\n";
        
        String dashes = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        ArrayList<String> Stats = new ArrayList<>();
        String teamNameCheck;
        // open connection
        try {
            String dbUrl = "jdbc:sqlite:" + SQLFile;
            connection = DriverManager.getConnection(dbUrl);        
        } catch (SQLException e) {
            System.err.println(e);
            //return "There was an error in connecting to SQL File";
        }  
        
        // add code that prints the task with the specified completed status
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM StatsTable ORDER BY TeamName")) {
            
            //create a pitcher object and get the first team's name, adds it to the string and assigns the check variable for separating the two teams with ------
            ResultSet rs = ps.executeQuery();
            Pitcher p = new Pitcher(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12));
            //add team name and break line to the start of the string
            teamNameCheck = p.getTeamName();
            Stats.add(teamNameCheck + "*" + topRow);
            Stats.add(dashes);
            while (rs.next()){
                //create a pitcher class for each row
                p = new Pitcher(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12));
                //use the created pitcher class to get a string and add it to the string variable that gets returned.
                //if the currently looked at pitcher has a different team name, adds ---- to separate and adds the second team's name
                if (!teamNameCheck.equalsIgnoreCase(p.getTeamName())){
                    Stats.add(dashes);
                    teamNameCheck = p.getTeamName();
                    Stats.add(teamNameCheck+ "*"  + topRow);
                    Stats.add(dashes);
                }
                //uses the getPitcherStats method to add a line of information gotten from the pitcher class object
                Stats.add(getPitcherStats(p));
            }            
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        // close connection
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);            
        }
        return Stats;
    }
    

    //pitcher class object and SQL File ex:("jdbc:sqlite:task_list.sqlite")
    //adds a row with stats given to the class into the sql file given.
    public static void insertPitcher(Pitcher p, String SQLFile) { 
        // open connection
        try {
            String dbUrl = "jdbc:sqlite:" + SQLFile;
            connection = DriverManager.getConnection(dbUrl);        
        } catch (SQLException e) {
            System.err.println(e);
            return;
        }  

        String insertPitcher
                = "INSERT INTO StatsTable (LastName, FirstName, TeamName, ip, ab, h, r, er, w, so, tbf, np) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try (PreparedStatement ps = connection.prepareStatement(insertPitcher)) {
            
            
            ps.setString(1,p.getLastName());
            ps.setString(2,p.getFirstName());
            ps.setString(3,p.getTeamName());
            ps.setDouble(4,p.getPitched());
            ps.setInt(5,p.getAtBats());
            ps.setInt(6,p.getHits());
            ps.setInt(7,p.getRuns());
            ps.setInt(8,p.getEarnedRuns());
            ps.setInt(9,p.getWalks());
            ps.setInt(10,p.getStrikeOuts());
            ps.setInt(11,p.getTotalBattersFaced());
            ps.setInt(12,p.getNumberOfPitches());
            ps.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        // close connection
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);            
        }
    }

    
    
    
    public static void CreateTable(){
        
        // Open a connection "jdbc:sqlite:StatsTable.sqlite"
      try(Connection conn = DriverManager.getConnection("jdbc:sqlite:StatsTable.sqlite");
         Statement stmt = conn.createStatement();
      ) {	//"INSERT INTO StatsTable (LastName, FirstName, TeamName, ip, ab, h, r, er, w, so, tbf, np) "	      
          String sql = "CREATE TABLE StatsTable " +
                   " (LastName VARCHAR(255), " +
                   " FirstName VARCHAR(255), " + 
                   " TeamName VARCHAR(255), " + 
                   " ip INTEGER, " + 
                   " ab INTEGER, " + 
                   " h INTEGER, " + 
                  " r INTEGER, " + 
                  " er INTEGER, " + 
                  " w INTEGER, " + 
                  " so INTEGER, " + 
                  " tbf INTEGER, " + 
                  " np INTEGER )"
                  ; 

         stmt.executeUpdate(sql);
         System.out.println("Created table in given database...");   	  
      } catch (SQLException e) {
         e.printStackTrace();
      }   
        
    }
    
    
}
