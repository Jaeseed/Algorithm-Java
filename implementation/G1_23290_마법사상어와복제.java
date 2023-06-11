package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
	
	static int[][] selectedRoute;
	static int maxCnt;
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
		selectedRoute = new int[3][2];
		maxCnt = -1;
		dfs(sr, sc, 0, 0, new int[3][2], new boolean[4][4]);
		List<int[]> newFishInfo = new ArrayList<>();
		for (int[] fish : fishInfo) {
			if(check(fish)) {
				newFishInfo.add(fish);
			}
			else {
				smell[fish[0]][fish[1]] = 3;
				fishCnt[fish[0]][fish[1]] -= 1;
			}
		}
		sr = selectedRoute[2][0];
		sc = selectedRoute[2][1];
		fishInfo = newFishInfo;
		
	}
	
	static void dfs(int r, int c, int step, int cnt, int[][]route, boolean[][] visited) {
		if (step == 3) {
			if (cnt > maxCnt) {
				maxCnt = cnt;
				for (int i = 0; i < 3; i++) {
					selectedRoute[i][0] = route[i][0];
					selectedRoute[i][1] = route[i][1];
				}
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dsr[i];
			int nc = c + dsc[i];
			if (nr >= 4 || nr < 0 || nc >= 4 || nc < 0) continue;
			route[step][0] = nr;
			route[step][1] = nc;
			if (visited[nr][nc]) {
				dfs(nr,nc,step+1,cnt,route, visited);
			}
			else {
				visited[nr][nc] = true;
				dfs(nr,nc,step+1,cnt + fishCnt[nr][nc],route, visited);
				visited[nr][nc] = false;
			}
		}
	}
	
	static boolean check (int[] info) {
		for (int i = 0; i < 3; i++) {
			if (info[0] == selectedRoute[i][0] && info[1] == selectedRoute[i][1]) {
				return false;
			}
		}
		return true;
	}

}
