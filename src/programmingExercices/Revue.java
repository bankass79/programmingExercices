package programmingExercices;

public class Revue extends Document {
    private int mois, annee;
    
    public Revue(int numeroEnreg, String titre, int mois, int annee) {
        super(numeroEnreg, titre);
        this.mois = mois;
        this.annee = annee;
    }
    
    public int mois() {
        return mois;
    }
    public int annee() {
        return annee;
    }
    
    public String toString() {
        return super.toString() + " " + mois + "/" + annee ;
    }
}