package algortimos;

public abstract class Distancias {
	
	public static double euclidiana(Double[] e1,Double[] e2){
		int resultado = 0;
		for(int i = 1; i< e1.length;i++){
			resultado +=  Math.pow((e1[i] - e2[i]),2);
		}
		return Math.sqrt(resultado);
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
}
