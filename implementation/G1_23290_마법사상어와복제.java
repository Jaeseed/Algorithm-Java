package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_23290_마법사상어와복제 {
	// 물고기 냄새
	static int smell[][] = new int[4][4];
	// 격자마다 물고기 수
	static int fishCnt[][] = new int[4][4];
	// 물고기 이동
	static int dfr[] = {1,1,1,0,-1,-1,-1,0};
	static int dfc[] = {-1,0,1,1,1,0,-1,-1};
	// 상어의 이동
	static int dsr[] = {-1,0,1,0};
	static int dsc[] = {0,-1,0,1};
	
	static List<int[]> fishInfo;
	
	// 상어 위치 정보
	static int sr;
	static int sc;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		fishInfo = new ArrayList<>();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int fr = Integer.parseInt(st.nextToken()) - 1;
			int fc = Integer.parseInt(st.nextToken()) - 1;
			int d = 8 - Integer.parseInt(st.nextToken());
			int[] newFish = {fr,fc,d};
			fishInfo.add(newFish);
			fishCnt[fr][fc] += 1;
		}
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken()) - 1;
		sc = Integer.parseInt(st.nextToken()) - 1;
		int s = 0;
		while(s++ < S) {
			// 1. 복제할 물고기 저장
			List<int[]> tong = new ArrayList<>();
			for (int[] fish : fishInfo) {
				tong.add(fish);
			}
			
			// 2. 물고기 이동
			for (int n = 0; n < fishInfo.size(); n++) {
				int fr = fishInfo.get(n)[0];
				int fc = fishInfo.get(n)[1];
				int d = fishInfo.get(n)[2] - 1;
				
				for (int i = 0; i < 8; i++) {
					d = (d + 1) % 8;
					int nfr = fr + dfr[d];
					int nfc = fc + dfc[d];
					if (nfr >= 4 || nfr < 0 || nfc >= 4 || nfc < 0 || smell[nfr][nfc] > 0) continue;
					if (nfr == sr && nfc == sc) continue;
					fishCnt[nfr][nfc] += 1;
					fishCnt[fr][fc] -= 1;
					int[] movedFish = {nfr, nfc, d};
					fishInfo.set(n, movedFish);
					break;
				}
			}
			
			// 3. 상어 이동
			sharkMove();
			
			// 4. 물고기 냄새 옅어지기
			for (int r = 0; r < 4; r++) {
				for (int c = 0; c < 4; c++) {
					if (smell[r][c] > 0) {
						smell[r][c] -= 1;
					}
				}
			}
			
			// 5. 물고기 복제
			for (int[] fish : tong) {
				fishInfo.add(fish);
				fishCnt[fish[0]][fish[1]]++;
			}
		}
		System.out.println(fishInfo.size());
	}
	
	static void sharkMove() {
		Queue<Move> qu = new LinkedList<>();
		Move selectedMove = new Move(sr,sc);
		qu.offer(selectedMove);
		while (!qu.isEmpty()) {
			Move now = qu.poll();
			if (now.step == 3) {
				if (selectedMove.step == 0 || now.cnt > selectedMove.cnt) {
					selectedMove = now;
				}
				continue;
			}
			int r = now.r;
			int c = now.c;
			for (int i = 0; i < 4; i++) {
				int nr = r + dsr[i];
				int nc = c + dsc[i];
				if (nr >= 4 || nr < 0 || nc >= 4 || nc < 0) continue;
				if (now.visited[nr][nc]) {
					qu.offer(new Move(nr,nc, now.step, now.route, now.cnt, now.visited));
				}
				else {
					qu.offer(new Move(nr,nc, now.step, now.route, now.cnt + fishCnt[nr][nc], now.visited));
				}
			}
		}
		
		List<int[]> newFishInfo = new ArrayList<>();
		for (int[] fish : fishInfo) {
			if(check(fish, selectedMove)) {
				newFishInfo.add(fish);
			}
			else {
				smell[fish[0]][fish[1]] = 3;
				fishCnt[fish[0]][fish[1]] -= 1;
			}
		}
		sr = selectedMove.route[2][0];
		sc = selectedMove.route[2][1];
		fishInfo = newFishInfo;
		
	}
	
	static boolean check (int[] info, Move move) {
		for (int i = 0; i < 3; i++) {
			if (info[0] == move.route[i][0] && info[1] == move.route[i][1]) {
				return false;
			}
		}
		return true;
	}
	
	static class Move {
		int r;
		int c;
		int step;
		int route[][] = new int[3][2];
		int cnt;
		boolean[][] visited = new boolean[4][4];
		
		Move(int r, int c) {
			this.r = r;
			this.c = c;
			visited[r][c] = true;
		}
		
		Move(int r, int c, int step, int[][] route, int cnt, boolean[][] visited) {
			this.r = r;
			this.c = c;
			for (int i = 0; i < step; i++) {
				this.route[i][0] = route[i][0];
				this.route[i][1] = route[i][1];
			}
			this.route[step][0] = r;
			this.route[step][1] = c;
			this.step = step + 1;
			this.cnt = cnt;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(visited[i][j]) {
						this.visited[i][j] = true;
					}
				}
			}
			this.visited[r][c] = true;
		}
		
	}

}
