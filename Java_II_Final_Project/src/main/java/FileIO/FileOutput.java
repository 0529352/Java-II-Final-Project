/**
 *
 * @author Troy Harrison
 */
package FileIO;
import java.util.ArrayList;
import java.sql.*;
import java.io.*;
import Classes.Pitcher; 

public class FileOutput {
    public static String Output(String filename, ArrayList <Pitcher> names){
        // open an output stream 
        try (
            PrintWriter out = new PrintWriter(
                          new BufferedWriter(
                          new FileWriter(filename)))) {
            out.close();
        } catch (Exception E){
            return E.getMessage();
        } 
        String URL = "jdbc:sqlite:" + filename;
        try (
            Connection connection = DriverManager.getConnection(URL)){
            String pitcherDatabase = "CREATE TABLE StatsTable("
                    + "LastName TEXT    ,"
                    + "FirstName TEXT   ,"
                    + "TeamName TEXT    ,"
                    + "ip   REAL        ,"
                    + "ab   INTEGER     ,"
                    + "h    INTEGER     ,"
                    + "r    INTEGER     ,"
                    + "er   INTEGER     ,"
                    + "w    INTEGER     ,"
                    + "so   INTEGER     ,"
                    + "tbf  INTEGER     ,"
                    + "np   INTEGER     "
                    + ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(pitcherDatabase);
            for (int i = 0; i < names.size(); i++) {
                Pitcher pitcher = names.get(i);
                String insert = "INSERT INTO StatsTable VALUES ("
                        + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(insert);
                ps.setString(1, pitcher.getLastName());
                ps.setString(2, pitcher.getFirstName());
                ps.setString(3, pitcher.getTeamName());
                ps.setString(4, pitcher.getInningsPitched()+"");
                ps.setString(5, pitcher.getAtBats()+"");
                ps.setString(6, pitcher.getHits()+"");
                ps.setString(7, pitcher.getRunScored()+"");
                ps.setString(8, pitcher.getEarnedRuns()+"");
                ps.setString(9, pitcher.getWalks()+"");
                ps.setString(10, pitcher.getStrikeouts()+"");
                ps.setString(11, pitcher.getBattersFaced()+"");
                ps.setString(12, pitcher.getNopitches()+"");
                ps.executeUpdate();
            }
            return "";
        } catch (SQLException E) {
            return E.getMessage();
        }               
    }
}