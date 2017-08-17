package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import algortimos.Grupo;


public abstract class Arquivo {
	/**
	 * Lê o arquivo especificado e converte cada linha de atributos para um array de BigDecimal com 
	 * precisão de 7 dígitos.
	 * @return ArrayList<BigDecimal[]> - coleção de arrays de Big Decimal normalizada onde cada array corresponde ao vetor de
	 * caracteristicas da escola e cada BigDecimal é um atributo.    
	 */
	public static ArrayList<BigDecimal[]> lerArquivo(){
		
		ArrayList<BigDecimal[]> base = new ArrayList<BigDecimal[]>();
		BufferedReader br = null;
		FileReader fr = null;
		
		try{
			fr = new FileReader("src/arquivos/escolas.txt");
			br = new BufferedReader(fr);
			String currentLine;
			BigDecimal contador = new BigDecimal(1);
			while(br.ready()) {
				currentLine = br.readLine();
				
				if(!currentLine.isEmpty()){
					BigDecimal[] index = new BigDecimal[1];
					index[0] = contador;
					
					base.add(Stream.concat
							(
									Arrays.stream(index),
									Arrays.stream(decimal(currentLine.split(",")))
							)
							.toArray(BigDecimal[]::new));
					contador = contador.add(new BigDecimal("1.0"));
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
	public static void escreverInformacoes(String frase) {
		try {
			FileWriter writer = new FileWriter("src/arquivos/escolasSaida.txt", true);
			writer.write("\n" + frase);
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Nomaliza a base de dados lida.
	 * @param base
	 * @return ArrayList<BigDecimal> - Base de dados já normalizada com presisão de no maximo 7 dígitos.
	 */
	public static ArrayList<BigDecimal[]> normalizar(ArrayList<BigDecimal[]> base){
		for(int i = 1; i< base.get(0).length;i++){
			BigDecimal maior = new BigDecimal("0.0");
			BigDecimal menor = new BigDecimal("2000000000");
			//itera toda a coluna j para saber o maior e o menor valor dela
			for(int j = 0;j < base.size();j++){
				if(base.get(j)[i].doubleValue() > maior.doubleValue())
					maior = base.get(j)[i];
				if(base.get(j)[i].doubleValue() < menor.doubleValue())
					menor = base.get(j)[i];
			}
			//itera novamente a coluna J para reatribuir os valores em cada coluna
			for(int j = 0;j < base.size();j++){
				if(maior.subtract(menor,MathContext.DECIMAL32).equals(new BigDecimal("0.0"))){
					base.get(j)[i] = new BigDecimal(0);
				}
				else{		
					BigDecimal a = (base.get(j)[i].subtract(menor,MathContext.DECIMAL32));
					BigDecimal b = (maior.subtract(menor,MathContext.DECIMAL32));
					BigDecimal c = (a)
							.divide(b, MathContext.DECIMAL32);
					
						base.get(j)[i] = c;
				}
			}
			
		}
		return base;
	}
	public static BigDecimal[] decimal(String[] linha){
		BigDecimal[] resultado = new BigDecimal[linha.length];
		for(short i = 0; i < linha.length;i++){
			resultado[i] = new BigDecimal(linha[i]);
		}
		return resultado;
	}

}
