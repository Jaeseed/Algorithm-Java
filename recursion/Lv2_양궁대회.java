package recursion;

class Lv2_양궁대회 {
	static int[] answer = { -1 };
	static int score_diff;
	static int min = -1;

	public static void recurse(int[] info, int[] lion, int score, int now, int arrows, int min_score) {
		if (now == 10 || arrows == 0) {
			if (score >= score_diff && score > 0) {
				if (score == score_diff && min > min_score) {
					return;
				}
				min = min_score;
				score_diff = score;
				if (arrows > 0)
					lion[now] = arrows;
				answer = lion.clone();
				lion[now] = 0;
				return;
			}
			return;
		}
		if (info[now] + 1 <= arrows) {
			lion[now] = info[now] + 1;
			if (info[now] == 0) {
				recurse(info, lion, score + 10 - now, now + 1, arrows - 1 - info[now], now);
			} else {
				recurse(info, lion, score + (10 - now) * 2, now + 1, arrows - 1 - info[now], now);
			}
			lion[now] = 0;
		}
		recurse(info, lion, score, now + 1, arrows, min_score);
	}

	public static int[] solution(int n, int[] info) {
		int[] lion = new int[11];
		int apeach = 0;
		for (int i = 0; i < 10; i++) {
			if (info[i] > 0) {
				apeach += 10 - i;
			}
		}
		recurse(info, lion, -apeach, 0, n, 10);
		return answer;
	}

	public static void main(String[] args) {
//    	int[] test = {1,0,0,0,0,0,0,0,0,0,0};
//    	int[] test = {2,1,1,1,0,0,0,0,0,0,0};
		int[] test = { 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0 };
		System.out.println(solution(3, test));
	}
}