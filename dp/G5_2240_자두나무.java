package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G5_2240_자두나무 {

	public static void main(String[] args) throws IOException {
		List<Integer> fruits = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int num = 1;
		int cnt = 0;
		for (int t = 0; t < T; t++) {
			int now = Integer.parseInt(br.readLine());
			if (now == num) {
				cnt += 1;
			}
			else {
				fruits.add(cnt);
				cnt = 1;
				num = now;
			}
		}
		fruits.add(cnt);
		int length = fruits.size();
		int[][] dp = new int[W+1][length+1];
		int cnt1 = 0;
		for (int l = 1; l < length+1; l++) {
			if (l % 2 == 1) {
				cnt1 += fruits.get(l-1);
				dp[0][l] = cnt1;
			}
		}
		for (int w = 1; w < W+1; w++) {
			for (int l = 1; l < length+1; l++) {
				dp[w][l] = Math.max(dp[w-1][l], dp[w-1][l-1] + fruits.get(l-1));
			}
		}
		System.out.println(dp[W][length]);
		

	}

}
