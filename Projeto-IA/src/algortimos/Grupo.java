package algortimos;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class Grupo {
	
	private ArrayList<BigDecimal[]> elementos;
	private BigDecimal[] centroide;
	private BigDecimal[] centroideAnterior;
	
	public Grupo(BigDecimal[] centroide){
		this.centroide = centroide;
		this.elementos = new ArrayList<BigDecimal[]>();
	}

	public ArrayList<BigDecimal[]> getElementos() {
		return elementos;
	}

	public void setElementos(ArrayList<BigDecimal[]> elementos) {
		this.elementos = elementos;
	}

	public BigDecimal[] getCentroide() {
		return centroide;
	}

	public void setCentroide(BigDecimal[] centroide) {
		this.centroideAnterior = this.centroide;
		this.centroide = centroide;
	}
	public boolean convergenciaParcial(){
		BigDecimal indiceDeParada = new BigDecimal("0.001");
		BigDecimal zero = new BigDecimal("0.0");
		for(int i = 1; i< this.centroide.length;i++){
			
			BigDecimal diferenca = centroide[i].subtract(centroideAnterior[i],MathContext.DECIMAL32).abs();
			if(!(diferenca.doubleValue() <= indiceDeParada.doubleValue()     &&  diferenca.doubleValue() >= 0.0))
				return false;
		}
		return true;
	}
	
	

}
