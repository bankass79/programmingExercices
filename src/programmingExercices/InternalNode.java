package programmingExercices;

import javax.swing.Action;

import Node;

/**
 * @author amadou
 *
 */
class InternalNode implements Node {
	    /** Key */
	    Comparable value;
	    /** Left subtree */
	    Node left;
	    /** Right subtree */
	    Node right;
	    /** Création d'un noeud interne */
	    InternalNode(Comparable value, Node left, Node right) 
	    {
		this.value = value;
		this.left = left;
		this.right = right;
	    }
	    /** Calcul de la hauteur du sous-arbre */
	    public int height() {return 1 + Math.max(left.height(), right.height());}
	    /** Ajout d'un élément dans le sous arbre */
	    public Node put(Comparable key)
	    {
		if (key.compareTo(value) <= 0)
		    left = left.put(key);
		else
		    right = right.put(key);
		return this;
	    }
	    /** Recherche d'un élément dans le sous arbre */
	    public boolean contains(Comparable key)
	    {
		if (key.compareTo(value) == 0)
		    return true;
		else if (key.compareTo(value) <= 0)
		    return left.contains(key);
		else 
		    return right.contains(key);
	    }
	    /** Parours préfixe du sous-arbre */

	    public void prefixRun(Action a)
	    {
		a.run(value);
		left.prefixRun(a);
		right.prefixRun(a);
	    }
	    /** Parours infixe du sous-arbre */

	    public void infixRun(Action a)
	    {
		left.infixRun(a);
		a.run(value);
		right.infixRun(a);
	    }
	    /** Parours suffixe du sous-arbre */

	     public void suffixRun(Action a)
	    {
		left.suffixRun(a);
		right.suffixRun(a);
		a.run(value);
	    }
	    public void toString(StringBuffer sb)
	    {
		sb.append("(");
		left.toString(sb);
		sb.append(value);
		right.toString(sb);
		sb.append(")");
	    }
	    public void toString(StringBuffer sb, int k)
	    {
		sb.append(' ');
		left.toString(sb, k-1);
		if (k == 0) {
		    sb.append(value);
		} else {
		    for(int l = 0; l < String.valueOf(value).length(); l++)
			sb.append(' ');
		}
		right.toString(sb, k-1);
		sb.append(' ');
	    }
		
}
