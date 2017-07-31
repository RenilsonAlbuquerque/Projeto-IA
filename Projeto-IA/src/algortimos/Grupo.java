package algortimos;

import java.util.ArrayList;
import java.util.Arrays;

public class Grupo {
	
	private ArrayList<Integer[]> elementos;
	private Double[] centroide;
	private Double[] centroideAnterior;
	
	public Grupo(Double[] centroide){
		this.centroide = centroide;
		this.elementos = new ArrayList<Integer[]>();
	}

	public ArrayList<Integer[]> getElementos() {
		return elementos;
	}

	public void setElementos(ArrayList<Integer[]> elementos) {
		this.elementos = elementos;
	}

	public Double[] getCentroide() {
		return centroide;
	}

	public void setCentroide(Double[] centroide) {
		this.centroideAnterior = this.centroide;
		this.centroide = centroide;
	}
	public boolean convergenciaParcial(){
		return Arrays.equals(this.centroide, this.centroideAnterior);
	}
	
	

}
