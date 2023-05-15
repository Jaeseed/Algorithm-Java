package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_1080_행렬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 바꿀 행열
		boolean[][] varMatrix = new boolean[N][M];
		// 고정 행열
		boolean[][] staticMatrix = new boolean[N][M];
		
		makeMatrix(varMatrix, br, N, M);
		makeMatrix(staticMatrix, br, N, M);
		
		
		int answer = 0;
		loop: for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (varMatrix[n][m] != staticMatrix[n][m]) {
					if (n + 2 >= N || m + 2 >= M) {
						answer = -1;
						break loop;
					}
					changeMatrix(n,m,varMatrix);
					answer += 1;
				}
			}
		}
		
		System.out.println(answer);

	}
	
	static void makeMatrix(boolean[][] matrix, BufferedReader br, int N, int M) throws IOException{
		for (int n = 0; n < N; n++) {
			String thisRow = br.readLine();
			for (int m = 0; m < M; m++) {
				if (thisRow.charAt(m) == '0') {
					matrix[n][m] = false;
				}
				else {
					matrix[n][m] = true;
				}
			}
		}
	}
	
	static void changeMatrix(int sr, int sc, boolean[][] matrix) {
		for (int r = sr; r < sr + 3; r++) {
			for (int c = sc; c < sc + 3; c++) {
				matrix[r][c] = !matrix[r][c];
			}
		}
	}

}
