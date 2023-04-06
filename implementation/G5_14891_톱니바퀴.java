package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_14891_톱니바퀴 {
	static boolean[][] arr = new boolean[4][8];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			String now = br.readLine();
			for (int j = 0; j < 8; j++) {
				if (now.charAt(j) == '1') {
					arr[i][j] = true;
				}
				else {
					arr[i][j] = false;
				}
			}
		}
		int N = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			target -= 1;
			int d = Integer.parseInt(st.nextToken());
			int[] directions = new int[4];
			directions[target] = d;
			// 왼쪽 톱니바퀴
			int left = target - 1;
			int leftD = d * -1;
			while (left >= 0) {
				if (arr[left][2] == arr[left+1][6]) break;
				directions[left] = leftD;
				leftD *= -1;
				left -= 1;
			}
			// 오른쪽 톱니바퀴
			int right = target + 1;
			int rightD = d * -1;
			while (right < 4) {
				if (arr[right-1][2] == arr[right][6]) break;
				directions[right] = rightD;
				
				rightD *= -1;
				right += 1;
			}
			for (int i = 0; i < 4; i++) {
				if (directions[i] == -1) {
					rotateCounterClockwise(i);
				}
				else if (directions[i] == 1) {
					rotateClockwise(i);
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			if (arr[i][0]) {
				answer += 1 << i;
			}
		}
		System.out.println(answer);

	}
	
	static void rotateClockwise(int target) {
		boolean tmp = arr[target][7];
		for (int i = 7; i > 0; i--) {
			arr[target][i] = arr[target][i-1];
		}
		arr[target][0] = tmp;
	}
	
	static void rotateCounterClockwise(int target) {
		boolean tmp = arr[target][0];
		for (int i = 0; i < 7; i++) {
			arr[target][i] = arr[target][i+1];
		}
		arr[target][7] = tmp;
	}

}
