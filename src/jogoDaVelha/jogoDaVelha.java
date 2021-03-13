package jogoDaVelha;

import java.util.Scanner;

/*  Nome                            | RA
    André Lucas Fabbris de Toledo   | 1902777
    Bruno Alkimim de Negreiros      | 1902646
    Lucas de Alencar Silva          | 1902989
    Mariana Rodrigues Zubi da Silva | 1904204
    Milena Bispo Gomes              | 1904052 */

public class jogoDaVelha {
	public static void main(String[] args) {
		game();
	}

	public static char[][] initialize() {
		char[][] tabuleiro = new char[][]{
				new char[]{' ', ' ', ' '},
				new char[]{' ', ' ', ' '},
				new char[]{' ', ' ', ' '}
		};

		return tabuleiro;
	}

	public static boolean step(char[][] M, int lin, int col, char gamer) {

		boolean posicaoInvalida = lin < 0 || lin > 2 || col < 0 || col > 2;

		if (posicaoInvalida) {
			System.out.println("Posição inválida. Escolha novamente.");
			return false;
		}

		boolean posicaoOcupada = M[lin][col] != ' ';

		if (posicaoOcupada) {
			System.out.println("Posição ocupada. Tente novamente.");
			return false;
		}

		M[lin][col] = gamer;

		return true;
	}

	public static int status(char[][] M) {
		String[] jogadas = new String[]{
				"" + M[0][0] + M[0][1] + M[0][2],
				"" + M[1][0] + M[1][1] + M[1][2],
				"" + M[2][0] + M[2][1] + M[2][2],
				"" + M[0][0] + M[1][0] + M[2][0],
				"" + M[0][1] + M[1][1] + M[2][1],
				"" + M[0][2] + M[1][2] + M[2][2],
				"" + M[0][0] + M[1][1] + M[2][2],
				"" + M[2][0] + M[1][1] + M[0][2]
		};

		for (int i = 0; i < jogadas.length; i++) {
			jogadas[i] = jogadas[i].replaceAll(" ", "");

			if (jogadas[i].equals("OOO")) {
				return 1;
			}

			if (jogadas[i].equals("XXX")) {
				return 2;
			}
		}

		String jogadasConcatenado = String.join("", jogadas);

		if (jogadasConcatenado.length() == 24)
			return 0;

		return -1;
	}

	public static void exibirTabuleiro(char[][] tabuleiro) {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				System.out.printf(" %s ", tabuleiro[i][j]);

				if (j < 2)
					System.out.printf("|");
			}

			System.out.println();
		}
	}

	public static char trocarJogador(char gamer) {
		if (gamer == 'O')
			return 'X';
		else
			return 'O';
	}

	public static void game() {
		char[][] tabuleiro = initialize();
		char gamer = 'O';
		int statusGame = -1;

		Scanner scanner = new Scanner(System.in);

		while (statusGame == -1) {
			boolean posicaoValida = false;

			do {
				System.out.printf("Jogador '%c' escolha a linha: ", gamer);
				int lin = scanner.nextInt();

				System.out.printf("Jogador '%c' escolha a coluna: ", gamer);
				int col = scanner.nextInt();
				posicaoValida = step(tabuleiro, lin, col, gamer);

				if (!posicaoValida)
					System.out.println("A posição inserida não é válida. Por favor, digite novamente.");
			}
			while (!posicaoValida);

			exibirTabuleiro(tabuleiro);

			statusGame = status(tabuleiro);
			gamer = trocarJogador(gamer);
		}

		switch (statusGame) {
			case 0: {
				System.out.println("Ocorreu um empate!");
				break;
			}
			case 1: {
				System.out.println("Jogador 'O' venceu!");
				break;
			}
			case 2: {
				System.out.println("Jogador 'X' venceu!");
				break;
			}
		}
	}
}
