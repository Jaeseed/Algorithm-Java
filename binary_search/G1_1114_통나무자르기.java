package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1_1114_통나무자르기 {
	static int[] arr;
	static int length;
	static int first;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		arr = new int[K+1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int k = 0; k < K; k++) {
			arr[k] = Integer.parseInt(st.nextToken());
		}
		arr[K-1] = L;
		
	}
	
	

}
