package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_15954_인형들 {
	public static void main(String[] args) throws IOException{
		double answer = Double.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		for (int u = 0; u <= N-K; u++) {
			int num = K + u;
			for (int i = 0; i <= N - num; i++) {
				int sum = 0;
				double m = 0;
				for (int k = 0; k < num; k++) {
					sum += arr[i+k];
				}
				m = sum / (double) num;
				double variance = 0.0;
				for (int k = 0; k < num; k++) {
					variance += Math.pow((m - arr[i + k]), 2);
				}
				double cand = Math.sqrt(variance / K);
				if (answer > cand) {
					answer = cand;
				}
			}
		}
		System.out.printf("%.6f", answer);
		
		
	}

}
