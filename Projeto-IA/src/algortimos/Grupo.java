package algortimos;

import java.util.ArrayList;

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
		for(int i = 0; i< this.centroide.length;i++){
			double diferenca = (centroide[i] - centroideAnterior[i]);
			if(!(diferenca <= 0.001 && diferenca >= 0))
				return false;
		}
		return true;
	}
	
	

}
