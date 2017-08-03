package principal;

import java.util.ArrayList;
import java.util.Date;

import algortimos.Classificador;
import algortimos.Grupo;
import avaliacao.Avaliacao;
import utils.Arquivo;

public class Main {

	public static void main(String[] args) {
		/*ArrayList<Double[]> base = Arquivo.lerArquivo();
		for(int i = 0; i < base.size(); i++){
			for(int j = 0; j< base.get(i).length; j++ ){
			}
			System.out.print("\n");
		}*/
		Date hora = new Date();
		long a = hora.getTime();
		Classificador calculo = new Classificador(Arquivo.lerArquivo());
		ArrayList<Grupo> resultado = calculo.agrupar(10);
		Arquivo.escrever(resultado);
		Arquivo.escreverInformacoes("Número de Dunn: " + Avaliacao.dunn(resultado));
		Arquivo.escreverInformacoes("Tempo de compilação:" + (hora.getTime() - a));
		System.out.println("Done!");
		
	}
}
