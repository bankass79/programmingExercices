package programmingExercices;

public class Livrotheque extends Bibliotheque {
    
    public Livrotheque(int capacite) {
        super(capacite);
    }
    
    public boolean ajouter(Document doc) {
        if (doc instanceof Livre)
            return super.ajouter(doc);
        return false;
    }
    
    public Livre livre(int i) {
        return (Livre) docs[i];
    }
    
    public void afficherAuteurs() {
        for (int i = 0; i < nombre; i++)
            System.out.println(((Livre)docs[i]).auteur());
    }
}