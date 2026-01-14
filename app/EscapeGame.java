package app;

import model.Hero;

import java.util.Scanner;

import model.HTWRoom;
/**
 * Steuert den Ablauf und Zustand des Spiels
 * @author Jathuran Sathananthan 
 * @author Arda Bingöl
 */
public class EscapeGame {
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    /**
     * Neue Spieler wird erstellt
     */
    public EscapeGame() {
        this.hero = new Hero();
    }
    /**
     * Gibt an ob, das Spiel aktuell läuft 
     * @return true, wenn Spiel läuft, false wenn Spiel nicht läuft
     */
    public boolean isGameRunning() {
        return gameRunning;
    }
    /**
     * Spiel wird ausgeführt 
     * @param gameRunning
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
    /**
     * Gibt Spielstand an 
     * @return true, wenn Spiel läuft, false , wenn Spiel beendet ist
     */
    public boolean isGameFinished() {
        return gameFinished;
    }
    /**
     * Spiel wird geschlossen 
     * @param gameFinished
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
    /**
     * Spiel fragt nach Aktivität
     */
    public void run() {
        System.out.println("The game has started. Or not?");
        Scanner scanner = new Scanner(System.in);

        if (hero == null) {
            System.out.print("Enter youre heros name: ");
            String name = scanner.nextLine();
            hero = new Hero(name, 50, 0);   
        }
        while (gameRunning) {
            System.out.println(" --- GAME MENU ---");
            System.out.println("(1) - Explore HTW");
            System.out.println("(2) - Show hero status");
            System.out.println("(3) - Show checklist");
            System.out.println("(4) - Take a rest");
            System.out.println("(5) - Exit game");
            System.out.println("Your choice: ");  
            
            String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                System.out.println("You explore the HTW");
                break;

            case "2":
                System.out.println("Name: " + hero.getName());
                System.out.println("HP: " + hero.getHealthPoints() + "/50");
                System.out.println("XP: " + hero.getExperiencePoints());
            break;

            case "3":
                System.out.println("Checklist:");
                Lecturer[] list = hero.getSignedExerciseLeaders();
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println((i + 1) + ") " + list[i].getName());
                    } else {
                        System.out.println((i + 1) + ") (empty)");
                    }
                }
                break;

            case "4":
                System.out.println("(1) Small rest (+3 HP)");
                System.out.println("(2) Long rest (+10 HP)");
                System.out.print("Your choice: ");
                String rest = scanner.nextLine();

                if (rest.equals("1")) {
                    hero.regenerate(false);
                } else if (rest.equals("2")) {
                    hero.regenerate(true);
                } else {
                    System.out.println("Invalid input.");
                }
                    System.out.println("Current HP: " + hero.getHealthPoints() + "/50");
                break;

           case "5":
                System.out.println("Leaving the game...");
                gameRunning = false;
                gameFinished = true;
                break;
                
            default:
                System.out.println("Invalid input. Please choose 1-5.");
                break;
            }
        }
     }
    /**
     * Gibt aktuellen Charakter zurück 
     * @return aktuelle Charakter 
     */
    public Hero getHero() {
        return hero;
    }
}