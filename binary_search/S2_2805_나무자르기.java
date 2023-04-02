package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2_2805_나무자르기 {
	static long treeSum[];
	static int target;
	static int trees[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			trees[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(trees);

		treeSum = new long[N];
		int cnt = 1;
		for (int n = N - 2; n >= 0; n--) {
			treeSum[n] = treeSum[n + 1] + (trees[n + 1] - trees[n]) * cnt;
			cnt += 1;
		}

		System.out.println(binarySearch());

	}

	static int binarySearch() {
		int start = 0;
		int end = treeSum.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (treeSum[mid] < target) {
				end = mid - 1;
			} else if (treeSum[mid] > target) {
				start = mid + 1;
			} else
				return trees[mid];
		}
		if (treeSum[mid] > target) {
			mid += 1;
		}
		int cnt = treeSum.length - mid;
		int answer = trees[mid] - (target - (int) treeSum[mid]) / cnt;
		if ((target - treeSum[mid]) % cnt != 0) {
			answer -= 1;
		}

		return answer;
	}

}
