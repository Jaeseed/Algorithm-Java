package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class G5_2866_문자열잘라내기 {
	static char[][] arr;
	static int R;
	static int C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		for (int r = 0; r < R; r++) {
			arr[r] = br.readLine().toCharArray();
		}
		int start = 1;
		int end = R - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (check(mid)) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		System.out.println(end);
	}
	
	static boolean check(int idx) {
		Set<String> now = new HashSet<>();
		for (int c = 0; c < C; c++) {
			StringBuilder sb = new StringBuilder();
			for (int r = idx; r < R; r++) {
				sb.append(arr[r][c]);
			}
			now.add(sb.toString());
			if (now.size() != c + 1) return false;
		}
		return true;
	}

}
