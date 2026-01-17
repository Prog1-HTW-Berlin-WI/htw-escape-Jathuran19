package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Klasse für Hauptprogrammablauf des Spiels
 * 
 * @author Jathuran Sathananthan
 * @author Arda Bingöl
 */
public class EscapeApp {

    public static final String SAVE_FILE_NAME = "save";
    private EscapeGame game;
    private boolean gameRunning = true;

    /**
     * Spielstart und je nach Wahl wird man zum Menü weitergeleitet
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
        System.out.println("========================================\n");

        EscapeApp app = new EscapeApp();

        while (true) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
        }
    }

    /**
     * zeigt Menüansicht an
     */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");

        if (isGameRunning())
            System.out.println("(2) Resume Game");

        if (hasSavedGame())
            System.out.println("(3) Load Game");

        if (isGameRunning())
            System.out.println("(4) Save Game");

        if (hasSavedGame())
            System.out.println("(5) Delete Game");

        System.out.println("(6) Quit");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 6: ");
    }

    /**
     * User führt eine Eingabe aus
     * 
     * @return Benutzereingabe als String
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }

    /**
     * Fehlereingabe wird dem Benutzer gemeldet
     * 
     */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.startGame();
                break;
            case "2":
                if (isGameRunning()) {
                    this.resumeGame();
                }
                break;
            case "3":
                if (hasSavedGame()) {
                    this.loadGame();
                }
                break;
            case "4":
                if (isGameRunning()) {
                    this.saveGame();
                }
                break;
            case "5":
                if (hasSavedGame()) {
                    this.deleteGame();
                }
                break;
            case "6":
                System.out.println("See you next time!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                break;
        }
    }

    /**
     * Startet neues Spiel
     */
    private void startGame() {
        this.game = new EscapeGame();
        resumeGame();
    }

    /**
     * Spielstand wird fortgesetzt
     */
    private void resumeGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /**
     * Spielstand wird gelöscht
     */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }

    /**
     * Spielstand wird gespeichert
     * 
     * @return Spiel wird gespeichert
     */
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
    }

    /**
     * Spiel wird geladen
     */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (EscapeGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }

    /**
     * Spiel läuft
     * 
     * @return Spiel vorhanden true, nicht vorhanden false
     */
    private boolean isGameRunning() {
        return game != null;
    }

    /**
     * Spiel beendet
     * 
     * @return true, wenn das Spiel beendet ist
     */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
     * Prüft Dateibestand
     * 
     * @return true wenn etwas vorhanden ist
     */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}