package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class G4_9252_LCS2 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tester = br.readLine();
		String target = br.readLine();
		int N = tester.length();
		int M = target.length();
		int[][] dp = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (dp[i-1][j] > dp[i][j-1]) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					if (tester.charAt(i-1) == target.charAt(j-1)) {
						dp[i][j] = dp[i-1][j-1] + 1;
					}
					else {
						dp[i][j] = dp[i][j-1];
					}
				}
			}
		}
		System.out.println(dp[N][M]);
		if (dp[N][M] > 0) {
			StringBuilder sb = new StringBuilder();
			int cnt = dp[N][M];
			int r = N;
			int c = M;
			while (r > 0 && cnt > 0) {
				for (int j = c; j > 0; j--) {
					if (dp[r][j] != cnt) break;
					if (tester.charAt(r-1) == target.charAt(j-1)) {
						sb.append(tester.charAt(r-1));
						cnt -= 1;
						c = j - 1;
						break;
					}
				}
				r -= 1;
			}
			System.out.println(sb.reverse());
		}

	}

}