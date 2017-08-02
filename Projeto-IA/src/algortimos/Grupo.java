package algortimos;

import java.util.ArrayList;

public class Grupo {
	
	private ArrayList<Double[]> elementos;
	private Double[] centroide;
	private Double[] centroideAnterior;
	
	public Grupo(Double[] centroide){
		this.centroide = centroide;
		this.elementos = new ArrayList<Double[]>();
	}

	public ArrayList<Double[]> getElementos() {
		return elementos;
	}

	public void setElementos(ArrayList<Double[]> elementos) {
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
		double indiceDeParada = 0.001f;
		for(int i = 0; i< this.centroide.length;i++){
			double diferenca = Math.abs(centroide[i] - centroideAnterior[i]);
			if(!(diferenca <= indiceDeParada && diferenca >= 0))
				return false;
		}
		return true;
	}
	
	

}
