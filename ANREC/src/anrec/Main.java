package anrec;


public class Main {
	public static void main(String[] args){

		// On d�finit le nombre de clusters souhait�
		int k = 2;
		
		// On d�finit le param�tre de la distance de Minkowski souhait�
		int p = 2;
		
		// On extrait les donn�es de test depuis un fichier texte, en sp�cifiant le nombre
		// de lignes et de colonnes �tudi�es, ainsi que les lignes et colonnes � partir desquelles
		// commencer
		//txtToMatrix test = new txtToMatrix ("src/anrec/exemple1.txt",3009,2,0,0);
		//txtToMatrix test = new txtToMatrix ("src/anrec/exemple2.txt",2665,2,0,0);
		txtToMatrix test = new txtToMatrix ("src/anrec/ListeDesMoyennes.txt",299,20,2,1);
		test.extraction();
	
		// On construit l'objet k-mean
		Kmean kmean = new Kmean(test.matrice,k);
		
		// On lance l'algorithme avec l'entier correspondant au calcul de la distance souhait�
		kmean.clustering(p);
		kmean.afficher();
	}
}