package programmingExercices;

import javax.swing.Action;

/**
 * Interface d'un noeud d'un arbre
 * @author O. Carton
 * @version 1.0
 */
interface Node {
    /** Hauteur de l'arbre */
    int height();
    /** Ajout d'un élément dans le sous arbre */
    Node put(Comparable key);
    /** Recherche d'un élément dans le sous arbre */
    boolean contains(Comparable key);
    /** Parours préfixe du sous-arbre */
    void prefixRun(Action a);
    /** Parours infixe du sous-arbre */
    void infixRun(Action a);
    /** Parours suffixe du sous-arbre */
    void suffixRun(Action a);
    /** Serialisation of the subtree */
    public void toString(StringBuffer sb);
    /** Serialisation of the k-th level of the subtree */
    public void toString(StringBuffer sb, int k);
}
