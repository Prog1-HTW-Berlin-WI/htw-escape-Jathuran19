package model;

/**
 * Repräsentiert ein böses Alien.
 * Dieses Alien ist feindlich und greift den Spieler an.
 * @author Arda Bingöl
 * @author Jathuran Sathananthan 
 */
public class EvilAlien extends Alien {
    public EvilAlien() {

        // Aufruf des Konstruktors der Oberklasse Alien
        super("Darth Vader", 15, false, "There is no escape for you, I will destroy you");

    }

}
