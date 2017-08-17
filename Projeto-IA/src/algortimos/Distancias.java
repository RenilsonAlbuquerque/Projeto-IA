package algortimos;

import java.math.BigDecimal;
import java.math.MathContext;

public abstract class Distancias {
	
	/**
	 * Calcula a distância euclidiana entre dois vetores
	 * @param e1 - BigDecimal[] 
	 * @param e2 - BigDecimal[] 
	 * @return a distância euclidiana entre os dois vetores com uma precisão de 7 dígitos.
	 */
	public static BigDecimal euclidiana(BigDecimal[] e1,BigDecimal[] e2){
		BigDecimal resultado = new BigDecimal("0.0");
		for(int i = 1; i< e1.length;i++){
			
			resultado = resultado.add(
							e1[i].subtract(e2[i],MathContext.DECIMAL32).pow(2)
						);
		}	
		return square(resultado,2);
	}
	/**
	 * Calcula a distância usando a formula Manhatam entre dois vetores
	 * @param e1 - BigDecimal[] 
	 * @param e2 - BigDecimal[] 
	 * @return a distância  entre os dois vetores com uma precisão de 7 dígitos.
	 */
	public static BigDecimal manhatam(BigDecimal[] e1,BigDecimal[] e2){
		BigDecimal resultado = new BigDecimal("0.0");
		for(int i = 1; i< e1.length;i++){
			resultado  = resultado.add(
					e1[i].subtract(e2[i],MathContext.DECIMAL32).abs()
				);
		}
		return resultado;
	}
	/**
	 * Calcula a distância euclidiana ponderada entre dois vetores
	 * @param e1 - BigDecimal[] 
	 * @param e2 - BigDecimal[] 
	 * @return a distância euclidiana ponderada entre os dois vetores com uma precisão de 7 dígitos.
	 */
	public static BigDecimal euclidianaPonderada(BigDecimal[] e1,BigDecimal[] e2){
		BigDecimal w = euclidiana(e1,e2);
		BigDecimal resultado = new BigDecimal("0.0");
		if(w.equals(new BigDecimal("0.0"))){
			resultado = new BigDecimal("0.0");
		}else{
			w = new BigDecimal("1.0").divide(
					w,MathContext.DECIMAL32);
			for(int i = 1; i< e1.length;i++){
				resultado = resultado.add(
							w.multiply(e1[i].subtract(e2[i],MathContext.DECIMAL32).pow(2,MathContext.DECIMAL32))
						);
			}
		}
		return square(resultado,2);
	}
	
	/**
	 * Calcula a raíz n de um BigDecimal x
	 * @param x - base 
	 * @param expo - expoente da raíz
	 * @return BigDecimal
	 */
	public static BigDecimal square(BigDecimal x,int n) {
		BigDecimal e =  BigDecimal.ONE.divide(new BigDecimal(n),MathContext.DECIMAL32);
		return BigDecimal.valueOf(StrictMath.pow(x.doubleValue(), e.doubleValue()));
	}
}
