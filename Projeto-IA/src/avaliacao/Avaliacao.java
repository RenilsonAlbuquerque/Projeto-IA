package avaliacao;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import algortimos.Distancias;
import algortimos.Grupo;

public class Avaliacao {

	
	public static BigDecimal dunn(ArrayList<Grupo> grupos){
		BigDecimal menorDunn = new BigDecimal(20000);
		for(short i = 0;i<grupos.size();i++){
			BigDecimal dunnAtual = new BigDecimal(0);
			for(short j = 0; j<grupos.size();j++){
				if(grupos.get(i).equals(grupos.get(j))){
					continue;
				}
				else{
					int elementos = (grupos.get(i).getElementos().size() > grupos.get(j).getElementos().size()) 
							? grupos.get(i).getElementos().size() :  grupos.get(j).getElementos().size(); 
					
					if(elementos != 0){
						BigDecimal a = Distancias.euclidiana(grupos.get(i).getCentroide(),grupos.get(j).getCentroide());
						dunnAtual = a.divide(BigDecimal.valueOf(elementos), MathContext.DECIMAL32);
					}else{
						dunnAtual = new BigDecimal("0.0");
					}
					
				}
			}
			menorDunn = (dunnAtual.doubleValue() < menorDunn.doubleValue()) ? dunnAtual : menorDunn;
		}
		return menorDunn;
	}
	
}
