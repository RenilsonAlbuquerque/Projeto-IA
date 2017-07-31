package algortimos;

import java.util.ArrayList;
import java.util.Random;

public class Distancias {
	
	private ArrayList<Integer[]> elementos;
	
	public Distancias(ArrayList<Integer[]> elementos){
		this.elementos = elementos;
	}
	
	public ArrayList<Grupo> agrupar(int k){
		ArrayList<Grupo> grupos = this.gerarGrupos(k, this.elementos);
		do{
			grupos = this.reagrupar(grupos);
			grupos = this.media(grupos);
		}while(!this.convergencia(grupos));
		return grupos;
	}
	private ArrayList<Grupo> gerarGrupos(int k, ArrayList<Integer[]> elementos){
		Random rand = new Random();
		ArrayList<Grupo> resultado = new ArrayList<Grupo>();
		for(int i = 0; i < k;i++){	
			resultado.add(new Grupo(
					 this.convert(elementos.get(rand.nextInt(elementos.size())))
					));
		}
		return resultado;
	}
	private ArrayList<Grupo> reagrupar(ArrayList<Grupo> grupos){
		for(Grupo g: grupos)
			g.getElementos().clear();
		for(int i = 0; i< elementos.size();i++){		
			double menorDistancia = 2000000;
			int indexMenor = 0;
			for(int j = 0;j <grupos.size();j++){
				double distancia = euclidiana(elementos.get(i),grupos.get(j).getCentroide());
				if(distancia < menorDistancia){
					menorDistancia = distancia;
					indexMenor = j;
				}
			}
			grupos.get(indexMenor).getElementos().add(elementos.get(i));
		}
		return grupos;
	}
	private double euclidiana(Integer[] e1,Double[] e2){
		int resultado = 0;
		for(int i = 0; i< e1.length;i++){
			resultado +=  Math.pow((e1[i] - e2[i]),2);
		}
		return Math.sqrt(resultado);
	}
	private ArrayList<Grupo> media(ArrayList<Grupo> grupos){
		for(int k = 0; k< grupos.size(); k++ ){
			Double[] centroide = new Double[grupos.get(k).getCentroide().length];
			for(int i = 0; i< centroide.length;i++){
				double soma = 0;
				for(int j = 0; j< grupos.get(k).getElementos().size();j++){
					soma += grupos.get(k).getElementos().get(j)[i];
				}
				centroide[i] = (soma/grupos.get(k).getElementos().size());
			}
			grupos.get(k).setCentroide(centroide);
		}
		return grupos;
	}
	private boolean convergencia(ArrayList<Grupo> grupos){
		for(Grupo g: grupos){
			if(!g.convergenciaParcial())
				return false;
		}
		return true;
	}
	private Double[] convert(Integer[] inteiros){
		Double[] resultado = new Double[inteiros.length];
		for(int i = 0; i< inteiros.length;i++){
			resultado[i] = Double.valueOf(inteiros[i]); 
		}
		return resultado;
	}

}
