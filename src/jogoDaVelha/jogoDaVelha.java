package jogoDaVelha;

public class jogoDaVelha {

	public static void main(String[] args) {
		
		
	}
	public static char[][] initialize(){
		
		char tabuleiro[][] = {{'-','-','-'},{'-','-','-'},{'-','-','-'}};
		
		return tabuleiro;

	}
	public static boolean step(char M[][], int lin, int col, char gamer) {
		
		if(M[lin][col] == '-') {
			
			M[lin][col] = gamer;
			return true;
		}
		else {
			
			return false;
		}
		
	}
}
