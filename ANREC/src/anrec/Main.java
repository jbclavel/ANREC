package anrec;


public class Main {
	public static void main(String[] args){

		// On définit le nombre de clusters souhaité
		int k = 2;
		
		// On définit le paramètre de la distance de Minkowski souhaité
		int p = 2;
		
		// On extrait les données de test depuis un fichier texte, en spécifiant le nombre
		// de lignes et de colonnes étudiées, ainsi que les lignes et colonnes à partir desquelles
		// commencer
		//txtToMatrix test = new txtToMatrix ("src/anrec/exemple1.txt",3009,2,0,0);
		//txtToMatrix test = new txtToMatrix ("src/anrec/exemple2.txt",2665,2,0,0);
		txtToMatrix test = new txtToMatrix ("src/anrec/ListeDesMoyennes.txt",299,20,2,1);
		test.extraction();
	
		// On construit l'objet k-mean
		Kmean kmean = new Kmean(test.matrice,k);
		
		// On lance l'algorithme avec l'entier correspondant au calcul de la distance souhaité
		kmean.clustering(p);
		kmean.afficher();
	}
}