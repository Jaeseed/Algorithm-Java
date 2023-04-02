package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S1_9465_스티커 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int t;
		int n;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			int arr[][] = new int[2][n];

			for (int j = 0; j < 2; j++) {
				arr[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}

			// 위에서부터 순서대로 -> 해당 열의 위의 점수, 아래 점수, 해당 열 사용하지 않았을 때 최댓값
			int t_score = arr[0][0];
			int b_score = arr[1][0];
			int n_score = 0;
			// 위 아래 점수 보관용 변수
			int t_tmp;
			int b_tmp;
			for (int k = 1; k < n; k++) {
				t_tmp = t_score;
				b_tmp = b_score;
				t_score = Math.max(n_score + arr[0][k], b_score + arr[0][k]);
				b_score = Math.max(n_score + arr[1][k], t_tmp + arr[1][k]);
				n_score = Math.max(t_tmp, b_tmp);
			}

			System.out.println(Math.max(t_score, b_score));

		}

	}

}
