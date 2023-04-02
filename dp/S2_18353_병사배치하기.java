package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_18353_병사배치하기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		dp[0] = 1;
		int answer = 1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i < N; i++) {
			int idx = i-1;
			int max_value = 1;
			while (idx >= 0) {
				if (arr[idx] > arr[i] && dp[idx] > max_value) {
					max_value = dp[idx];
				}
				idx -= 1;
			}
			dp[i] = max_value + 1;
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(N - answer);
	}

}
