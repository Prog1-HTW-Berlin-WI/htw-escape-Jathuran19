package model;

import java.io.Serializable;

/**
 * Klasse eines Übungsleiters und dessen Eigenschaften
 * 
 * @author Arda Bingöl
 * @author Jathuran Sathananthan
 */

public class Lecturer implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 540082607047283589L;

    private String name;
    private boolean hasSigned;

    public Lecturer(String name) {
        this.name = name;
        this.hasSigned = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isHasSigned() {
        return this.hasSigned;
    }

    // Methoden:

    /**
     * Prüft, ob der Übungsleiter noch unterschreiben kann.
     *
     * @return true, wenn noch keine Unterschrift erfolgt ist, sonst false
     */
    public boolean isReadyToSign() {
        return !hasSigned;
    }

    // Markiert den Übungsleiter als Unterschrieben
    public void sign() {
        hasSigned = true;
    }

}