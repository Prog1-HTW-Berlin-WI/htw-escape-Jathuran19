package model;
import java.io.Serializable;

public class Lecturer implements Serializable {

    private String name;
    private boolean hasSigned;

    public Lecturer(String name, boolean hasSigned){
        this.name = name;
        this.hasSigned = hasSigned;
<<<<<<< HEAD
    }

=======

    }
>>>>>>> b2a11dacb985977411b649ca68754de85492c5fa
    public String getName() {
        return this.name;
    }

    public boolean isHasSigned() {
        return this.hasSigned;
    }

    public boolean getHasSigned() {
        return this.hasSigned;
    }
    
<<<<<<< HEAD
    //Methoden:
    public boolean isReadyToSign() {
        return !hasSigned;
    }
    
    public void sign() {
        hasSigned = true;
    }
    
=======
    public boolean isReadyToSign() {
    return !hasSigned;
    }
    
    public void sign() {
    hasSigned = true;
    }
    




























>>>>>>> b2a11dacb985977411b649ca68754de85492c5fa
    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 540082607047283589L;
}