package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_15684_사다리게임 {
	
	static int N;
	static int H;
	static boolean[][] arr;
	static int answer = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new boolean[H+2][N-1];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()) - 1;
			arr[r][c] = true;
		}
		for (int i = 0; i <= 3; i++) {
			chooseHorizon(i, 0, N-1);
			if (answer != -1) break;
		}
		System.out.println(answer);

	}
	
	static void chooseHorizon(int max, int cnt, int idx) {
		if (max == cnt) {
			if (findEndPoint()) {
				answer = max;
			}
			return;
		}
		if (answer != -1) {
			return;
		}
		for (int i = idx; i < (H+1) * (N-1); i++) {
			int r = i / (N-1);
			int c = i % (N-1);
			if (arr[r][c]) continue;
			if (c > 0 && arr[r][c - 1]) continue;
			if (c < N - 2 && arr[r][c+1]) continue;
			arr[r][c] = true;
			chooseHorizon(max, cnt + 1, idx + 1);
			arr[r][c] = false;
		}
	}
	
	static boolean findEndPoint() {
		for (int i = 0; i < N; i++) {
			int r = 1;
			int c = i;
			while (r <= H + 1) {
				if (c > 0 && arr[r][c-1]) {
					c -= 1;
				}
				else if (c < N-1 && arr[r][c]) {
					c += 1;
				}
				r += 1;
			}
			if (c != i) return false;
		}
		return true;
	}

}
