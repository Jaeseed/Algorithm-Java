package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_15817_배수공사 {
	static int[] dp;
	static int X;
	static int[] tmp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		int[][] pipes = new int[N][2];
		dp = new int[X+1];
		
		for (int n = 0; n < N; n++) {
			st  = new StringTokenizer(br.readLine());
			pipes[n][0] = Integer.parseInt(st.nextToken());
			pipes[n][1] = Integer.parseInt(st.nextToken());
		}
		
		plusFromZero(pipes[0][0], pipes[0][1]);
		for (int i = 1; i < N; i++) {
			tmp = new int[X+1];
			for (int j = 1; j < X; j++) {
				if (dp[j] != 0) {
					plusOthers(pipes[i][0], pipes[i][1], j);
				}
			}
			for (int k = 1; k <= X; k++) {
				dp[k] += tmp[k];
			}
			plusFromZero(pipes[i][0], pipes[i][1]);
		}
		System.out.println(dp[X]);
		
	}
	
	static void plusFromZero (int num, int cnt) {
		for (int i = 1; i <= cnt; i++) {
			int value = num * i;
			if (value > X) return;
			dp[value] += 1;
		}
	}
	
	static void plusOthers (int num, int cnt, int target) {
		for (int i = 1; i <= cnt; i++) {
			int value = num * i;
			if (value + target > X) break;
			tmp[value + target] += dp[target];
		}
	}

}
