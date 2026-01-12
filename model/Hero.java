package model;

import java.io.Serializable;

public class Hero implements Serializable {
       
        private String name;
        private int healthPoints;
        private int experiencePoints;
        private Lecturer[] signedExerciseLeaders;

        public Hero (String name, int healthPoints, int experiencePoints){
            this.name = name;
            this.healthPoints = 50;
            this.experiencePoints = 0;
            this.signedExerciseLeaders = new Lecturer[5];


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



    public void takeDamage(int amount){
        healthPoints -= amount;
            if(healthPoints < 0){
                healthPoints = 0; 
            }
    }


    public void regenerate( boolean longRest){
        if(longRest ==false){
            healthPoints += 3;
            System.out.println("")
        } 
        if(longRest == true){
           healthPoints += 10; 
        }

        if(healthPoints > 50){
            healthPoints = 50;
        }
    }
        }   
    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;
}