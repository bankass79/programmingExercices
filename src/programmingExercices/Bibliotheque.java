package programmingExercices;

public class Bibliotheque {
    protected Document[] docs;
    protected int capacite;
    protected int nombre;
    
    public Bibliotheque(int capacite) {
        this.capacite = capacite;
        docs = new Document[capacite];
        nombre = 0;
    }

    public void afficherDocuments() {
        for (int i = 0; i < nombre; i++)
            System.out.println(docs[i]);
    }
    
    public boolean ajouter(Document doc) {
        if (nombre < docs.length) {
            docs[nombre++] = doc;
            return true;
        }
        return false;
    }
    
    public Document document(int i) {
        return docs[i];
    }
    
    public void afficherAuteurs() {
        for (int i = 0; i < nombre; i++)
            if (docs[i] instanceof Livre) 
                System.out.println(((Livre)docs[i]).auteur());
    }
    
    public boolean supprimer(Document doc) {
        for (int i = 0; i < nombre; i++) {
            if (docs[i].equals(doc)) {
                for (int j = i + 1; j < nombre; j++)
                    docs[j - 1] = docs[j];
                nombre--; 
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Bibliotheque bib = new Bibliotheque(100);

        bib.ajouter(new Document(1001, "Les misérables"));
        bib.ajouter(new Livre(1002, "Guerre et paix", "L. Tolstoi", 2400));
        bib.ajouter(new Roman(1003, "Les raisins de la colère", "J. Steinbeck", 650,
                Roman.NOBEL | Roman.ACADEMIE));
        bib.ajouter(new Manuel(1004, "Algebre", "E. Galois", 120, 2));
        bib.ajouter(new Revue(1005, "Informatique & psychanalyse", 4, 2004));
        bib.ajouter(new Dictionnaire(1006, "Caramba!", Dictionnaire.ESPAGNOL));
        
        bib.afficherDocuments();
        System.out.println("-------------------------------------------------------------");
        bib.afficherAuteurs();
        System.out.println("-------------------------------------------------------------");
        
        bib.supprimer(new Roman(1003, "", "", 0, 0));
        bib.afficherDocuments();
    }
}
