package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_1303_전쟁전투 {
	static boolean visited[][];
	static char map[][];
	static int cnt;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int myPower = 0;
		int enemyPower = 0;
		map = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					continue;
				cnt = 1;
				visited[i][j] = true;
				dfs(i, j, map[i][j]);
				if (map[i][j] == 'W') {
					myPower += Math.pow(cnt, 2);
				} else {
					enemyPower += Math.pow(cnt, 2);
				}
			}
		}

		System.out.println(myPower + " " + enemyPower);
	}

	static void dfs(int r, int c, char country) {
		for (int i = 0; i < 4; i++) {
			int nr = dr[i] + r;
			int nc = dc[i] + c;
			if (nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc] || map[nr][nc] != country)
				continue;
			cnt += 1;
			visited[nr][nc] = true;
			dfs(nr, nc, country);
		}
	}

}
