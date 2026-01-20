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
    private HTWRoom[] rooms = new HTWRoom[30];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private int round = 0;
    private final int RoundLimit = 24;
    private int question = 0;

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

    private void initRooms() {

        Lecturer l1 = new Lecturer("Reni Amalia Safitri");
        Lecturer l2 = new Lecturer("Lyudmila Vaseva");
        Lecturer l3 = new Lecturer("Janine Gärtner");
        Lecturer l4 = new Lecturer("Selim Gnaoui");
        Lecturer l5 = new Lecturer("Thomas Poeser");

        rooms[0] = new HTWRoom("143", "Lecture hall", l1);
        rooms[1] = new HTWRoom("144", "Lecture hall", l4);
        rooms[2] = new HTWRoom("018", "Classroom", null);
        rooms[3] = new HTWRoom("236", "Lecture hall", l2);
        rooms[4] = new HTWRoom("015", "Classroom", null);
        rooms[5] = new HTWRoom("142", "Lecture hall", l5);
        rooms[6] = new HTWRoom("219", "Classroom", null);
        rooms[7] = new HTWRoom("013", "Classroom", null);
        rooms[8] = new HTWRoom("014", "Classroom", null);
        rooms[9] = new HTWRoom("214", "Lecture hall", l3);
        rooms[10] = new HTWRoom("016", "Classroom", null);
        rooms[11] = new HTWRoom("017", "Classroom", null);
        rooms[12] = new HTWRoom("142", "Classroom", null);
        rooms[13] = new HTWRoom("236", "Classroom", null);
        rooms[14] = new HTWRoom("145", "Classroom", null);
        rooms[15] = new HTWRoom("146", "Classroom", null);
        rooms[16] = new HTWRoom("147", "Classroom", null);
        rooms[17] = new HTWRoom("148", "Classroom", null);
        rooms[18] = new HTWRoom("210", "Classroom", null);
        rooms[19] = new HTWRoom("211", "Classroom", null);
        rooms[20] = new HTWRoom("212", "Classroom", null);
        rooms[21] = new HTWRoom("213", "Classroom", null);
        rooms[22] = new HTWRoom("215", "Classroom", null);
        rooms[23] = new HTWRoom("216", "Classroom", null);
        rooms[24] = new HTWRoom("217", "Classroom", null);
        rooms[25] = new HTWRoom("218", "Classroom", null);
        rooms[26] = new HTWRoom("000", "Office corridor", null);
        rooms[27] = new HTWRoom("111", "Office corridor", null);
        rooms[28] = new HTWRoom("222", "Office corridor", null);
        rooms[29] = new HTWRoom("333", "Office corridor", null);
        rooms[30] = new HTWRoom("123", "Office corridor", null);

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

        if (hasAllSignatures()) {
            meetProfessor(scanner);
            return;
        }

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

        // Raum auswählen
        HTWRoom room = rooms[(int) (Math.random() * rooms.length)];
        System.out.println(room.getIdentifier() + ": " + room.getDescription());

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

            if (choice.equals("2")) {
                if (hero.flee()) {
                    System.out.println("You escaped successfully!");
                    return;
                } else {
                    System.out.println("Escape failed!");
                }
            }

            while (hero.isOperational() && !alien.isDefeated()) {

                alien.takeDamage(hero.attack());

                if (alien.isDefeated()) {
                    System.out.println("Alien defeated!");
                    hero.addExperiencePoints(5);
                    return;
                }

                hero.takeDamage(3);
            }

            System.out.println("You lost the fight!");
            hero.addExperiencePoints(1);
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

    private boolean hasAllSignatures() {
        Lecturer[] list = hero.getSignedExerciseLeaders();
        int count = 0;

        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                count++;
            }
        }
        return count == list.length;
    }

    private void meetProfessor(Scanner scanner) {
        System.out.println("Professor Majuntke appears!");
        System.out.println(
                "Professor Majuntke will give you three questions that you must answer correctly to get out of HTW. If you fail this quiz, you have one last attempt.");

        boolean firstTry = askQuestion(scanner);

        if (firstTry) {
            winGame();
        } else {
            System.out.println("Wrong answer! You get one more chance...");
            boolean secondTry = askQuestion(scanner);

            if (secondTry) {
                winGame();
            } else {
                loseGame();
            }
        }
    }

    private boolean askQuestion(Scanner scanner) {

        // Frage 1
        if (question == 0) {
            question++;

            System.out.println("Question 1: What does 'if' do in Java?");
            System.out.println("(1) It repeats a block of code");
            System.out.println("(2) It checks a condition and runs code if true");
            System.out.println("(3) It creates a new class");
            System.out.println("(4) It prints text automatically");

            String answer = scanner.nextLine();
            return answer.equals("2");
        }

        // Frage 2
        if (question == 1) {
            question++;

            System.out.println("Question 2: Which type is used for whole numbers?");
            System.out.println("(1) String");
            System.out.println("(2) int");
            System.out.println("(3) boolean");
            System.out.println("(4) double[]");

            String answer = scanner.nextLine();
            return answer.equals("2");
        }

        // Frage 3
        if (question == 2) {
            question++;

            System.out.println("Question 3: What does 'int' stand for?");
            System.out.println("(1) Internet");
            System.out.println("(2) Integer");
            System.out.println("(3) Interface");
            System.out.println("(4) Internal");

            String answer = scanner.nextLine();
            return answer.equals("2");
        }

        // keine Frage mehr
        return false;
    }

    private void winGame() {
        System.out.println("Correct! You passed!");
        System.out.println("You receive your certificate.");
        System.out.println("The doors open. You escaped HTW!");
        gameRunning = false;
        gameFinished = true;
    }

    private void loseGame() {
        System.out.println("Wrong again!");
        System.out.println("An evil alien has kidnapped Professor Majuntke.");
        gameRunning = false;
        gameFinished = true;
    }

}