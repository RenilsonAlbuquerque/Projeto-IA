package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import algortimos.Grupo;


public abstract class Arquivo {
	
	public static ArrayList<Double[]> lerArquivo(){
		
		ArrayList<Double[]> base = new ArrayList<Double[]>();
		BufferedReader br = null;
		FileReader fr = null;
		
		try{
			fr = new FileReader("src/arquivos/escolas.txt");
			br = new BufferedReader(fr);
			String currentLine;

			while(br.ready()) {
				currentLine = br.readLine();
				if(!currentLine.isEmpty()){
					base.add(Arrays.stream(currentLine.split(",")).map(Double::parseDouble).toArray( Double[]::new ));
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return normalizar(base);
	}
	public static void escrever(ArrayList<Grupo> grupos) {
		try {
		
			FileWriter writer = new FileWriter("src/arquivos/escolasSaida.txt", false);
			for(int k = 0; k < grupos.size(); k++) {
				for (int i = 0; i < grupos.get(k).getElementos().size(); i++) {
					writer.write("Grupo" + (k+1) + " ");
					for(int j = 0; j < grupos.get(k).getElementos().get(i).length;j++){
						writer.write( String.valueOf(grupos.get(k).getElementos().get(i)[j]) + ",");
					}
					writer.write("\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<Double[]> normalizar(ArrayList<Double[]> base){
		for(int i = 0; i< base.get(0).length;i++){
			double maior = 0;
			double menor = 2000000000;
			//itera toda a coluna j para saber o maior e o menor valor dela
			for(int j = 0;j < base.size();j++){
				if(base.get(j)[i] > maior)
					maior = base.get(j)[i];
				if(base.get(j)[i] < menor)
					menor = base.get(j)[i];
			}
			//itera novamente a linha J para reatribuir os valores em cada coluna
			for(int j = 0;j < base.size();j++){
				if(maior - menor == 0){
					base.get(j)[i] = 0.0;
				}
				else{
					base.get(j)[i] = ((base.get(j)[i] - menor)/(maior - menor));
				}
				
			}
		}
		return base;
	}
	

}
