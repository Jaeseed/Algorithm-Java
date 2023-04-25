package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_11659_구간합구하기4 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] dp = new int[N+1];
		st = new StringTokenizer(br.readLine());
		int stackNum = 0;
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
			stackNum += arr[n];
			dp[n+1] = stackNum;
		}
		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int now = dp[end] - dp[start-1];
			sb.append(now + "\n");
		}
		System.out.println(sb);
		
		
	}

}
