package programmingExercices;

public class Dictionnaire extends Document {
    public static final int FRANCAIS = 1;
    public static final int ANGLAIS  = 2;
    public static final int ALLEMAND = 3;
    public static final int ESPAGNOL = 4;
    public static final int RUSSE    = 5;
    
    public static final String[] langues = { null,
            "Français", "Anglais", "Allemand", "Espagnol", "Russe" };
    
    private int langue;
    
    public Dictionnaire(int numeroEnreg, String titre, int langue) {
        super(numeroEnreg, titre);
        this.langue = langue;
    }
    
    public int langue() {
        return langue;
    }
    
    public String toString() {
        return super.toString() + " " + langues[langue];
    }
}
