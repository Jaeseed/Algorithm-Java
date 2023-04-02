package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1_2693_N번째큰수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int length = st.countTokens();
			int[] arr = new int[length];
			for (int l = 0; l < length; l++) {
				arr[l] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			sb.append(Integer.toString(arr[length-3]) + "\n");
			
		}
		System.out.println(sb);
	}

}
