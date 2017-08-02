package principal;

import java.util.ArrayList;

import utils.Arquivo;

public class Main {

	public static void main(String[] args) {
		ArrayList<Double[]> base = Arquivo.lerArquivo();
		for(int i = 0; i < base.size(); i++){
			for(int j = 0; j< base.get(i).length; j++ ){
				System.out.print(base.get(i)[j] + ",");
			}
			System.out.print("\n");
		}
		/*
		Classificador calculo = new Classificador(Arquivo.lerArquivo());
		Arquivo.escrever(calculo.agrupar(2));
		System.out.println("Done!");
		*/
	}
}
