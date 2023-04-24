package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_11660_구간합구하기5 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		int[][] dp = new int[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int nn = 0; nn < N; nn++) {
				map[n][nn] = Integer.parseInt(st.nextToken());
			}
		}
		for (int n = 0; n < N; n++) {
			int tmp = 0;
			for (int nn = 0; nn < N; nn++) {
				tmp += map[n][nn];
				dp[n][nn] = tmp;
			}
		}
		for (int nn = 0; nn < N; nn++) {
			for (int n = 1; n < N; n++) {
				dp[n][nn] += dp[n-1][nn];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;
			int now = dp[x2][y2];
			if (y1 > 0) {
				now -= dp[x2][y1-1];
			}
			if (x1 > 0) {
				now -= dp[x1-1][y2];
			}
			if (x1 > 0 && y1 > 0) {
				now += dp[x1-1][y1-1];
			}
			sb.append(now + "\n");
		}
		System.out.println(sb);
	}

}
