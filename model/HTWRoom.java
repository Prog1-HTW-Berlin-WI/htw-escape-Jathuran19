package model;

import java.io.Serial;
import java.io.Serializable;

public class HTWRoom implements Serializable {
    private String identifier;
    private String description;
    private Lecturer lecturer;

    public HTWRoom(String identifier, String description, Lecturer lecturer){
            this.identifier = identifier;
            this.description = description;
            this.lecturer = lecturer;

    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Lecturer getLecturer() {
        return this.lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }


    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 9065680017147292999L;
}