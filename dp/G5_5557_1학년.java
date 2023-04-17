package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_5557_1학년 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N-1];
		for (int n = 0; n < N - 1; n++) {
			int now = Integer.parseInt(st.nextToken());
			arr[n] = now;
		}
		int result = Integer.parseInt(st.nextToken());
		long[][] dp = new long[N-1][21];
		dp[0][arr[0]] = 1;
		for (int n = 1; n < N - 1; n++) {
			for (int t = 0; t <= 20; t++) {
				if (t - arr[n] >= 0) {
					dp[n][t - arr[n]] += dp[n-1][t];
				}
				if (t + arr[n] <= 20) {
					dp[n][t + arr[n]] += dp[n-1][t];
				}
			}
		}
		System.out.println(dp[N-2][result]);

	}

}
