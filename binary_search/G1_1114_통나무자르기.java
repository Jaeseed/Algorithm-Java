package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G1_1114_통나무자르기 {
	static int[] arr;
	static int L;
	static int K;
	static int C;
	static int answer;
	static int first;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		if (K < C) {
			C = K;
		}
		arr = new int[K+1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int k = 0; k < K; k++) {
			arr[k] = Integer.parseInt(st.nextToken());
		}
		
		arr[K] = L;
		Arrays.sort(arr);
		answer = L;
		
		binarySearch();
		System.out.println(Integer.toString(answer) + " " + Integer.toString(first));
		
	}
	
	static void binarySearch() {
		int start = 1;
		int end = L;
		for (int i = 0; i < 30; i++) {
			if (start > end) break;
			int mid = (start + end) / 2;
			if (slice(mid)) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
	}
	
	static boolean slice(int nowL) {
		int front = K;
		int rear = K;
		int cnt = 0;
		while (front >= 0 && cnt < C) {
			if (front == 0) {
				cnt += 1;
				break;
			}
			front -= 1;
			if (arr[rear] - arr[front] > nowL) {
				if (front == rear - 1) return false;
				rear = front + 1;
				front += 1;
				cnt += 1;
			}
			else if (arr[rear] - arr[front] == nowL){
				rear = front;
				cnt += 1;
			}
		}
		if (arr[front] > nowL) return false;
		if (nowL < answer) {
			answer = nowL;
			first = arr[front];
		}
		else if (nowL == answer && first > arr[front]) {
			first = arr[front];
		}
		return true;
	}
	
	

}
