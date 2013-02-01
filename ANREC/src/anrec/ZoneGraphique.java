package anrec;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JFrame;

/**
 * une classe comportant une zone graphique dans laquelle on peut dessiner ;
 * le dessin est refait automatiquement par la classe Panel associee ; tous
 * les objets de type ObjetDessinable ajout�s � la liste sont redessines par 
 * un appel a leur methode dessinerObjet(Graphics g)
 * 
 * @see ObjectDessinable,LoftPanel
 * @author moreau
 *
 */
public class ZoneGraphique extends JFrame {

	/**
	 * la liste d'objets a dessiner
	 */
	LinkedList<ObjetDessinable> liste;
	
	/**
	 * constructeur
	 *
	 * @param titre le nom de l'application
	 */
	public ZoneGraphique(String titre)  {
		// appel au constructeur de base
		super(titre);
		
		// ajout d'une taille par d�faut
		setSize(600,600);
		
		// creation de la liste d'objets
		liste = new LinkedList<ObjetDessinable>();
		
		// ajout d'un listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0) ;
			}
	    	} ) ;

		// creation du panneau
		ClusterPanel a = new ClusterPanel(liste);
		getContentPane().add(a);
		
		setVisible(true);
	}

	/**
	 * ajout d'un objet dans la zone graphique
	 */
	void ajouterObjet(ObjetDessinable o) {
		liste.add(o);
	}
	void enleverObjet(ObjetDessinable o) {
		liste.remove(o);
	}
	
	/**
	 * largeur de la partie dessinable
	 */
	public int getWidth() {
		return getContentPane().getWidth();
	}
	
	/**
	 * hauteur de la partie dessinable
	 */
	public int getHeight() {
		return getContentPane().getHeight();
	}
	
}
