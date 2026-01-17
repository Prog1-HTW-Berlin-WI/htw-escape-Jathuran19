package app;

import model.Hero;
import model.Lecturer;
import model.HTWRoom;
import model.Alien;
import model.EvilAlien;
import model.FriendlyAlien;
import java.util.Scanner;

/**
 * Steuert den Ablauf und Zustand des Spiels
 * 
 * @author Jathuran Sathananthan
 * @author Arda Bingöl
 */
public class EscapeGame {
    private Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private int round = 0;
    private final int RoundLimit = 24;

    /**
     * Neue Spieler wird erstellt
     */
    public EscapeGame() {
        this.hero = null;
    }

    /**
     * Gibt an ob, das Spiel aktuell läuft
     * 
     * @return true, wenn Spiel läuft, false wenn Spiel nicht läuft
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Spiel wird ausgeführt
     * 
     * @param gameRunning
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    /**
     * Gibt Spielstand an
     * 
     * @return true, wenn Spiel läuft, false , wenn Spiel beendet ist
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Spiel wird geschlossen
     * 
     * @param gameFinished
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Spiel fragt nach Aktivität
     */
    public void run() {
        System.out.println("Welcome!");
        Scanner scanner = new Scanner(System.in);

        if (hero == null) {
            System.out.print("Enter youre heros name: ");
            String name = scanner.nextLine();
            hero = new Hero(name);
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
                    exploreHTW(scanner);
                    break;

                case "2":
                    showHeroStatus();
                    break;

                case "3":
                    showChecklist();
                    break;

                case "4":
                    takeRest(scanner);
                    break;

                case "5":
                    exitGame();
                    break;

                default:
                    System.out.println("Invalid input. Please choose 1-5.");
                    break;
            }
        }
    }

    /**
     * Gibt aktuellen Charakter zurück
     * 
     * @return aktuelle Charakter
     */
    public Hero getHero() {
        return hero;
    }

    public void exploreHTW(Scanner scanner) {

        System.out.println("You explore the HTW");

        // Runden überprüfung
        if (round >= RoundLimit) {
            System.out.println("--The doors are closing. Your chance is gone--");
            gameRunning = false;
            gameFinished = true;
            return;
        }
        // Neue Runde

        round = round + 1;
        System.out.println("-- Round: " + round + " / " + RoundLimit + " --");

        double risk = Math.random();
        // Mit einer Wahrscheinlichkeit von 20 Prozent verläuft die Erkundung
        // ereignislos.
        if (risk < 0.20) {
            System.out.println("Nothing happens.");
            return;
        }
        // In 52 Prozent der Fälle trifft der Spielcharakter auf ein außerirdisches
        // Wesen (Alien).
        if (risk < 0.72) {
            alienCase(scanner);
            return;
        }
        if (risk < 1.00) {
            lecturerCase(scanner);
            return;
        }
    }

    private void alienCase(Scanner scanner) {

        Alien alien;

        if (Math.random() < 0.5) {
            alien = new FriendlyAlien();
        } else {
            alien = new EvilAlien();
        }

        System.out.println("You meet an alien: " + alien.getName());
        System.out.println(alien.getGreeting());

        if (alien.isFriendly()) {
            System.out.println("The alien is friendly. You continue exploring.");
            return;
        } else {

            System.out.println("The alien is evil, runnnn!!!");
            System.out.println("(1) Fight");
            System.out.println("(2) Flee");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println("You decided to fight.");
            } else if (choice.equals("2")) {
                System.out.println("You decided to flee.");
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private void lecturerCase(Scanner scanner) {

        Lecturer lecturer = new Lecturer("...");

        System.out.println("You meet a lecturer: " + lecturer.getName());

        if (lecturer.isReadyToSign()) {
            hero.signExerciseLeader(lecturer);
            lecturer.sign();
            System.out.println("The lecturer signed your checklist.");
        } else {
            System.out.println("The lecturer already signed your checklist.");
        }
    }




    private void showHeroStatus() {
        System.out.println("--- HERO STATUS ---");

        System.out.println("Name: " + hero.getName());
        System.out.println("HP: " + hero.getHealthPoints() + "/50");
        System.out.println("XP: " + hero.getExperiencePoints());
        System.out.println("Round: " + round + " /24");

        Lecturer[] list = hero.getSignedExerciseLeaders();
        int signed = 0;

        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                signed++;
            }
        }

        System.out.println("Signatures: " + signed + " /5");
    }



    private void showChecklist() {
        System.out.println("--- CHECKLIST ---");
        Lecturer[] list = hero.getSignedExerciseLeaders();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println((i + 1) + ") " + list[i].getName());
            } else {
                System.out.println((i + 1) + ") ...");
            }
        }
    }



    private void takeRest(Scanner scanner) {
        System.out.println("--- REST ---");
        System.out.println("(1) Small rest (+3 HP)");
        System.out.println("(2) Long rest (+10 HP) (+1 round)");
        System.out.print("Your choice:");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            hero.regenerate(false);
        } else if (choice.equals("2")) {
            round = round + 1;
            hero.regenerate(true);
        } else {
            System.out.println("Invalid input.");
        }

        System.out.println("HP: " + hero.getHealthPoints() + "/50");
        System.out.println("Round: " + round + " /24");
    }



    private void exitGame() {
        System.out.println("Leaving the game.");
        gameRunning = false;
        gameFinished = true;
    }

}