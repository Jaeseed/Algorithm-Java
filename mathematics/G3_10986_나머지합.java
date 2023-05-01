package mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_10986_나머지합 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long answer = 0;
		// 전체 나머지 저장
		int[] arr = new int[N];
		// 인덱스와 같은 나머지의 개수 보관함
		int[] restBox = new int[M+1];
		st = new StringTokenizer(br.readLine());
		int rest = 0;
		for (int n = 0; n < N; n++) {
			long now = Long.parseLong(st.nextToken());
			int nowRest = (int)now % M;
			arr[n] = (int)now % M;
			rest = (rest + nowRest) % M;
			restBox[rest] += 1;
		}
		// 현재 값에서 뺄 나머지
		rest = 0;
		for (int n = 0; n < N; n++) {
			answer += restBox[rest];
			rest = (arr[n] + rest) % M;
			restBox[rest] -= 1;
		}
		System.out.println(answer);
		

	}

}
