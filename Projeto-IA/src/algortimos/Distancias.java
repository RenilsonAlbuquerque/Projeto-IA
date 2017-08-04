package algortimos;

import java.math.BigDecimal;
import java.math.MathContext;

public abstract class Distancias {
	
	public static BigDecimal euclidiana(BigDecimal[] e1,BigDecimal[] e2){
		BigDecimal resultado = new BigDecimal("0.0");
		for(int i = 1; i< e1.length;i++){
			
			resultado = resultado.add(
							e1[i].subtract(e2[i],MathContext.DECIMAL32).pow(2)
						);
		}	
		return square(resultado);
	}
	public static double manhatam(Double[] e1,Double[] e2){
		int resultado = 0;
		for(int i = 1; i< e1.length;i++){
			resultado +=  Math.abs(e1[i] - e2[i]);
		}
		return resultado;
	}
	public static double minkovisk(Double[] e1,Double[] e2){
		int resultado = 0;
		for(int i = 1; i< e1.length;i++){
			resultado +=  Math.pow((e1[i] - e2[i]),3);
		}
		return Math.pow(resultado,1/3);
	}

	public static BigDecimal sqrt(BigDecimal x, MathContext mc) {
		BigDecimal TWO = BigDecimal.valueOf(2L);
		BigDecimal g = x.divide(TWO, mc);
		boolean done = false;
		final int maxIterations = mc.getPrecision() + 1;		
		for (int i = 0; !done && i < maxIterations; i++) {
			// r = (x/g + g) / 2
			BigDecimal r = x.divide(g, mc);
			r = r.add(g);
			r = r.divide(TWO, mc);
			done = r.equals(g);
			g = r;
		}
		return g;
	}
	public static BigDecimal square(BigDecimal x) {
		return BigDecimal.valueOf(StrictMath.sqrt(x.doubleValue()));
	}
}
