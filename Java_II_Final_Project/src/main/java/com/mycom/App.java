/*
Author: Jonathan Mestel
Date: 4/25/2023
Java II Final Project
File purpose: This file will run the GUI and control the general logic of the program
*/

package com.mycom;

// --- JavaFx Imports --- //
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

// --- Java Imports --- //
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.ArrayList;

public class App extends Application {
    
    // --- int --- //
    private static int menuNum = 0;
    
    // --- Scene --- //
    private static Scene scene;
    
    // --- Insets --- //
    private static Insets inset = new Insets(25,25,25,25);
    
    // ----- Controls for Main Menu ----- //
    
    // --- Buttons for main menu and their labels--- //
    private static Label CreateLabel = new Label("Create a record of a game: ");
    private static Button CreateFile = new Button("Create Game Record");
    
    private static Label OpenLabel = new Label("View a record of a game or a summary: ");
    private static Button OpenFile = new Button("View Game Record(s)");
    
    private static Label OpenMulLabel = new Label("View or Manage save data");
    private static Button ManageSaves = new Button("Manage Saved Records");
    
    private static Button HelpButton = new Button("Help");
    private static Button CancelButton = new Button("Cancel");
    private static Button NextButton = new Button("Next");
    
    private static Button ExitButton = new Button("Exit");
    
    // ----- Controls for Teams & Date Menu ----- //
    
    // --- Team text fields & dateTime --- //
    private static TextField team1;
    private static TextField team2;
    private static DatePicker dateOfGame;
    
    // ----- Controls for Add Players Menu ----- //
    
    // --- Text Fields for add players menu --- //
    private static TextField FirstNameTF;
    private static TextField LastNameTF;
    private static TextField InningsTF;
    private static TextField HitsTF;
    private static TextField RunsTF;
    private static TextField ERunsTF;
    private static TextField WalksTF;
    private static TextField SOTF;
    private static TextField ABTF;
    private static TextField BFTF;
    private static TextField NPTF;
    
    // --- radio buttons and toggle group --- //
    private static RadioButton team1RB;
    private static RadioButton team2RB; 
    
    private static ToggleGroup teams;
    
    // --- ListView and array object --- //
    private static ListView<String> players = new ListView<>();
    //private static ArrayList<Pitcher> pitchers = new ArrayList<>();
    
    // --- Buttons for list --- //
    private static Button addPlayer = new Button("Add");
    private static Button removePlayer = new Button("Remove");
    
    // ----- Controls for View Game Record ----- //
    
    
    // --- Buttons --- //
    private static Button LoadFile = new Button("Load File");
    
    // --- ListView object --- //
    private static ListView<String> loadList = new ListView<>();
    
    // ----- Controls for Load multiple files menu ----- //
    
    // --- TextFields --- //
    private static TextField addFileName;
    
    // --- Buttons --- //
    private static Button AddFile = new Button("Add File");
    private static Button RemoveFile = new Button("Remove File");
    
    // --- ListView object --- //
    private static ListView<String> saveList = new ListView<>();
    
    /**
     * Written By Jonathan Mestel On 4/25/2023 - 4/29/2023
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pitcher Team Application");

        //create and display main menu
        scene = getMainMenu();
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        //create and set up the team menu
        CreateFile.setOnAction(event -> {
            scene = getTeamMenu();
            primaryStage.setScene(scene);
            //set text for next button
            NextButton.setText("Next");
        });
        
        //go back to the main menu
        //No matter where the Cancel button is present, it will bring you back to the main menu
        CancelButton.setOnAction(event -> {
            scene = getMainMenu();
            primaryStage.setScene(scene);
            CancelButton.setText("Cancel");
        });
        
        //addplayer button
        addPlayer.setOnAction(event -> {
           //validate the data entered
           String msg = DataHandling.Validation.getAddPlayerValidation(FirstNameTF.getText(), LastNameTF.getText(), InningsTF.getText(), HitsTF.getText(), RunsTF.getText(), ERunsTF.getText(), WalksTF.getText(), SOTF.getText(), ABTF.getText(), BFTF.getText(), NPTF.getText());
           if(msg.isEmpty()) {
               //get selected team
               RadioButton selected = (RadioButton) teams.getSelectedToggle();
               players.getItems().add(LastNameTF.getText() + ", " + FirstNameTF.getText() + " - " + selected.getText());
               /* ADD CODE HERE TO ADD PLAYER TO THE PITCHERS ARRAY */
               
               //reset data entry menu
               setPlayerMenu();
           } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Entry");
                alert.setContentText(msg);
                alert.showAndWait();
           }
        });
        
        //removeplayer button
        removePlayer.setOnAction(event -> {
            List<String> selectedPlayers = players.getSelectionModel().getSelectedItems();
            List<String> allPlayers = players.getItems();
        
            //if nothing was selected or if there are no players do nothing
            if(selectedPlayers.isEmpty() || allPlayers.isEmpty()) {
            } else {
                //loop through and remove selected items
                for(var item : selectedPlayers) {
                    //get index of player being removed
                    int index = players.getItems().indexOf(item);
                    players.getItems().remove(item);
                    
                    //use index of removed player to remove the pitcher from the parallel array
                    //pitchers.remove(index);
                }
            }
        });
        
        //view game record button
        OpenFile.setOnAction(event -> {
            scene = setGameRecordMenu();
            primaryStage.setScene(scene);
        });
        
        //Add file button
        AddFile.setOnAction(event -> {
            //first go to validaton to make sure that text field is not empty and ends with .sqlite
            String msg = DataHandling.Validation.getFileValidation(addFileName.getText());
            
            // if msg contains a string output an error
            if (!msg.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Entry");
                alert.setContentText(msg);
                alert.showAndWait();
            } else {
                
                FileIO.SaveData.upLoadSave(addFileName.getText());
                saveList.getItems().clear();
        
                //get items for saveList
                ArrayList<String> saves = FileIO.SaveData.getSaveData();
                for (String save : saves) {
                    saveList.getItems().add(save);
                }  
            }
        });
        
        //Remove file button
        RemoveFile.setOnAction(event -> {
            String selectedSave = saveList.getSelectionModel().getSelectedItem();
            List<String> allSaves = saveList.getItems();
            ArrayList<String> saves = new ArrayList<>();
        
            //if nothing was selected or if there are no players do nothing
            if(selectedSave.isEmpty() || allSaves.isEmpty()) {
            } else {
                //remove the saved item
                allSaves.remove(selectedSave);
                
                //create an arraylist of remaining items
                for (String save : allSaves) {
                    saves.add(save);
                }
                
                //upload array to save file
                FileIO.SaveData.updateSave(saves);
            }
        });
        
        //view game summary
        ManageSaves.setOnAction(event -> {
            scene = setSaveRecordMenu();
            primaryStage.setScene(scene);
        });
        
        
        //load files button
        LoadFile.setOnAction(event -> {
            List<String> selected = loadList.getSelectionModel().getSelectedItems();
            ArrayList<String> msg = new ArrayList<>();
            if (selected.isEmpty()) {
                
            } else {
                if (selected.size() == 1) {
                    
                    /* INSERT METHOD HERE TO GET TABLE FROM ONE FILE */
                    msg = FileIO.FileInput.PitcherStatistics(selected.get(0));
                    
                } else {
                    
                    /* INSERT METHOD HERE TO GET TABLE FROM MULTIPLE FILES */
                    
                }
                scene = viewRecord(msg);
                primaryStage.setScene(scene);
            }
        });
        
        //next button event
        //IMPORTANT: The next button event does multiple different events depending on the menu that is currently loaded and can tell on the menu loaded based on the value of menuNum
        NextButton.setOnAction(event -> {
            if(menuNum == 1) {
                //if menu number is one then the menu that the user is on is for data entry for team names and date
                //validate data for this
                String msg = DataHandling.Validation.getTeamMenuValidation(team1.getText(), team2.getText(), dateOfGame.getValue());
                //if msg is not empty display an error message
                if (!msg.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Entry");
                    alert.setContentText(msg);
                    alert.showAndWait();
                } else {
                    //if there is no error, proceed to the next menu
                    scene = addPlayerMenu();
                    primaryStage.setScene(scene);
                    //update menu number and NextButton text
                    NextButton.setText("Create File");
                }
            } else if (menuNum == 2) {
                //if menu == 2 then the user has clicked "Create File"
                
                //create name of file using date time object and names of teams
                LocalDate date = dateOfGame.getValue();
                DateTimeFormatter format = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
                String stringDate = format.format(date);
                String[] dates = stringDate.split("/");
                stringDate = dates[0] + "-" + dates[1] + "-" + dates[2];

                String nameOfFile = team1.getText() + " v " + team2.getText() + " - " + stringDate + ".sqlite";

                /* ADD CODE HERE TO PASS THE NAME OF FILE AND THE PITCHER ARRAY TO THE FILE OUTPUT CLASS*/
                String msg = "";
            
                //if msg is not empty, display an error else display a success message with the file name and bring the user back to the main menu
                if (msg.isEmpty()) {
                    FileIO.SaveData.upLoadSave(nameOfFile);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("File has been successfully created.\nThe file saved is:");
                    alert.setContentText(nameOfFile);
                    alert.showAndWait();

                    scene = getTeamMenu();
                    primaryStage.setScene(scene);
                    //set text for next button
                    NextButton.setText("Next");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("An Error Has occured");
                    alert.setContentText(msg);
                    alert.showAndWait();
                }
            }
        });
        
        //exit the program button
        ExitButton.setOnAction(event -> {
            System.exit(0);
        });
        
        //help button
        HelpButton.setOnAction(event -> {
            DataHandling.Help.getHelpMenu(menuNum);
        });
    }
    /*
    Main method to launch GUI
    */
    public static void main(String[] args) {
        launch();
    }
    
    
    // --- Methods to build menus --- //
    
    /*
    Method to generate the main menu of the application
    */
    public static Scene getMainMenu() {
        menuNum = 0;
        GridPane menu = new GridPane();
        menu.setAlignment(Pos.TOP_LEFT);
        menu.setPadding(inset);
        
        menu.setHgap(10);
        menu.setVgap(10);
       
        
        menu.add(CreateLabel, 0, 0);
        menu.add(CreateFile, 1, 0);
        
        menu.add(OpenLabel, 0, 1);
        menu.add(OpenFile, 1, 1);
        
        menu.add(OpenMulLabel, 0, 2);
        menu.add(ManageSaves, 1, 2);
        
        HBox ExitBox = new HBox(10);
        ExitBox.setAlignment(Pos.BOTTOM_RIGHT);
        ExitBox.getChildren().add(HelpButton);
        ExitBox.getChildren().add(ExitButton);
        menu.add(ExitBox, 1, 4);
        
        return new Scene(menu, 430, 200);
    }
    
    /*
    Method to generate the set team names menu of application
    */
    public static Scene getTeamMenu() {
        menuNum = 1;
        GridPane teamMenu = new GridPane();
        teamMenu.setAlignment(Pos.TOP_LEFT);
        teamMenu.setPadding(inset);
        
        teamMenu.setHgap(10);
        teamMenu.setVgap(10);
        
        teamMenu.add(new Label("Enter Team 1: "), 0, 0);
        teamMenu.add(team1 = new TextField(), 1, 0);
        
        teamMenu.add(new Label("Enter Team 2: "), 0, 1);
        teamMenu.add(team2 = new TextField(), 1, 1);
        
        teamMenu.add(new Label("Date of the Game: "), 0, 2);
        teamMenu.add(dateOfGame = new DatePicker(), 0, 3);
        
        HBox ExitBox = new HBox(10);
        ExitBox.setAlignment(Pos.BOTTOM_RIGHT);
        ExitBox.getChildren().add(CancelButton);
        ExitBox.getChildren().add(HelpButton);
        ExitBox.getChildren().add(NextButton);
        
        teamMenu.add(ExitBox, 0, 20, 2, 1);
        return new Scene(teamMenu, 400, 400);
    }
    
    /*
    Method to generate the add players menu
    */
    public static Scene addPlayerMenu() {
        menuNum = 2;
        GridPane addPlayerMenu = new GridPane();
        addPlayerMenu.setAlignment(Pos.TOP_LEFT);
        addPlayerMenu.setPadding(inset);
        
        addPlayerMenu.setHgap(10);
        addPlayerMenu.setVgap(20);
        
        addPlayerMenu.add(new Label("Player Data Entry:"), 0, 0, 4, 1);
        
        addPlayerMenu.add(new Label("First Name: "), 0, 1);
        addPlayerMenu.add(FirstNameTF = new TextField(), 1, 1);
        
        addPlayerMenu.add(new Label("Last Name: "), 2, 1);
        addPlayerMenu.add(LastNameTF = new TextField(), 3, 1);
        
        addPlayerMenu.add(new Label("Innings Played: "), 0, 2);
        addPlayerMenu.add(InningsTF = new TextField(), 1, 2);
        
        addPlayerMenu.add(new Label("Hits: "), 2, 2);
        addPlayerMenu.add(HitsTF = new TextField(), 3, 2);
        
        addPlayerMenu.add(new Label("Runs: "), 0, 3);
        addPlayerMenu.add(RunsTF = new TextField(), 1, 3);
        
        addPlayerMenu.add(new Label("Earned Runs: "), 2, 3);
        addPlayerMenu.add(ERunsTF = new TextField(), 3, 3);
        
        addPlayerMenu.add(new Label("Walks: "), 0, 4);
        addPlayerMenu.add(WalksTF = new TextField(), 1, 4);
        
        addPlayerMenu.add(new Label("Strike Outs: "), 2, 4);
        addPlayerMenu.add(SOTF = new TextField(), 3, 4);
        
        addPlayerMenu.add(new Label("At Bats: "), 0, 5);
        addPlayerMenu.add(ABTF = new TextField(), 1, 5);
        
        addPlayerMenu.add(new Label("Batters Faced: "), 2, 5);
        addPlayerMenu.add(BFTF = new TextField(), 3, 5);
        
        addPlayerMenu.add(new Label("Number of Pitches: "), 0, 6);
        addPlayerMenu.add(NPTF = new TextField(), 1, 6);
        
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        buttons.getChildren().add(team1RB = new RadioButton(team1.getText()));
        team1RB.setSelected(true);
        buttons.getChildren().add(team2RB = new RadioButton(team2.getText()));
        
        //add team buttons to toggleGroup
        teams = new ToggleGroup();
        team1RB.setToggleGroup(teams);
        team2RB.setToggleGroup(teams);
        addPlayerMenu.add(new Label("Team: "), 2, 6);
        addPlayerMenu.add(buttons, 3, 6);
        
        //add add button
        HBox addBox = new HBox (10);
        addBox.setAlignment(Pos.BOTTOM_RIGHT);
        addBox.getChildren().add(addPlayer);
        addBox.getChildren().add(removePlayer);
        
        addPlayerMenu.add(addBox, 0, 7, 4, 1);
        
        //clear the listview and arraylist
        //pitchers.clear();
        players.getItems().clear();
        
        players.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        players.setPrefHeight(4 * 24);
        //add the listview to the grid
        addPlayerMenu.add(players, 0, 8, 4, 4);
        
        HBox NBox = new HBox (10);
        NBox.setAlignment(Pos.BOTTOM_RIGHT);
        NBox.getChildren().add(NextButton);
        addPlayerMenu.add(NBox, 0, 12, 4, 1);
        
        HBox ExitBox = new HBox (10);
        ExitBox.setAlignment(Pos.BOTTOM_RIGHT);
        ExitBox.getChildren().add(CancelButton);
        ExitBox.getChildren().add(HelpButton);
        ExitBox.getChildren().add(ExitButton);
        addPlayerMenu.add(ExitBox, 0, 13, 4, 1);
        
        //setPlayer menu default values
        setPlayerMenu();
        
        return new Scene (addPlayerMenu, 600, 600);
    }
    
    public static void setPlayerMenu() {
        FirstNameTF.setText("");
        LastNameTF.setText("");
        InningsTF.setText("0.0");
        HitsTF.setText("0");
        RunsTF.setText("0");
        ERunsTF.setText("0");
        WalksTF.setText("0");
        SOTF.setText("0");
        ABTF.setText("0");
        BFTF.setText("0");
        NPTF.setText("0");
    }
    
    public static Scene setGameRecordMenu() {
        menuNum = 3;
        GridPane recordMenu = new GridPane();
        recordMenu.setAlignment(Pos.TOP_LEFT);
        recordMenu.setPadding(inset);
        
        recordMenu.setHgap(20);
        recordMenu.setVgap(10);
        
        recordMenu.add(new Label("Select a data file to view a game record\nor select multiple files to view a summary record!"), 0, 0);
        recordMenu.add(loadList, 0, 1);
        
        //24 is the line pixel height
        loadList.setPrefHeight(7 * 24);
        loadList.setPrefWidth(400);
        loadList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        loadList.getItems().clear();
        
        //load list of saves
        ArrayList<String> saves = FileIO.SaveData.getSaveData();
        for (String save : saves) {
            loadList.getItems().add(save);
        }
        
        HBox load = new HBox(10);
        load.setAlignment(Pos.BOTTOM_RIGHT);
        load.getChildren().add(LoadFile);
        
        recordMenu.add(load, 0, 2);
        
        HBox exitBox = new HBox(10);
        exitBox.setAlignment(Pos.BOTTOM_RIGHT);
        
        exitBox.getChildren().add(CancelButton);
        exitBox.getChildren().add(HelpButton);
        exitBox.getChildren().add(ExitButton);
        recordMenu.add(exitBox, 0, 3);
        
        return new Scene(recordMenu, 450, 200);
    }
    
    public static Scene setSaveRecordMenu() {
        menuNum = 4;
        GridPane SummaryMenu = new GridPane();
        SummaryMenu.setAlignment(Pos.TOP_LEFT);
        SummaryMenu.setPadding(inset);
        
        SummaryMenu.setHgap(10);
        SummaryMenu.setVgap(10);
        
        //add file entry and buttons
        SummaryMenu.add(new Label("Enter Data File Name: (should end with .sqlite)"), 0, 0);
        SummaryMenu.add(addFileName = new TextField(), 0, 1);
        addFileName.setMinWidth(400);
        
        HBox addRemove = new HBox(10);
        addRemove.getChildren().add(AddFile);
        addRemove.getChildren().add(RemoveFile);
        
        SummaryMenu.add(addRemove, 0, 2);
        
        //clear the list of items from load list and set perfered height
        saveList.getItems().clear();
        saveList.setPrefHeight(4 * 24);
        
        //get items for saveList
        ArrayList<String> saves = FileIO.SaveData.getSaveData();
        for (String save : saves) {
            saveList.getItems().add(save);
        }
        
        
        SummaryMenu.add(saveList, 0, 3);
        
        //create an exit box for cancel and exit buttons
        HBox exitBox = new HBox(10);
        exitBox.setAlignment(Pos.BOTTOM_RIGHT);
       
        exitBox.getChildren().add(CancelButton);
        exitBox.getChildren().add(HelpButton);
        exitBox.getChildren().add(ExitButton);
        SummaryMenu.add(exitBox, 0, 4);
        
        return new Scene (SummaryMenu, 450, 300);
    }
    
    public static Scene viewRecord(ArrayList<String> table) {
        menuNum = 5;
        GridPane SummaryMenu = new GridPane();
        SummaryMenu.setAlignment(Pos.TOP_LEFT);
        SummaryMenu.setPadding(inset);
        

        int line = 0;
        for(String arr : table) {
            if(arr.contains("*")) {
                String[] strings = arr.split("\\*");
                for(int i = 0; strings.length > i; i++) {
                    Insets in = new Insets(0, 12, 0, 0);
                    Label lab = new Label(strings[i]);
                    lab.setPadding(in);
                    SummaryMenu.add(lab, i, line);
                }
                line++;
            } else {
                SummaryMenu.add(new Label(arr), 0, line, 10, 1);
                line++;
            }
        }
        
        
        HBox exitBox = new HBox(10);
        exitBox.setAlignment(Pos.BOTTOM_RIGHT);
       
        exitBox.getChildren().add(CancelButton);
        CancelButton.setText("Main Menu");
        exitBox.getChildren().add(HelpButton);
        exitBox.getChildren().add(ExitButton);
        SummaryMenu.add(exitBox, 0, line, 10, 1);
        
        return new Scene(SummaryMenu, 850, 500);
    }
}
