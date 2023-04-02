package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2_1946_신입사원 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			int scoreList[][] = new int[n][2];
			for (int j = 0; j < n; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int score1 = Integer.parseInt(st.nextToken());
				int score2 = Integer.parseInt(st.nextToken());
				scoreList[j][0] = score1;
				scoreList[j][1] = score2;
			}
			Arrays.sort(scoreList, (a, b) -> a[0] - b[0]);
			int answer = 1;
			int minScore = scoreList[0][1];
			for (int j = 1; j < n; j++) {
				if (scoreList[j][1] < minScore) {
					minScore = scoreList[j][1];
					answer += 1;
				}
			}
			System.out.println(answer);
		}
	}

}
