package programmingExercices;

import javax.swing.Action;

/**
 * Classe pour l'arbre vide
 *
 * @version 1.0
 */
class EmptyNode implements Node {
    /** Hauteur */
    public int height() { return 0; }
    public Node put(Comparable key) 
    {
	return new InternalNode(key, this, this);
    }
    public boolean contains(Comparable key) { return false; }
    public void prefixRun(Action a) {}
    public void infixRun(Action a) {}
    public void suffixRun(Action a) {}
    public void toString(StringBuffer sb) {}
    public void toString(StringBuffer sb, int k) {}
	
}
