package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_10971_외판원순회 {
	static int N;
	static int[][] arr;
	static int answer = 100000000;
	static boolean[] visited;
	static int start;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		arr = new int[N][N];
		visited = new boolean[N];
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				arr[n][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int n = 0; n < N; n++) {
			start = n;
			visited[n] = true;
			tracking(start,0,1);
			visited[n] = false;
		}
		System.out.println(answer);

	}
	static void tracking(int now, int value, int cnt) {
		if (cnt == N) {
			for (int n = 0; n < N; n++) {
				if (arr[now][start] > 0) {
					answer = Math.min(answer, value + arr[now][start]);
					return;
				}
			}
			return;
		}
		for (int n = 0; n < N; n++) {
			if (now == n || arr[now][n] == 0 || arr[now][start] + value >= answer || visited[n]) {
				continue;
			}
			visited[n] = true;
			tracking(n, value + arr[now][n], cnt + 1);
			visited[n] = false;
		}
	}

}
