package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class G2_7453_합이0인네정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] numbers = new int[N][4];
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				numbers[n][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] leftSum = new int[N * N];
		int[] rightSum = new int[N * N];
		
		calculation(numbers, leftSum, 0, N);
		calculation(numbers, rightSum, 2, N);
		
		Arrays.sort(leftSum);
		Arrays.sort(rightSum);
		
		long answer = 0;
		int left = 0;
		int right = N * N - 1;
		while (left < N * N && right >= 0) {
			int leftNow = leftSum[left];
			int rightNow = rightSum[right];
			if (leftNow + rightNow < 0) {
				left++;
			}
			else if (leftNow + rightNow > 0) {
				right--;
			}
			else {
				int leftCnt = 0;
				int rightCnt = 0;
				while (left < N * N && leftSum[left] == leftNow) {
					leftCnt++;
					left++;
				}
				while (right >= 0 && rightSum[right] == rightNow) {
					rightCnt++;
					right--;
				}
				
				answer += (long)leftCnt * rightCnt;
			}
		}
		
		
		
		System.out.println(answer);
	}
	
	static void calculation(int[][] numbers, int[] sum, int start, int N) {
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tmp = numbers[i][start] + numbers[j][start+1];
				sum[idx] = tmp;
				idx++;
			}
		}
	}

}
