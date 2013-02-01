package anrec;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import anrec.ObjetDessinable;

/**
 * Cette classe représente un Cluster pour une méthode de clustering k-mean.
 * Un cluster possède un centre, ainsi qu'une liste de points.
 * @author Sébastien BRO & Marie MAINGUY
 * @version 1.0
 */

public class Cluster implements ObjetDessinable{

// Attributs
	
	/**
	* Centre du cluster
	*/
	private int ID;
	
	/**
	* Centre du cluster
	*/
	private float[] centre;
	
	/**
	* Liste des points du cluster
	*/
	private LinkedList<float[]> points;
	
	/**
	* Liste des points du cluster
	*/
	private Kmean kmean;
	
// Constructeur
	/**
	* Constructeur d'un Cluster étant données un centre
	* @param centre Centre du cluster
	*/
    public Cluster(int ID, float[] centre, Kmean kmean) {
        this.ID = ID;
    	this.centre = centre;
        points = new LinkedList<float[]>();
        this.kmean = kmean;
    }

    
// Getters et setters
	
    public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
    
    public float[] getCentre() {
		return centre;
	}

	public void setCentre(float[] centre) {
		this.centre = centre;
	}
	
	public LinkedList<float[]> getPoints() {
		return points;
	}

	public void setPoints(LinkedList<float[]> points) {
		this.points = points;
	}


// Méthodes	
	
	/**
	 * Ajoute un point au cluster
	 * @param 	point	Point à ajouter au cluster
	 */
	public void addPoint(float[] point) { //called from CAInstance
        this.points.add(point);
    }
	
	/**
	 * Enleve un point au cluster
	 * @param 	point	Point à enlever au cluster
	 */
	public void removePoint(float[] point) { //called from CAInstance
        this.points.remove(point);
    }
	
	/**
	 * Renvoie le nombre de points du cluster
	 * @return 	this.points.size()	Nombre de points du cluster
	 */
    public int getNbPoints() {
        return this.points.size();
    }


	@Override
	public void dessinerObjet(Graphics g) {
		// TODO Auto-generated method stub
		if (this.centre.length==2){
			// On plot les points du cluster
			Random alea = new java.util.Random(System.currentTimeMillis());
			Color couleur = new Color(Math.abs(alea.nextInt()) % 256,Math.abs(alea.nextInt()) % 256,Math.abs(alea.nextInt()) % 256);
			g.setColor(couleur);
			Iterator<float[]> iter = this.points.iterator();
			while (iter.hasNext()){
				float[] cluster = iter.next();
				int[] pointDessin= new int[2];
				pointDessin[0]= (int) cluster[0]*5;
				pointDessin[1]= (int) cluster[1]*5;
				g.fillRect(pointDessin[0],pointDessin[1],3,3);
			}
			g.fillRect((int)this.centre[0]*5,(int)this.centre[1]*5,5,5);
		}
		else{
			// On dessine un diagramme en barre
			Random alea = new java.util.Random(System.currentTimeMillis());
			Color couleur = new Color(Math.abs(alea.nextInt()*this.getID()) % 255,Math.abs(alea.nextInt()*this.getID()) % 255,Math.abs(alea.nextInt()*this.getID() % 255));
			g.setColor(couleur);
			int[] pointDessinX = {this.getID()*100,(this.getID()+1)*100,(this.getID()+1)*100, this.getID()*100};
			int[] pointDessinY = {0, 0, (int)(this.getNbPoints()*600/this.kmean.getN()), (int)(this.getNbPoints()*600/this.kmean.getN())};
			g.fillPolygon(pointDessinX,pointDessinY,4);
		}
	}
		
}
