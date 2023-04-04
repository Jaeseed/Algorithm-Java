package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class S1_5107_마니또게임 {
	static HashMap<String, Integer> hm;
	static Integer[] relation;
	public static void main(String[] args) throws IOException {
		int t = 1;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			relation = new Integer[N];
			hm = new HashMap<>();
			for (int i = 0; i < N; i++) {
				relation[i] = i;
			}
			int idx = 0;
			for (int n = 0; n < N; n++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String parent = st.nextToken();
				String son = st.nextToken();
				if (!hm.containsKey(parent)) {
					hm.put(parent, idx);
					idx += 1;
				}
				if (!hm.containsKey(son)) {
					hm.put(son, idx);
					idx += 1;
				}
				union(hm.get(parent), hm.get(son));
			}
			for (int i = 0; i < N; i++) {
				find(i);
			}
			int[] tmp = new int[N];
			for (int n = 0; n < N; n++) {
				tmp[relation[n]] += 1;
			}
			
			int answer = 0;
			
			for (int n = 0; n < N; n++) {
				if (tmp[n] > 0) {
					answer += 1;
				}
			}
			sb.append(t + " " + answer+"\n");
			t += 1;
		}
		System.out.println(sb);

	}
	
	static void union(int p, int s) {
		int pp = find(p);
		int ps = find(s);
		relation[ps] = pp;
	}
	
	static int find(int s) {
		if (relation[s] == s) {
			return s;
		}
		int ps = find(relation[s]);
		relation[s] = ps;
		return ps;
	}

}