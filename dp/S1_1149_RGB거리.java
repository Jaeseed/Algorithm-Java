package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_1149_RGB거리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][3];
		int[][] dp = new int[N][3];
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				arr[n][i] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = arr[0][0];
		dp[0][1] = arr[0][1];
		dp[0][2] = arr[0][2];
		for (int n = 1; n < N; n++) {
			for (int i = 0; i < 3; i++) {
				int minValue = 1000000;
				int target = 0;
				for (int j = 0; j < 3; j++) {
					if (i == j) continue;
					if (minValue > dp[n-1][j]) {
						minValue = dp[n-1][j];
						target = j;
					}
				}
				dp[n][i] = dp[n-1][target] + arr[n][i];
				
			}
		}
		int answer = 1000000;
		for (int i = 0; i < 3; i++) {
			if (answer > dp[N-1][i]) {
				answer = dp[N-1][i];
			}
		}
		System.out.println(answer);

	}

}
