package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_2468_안전영역 {
	
	static class Node {
		public int r;
		public int c;
		Node (int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}

	
	static int[][] map;
	static int N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int max_height = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max_height = Math.max(max_height, map[i][j]);
			}
		}
		int answer = 0;
		for (int h = 1; h <= max_height; h++) {
			boolean[][] visited = new boolean[N][N];
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c] == false && map[r][c] >= h) {
						visited[r][c] = true;
						visited = bfs(r, c, h, visited);
						cnt += 1;
					}
				}
			}
			answer = Math.max(answer, cnt);
		}
		System.out.println(answer);
	}
	
	static boolean[][] bfs(int r, int c, int height, boolean[][] visited) {
		Queue<Node> qu = new LinkedList<>();
		qu.offer(new Node(r,c));
		while (!qu.isEmpty()) {
			Node now = qu.poll();
			int rr = now.r;
			int cc = now.c;
			for (int i = 0; i < 4; i++) {
				int nr = rr + dr[i];
				int nc = cc + dc[i];
				if (nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc] || map[nr][nc] < height) continue;
				visited[nr][nc] = true;
				qu.offer(new Node(nr,nc));
			}
		}
		return visited;
	}

}
