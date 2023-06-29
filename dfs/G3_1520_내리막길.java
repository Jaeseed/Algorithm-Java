package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_1520_내리막길 {
	static int N;
	static int M;
	static int[][] map;
	static int[][] visited;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				visited[n][m] = -1;
			}
		}
		
		dfs(0,0);
		System.out.println(visited[0][0]);

	}
	
	static int dfs(int r, int c) {
		if (r == N - 1 && c == M - 1) {
			return 1;
		}
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= N || nr < 0 || nc >= M || nc < 0 || map[r][c] <= map[nr][nc]) continue;
			if (visited[nr][nc] > 0) {
				cnt += visited[nr][nc];
				continue;
			}
			if (visited[nr][nc] == 0) {
				continue;
			}
			int ret = dfs(nr,nc);
			cnt += ret;
		}
		visited[r][c] = cnt;
		return cnt;
	}

}
