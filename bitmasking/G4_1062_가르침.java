package bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_1062_가르침 {
	static int[] waiting = {1,3,4,5,6,7,9,10,11,12,14,15,16,17,18,20,21,22,23,24,25};
	static int answer = 0;
	static int[] words;
	static int N;
	static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if (K < 5) {
			System.out.println(0);
		}
		else {
			words = new int[N];
			for (int n = 0; n < N; n++) {
				String word = br.readLine();
				for (int i = 0; i < word.length(); i++) {
					words[n] |= 1 << ((int)word.charAt(i) - 97);
				}
			}
			int defaultValue = (1 << 0) | (1 << 2) | (1 << 13) |(1 << 8) | (1 << 19);
			recursion(defaultValue, 0, 0);
			System.out.println(answer);
		}

	}
	
	static void recursion(int visited, int nowIdx, int cnt) {
		if (answer == N || 21 - nowIdx < K -5 - cnt) {
			return;
		}
		if (cnt == K - 5 || nowIdx == 21) {
			if (cnt < K - 5) {
				return;
			}
			search(visited);
			return;
		}
		for (int i = nowIdx; i < 21; i++) {
			recursion(visited | 1 << waiting[i], i + 1, cnt + 1);
		}
	}
	
	static void search(int visited) {
		int cnt = 0;
		for (int n = 0; n < N; n++) {
			if ((visited & words[n]) == words[n]) {
				cnt += 1;
			}
		}
		answer = Math.max(answer, cnt);
	}

}
