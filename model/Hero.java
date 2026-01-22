package model;

import java.io.Serializable;

/**
 * Klasse eines Heros und dessen Eigenschaften 
 * 
 * @author Jathuran Sathananthan 
 * @author Arda Bingöl
 */

public class Hero implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;

    private String name;
    private int healthPoints;
    private int experiencePoints;
    private Lecturer[] signedExerciseLeaders;

    public Hero(String name) {
        this.name = name;
        this.healthPoints = 50;
        this.experiencePoints = 0;
        this.signedExerciseLeaders = new Lecturer[5];
    }

    public String getName() {
        return this.name;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    public Lecturer[] getSignedExerciseLeaders() {
        return this.signedExerciseLeaders;
    }

    // Methoden:

    /**
     * Verringert die Lebenspunkte um den angegebenen Schaden.
     * Die Lebenspunkte können dabei nicht unter 0 fallen.
     *
     * @param amount Schaden
     */
    public void takeDamage(int amount) {
        healthPoints -= amount;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }

    /**
     * Regeneriert die Lebenspunkte des Heros.
     * Je nach Art der Pause werden unterschiedlich viele Lebenspunkte geheilt.
     *
     * @param longRest true für eine lange Pause, false für eine kurze Pause
     */
    public void regenerate(boolean longRest) {
        if (longRest == false) {
            healthPoints += 3;
        }
        if (longRest == true) {
            healthPoints += 10;
        }

        if (healthPoints > 50) {
            healthPoints = 50;
        }
    }

    /**
     * Versucht, vor einem Gegner zu fliehen.
     * Die Flucht gelingt mit einer bestimmten Wahrscheinlichkeit (42 Prozent).
     * 
     * @return true, wenn die Flucht erfolgreich ist, sonst false
     */
    public boolean flee() {
        double risk = Math.random();
        if (risk < 0.42) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Führt einen Angriff aus und berechnet den verursachten Schaden.
     * Der Schaden hängt von den Erfahrungspunkten ab und kann variieren.
     *
     * @return verursachter Schaden als Ganzzahl
     */
    public int attack() {
        double damage = experiencePoints * 2.3 + 1;
        double chance = Math.random();
        if (chance < 0.13) {
            return 0;
        }
        if (chance < 0.25) {
            damage = damage * 2;
        }
        return (int) damage;
    }

    /**
     * Fügt einen Übungsleiter zur Unterschriftenliste des Heros hinzu.
     * Der Übungsleiter wird nur eingetragen, wenn er noch nicht vorhanden ist.
     *
     * @param lecturer Übungsleiter, der unterschreiben soll
     */
    public void signExerciseLeader(Lecturer lecturer) {
        // Abbrechen, falls kein Übungsleiter übergeben wurde
        if (lecturer == null) {
            return;
        }

        // Prüfen, ob der Übungsleiter bereits unterschrieben hat
        for (int i = 0; i < signedExerciseLeaders.length; i++) {
            if (signedExerciseLeaders[i] != null &&
                    signedExerciseLeaders[i].getName().equals(lecturer.getName())) {
                return;
            }
        }
        // Prüfen, ob der Übungsleiter bereits unterschrieben hat
        for (int i = 0; i < signedExerciseLeaders.length; i++) {
            if (signedExerciseLeaders[i] == null) {
                signedExerciseLeaders[i] = lecturer;
                lecturer.sign();
                return;
            }
        }
    }

    /**
     * Erhöht die Erfahrungspunkte des Heros.
     *
     * @param experiencePoints Anzahl der hinzuzufügenden Erfahrungspunkte
     */
    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;
    }

    /**
     * Prüft, ob der Held noch kampffähig ist.
     *
     * @return true, wenn der Held noch Lebenspunkte hat, sonst false
     */
    public boolean isOperational() {
        if (healthPoints <= 0) {
            System.out.println("--You are dead!!!--");
            return false;
        }
        return true;
    }

}