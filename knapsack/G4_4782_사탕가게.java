package knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_4782_사탕가게 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			double tmp = Double.parseDouble(st.nextToken());
			int P = (int)(tmp * 100 + 0.5);
			if (N == 0 && P == 0) {
				break;
			}
			int[] bag = new int[P+1];
			int answer = 0;
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int cal = Integer.parseInt(st.nextToken());
				tmp = Double.parseDouble(st.nextToken());
				int nowP = (int)(tmp * 100);
				for (int p = 0; p <= P - nowP; p++) {
					bag[p + nowP] = Math.max(bag[p + nowP], bag[p] + cal);
					answer = Math.max(answer, bag[p + nowP]);
				}
			}
			sb.append(answer + "\n");
		}
		System.out.println(sb);

	}

}
