package anrec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class txtToMatrix {
	public String txt;
	public float[][] matrice;
	public int startI;
	public int startJ;
	
	//constructeur
	public txtToMatrix (String Path, int dimI, int dimJ, int startI, int startJ){
		this.txt = Path;
		this.matrice= new float[dimI][dimJ];
		this.startI = startI;
		this.startJ = startJ;
	}
	
	
	public float[][] getMatrice() {
		return matrice;
	}



	public void setMatrice(float[][] matrice) {
		this.matrice = matrice;
	}



	//methode
		//extraction des donnees du txt vers la matrice
	public void extraction (){
		try {
			FileReader fr = new FileReader ( this.txt ) ;
			BufferedReader br = new BufferedReader ( fr ) ;
			String ligne;
			try{
				int i=1;
				int j=0;
				while (i<this.startI){
					ligne = br.readLine();
					i++;
				}
				while (i<this.matrice.length+this.startI) {
					ligne = br.readLine( );
					Scanner s = new Scanner(ligne).useDelimiter("\\t");
					while (s.hasNextFloat()){
						if (j>=this.startJ){
							this.matrice[i-startI][j-startJ]=s.nextFloat();
						}else{
							s.nextFloat();
						}
						j++;
					}
					i++;
					j=0;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
