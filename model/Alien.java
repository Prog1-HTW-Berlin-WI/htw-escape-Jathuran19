package model;

import java.io.Serializable;
/**
 * Klasse eines Aliens und dessen Eigenschaften
 * @author Arda Bingöl
 * @author Jathuran Sathananthan
 */
public abstract class Alien implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 1729389822767173584L;

    private String name;
    private int lifePoints;
    private boolean friendly;
    private String greeting;

    public Alien(String name, int lifePoints, boolean friendly, String greeting) {

        this.name = name;
        this.lifePoints = lifePoints;
        this.friendly = friendly;
        this.greeting = greeting;
    }

    public String getName() {
        return this.name;
    }

    public int getLifePoints() {
        return this.lifePoints;
    }

    public boolean isFriendly() {
        return this.friendly;
    }

    public String getGreeting() {
        return greeting;
    }

    /**
     * Verringert die Lebenspunkte des Aliens um den angegebenen Schaden.
     * Die Lebenspunkte können dabei nicht unter 0 fallen.
     * @param amount Schadensmenge
     */
    public void takeDamage(int amount) {
        lifePoints -= amount;
        if (lifePoints < 0) {
            lifePoints = 0;
        }
        System.out.println(name + " got " + amount + " damage. Remaining life points: " + lifePoints);
    }

    /**
     * Prüft, ob das Alien besiegt wurde.
     * @return true, wenn keine Lebenspunkte mehr vorhanden sind, sonst false
     */
    public boolean isDefeated() {
        if (lifePoints <= 0) {
            return true;
        } else {
            return false;
        }
    }

}