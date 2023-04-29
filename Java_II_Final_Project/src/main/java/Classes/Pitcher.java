/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Tron
 */
public class Pitcher {
    
    private String firstName;
    private String lastName;
    private String teamName;
    private double pitched;
    private int hits;
    private int runs;
    private int earnedRuns;
    private int walks;
    private int strikeOuts;
    private int atBats;
    private int totalBattersFaced;
    private int numberOfPitches;
    
    
    private int rbi;
    private int putOut;
    private int assists;
    
    private int ipPt1;
    private int ipPt2;
    private double ip;
    
    
    public Pitcher(String fn, String ln, String tm, double ip, int hits, int runs, int earnedRuns, int walks, int strikeOuts, int atBats, int totalBattersFaced, int numberOfPitches){
        
        firstName = fn;
        lastName = ln;
        teamName = tm;
        this.ip = ip;
        this.hits = hits;
        this.runs = runs;
        this.earnedRuns = earnedRuns;
        this.walks = walks;
        this.strikeOuts = strikeOuts;
        this.atBats = atBats;
        this.totalBattersFaced = totalBattersFaced;
        this.numberOfPitches = numberOfPitches;
        
        
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return the pitched
     */
    public double getPitched() {
        return pitched;
    }

    /**
     * @param pitched the pitched to set
     */
    public void setPitched(double pitched) {
        this.pitched = pitched;
    }

    /**
     * @return the hits
     */
    public int getHits() {
        return hits;
    }

    /**
     * @param hits the hits to set
     */
    public void setHits(int hits) {
        this.hits = hits;
    }

    /**
     * @return the runs
     */
    public int getRuns() {
        return runs;
    }

    /**
     * @param runs the runs to set
     */
    public void setRuns(int runs) {
        this.runs = runs;
    }

    /**
     * @return the earnedRuns
     */
    public int getEarnedRuns() {
        return earnedRuns;
    }

    /**
     * @param earnedRuns the earnedRuns to set
     */
    public void setEarnedRuns(int earnedRuns) {
        this.earnedRuns = earnedRuns;
    }

    /**
     * @return the walks
     */
    public int getWalks() {
        return walks;
    }

    /**
     * @param walks the walks to set
     */
    public void setWalks(int walks) {
        this.walks = walks;
    }

    /**
     * @return the strikeOuts
     */
    public int getStrikeOuts() {
        return strikeOuts;
    }

    /**
     * @param strikeOuts the strikeOuts to set
     */
    public void setStrikeOuts(int strikeOuts) {
        this.strikeOuts = strikeOuts;
    }

    /**
     * @return the atBats
     */
    public int getAtBats() {
        return atBats;
    }

    /**
     * @param atBats the atBats to set
     */
    public void setAtBats(int atBats) {
        this.atBats = atBats;
    }

    /**
     * @return the totalBattersFaced
     */
    public int getTotalBattersFaced() {
        return totalBattersFaced;
    }

    /**
     * @param totalBattersFaced the totalBattersFaced to set
     */
    public void setTotalBattersFaced(int totalBattersFaced) {
        this.totalBattersFaced = totalBattersFaced;
    }

    /**
     * @return the numberOfPitches
     */
    public int getNumberOfPitches() {
        return numberOfPitches;
    }

    /**
     * @param numberOfPitches the numberOfPitches to set
     */
    public void setNumberOfPitches(int numberOfPitches) {
        this.numberOfPitches = numberOfPitches;
    }

    /**
     * @return the ipPt1
     */
    public int getIpPt1() {
        return ipPt1;
    }

    /**
     * @param ipPt1 the ipPt1 to set
     */
    public void setIpPt1(int ipPt1) {
        this.ipPt1 = ipPt1;
    }

    /**
     * @return the ipPt2
     */
    public int getIpPt2() {
        return ipPt2;
    }

    /**
     * @param ipPt2 the ipPt2 to set
     */
    public void setIpPt2(int ipPt2) {
        this.ipPt2 = ipPt2;
    }

    /**
     * @return the ip
     */
    public double getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(int ip) {
        this.ip = ip;
    }

    /**
     * @return the rbi
     */
    public int getRbi() {
        return rbi;
    }

    /**
     * @param rbi the rbi to set
     */
    public void setRbi(int rbi) {
        this.rbi = rbi;
    }

    /**
     * @return the putOut
     */
    public int getPutOut() {
        return putOut;
    }

    /**
     * @param putOut the putOut to set
     */
    public void setPutOut(int putOut) {
        this.putOut = putOut;
    }

    /**
     * @return the assists
     */
    public int getAssists() {
        return assists;
    }

    /**
     * @param assists the assists to set
     */
    public void setAssists(int assists) {
        this.assists = assists;
    }
    
    
    
    
    
    
    
    
    
    
}
