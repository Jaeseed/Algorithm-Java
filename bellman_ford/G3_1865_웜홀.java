package bellman_ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_1865_웜홀 {
	static int INF = 25000000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int arr[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = INF;
				}
			}
			for (int m = 0; m < M ; m++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken()) - 1;
				int e = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken());
				if (arr[s][e] < d) continue;
				arr[s][e] = d;
				arr[e][s] = d;
			}
			for (int w = 0; w < W; w++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken()) - 1;
				int e = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken());
				if (arr[s][e] < d * -1) continue;
				arr[s][e] = d * -1;
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						arr[i][j] = Math.min(arr[i][k] + arr[k][j], arr[i][j]);
					}
				}
			}
			String answer = "NO";
			for (int n = 0; n < N; n++) {
				if (arr[n][n] < 0) {
					answer = "YES";
				}
			}
			System.out.println(answer);
		}

	}

}
