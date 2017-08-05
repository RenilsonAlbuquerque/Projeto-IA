package principal;

import java.util.ArrayList;
import java.util.Calendar;

import algortimos.Classificador;
import algortimos.Grupo;
import avaliacao.Avaliacao;
import utils.Arquivo;

public class Main {

	public static void main(String[] args) {
		/*ArrayList<BigDecimal[]> base = Arquivo.lerArquivo();
		for(int i = 0; i < base.size(); i++){
			for(int j = 0; j< base.get(i).length; j++ ){
				System.out.print(base.get(i)[j] + ",");
			}
			System.out.print("\n");
		}*/
		Calendar horaInicio = Calendar.getInstance();
		
		Classificador calculo = new Classificador(Arquivo.lerArquivo());
		ArrayList<Grupo> resultado = calculo.agrupar(10);
		Arquivo.escrever(resultado);
		Arquivo.escreverInformacoes("Número de Dunn: " + Avaliacao.dunn(resultado));
		Arquivo.escreverInformacoes("Tempo de execução: " + 
		Math.abs(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - horaInicio.get(Calendar.HOUR_OF_DAY)) + ":" +
		Math.abs(Calendar.getInstance().get(Calendar.MINUTE) - horaInicio.get(Calendar.MINUTE)) + ":" +
		Math.abs(Calendar.getInstance().get(Calendar.SECOND) - horaInicio.get(Calendar.SECOND))
		);
		System.out.println("terminou!");
		
	}
}
