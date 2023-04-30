/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/*
Created By Joice
*/
package Classes;


public class Pitcher {

    private String firstName;
    private String lastName;
    private String teamName;
    private int nopitches;
    private int battersFaced;
    private int strikeouts;
    private double inningsPitched;
    private int hits;
    private int runScored;
    private int earnedRuns;
    private int walks;
    private int atBats;
    //private int strikes;
    //private int hitByPitch;
    private double era;
    
    public Pitcher(){
        this.firstName = "";
        this.lastName = "";
        this.teamName = "";
        this.nopitches = 0;
        this.battersFaced = 0;
        this.strikeouts = 0;
        this.inningsPitched= 0.0;
        this.hits = 0;
        this.runScored = 0;
        this.earnedRuns = 0;
        this.walks = 0;
        this.walks = 0;
        this.atBats = 0;
        //this.strikes = 0;
        //this.hitByPitch = 0;
    }
    
    /*
    Pitcher constructor made by Colin
    edited slightly by Jon
    */
    public Pitcher(String fn, String ln, String tm, double ip, int hits, int runs, int earnedRuns, int walks, int strikeOuts, int atBats, int totalBattersFaced, int numberOfPitches){
        
        firstName = fn;
        lastName = ln;
        teamName = tm;
        this.inningsPitched = ip;
        this.hits = hits;
        this.runScored = runs;
        this.earnedRuns = earnedRuns;
        this.walks = walks;
        this.strikeouts = strikeOuts;
        this.atBats = atBats;
        this.battersFaced = totalBattersFaced;
        this.nopitches = numberOfPitches;   
    }

    public String getFirstName() {
        return firstName;
    }

   
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    public String getLastName() {
        return lastName;
    }

    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
  
    
    public int getNopitches() {
        return nopitches;
    }

    
    public void setNopitches(int nopitches) {
        this.nopitches = nopitches;
    }

    
    public int getBattersFaced() {
        return battersFaced;
    }

    
    public void setBattersFaced(int battersFaced) {
        this.battersFaced = battersFaced;
    }

    
    public int getStrikeouts() {
        return strikeouts;
    }

    
    public void setStrikeouts(int strikeouts) {
        this.strikeouts = strikeouts;
    }

    
    public double getInningsPitched() {
        return inningsPitched;
    }

    
    public void setInningsPitched(double inningsPitched) {
        this.inningsPitched = inningsPitched;
    }

    
    public int getHits() {
        return hits;
    }

    
    public void setHits(int hits) {
        this.hits = hits;
    }

    
    public int getRunScored() {
        return runScored;
    }

    
    public void setRunScored(int runScored) {
        this.runScored = runScored;
    }

    public int getEarnedRuns() {
        return earnedRuns;
    }

    
    public void setEarnedRuns(int earnedRuns) {
        this.earnedRuns = earnedRuns;
    }

    
    public int getWalks() {
        return walks;
    }

    
    public void setWalks(int walks) {
        this.walks = walks;
    }

    
    public int getAtBats() {
        return atBats;
    }

    
    public void setAtBats(int atBats) {
        this.atBats = atBats;
    }
/*
    public int getStrikes() {
        return strikes;
    }

   
    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

   
    public int getHitByPitch() {
        return hitByPitch;
    }

   
    public void setHitByPitch(int hitByPitch) {
        this.hitByPitch = hitByPitch;
    }
*/
    public double getERA(double inningsPitched, int earnedRuns){
        era = earnedRuns/inningsPitched;
        return era;
    }
    
    public String getTeamName() {
        return teamName;
    }
    
    public void setTeamName(String tn) {
        teamName = tn;
    }

    /**
     * @return the firstName
     */
   
    
    
    
}