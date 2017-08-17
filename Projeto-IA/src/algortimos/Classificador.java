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
	
	/**
	 * Executa o loop principal da execu��o do algor�tmo, chama os subprogramas para reagrupar os grupos e 
	 * redefinir os centr�ides at� os mesmos convergirem.
	 * @param k - Quantidade de grupos
	 * @return ArrayList<Grupos> - todos os elementos agrupados em K grupos.
	 */
	public ArrayList<Grupo> agrupar(int k){
		ArrayList<Grupo> grupos = this.gerarGrupos(k, this.elementos);
		do{
			grupos = this.reagrupar(grupos);
			grupos = this.media(grupos);
			
		}while(!this.convergencia(grupos));
		return grupos;
	}
	/**
	 * Gera aleatoriamente K n�meros n�o repetidos cujos quais ser�o os �ndices dos K vetores(elementos) a 
	 * serem escolhidos como centr�ides iniciais dos K Grupos gerados. 
	 * @param k - quantidade de grupos a serem gerados.
	 * @param elementos - elementos dos quais ser�o escolhidos os centr�ides dos grupos.
	 * @return ArrayList<Grupo>
	 */
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
	/**
	 * Calcula a dist�ncia de cada elemento para cada centr�ide e realoca cada um para o grupo 
	 * cujo centr�ide est� mais pr�ximo. 
	 * @param grupos
	 * @return ArrayList<Grupo> - grupos com os elementos redefinidos com elementos mais pr�ximos dos
	 * centr�ides.
	 */
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
	/**
	 * Gera novos centr�ides para os grupos calculando o vetor da m�dia dos elementos de cada.
	 * @param grupos 
	 * @return ArrayList<Grupo> - grupos com os novos centr�ides definidos.
	 */
	private ArrayList<Grupo> media(ArrayList<Grupo> grupos){
		//varre cada grupo
		for(int k = 0; k< grupos.size(); k++ ){
			BigDecimal[] centroide = new BigDecimal[grupos.get(k).getCentroide().length];
			//varre cada i(linha) de atributo ! come�a do 1 porque a primeira linha � o c�digo
			for(int i = 1; i< centroide.length;i++){
				BigDecimal soma = BigDecimal.ZERO;
				//varre cada atributo por escola sendo j o �ndice da escola
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
	/**
	 * Verifica se todos os centr�ides de todos os grupos convergiram.
	 * @param grupos
	 * @return verdadeiro se os grupos convergiram, falso caso contr�rio.
	 */
	private boolean convergencia(ArrayList<Grupo> grupos){
		for(Grupo g: grupos){
			if(!g.convergenciaParcial())
				return false;
		}
		return true;
	}

}
