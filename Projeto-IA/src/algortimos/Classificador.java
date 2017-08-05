package algortimos;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Random;

public class Classificador {
	
private ArrayList<BigDecimal[]> elementos;
	
	public Classificador(ArrayList<BigDecimal[]> elementos){
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
	private ArrayList<Grupo> gerarGrupos(int k, ArrayList<BigDecimal[]> elementos){
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
			BigDecimal menorDistancia = new BigDecimal("20000.0");
			int indexMenor = 0;
			for(int j = 0;j <grupos.size();j++){
				BigDecimal distancia = Distancias.manhatam(elementos.get(i),grupos.get(j).getCentroide());
				if(distancia.compareTo(menorDistancia) <= -1){
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
			BigDecimal[] centroide = new BigDecimal[grupos.get(k).getCentroide().length];
			//varre cada i(linha) de atributo ! começa do 1 porque a primeira linha é o código
			for(int i = 1; i< centroide.length;i++){
				BigDecimal soma = BigDecimal.ZERO;
				//varre cada atributo por escola sendo j o índice da escola
				for(int j = 0; j< grupos.get(k).getElementos().size();j++){
					soma = soma.add(grupos.get(k).getElementos().get(j)[i],MathContext.DECIMAL32);
				}
				BigDecimal divisor = BigDecimal.ZERO;
				if(grupos.get(k).getElementos().size() > 0){
					divisor = new BigDecimal(grupos.get(k).getElementos().size());
					centroide[i] = (soma.divide(divisor,MathContext.DECIMAL32));
				}
				else{
					centroide[i] = new BigDecimal("0.0");
				}
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

}
