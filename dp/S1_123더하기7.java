package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_123더하기7 {
	public static void main(String[] args) throws IOException{
		long[][] dp = new long[1001][1001];
		for (int i = 1; i < 4; i++) {
			dp[i][1] = 1;
		}
		for (int i = 2; i < 1001; i++) {
			for (int j = 1; j < 4; j++) {
				int target = i - j;
				int now = target / 3;
				int end = target / 1 + 1;
				while (now < end) {
					dp[i][now+1] += dp[target][now] % 1000000009;
					now += 1;
				}
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			sb.append(dp[n][m] % 1000000009 + "\n");
		}
		System.out.println(sb);
	}

}
