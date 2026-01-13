package model;

import java.io.Serializable;

public class Hero implements Serializable {
       
<<<<<<< HEAD
    private String name;
    private int healthPoints;
    private int experiencePoints;
    private Lecturer[] signedExerciseLeaders;

    public Hero (String name, int healthPoints, int experiencePoints){
=======
        private String name;
        private int healthPoints;
        private int experiencePoints;
        private Lecturer[] signedExerciseLeaders;

        public Hero (String name, int healthPoints, int experiencePoints){
>>>>>>> b2a11dacb985977411b649ca68754de85492c5fa
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

<<<<<<< HEAD
    //Methoden:
=======
>>>>>>> b2a11dacb985977411b649ca68754de85492c5fa

    // Lebenspunkte können Leben verlieren je nach Schaden und die Untergrenze ist 0 Lebenspunkte
    public void takeDamage(int amount){
        healthPoints -= amount;
            if(healthPoints < 0){
                healthPoints = 0; 
            }
    }

    // Hero kann sich heilen mit kleine/große Verschnaufpause und die Obergrenze ist 50 Lebenspunkte.
    public void regenerate( boolean longRest){
        if(longRest ==false){
            healthPoints += 3;
        } 
        if(longRest == true){
           healthPoints += 10; 
        }

        if(healthPoints > 50){
            healthPoints = 50;
        }
    }
    // Hero hat 42% chance um zu entkommen 
    public boolean flee(){
        double risk = Math.random(); 
        if(risk < 0.42){
            return true;
        } else{
            return false;
        }
    }
    public int attack(){
        double damage = experiencePoints * 2.3 + 1;
        double chance = Math.random();
<<<<<<< HEAD
        if(chance < 0.13){
            return 0;
        }
        if(chance < 0.25){
            damage * 2;
=======
        if( chance < 0.13){
            return 0;
        }
        if(chance < 0.12){
            return damage * 2;
>>>>>>> b2a11dacb985977411b649ca68754de85492c5fa
        }
        
    }
        public void signExerciseLeader(Lecturer lecturer) {
    if (lecturer == null) {
        return;
<<<<<<< HEAD
        }
=======
    }
>>>>>>> b2a11dacb985977411b649ca68754de85492c5fa
    for (int i = 0; i < signedExerciseLeaders.length; i++) {
    if (signedExerciseLeaders[i] != null &&
        signedExerciseLeaders[i].getName().equals(lecturer.getName())) {
        return;  
<<<<<<< HEAD
            }
        }
=======
    }
}
>>>>>>> b2a11dacb985977411b649ca68754de85492c5fa
    for (int i = 0; i < signedExerciseLeaders.length; i++) {
        if (signedExerciseLeaders[i] == null) {
            signedExerciseLeaders[i] = lecturer;
            lecturer.setHasSigned(true);
            return;
<<<<<<< HEAD
            }
        }
    }

    public void int getExperiencePoints(){
    return experiencePoints;
    }

    public void addExperiencePoints(int experiencePoints) {
    this.experiencePoints += experiencePoints;
    }

    public boolean isOperational(){
    if(healthPoints <= 0){
        System.out.println("--You are dead!!!--");
        return false;
        }   
        return true;
    }

 
=======
        }
    }
}

public void int getExperiencePoints(){
    return experiencePoints;
}

public void addExperiencePoints(int experiencePoints) {
    this.experiencePoints += experiencePoints;
}

public boolean isOperational(){
    if(healthPoints <= 0){
        System.out.println("--You are dead!!!--");
        return false;
    }
    return true;
}







        }   
>>>>>>> b2a11dacb985977411b649ca68754de85492c5fa
    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;
}