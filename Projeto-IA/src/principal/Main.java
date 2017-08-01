package principal;

import algortimos.Distancias;
import utils.Arquivo;

public class Main {

	public static void main(String[] args) {
		Distancias calculo = new Distancias(Arquivo.lerArquivo());
		Arquivo.escrever(calculo.agrupar(4));
		System.out.println("Done!");
	}
}
