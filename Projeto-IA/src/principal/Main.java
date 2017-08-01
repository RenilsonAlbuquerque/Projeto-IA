package principal;

import algortimos.Classificador;
import utils.Arquivo;

public class Main {

	public static void main(String[] args) {
		Classificador calculo = new Classificador(Arquivo.lerArquivo());
		Arquivo.escrever(calculo.agrupar(4));
		System.out.println("Done!");
	}
}
