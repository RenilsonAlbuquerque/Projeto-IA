package algortimos;

import java.util.ArrayList;
import java.util.Random;

public class Classificador {
	
private ArrayList<Double[]> elementos;
	
	public Classificador(ArrayList<Double[]> elementos){
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
	private ArrayList<Grupo> gerarGrupos(int k, ArrayList<Double[]> elementos){
		Random rand = new Random();
		ArrayList<Integer> escolhidos = new ArrayList<Integer>(); 
		ArrayList<Grupo> resultado = new ArrayList<Grupo>();
		
		for(int i = 0; i < k;i++){	
			int aleatorio = 0;
			do{
				aleatorio = rand.nextInt(elementos.size());
			}while(escolhidos.contains(aleatorio));
			
			resultado.add(new Grupo(elementos.get(aleatorio)));
			escolhidos.add(aleatorio);
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
				double distancia = Distancias.euclidiana(elementos.get(i),grupos.get(j).getCentroide());
				if(distancia < menorDistancia){
					menorDistancia = distancia;
					indexMenor = j;
				}
			}
			grupos.get(indexMenor).getElementos().add(elementos.get(i));
		}
		return grupos;
	}
	private ArrayList<Grupo> media(ArrayList<Grupo> grupos){
		//varre cada grupo
		for(int k = 0; k< grupos.size(); k++ ){
			Double[] centroide = new Double[grupos.get(k).getCentroide().length];
			//varre cada i(linha) de atributo 
			for(int i = 0; i< centroide.length;i++){
				double soma = 0;
				//varre cada atributo por escola sendo j o índice da escola
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
	/*
	public static Double[] convert(Integer[] inteiros){
		Double[] resultado = new Double[inteiros.length];
		for(int i = 0; i< inteiros.length;i++){
			resultado[i] = Double.valueOf(inteiros[i]); 
		}
		return resultado;
	}
	*/
}
