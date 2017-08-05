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
		return square(resultado,2);
	}
	public static BigDecimal manhatam(BigDecimal[] e1,BigDecimal[] e2){
		BigDecimal resultado = new BigDecimal("0.0");
		for(int i = 1; i< e1.length;i++){
			resultado  = resultado.add(
					e1[i].subtract(e2[i],MathContext.DECIMAL32).abs()
				);
		}
		return resultado;
	}
	public static BigDecimal euclidianaPonderada(BigDecimal[] e1,BigDecimal[] e2){
		BigDecimal w = euclidiana(e1,e2);
		BigDecimal resultado = new BigDecimal("0.0");
		if(w.equals(new BigDecimal("0.0"))){
			resultado = new BigDecimal("0.0");
		}else{
			//System.out.println("Before: " +w);
			w = new BigDecimal("1.0").divide(
					w,MathContext.DECIMAL32);
			//System.out.println("After: " +w);
			for(int i = 1; i< e1.length;i++){
				resultado = resultado.add(
							w.multiply(e1[i].subtract(e2[i],MathContext.DECIMAL32).pow(2,MathContext.DECIMAL32))
						);
			}
		}
		return square(resultado,2);
	}

	public static BigDecimal sqrt(BigDecimal x,int expo, MathContext mc) {
		BigDecimal TWO = BigDecimal.valueOf(expo);
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
	public static BigDecimal square(BigDecimal x,int expo) {
		BigDecimal e =  BigDecimal.ONE.divide(new BigDecimal(expo),MathContext.DECIMAL32);
		
		return BigDecimal.valueOf(StrictMath.pow(x.doubleValue(), e.doubleValue()));
	}
}
