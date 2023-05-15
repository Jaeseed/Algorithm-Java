package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_12865_평범한배낭 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] info = new int[N][2];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			info[n][0] = W;
			info[n][1] = V;
		}
		
		int[] dp = new int[100001];
		
		for (int n = 0; n < N; n++) {
			int weight = info[n][0];
			int value = info[n][1];
			
			for (int w = 100000; w >= weight; w--) {
				dp[w] = Math.max(dp[w], dp[w-weight] + value);
			}
		}
		
		System.out.println(dp[K]);

	}

}
