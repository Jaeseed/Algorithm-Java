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
		for (int length = K; length <= N; length++) {
			for (int i = 0; i <= N - length; i++) {
				double sum = 0.0;
				double m = 0.0;
				for (int k = 0; k < length; k++) {
					sum += arr[i+k];
				}
				m = sum / (double) length;
				double variance = 0.0;
				for (int k = 0; k < length; k++) {
					variance += Math.pow(arr[i + k] - m, 2);
				}
				double cand = Math.sqrt(variance / K);
				if (answer > cand) {
					answer = cand;
				}
			}
		}
		System.out.printf("%.7f", answer);
		
		
	}

}
