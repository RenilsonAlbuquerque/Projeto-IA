package algortimos;

public abstract class Distancias {
	
	public static double euclidiana(Integer[] e1,Double[] e2){
		int resultado = 0;
		for(int i = 0; i< e1.length;i++){
			resultado +=  Math.pow((e1[i] - e2[i]),2);
		}
		return Math.sqrt(resultado);
	}
	public static double manhatam(Integer[] e1,Double[] e2){
		int resultado = 0;
		for(int i = 0; i< e1.length;i++){
			resultado +=  Math.abs(e1[i]) - Math.abs(e2[i]);
		}
		return resultado;
	}
	public static double minkovisk(Integer[] e1,Double[] e2){
		int resultado = 0;
		for(int i = 0; i< e1.length;i++){
			resultado +=  Math.pow((e1[i] - e2[i]),3);
		}
		return Math.pow(resultado,1/3);
	}
}
