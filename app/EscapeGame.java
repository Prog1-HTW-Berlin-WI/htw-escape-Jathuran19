

import model.Hero;
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
    }
    /**
     * Gibt aktuellen Charakter zurück 
     * @return aktuelle Charakter 
     */
    public Hero getHero() {
        return hero;
    }
}
