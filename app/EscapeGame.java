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
 *
 * @author Jathuran Sathananthan
 * @author Arda Bingöl
 */

public class EscapeGame {
    private Hero hero;
    private HTWRoom[] rooms = new HTWRoom[31];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private int round = 0;
    private final int RoundLimit = 24;
    private int question = 0;

    /**
     * Neue Spieler wird erstellt und Raum wird aufgerufen
     */
    public EscapeGame() {
        this.hero = null;
        initRooms();
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
     * Breitet alle Räume des Escape Games vor.
     * Den Räumen werden Raumnummer, Raumtyp sowie optional ein Dozent zugewiesen.
     * Die erstellten Räume werden im rooms-Array gespeichert.
     */

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
     * Startet das Spiel und zeigt das Hauptmenü an.
     * Liest die Benutzereingaben ein und führt die ausgewählte Aktion aus,
     * solange das Spiel läuft.
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

    /**
     * Ermöglicht dem Spieler, die HTW zu erkunden.
     * Eine neue Runde wird gestartet, ein zufälliger Raum ausgewählt
     * und abhängig vom Zufall ein Ereignis ausgelöst.
     *
     * @param scanner Scanner zum Einlesen von Benutzereingaben
     */

    public void exploreHTW(Scanner scanner) {

        // Überprüft ob alle Unterschriften gesammelt wurden.

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

        // Zufäliig ein Raum von 30 Räumen auswähen.

        HTWRoom room = rooms[(int) (Math.random() * rooms.length)];
        System.out.println(room.getIdentifier() + ": " + room.getDescription());

        double risk = Math.random();

        // Mit einer Wahrscheinlichkeit von 20 Prozent verläuft die Erkundung
        // ereignislos.

        if (risk < 0.20) {
            System.out.println("Nothing happens.");
            return;
        }
        // In 52 Prozent der Fälle trifft der Hero auf ein außerirdisches
        // Wesen (Alien).

        if (risk < 0.72) {
            alienCase(scanner);
            return;
        }

        // In 28 Prozent der Fälle trifft der Hero ein Lehrer

        if (risk < 1.00) {
            lecturerCase(scanner, room);
            return;
        }
    }

    /**
     * Behandelt die Begegnung des Spielers mit einem Alien.
     * Es wird zufällig entschieden, ob das Alien freundlich oder feindlich ist.
     * Bei einem feindlichen Alien kann der Spieler fliehen oder kämpfen.
     *
     * @param scanner Scanner zum Einlesen der Benutzereingaben
     */

    private void alienCase(Scanner scanner) {

        // Klasse Alien bereitet Unterklassen vor
        Alien alien;

        // Zufällige Entscheidung, ob das Alien freundlich oder böse ist
        if (Math.random() < 0.5) {
            alien = new FriendlyAlien();
        } else {
            alien = new EvilAlien();
        }

        // Ausgabe des Namens und der Begrüßung des Aliens
        System.out.println("You meet an alien: " + alien.getName());
        System.out.println(alien.getGreeting());

        // Falls das Alien freundlich ist, passiert nichts weiter
        if (alien.isFriendly()) {
            System.out.println("The alien is friendly. You continue exploring.");
            return;
        } else {

            // Bei einem bösen Alien muss der Hero folgendes entscheiden.
            System.out.println("The alien is evil, runnnn!!!");
            System.out.println("(1) Fight");
            System.out.println("(2) Flee");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            // Fluchtversuch
            if (choice.equals("2")) {
                if (hero.flee()) {
                    System.out.println("You escaped successfully!");
                    return;
                } else {
                    System.out.println("Escape failed!");
                }
            }

            // Kampf: läuft solange der Held kampffähig ist
            // und das Alien noch nicht besiegt wurde

            while (hero.isOperational() && !alien.isDefeated()) {

                // Held greift das Alien an
                alien.takeDamage(hero.attack());

                // Überprüfung, ob das Alien besiegt wurde
                if (alien.isDefeated()) {
                    System.out.println("Alien defeated!");
                    hero.addExperiencePoints(5);
                    return;
                }
                // Alien greift Hero an
                hero.takeDamage(3);
            }
            // Falls der Held den Kampf verliert
            System.out.println("You lost the fight!");
            hero.addExperiencePoints(1);
        }
    }

    /**
     * Behandelt die Begegnung des Spielers mit einem Übungsleiter.
     * Der Übungsleiter kann den Laufzettel des Heros unterschreiben,
     * sofern noch keine Unterschrift vorhanden ist.
     *
     * @param scanner Scanner für die Benutzereingabe
     */

    private void lecturerCase(Scanner scanner, HTWRoom room) {

        // Übungsleiter aus dem Raum wird angezeigt

        Lecturer lecturer = room.getLecturer();

        // WICHTIG: Prüfen, ob überhaupt ein Dozent im Raum ist
        if (lecturer == null) {
            System.out.println("There is no lecturer in this room.");
            return;
        }

        System.out.println("You meet a lecturer: " + lecturer.getName());

        // Prüfen, ob der Übungsleiter noch unterschreiben kann, und kann
        // dementsprechend Unterschreiben

        if (lecturer.isReadyToSign()) {
            hero.signExerciseLeader(lecturer);
            lecturer.sign();
            System.out.println("The lecturer signed your checklist.");
        } else {
            System.out.println("The lecturer already signed your checklist.");
        }
    }

    /**
     * Zeigt den aktuellen Status des Helden an.
     * Dazu gehören Name, Lebenspunkte, Erfahrungspunkte,
     * aktuelle Runde sowie die Anzahl der gesammelten Unterschriften.
     */

    private void showHeroStatus() {

        System.out.println("--- HERO STATUS ---");

        System.out.println("Name: " + hero.getName());
        System.out.println("HP: " + hero.getHealthPoints() + "/50");
        System.out.println("XP: " + hero.getExperiencePoints());
        System.out.println("Round: " + round + " /24");

        // Liste der bereits unterschriebenen Übungsleiter
        Lecturer[] list = hero.getSignedExerciseLeaders();
        int signed = 0;

        // Anzahl der vorhandenen Unterschriften zählen
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                signed++;
            }
        }

        System.out.println("Signatures: " + signed + " /5");
    }

    /**
     * Zeigt die Checkliste der Dozenten an.
     * Bereits unterschriebene Dozenten werden mit Namen angezeigt,
     * fehlende Unterschriften mit Platzhaltern.
     */
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

    /**
     * Ermöglicht dem Spieler, eine Pause einzulegen.
     * Der Spieler kann zwischen einer kurzen oder langen Pause wählen,
     * um Lebenspunkte zu regenerieren.
     *
     * @param scanner Scanner für die Benutzereingabe
     */

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

    /**
     * Beendet das Spiel und setzt den Spielstatus entsprechend.
     */
    private void exitGame() {
        System.out.println("Leaving the game.");
        gameRunning = false;
        gameFinished = true;
    }

    /**
     * Prüft, ob der Held alle benötigten Unterschriften gesammelt hat.
     *
     * @return true, wenn alle Unterschriften vorhanden sind, sonst false
     */
    private boolean hasAllSignatures() {
        Lecturer[] list = hero.getSignedExerciseLeaders();
        int signed = 0;

        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                signed++;
            }
        }
        return signed == list.length;
    }

    /**
     * Startet das Abschlussgespräch mit dem Professor.
     * Der Spieler muss Fragen beantworten, um das Spiel zu gewinnen.
     * Es gibt maximal zwei Versuche.
     *
     * @param scanner Scanner für die Benutzereingabe
     */
    private void meetProfessor(Scanner scanner) {
        System.out.println("Professor Majuntke appears!");
        System.out.println(
                "Professor Majuntke will give you three questions that you must answer correctly to get out of HTW. If you fail this quiz, you have one last attempt.");

        // Erster Versuch
        boolean firstTry = askQuestion(scanner);

        // Bei Erfolg: Spiel gewinnen
        if (firstTry) {
            winGame();

            // Bei Misserfolg: zweiter Versuch
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

    /**
     * Professorin Majuntke Stellt dem Hero nacheinander Quizfragen.
     * 
     * @param scanner Scanner für die Benutzereingabe
     * @return true, wenn die Frage richtig beantwortet wurde, sonst false
     */
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

    /**
     * Beendet das Spiel mit einem Sieg.
     * Hero hat das Quiz erfolgreich abgeschlossen.
     */
    private void winGame() {
        System.out.println("Correct! You passed!");
        System.out.println("You receive your certificate.");
        System.out.println("The doors open. You escaped HTW!");
        gameRunning = false;
        gameFinished = true;
    }

    /**
     * Beendet das Spiel mit einer Niederlage.
     * Hero hat das Quiz nicht bestanden.
     */
    private void loseGame() {
        System.out.println("Wrong again!");
        System.out.println("An evil alien has kidnapped Professor Majuntke.");
        gameRunning = false;
        gameFinished = true;
    }

}