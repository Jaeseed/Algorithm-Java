package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_2623_음악프로그램 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Integer>> arr = new ArrayList<>();
		int[] parentCnt = new int[N+1];
		arr.add(new ArrayList<>());
		for (int n = 0; n < N; n++) {
			arr.add(new ArrayList<>());
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if (num == 0) continue;
			int before = Integer.parseInt(st.nextToken());
			for (int i = 1; i < num; i++) {
				int singer = Integer.parseInt(st.nextToken());
				arr.get(before).add(singer);
				parentCnt[singer] += 1;
				before = singer;
			}
		}
		
		Queue<Integer> qu = new LinkedList<>();
		int[] sortedArr = new int[N+1];
		int idx = 1;
		
		for (int n = 1; n <= N; n++) {
			if (parentCnt[n] == 0) {
				qu.offer(n);
			}
		}
		
		while (!qu.isEmpty()) {
			int now = qu.poll();
			sortedArr[idx] = now;
			idx++;
			for (int next : arr.get(now)) {
				parentCnt[next]--;
				if (parentCnt[next] == 0) {
					qu.offer(next);
				}
			}
		}
		
		
		if (sortedArr[N] == 0) {
			System.out.println(0);
		}
		else {
			StringBuilder sb = new StringBuilder();
			for (int n = 1; n <= N; n++) {
				sb.append(sortedArr[n] + "\n");
			}
			System.out.println(sb);
		}

	}

}
