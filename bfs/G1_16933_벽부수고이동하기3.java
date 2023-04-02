package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_16933_벽부수고이동하기3 {
	static int visited[][];
	static int n;
	static int m;
	static int k;
	static int arr[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	static class Node {
		public int r;
		public int c;
		public int dist;
		public int smash;
		public boolean isDayTime;

		Node(int r, int c, int dist, int smash, boolean isDayTime) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.smash = smash;
			this.isDayTime = isDayTime;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = 10000000;
			}
		}
		for (int i = 0; i < n; i++) {
			arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		System.out.println(bfs());

	}

	static int bfs() {
		int nr;
		int nc;
		boolean flag;
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, 1, 0, true));
		visited[0][0] = 0;
		while (!q.isEmpty()) {
			Node now = q.poll();
			flag = false;
			int r = now.r;
			int c = now.c;
			if (r == n - 1 && c == m - 1)
				return now.dist;
			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if (nr < 0 || nr >= n || nc < 0 || nc >= m)
					continue;
				if (arr[nr][nc] == 1) {
					if (!now.isDayTime) {
						flag = true;
					} else {
						if (now.smash + 1 >= visited[nr][nc])
							continue;
						if (now.smash + 1 > k)
							continue;
						visited[nr][nc] = now.smash + 1;
						q.offer(new Node(nr, nc, now.dist + 1, now.smash + 1, !now.isDayTime));
					}
				} else {
					if (now.smash >= visited[nr][nc])
						continue;
					visited[nr][nc] = now.smash;
					q.offer(new Node(nr, nc, now.dist + 1, now.smash, !now.isDayTime));
				}
			}
			if (flag) {
				q.offer(new Node(r, c, now.dist + 1, now.smash, !now.isDayTime));
			}
		}
		return -1;
	}

}
