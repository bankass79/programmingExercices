package programmingExercices;


//======================================================================
//ALGORITHMES SUR LES GRAPHES
//======================================================================
//
//L'applet affiche dynamiquement un graphe compose de sommets et
//d'aretes. Elle permet d'illuster les algorithmes classique de
//parcours de graphe.
//
//	- Parcours en largeur (PL)
//	- Parcours en profondeur (PP)
//
//======================================================================
//AUTEURS
//======================================================================
//
//	- Denis Capouillez
//	- Jean Mangel
//
//======================================================================
//UTILISATION
//======================================================================
//
//	- RESET       Remise a zero en fin de parcours.
//
//	- STICK       Stabilisation de l'affichage du graphe.
//
//	- PL          Algorithme de Parcourcs en Largeur.
//
//	- PP          Algorithme de Parcourcs en Profondeur.
//
//	- NUM         Numerotation des noeuds.
//
//	- GO          Parcours automatique du graphe.
//
//	- STOP        Arret du parcours automatique.
//
//	- STEP        Parcours pas a pas du graphe.
//
//======================================================================
//COULEURS
//======================================================================
//
//	- BLANC		Affichage dynamique du graphe.
//
//	- GRIS		Affichage stabilise du graphe.
//
//	- JAUNE		Sommet non decouvert (0).
//
//	- ROUGE		Point de depart du graphe.
//
//	- VERT		Sommet visite du graphe (-1).
//
//	- BLEU		Sommet explore du graphe (N).
//
//======================================================================
//PARAMETRES
//======================================================================
//
//center	Le sommet central du graphe fixe au centre de l'ecran.
//
//edges        Les aretes du graphe. voir l'explication ci-dessous.
//
//======================================================================
//FORMAT
//======================================================================
//
//Le format du parametre "edges" consiste en une liste d'aretes entre
//les sommets separes par des virgules. Les sommets sont crees quand
//on en a besoin. Chaque arete est definie comme une paire de sommets:
//
//   <source>-<destination>
//
//On peut aussi specifier la longueur desiree de l'arete.
//
//   <source>-<destination>/<longueur>
//
//======================================================================
//EXEMPLE
//======================================================================
//
//   <APPLET CODE="Graph.class" WIDTH=400 HEIGHT=400>
//   <PARAM NAME="edges" VALUE="
//   	A-B/100,A-C/100,A-D/100,A-E/100,A-F/100,
//           B-G,B-H,B-I,B-J,
//           C-K,C-L,C-M,C-N,
//           D-O,D-P,D-Q,D-R,
//           E-S,E-T,E-U,E-V,
//           F-W,F-X,F-Y,F-Z">
//   <PARAM NAME="center" VALUE="A">
//   </APPLET>
//
//======================================================================
//ARCHITECTURE
//======================================================================
//
//	+-----------------------+	+-----------------------+
//	| APPLET                |	| RUNNABLE (PANEL)      |
//	| +-------------------+ |	|+----------+----------+|
//	| | PANEL             | |	||  NODE    |   EDGE   ||
//	| |                   | |	|+----------+----------+|
//	| |                   | |	+-----------------------+
//	| |      GRAPH        | |
//	| |                   | |	+-----------------------+
//	| |                   | |	| THREAD (ALGO)         |
//	| +--------+----------+ |	|+-------+------+------+|
//	| | BUTTON | CHECKBOX | |	|| LISTE | PILE | FILE ||
//	| +--------+----------+ |	|+-------+------+------+|
//	+-----------------------+	+-----------------------+
//
//======================================================================
import java.util.*;
import java.awt.*;
import java.applet.Applet;
//======================================================================
//SOMMET
//======================================================================
class Node2 {
	double x;
	double y;
	double dx;
	double dy;
	boolean fixed;
	boolean touch;
	boolean first;
	String lbl;
	int value;
}
//======================================================================
//ARETE
//======================================================================
class Edge {
	int from;
	int to;
	double len;
}
//======================================================================
//LISTE : Listes d'adjacence utilisee pour le parcours des graphes
//======================================================================
class Liste {
	int s;
	Liste l;
}
//======================================================================
//PILE : LIFO utilisee pour le parcours en profondeur des graphes
//======================================================================
class Pile {
	int p[] = new int[100];
	int p_pile = 0;
	boolean pileVide() {
		if (p_pile == 0) {
			return true;
		} else {
			return false;
		}
	}
	void empiler(int x) {
		if (p_pile < 100) {
			p[p_pile++] = x;
		} else {
			p[p_pile] = x;
		}
	}
	int depiler() {
		if (p_pile > 0) {
			return p[--p_pile];
		} else {
			return p[p_pile];
		}
	}
}
//======================================================================
//FILE : FIFO utilisee pour le parcours en largeur des graphes
//======================================================================
class File {
	int f[] = new int[100];
	int debut = 0;
	int fin = 0;
	boolean fileVide() {
		if (debut == fin) {
			return true;
		} else {
			return false;
		}
	}
	void enfiler(int x) {
		f[fin++] = x;
		if (fin > 100) {
			fin = 0;
		}
	}
	int defiler() {
		int t = f[debut++];
		if (debut > 100) {
			debut = 0;
		}
		return t;
	}
	public void ajouter(Arbre arbre) {
		//debut=ajouter(debut, arbre);
		
	}
}
//======================================================================
//PANEL
//======================================================================
class GraphPanel extends Panel implements Runnable {
	Graph graph;
	int nnodes;
	Node2 nodes[] = new Node2[100];
	int nedges;
	static Edge edges[] = new Edge[200];
	Thread relaxer;
	boolean number;
	boolean stable;
//----------------------------------------------------------------------
	GraphPanel(Graph graph) {
		this.graph = graph;
	}
//----------------------------------------------------------------------
	int findNode(String lbl) {
		for (int i = 0 ; i < nnodes ; i++) {
			if (nodes[i].lbl.equals(lbl)) {
				return i;
	    		}
		}
		return addNode(lbl);
	}
//----------------------------------------------------------------------
	int addNode(String lbl) {
		Node2 n = new Node2();
		n.x = 10 + 380*Math.random();
		n.y = 10 + 380*Math.random();
		n.lbl = lbl;
		nodes[nnodes] = n;
		return nnodes++;
	}
//----------------------------------------------------------------------
	void addEdge(String from, String to, int len) {
		Edge e = new Edge();
		e.from = findNode(from);
		e.to = findNode(to);
		e.len = len;
		edges[nedges++] = e;
	}
//----------------------------------------------------------------------
	public void run() {
		while (true) {
			relax();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
//----------------------------------------------------------------------
	synchronized void relax() {
		for (int i = 0 ; i < nedges ; i++) {
			Edge e = edges[i];
			double vx = nodes[e.to].x - nodes[e.from].x;
			double vy = nodes[e.to].y - nodes[e.from].y;
			double len = Math.sqrt(vx * vx + vy * vy);
			double f = (edges[i].len - len) / (len * 3) ;
			double dx = f * vx;
			double dy = f * vy;

			nodes[e.to].dx += dx;
			nodes[e.to].dy += dy;
			nodes[e.from].dx += -dx;
			nodes[e.from].dy += -dy;
		}
		for (int i = 0 ; i < nnodes ; i++) {
			Node2 n1 = nodes[i];
			double dx = 0;
			double dy = 0;
			for (int j = 0 ; j < nnodes ; j++) {
				if (i == j) {
					continue;
				}
				Node2 n2 = nodes[j];
				double vx = n1.x - n2.x;
				double vy = n1.y - n2.y;
				double len = vx * vx + vy * vy;
				if (len == 0) {
					dx += Math.random();
					dy += Math.random();
				} else if (len < 100*100) {
					dx += vx / len;
					dy += vy / len;
				}
			}
			double dlen = dx * dx + dy * dy;
			if (dlen > 0) {
				dlen = Math.sqrt(dlen) / 2;
				n1.dx += dx / dlen;
				n1.dy += dy / dlen;
			}
		}
		Dimension d =getMaximumSize();
		for (int i = 0 ; i < nnodes ; i++) {
			Node2 n = nodes[i];
			if (!stable) {
				if (!n.fixed) {
					n.x += Math.max(-5, Math.min(5, n.dx));
					n.y += Math.max(-5, Math.min(5, n.dy));
					//System.out.println("v= " + n.dx + "," + n.dy);
					if (n.x < 0) {
						n.x = 0;
					} else if (n.x > d.width) {
						n.x = d.width;
					}
					if (n.y < 0) {
						n.y = 0;
					} else if (n.y > d.height) {
						n.y = d.height;
					}
				}
			}
			n.dx /= 2;
			n.dy /= 2;
		}
		repaint();
	}
//----------------------------------------------------------------------
	Node2 pick;
	boolean pickfixed;
	Image offscreen;
	Dimension offscreensize;
	Graphics offgraphics;
	final Color fixedColor = Color.gray;
	final Color selectColor = Color.pink;
	final Color edgeColor = Color.black;
	final Color nodeColor = Color.white;
	final Color arcColor = Color.black;
	final Color firstColor = Color.red;
	final Color zeroColor = Color.yellow;
	final Color lessColor = Color.green;
	final Color plusColor = Color.blue;
//----------------------------------------------------------------------
	public void paintNode(Graphics g, Node2 n, FontMetrics fm) {
		int x = (int)n.x;
		int y = (int)n.y;
		if (n == pick) {
			g.setColor(selectColor);
		} else {
			if (n.touch) {
				switch (n.value) {
					case -1: 
						g.setColor(lessColor);
						break;
					case 0:
						if (n.first) {
							g.setColor(firstColor);
						} else {
							g.setColor(zeroColor);
						}
						break;
					default:
						g.setColor(plusColor);
				}
			} else {
				if (stable) {
					g.setColor(fixedColor);
				} else {
					if (n.fixed) {
						g.setColor(fixedColor);
					} else {
						g.setColor(nodeColor);
					}
				}
			}
		}
		int w = fm.stringWidth(n.lbl) + 10;
		int h = fm.getHeight() + 4;
		g.fillOval(x - w/2, y - h / 2, w, h);
		g.setColor(Color.black);
		g.drawOval(x - w/2, y - h / 2, w-1, h-1);
		g.drawString(n.lbl, x - (w-10)/2, (y - (h-4)/2) + fm.getAscent());
		if (number) {
			String lbl = String.valueOf(n.value);
			g.drawString(lbl, x - (w-10)/2, (y - (h-4)/2));
		}
	}
//----------------------------------------------------------------------
	public synchronized void update(Graphics g) {
		Dimension d = getMaximumSize();
		if ((offscreen == null) || (d.width != offscreensize.width) || (d.height != offscreensize.height)) {
			offscreen = createImage(d.width, d.height);
			offscreensize = d;
			offgraphics = offscreen.getGraphics();
			offgraphics.setFont(getFont());
		}
		offgraphics.setColor(getBackground());
		offgraphics.fillRect(0, 0, d.width, d.height);
		for (int i = 0 ; i < nedges ; i++) {
			Edge e = edges[i];
			int x1 = (int)nodes[e.from].x;
			int y1 = (int)nodes[e.from].y;
			int x2 = (int)nodes[e.to].x;
			int y2 = (int)nodes[e.to].y;
			int len = (int)Math.abs(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)) - e.len);
			offgraphics.setColor(arcColor);
			offgraphics.drawLine(x1, y1, x2, y2);
		}
		FontMetrics fm = offgraphics.getFontMetrics();
		for (int i = 0 ; i < nnodes ; i++) {
			paintNode(offgraphics, nodes[i], fm);
		}
		g.drawImage(offscreen, 0, 0, null);
	}
//----------------------------------------------------------------------
	public synchronized boolean mouseDown(Event evt, int x, int y) {
		double bestdist = Double.MAX_VALUE;
		for (int i = 0 ; i < nnodes ; i++) {
			Node2 n = nodes[i];
			double dist = (n.x - x) * (n.x - x) + (n.y - y) * (n.y - y);
			if (dist < bestdist) {
				pick = n;
				bestdist = dist;
			}
		}
		pickfixed = pick.fixed;
		pick.fixed = true;
		pick.x = x;
		pick.y = y;
		repaint();
		return true;
	}
//---------------------------------------------------------------------
	public synchronized boolean mouseDrag(Event evt, int x, int y) {
		pick.x = x;
		pick.y = y;
		repaint();
		return true;
	}
//----------------------------------------------------------------------
	public synchronized boolean mouseUp(Event evt, int x, int y) {
		pick.x = x;
		pick.y = y;
		pick.fixed = pickfixed;
		pick = null;
		repaint();
		return true;
	}
//----------------------------------------------------------------------
	public void start() {
		relaxer = new Thread(this);
		relaxer.start();
	}
//----------------------------------------------------------------------
	public void stop() {
		relaxer.interrupt();
	}
}
//======================================================================
//APPLET
//======================================================================
public class Graph extends Applet {
	GraphPanel panel;
	AlgoThread algo;
//----------------------------------------------------------------------
	public void init() {
		setLayout(new BorderLayout());
		panel = new GraphPanel(this);
		add("Center", panel);
		Panel p = new Panel();
		add("South", p);
		p.add(new Button("RESET"));
		p.add(new Checkbox("STICK"));
		p.add(new Button("PL"));
		p.add(new Button("PP"));
		p.add(new Checkbox("NUM"));
		p.add(new Button("GO"));
		p.add(new Button("STOP"));
		p.add(new Button("STEP"));
		String edges = getParameter("edges");
		for (StringTokenizer t = new StringTokenizer(edges, ",") ; t.hasMoreTokens() ; ) {
			String str = t.nextToken();
			int i = str.indexOf('-');
			if (i > 0) {
				int len = 50;
				int j = str.indexOf('/');
				if (j > 0) {
		    			len = Integer.valueOf(str.substring(j+1)).intValue();
		    			str = str.substring(0, j);
				}
				panel.addEdge(str.substring(0,i), str.substring(i+1), len);
			}
		}
		Dimension d = getMaximumSize();
		String center = getParameter("center");
		if (center != null) {
			Node2 n = panel.nodes[panel.findNode(center)];
			n.x = d.width / 2;
			n.y = d.height / 2;
			n.fixed = true;
			n.touch = false;
		}
		algo = new AlgoThread(panel);
		algo.construireListeAdjacente();
	}
//----------------------------------------------------------------------
	public void start() {
		panel.start();
		algo.start();
	}
//----------------------------------------------------------------------
	public void stop() {
		panel.stop();
		algo.interrupt();;
	}
//----------------------------------------------------------------------
	public boolean action(Event evt, Object arg) {
		if (arg instanceof Boolean) {
			if (((Checkbox)evt.target).getLabel().equals("NUM")) {
				panel.number = ((Boolean)arg).booleanValue();
			} else {
				panel.stable = ((Boolean)arg).booleanValue();
			}
		    return true;
		} 
		if ("RESET".equals(arg)) {
			if (algo.ready) {
				algo.remiseAzero();
			}
			return true;
		}
		if ("PL".equals(arg)) {
			if (algo.ready) {
				algo.pl = true;
			}
			return true;
		}
		if ("PP".equals(arg)) {
			if (algo.ready) {
				algo.pp = true;
			}
			return true;
		}
		if ("GO".equals(arg)) {
			if (!algo.ready) {
				algo.automatic = true;
				algo.stepByStep();
			}
			return true;
		}
		if ("STOP".equals(arg)) {
			if (!algo.ready) {
				algo.automatic = false;
			}
			return true;
		}
		if ("STEP".equals(arg)) {
			if (!algo.ready) {
				if (!algo.automatic) {
					algo.stepByStep();
				}
			}
			return true;
		}
		return false;
	}
}
//======================================================================
//ALGO
//======================================================================
class AlgoThread extends Thread {
	GraphPanel panel;
	Liste t,z;
	Liste liste[] = new Liste[200];
	int id = 0;
	Pile pile;
	File file;
	boolean ready = true;
	boolean pp = false;
	boolean pl = false;
	boolean automatic;
//----------------------------------------------------------------------
	AlgoThread(GraphPanel panel) {
		this.panel = panel;
	}
//----------------------------------------------------------------------
	void construireListeAdjacente() {
		z = new Liste();
		z.l = z;
		for (int j = 0 ; j < panel.nnodes ; j++) {
		    liste[j] = z;
		}
		for (int j = 0 ; j < panel.nedges ; j++) {
			int x = panel.edges[j].from;
			int y = panel.edges[j].to;
			t = new Liste();
			t.s = x;
			t.l = liste[y];
			liste[y] = t;
			t = new Liste();
			t.s = y;
			t.l = liste[x];
			liste[x] = t;
		}
		panel.nodes[0].first = true;
	}
//----------------------------------------------------------------------
	synchronized void remiseAzero() {
		for (int i = 0 ; i < panel.nnodes ; i++) {
			id = 0;
			automatic = false;
			panel.nodes[i].value = 0;
			panel.nodes[i].touch = false;
		}
	}
//----------------------------------------------------------------------
	synchronized void stepByStep() {
		notify();
	}
//----------------------------------------------------------------------
	void explorer(int k) {
		Liste t;
		pile.empiler(k);
		while (!pile.pileVide()) {
			if (automatic) {
				try {
					sleep(200);
				} catch (InterruptedException e) {
				}
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			k = pile.depiler();
			panel.nodes[k].value = ++id;
			panel.nodes[k].touch = true;
			for (t = liste[k]; t != z; t = t.l) {
				if (panel.nodes[t.s].value == 0) {
					if (automatic) {
						try {
							sleep(200);
						} catch (InterruptedException e) {
						}
					} else {
						try {
							wait();
						} catch (InterruptedException e) {
						}
					}
					pile.empiler(t.s);
					panel.nodes[t.s].value = -1;
					panel.nodes[t.s].touch = true;
				}
			}
		}
	}
//----------------------------------------------------------------------
	synchronized void parcoursEnProfondeur() {
		pile = new Pile();
		for (int k = 0 ; k < panel.nnodes ; k++) {
			panel.nodes[k].touch = true;
		}
		for (int k = 0 ; k < panel.nnodes ; k++) {
			if (panel.nodes[k].value == 0) {
				explorer(k);
			}
		}
		ready = true;
	}
//----------------------------------------------------------------------
	void visiter(int k) {
		Liste t;
		file.enfiler(k);
		while (!file.fileVide()) {
			if (automatic) {
				try {
					sleep(200);
				} catch (InterruptedException e) {
				}
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			k = file.defiler();
			panel.nodes[k].value = ++id;
			panel.nodes[k].touch = true;
			for (t = liste[k]; t != z; t = t.l) {
				if (panel.nodes[t.s].value == 0) {
					if (automatic) {
						try {
							sleep(200);
						} catch (InterruptedException e) {
						}
					} else {
						try {
							wait();
						} catch (InterruptedException e) {
						}
					}
					file.enfiler(t.s);
					panel.nodes[t.s].value = -1;
					panel.nodes[t.s].touch = true;
				}
			}
		}
	}
//----------------------------------------------------------------------
	synchronized void parcoursEnLargeur() {
		file = new File();
		for (int k = 0 ; k < panel.nnodes ; k++) {
			panel.nodes[k].touch = true;
		}
		for (int k = 0 ; k < panel.nnodes ; k++) {
			if (panel.nodes[k].value == 0) {
				visiter(k);
			}
		ready = true;
		}
	}
//----------------------------------------------------------------------
	public void run() {
		while (true) {
			if (ready) {
				if (pl) {
					ready = false;
					pl = false;
					parcoursEnLargeur();
				} else {
					if (pp) {
						ready = false;
						pp = false;
						parcoursEnProfondeur();
					}
				}
			} 
			try {
				sleep(100);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
//======================================================================
/*
 * // Java program to print BFS traversal from a given source vertex.
// BFS(int s) traverses vertices reachable from s.
import java.io.*;
import java.util.*;
 
// This class represents a directed graph using adjacency list
// representation
class Graph
{
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists
 
    // Constructor
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 
    // Function to add an edge into the graph
    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }
 
    // prints BFS traversal from a given source s
    void BFS(int s)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s+" ");
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
 
    // Driver method to
    public static void main(String args[])
    {
        Graph g = new Graph(4);
 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        System.out.println("Following is Breadth First Traversal "+
                           "(starting from vertex 2)");
 
        g.BFS(2);
    }
}
// import java.io.*;

// Java program to print BFS traversal from a given source vertex.
// BFS(int s) traverses vertices reachable from s.
import java.io.*;
import java.util.*;
 
// This class represents a directed graph using adjacency list
// representation
class Graph
{
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists
 
    // Constructor
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 
    // Function to add an edge into the graph
    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }
 
    // prints BFS traversal from a given source s
    void BFS(int s)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s+" ");
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
 
    // Driver method to
    public static void main(String args[])
    {
        Graph g = new Graph(4);
 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        System.out.println("Following is Breadth First Traversal "+
                           "(starting from vertex 2)");
 
        g.BFS(2);
    }
}
// This code is contributed by Aakash Hasija
 * 
 * 
 * 
 * 
 * 
 * */





