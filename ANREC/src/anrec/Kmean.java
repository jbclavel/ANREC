package anrec;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Arrays;


/**
 * Cette classe représente une méthode de clustering k-mean.
 * Cette méthode utilise une matrice de points mat, un nombre 
 * de clusters souhaités, ainsi que la liste de ces clusters.
 * @author CLAVEL & AMAR
 * @version 1.0
 */

public class Kmean {
	
// Attributs
	
	/**
	* Matrice des points à tester
	*/
	private float[][] mat;
	
	/**
	* Nombre de clusters souhaités
	*/
	private int k;
	
	/**
	* Liste des clusters
	*/
	private LinkedList<Cluster> clusters;

	
// Constructeur
	
	/**
	* Constructeur d'un Kmean étant données une matrice de 
	* points à tester ainsi qu'un nombre de clusters souhaités.
	* @param m Matrice de points
	* @param k Nombre de clusters
	*/
	public Kmean(float[][] m, int k){
		this.mat = m;
		this.k = k;
		this.clusters = new LinkedList<Cluster>();
	}

// Getters & Setters
	public float[][] getM() {
		return mat;
	}

	public void setM(float[][] m) {
		this.mat = m;
	}

	public int getN() {
		return this.mat.length;
	}
	
	public int getP() {
		return this.mat[0].length;
	}
	
	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
	
// Méthodes
	
	/**
	 * Renvoie une liste de k entiers distincts compris entre 0 et n-1
	 * @param 	n			  Borne supérieure
	 * @param   k			  Nombre d'entiers souhaités
	 * @return 	a			  Liste d'entiers
	 */
	public static int[] randomize(int k, int n) {
	    int[] returnArray = null;
	    int[] a = null;
	    if (n > 0) {
	        // Permutation aléatoire des n entiers compris entre 0 et n-1
	    	returnArray = new int[n];
	        for (int index = 0; index < n; ++index) {
	            returnArray[index] = index;
	        }
	        Random random = new Random(System.currentTimeMillis());
	        for (int index = 0; index < n; ++index) {
	            int j = (int) (random.nextDouble() * (n - index) + index);
	            int tmp = returnArray[index];
	            returnArray[index] = returnArray[j];
	            returnArray[j] = tmp;
	        }
	        
	        // Sélection des k premières lignes
	        a = new int[k];
	        for (int i = 0; i < k; i++){
	        	a[i]=returnArray[i];
	        }
	    }
	    return a;
	}
	
	/**
	 * Renvoie le plus grand élément en valeur absolue d'une matrice
	 * @param 	mat			  Matrice
	 * @return 	max			  Plus grand élément
	 */
	public static float matMax(float[][] mat) {
		float max = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (Math.abs(mat[i][j]) > max){
					max = Math.abs(mat[i][j]);
				}
			}
		}
		return max;
	}
	
	/**
	 * Renvoie la distance de Minkowski entre deux points
	 * Cas particuliers:
	 * 		p=1: Distance de Manhattan
	 * 		p=2: Distance Euclidienne
	 * @param 	a			  1er point
	 * @param 	b			  2e point
	 * @param   p			  Parametre p-distance
	 * @return 	dis		  Distance de Minkowski entre a et b
	 */
	public static float distance(float[] a, float[] b, int p) {
		float dis = 0;
		if (a.length == b.length){
			for (int i = 0; i < a.length; i++) {
				dis += Math.pow(Math.abs(a[i]-b[i]),p);
			}
		}
		dis = (float)Math.pow(dis,(1.0/p));
		return dis;
	}
	
	/**
	 * Affiche une matrice
	 * @param 	mat			  Matrice
	 */
	public static void displayMat(LinkedList<float[]> mat) {
		for (int i = 0; i < mat.size(); i++){
			for (int j = 0; j < mat.get(i).length; j++){
				System.out.print(" "+mat.get(i)[j]+" ");
			}
			System.out.println();
		}
	}
	
	
	public LinkedList<Cluster> clustering(int parametreDistance) {
		// Sélection aléatoire de k entiers compris entre 0 et n-1
		int[] a = randomize(this.getK(),this.getN());
		
		// Initialisation des clusters avec les centres dont les indices correspondent aux valeurs de a
		for (int i = 0; i < this.getK(); i++){
			Cluster cluster = new Cluster(i,mat[a[i]], this);
			this.clusters.add(cluster);
		}
		
		int change = 1;

		while (change == 1){
		// Vidage des points des clusters
		for(int k = 0; k<this.getK(); k++){
			clusters.get(k).getPoints().clear();
		}
			
		// Répartition dans les clusters
		for (int i = 0; i < this.getN(); i++){
			
			// Initialisation de disMin par +infini
			float disMin = Float.POSITIVE_INFINITY;
			int index = -1;
			change=0;
			for (int k = 0; k < this.getK(); k++){
				// Calcul de la distance du point à un centre de cluster
				float dis = distance(mat[i],clusters.get(k).getCentre(),parametreDistance);
				// Si cette distance est minimale, stocker l'indice du cluster
				if (dis < disMin){
					disMin = dis;
					index = k; // Indice du cluster correspondant
				}
			}
			// Ajouter le point au cluster le plus proche
			clusters.get(index).addPoint(mat[i]);
		}

		// Calcul du nouveau centre de chaque cluster
		for (int k = 0; k < this.getK(); k++){
			float[] nouveauCentre = new float[this.getP()];
			for (int j = 0; j < this.getP(); j++){
				nouveauCentre[j] = 0;
				for (int l = 0; l < clusters.get(k).getNbPoints(); l++){
					nouveauCentre[j] += clusters.get(k).getPoints().get(l)[j];
				}
				nouveauCentre[j] = (float)nouveauCentre[j]/clusters.get(k).getNbPoints();
			}
			
			for (int i = 0; i<this.getP();i++){
				System.out.print(" "+clusters.get(k).getCentre()[i]+" ");
			}
			System.out.println("");
			// Remplacement du centre du cluster
			boolean egal = true;
			for (int i = 0; i<this.getP();i++){
				egal=egal&&(nouveauCentre[i]-clusters.get(k).getCentre()[i])<(1e-5);
			}
			if (!egal){
				change = 1;
				clusters.get(k).setCentre(nouveauCentre);
			}
		}
		}
		

		for (int i = 0; i<this.getK();i++){
			System.out.println("Cluster"+i+": "+clusters.get(i).getNbPoints());
		}
		
		
		/* Construction de la matrice distance
		float[][] dis = new float[this.getN()][this.getP()];
		for (int i = 0; i<this.getN(); i++){
			for (int j = 0; j < i; j++){
				dis[i][j] = disMan(mat[i],mat[j]);
				dis[j][i] = dis[i][j];
			}
		}*/
		return clusters;
	}
	
	public void afficher(){
		ZoneGraphique zone = new ZoneGraphique("K means");
		Iterator<Cluster> iter = this.clusters.iterator();
		while (iter.hasNext()){
			zone.ajouterObjet(iter.next());
		}
	}
	
}
