package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class S1_5107_마니또게임 {
	static HashMap<String, Integer> hm = new HashMap<>();
	static int[] relation;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		relation = new int[N];
		for (int i = 0; i < N; i++) {
			relation[i] = -1;
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
