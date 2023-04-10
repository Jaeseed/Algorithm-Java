package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_2206_벽부수고이동하기 {
	static class Node {
		int r;
		int c;
		int cnt;
		boolean crush;
		Node (int r, int c, int cnt, boolean crush) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.crush = crush;
		}
	}
	static boolean[][][] visited;
	static int N;
	static int M;
	static char[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M][2];
		map = new char[N][M];
		for (int n = 0; n < N; n++) {
			String tmp = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = tmp.charAt(m);
			}
		}
		int answer = bfs();
		System.out.println(answer);
	}
	static int bfs() {
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		Queue<Node> qu = new LinkedList<>();
		qu.offer(new Node(0,0,1,false));
		int answer = -1;
		while (!qu.isEmpty()) {
			Node now = qu.poll();
			int r = now.r;
			int c = now.c;
			if (r == N -1 && c == M - 1) {
				answer = now.cnt;
				break;
			}
			boolean crush = now.crush;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= N || nr < 0 || nc >= M || nc < 0) continue;
				if (map[nr][nc] == '0') {
					if (crush == false && visited[nr][nc][0]) continue;
					else if (crush && visited[nr][nc][1]) continue;
					if (crush) {
						visited[nr][nc][1] = true;
					}
					else {
						visited[nr][nc][0] = true;
					}
					qu.offer(new Node(nr,nc,now.cnt+1, crush));
				}
				else {
					if (crush || visited[nr][nc][1]) continue;
					visited[nr][nc][1] = true;
					qu.offer(new Node(nr,nc,now.cnt+1, true));
				}
			}
		}
		return answer;
	}

}
