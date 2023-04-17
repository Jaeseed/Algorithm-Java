package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G1_2098_외판원순회 {
	static int[][] map;
	static int N;
	static int[][]dp;
	static int INF = 32000000;
	static int nonCycle = 18000000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				map[n][i] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[N][(1 << N) - 1];
		for (int n = 0; n < N; n++) {
			Arrays.fill(dp[n], INF);
		}
		System.out.println(dfs(0,1));

	}
	
	static int dfs(int now, int visited) {
		if (visited == (1 << N) - 1) {
			if (map[now][0] == 0) {
				return nonCycle;
			}
			return map[now][0];
		}
		
		if (dp[now][visited] < INF) {
			return dp[now][visited];
		}
		
		for (int i = 1; i < N; i++) {
			if ((visited & (1 << i)) != 0 || map[now][i] == 0) continue;
			dp[now][visited] = Math.min(dp[now][visited], dfs(i, visited | 1 << i) + map[now][i]);
		}
		return dp[now][visited];
	}

}
