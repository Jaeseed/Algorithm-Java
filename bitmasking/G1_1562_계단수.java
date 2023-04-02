package bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G1_1562_계단수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long dp[][][] = new long[N][10][1 << 10];
		int visit;
		final int factor = 1000000000;
		for (int k = 1; k < 10; k++) {
			dp[0][k][1 << k] = 1;
		}

		for (int n = 1; n < N; n++) {
			for (int k = 0; k < 10; k++) {
				for (int b = 1; b < 1 << 10; b++) {
					visit = b | 1 << k;
					if (k == 0) {
						dp[n][k][visit] += dp[n - 1][k + 1][b] % factor;
					} else if (k == 9) {
						dp[n][k][visit] += dp[n - 1][k - 1][b] % factor;
					} else {
						dp[n][k][visit] += dp[n - 1][k - 1][b] + dp[n - 1][k + 1][b] % factor;
					}
				}
			}
		}
		int answer = 0;
		for (int k = 0; k < 10; k++) {
			answer += dp[N - 1][k][(1 << 10) - 1] % factor;
			answer %= factor;
		}
		System.out.println(answer);

	}

}
