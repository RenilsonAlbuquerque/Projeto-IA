package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import algortimos.Grupo;


public abstract class Arquivo {
	
	public static ArrayList<Integer[]> lerArquivo(){
		
		ArrayList<Integer[]> base = new ArrayList<Integer[]>();
		BufferedReader br = null;
		FileReader fr = null;
		
		try{
			fr = new FileReader("src/arquivos/escolas.txt");
			br = new BufferedReader(fr);
			String currentLine;

			while(br.ready()) {
				currentLine = br.readLine();
				if(!currentLine.isEmpty()){
					base.add(Arrays.stream(currentLine.split(",")).mapToInt(Integer::parseInt).boxed().toArray( Integer[]::new ));
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
		return base;
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

}
