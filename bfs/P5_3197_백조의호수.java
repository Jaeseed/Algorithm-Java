package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5_3197_백조의호수 {
	static int R;
	static int C;
	static int[][] meltDay;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 얼음 녹는 날
		// 초기 상태 - 물: 0, 얼음: -1
		// bfs로 돌리며 얼음이 녹는 날짜를 축적하여 입력
		meltDay = new int[R][C];
		
		int[] start = {-1,-1};
		int[] end = new int[2];
		
		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				// 얼음일 때
				if (row.charAt(j) == 'X') {
					meltDay[i][j] = -1;
				}
				// 물일 때
				else {
					meltDay[i][j] = 0;
					// 첫 발견 백조
					if (row.charAt(j) == 'L') {
						if (start[0] == -1) {
							start[0] = i;
							start[1] = j;
						}
						// 두 번째 백조
						else {
							end[0] = i;
							end[1] = j;
						}
					}
				}
			}
		}
		
		// 얼음과 인접한 물들을 통해 첫쨰날 녹는 얼음 list 저장
		Queue<Node> nextDays = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (meltDay[i][j] == 0) {
					setMeltDay(i, j, 0, nextDays);
				}
			}
		}
		
		while (!nextDays.isEmpty()) {
			Node now = nextDays.poll();
			int r = now.r;
			int c = now.c;
			setMeltDay(r, c, meltDay[r][c], nextDays);
		}
		
		System.out.println(findBird(start[0], start[1], end[0], end[1]));
		
		
		
		
	}
	
	static void setMeltDay(int r, int c, int nowDay, Queue<Node> nextDays) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 격자 밖으로 나가는 상황, 이미 날짜가 정해진 상황 무시
			if (nr >= R || nr < 0 || nc >= C || nc < 0 || meltDay[nr][nc] != -1) continue;
			meltDay[nr][nc] = nowDay + 1;
			nextDays.offer(new Node(nr,nc));
		}
	}
	
	static int findBird(int sr, int sc, int er, int ec) {
		int min = 3000;
		int[][] visited = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(visited[i], min);
		}
        visited[sr][sc] = 0;
		Queue<Node> qu = new LinkedList<>();
		qu.offer(new Node(sr, sc, 0));
		
		while (!qu.isEmpty()) {
			Node now = qu.poll();
			int r = now.r;
			int c = now.c;
			int nowDay = now.day;
			// 최소 소요 날짜보다 같거나 클 때, visited 보다 클 때 continue
			if (nowDay >= min || nowDay >= visited[r][c]) continue;
			// 또 다른 백조 발견 시 최소 날짜 초기화 및 continue
			if (r == er && c == ec) {
				min = Math.min(min, nowDay);
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				// 격자 밖으로 나가거나 더 빠른 길이 있는 상황 무시
				if (nr >= R || nr < 0 || nc >= C || nc < 0) continue;
				int nextDay = Math.max(nowDay, meltDay[nr][nc]);
				if (visited[nr][nc] <= nextDay) continue;
				// 현재 지나 온 최대의 날짜로 갱신
				qu.offer(new Node(nr, nc, nextDay));
				visited[nr][nc] = nextDay;
			}
			
		}
		
		return min;
	}
	
	static class Node {
		int r;
		int c;
		int day;
		
		Node (int r,  int c) {
			this.r = r;
			this.c = c;
		}
		
		Node (int r, int c, int day) {
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}
}
