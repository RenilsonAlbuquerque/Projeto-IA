package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public abstract class Arquivo {
	
	public static ArrayList<Integer[]> lerArquivo(){
		
		ArrayList<Integer[]> base = new ArrayList<Integer[]>();
		BufferedReader br = null;
		FileReader fr = null;
		
		try{
			fr = new FileReader("src/entradas/escolas.txt");
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

}
