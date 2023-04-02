package priority_queue;

import java.util.Collections;
import java.util.PriorityQueue;

public class Lv2_디펜스게임 {
	public static void main(String[] args) {
		int n = 7;
		int k = 3;
		int[] enemy = { 4, 2, 4, 5, 3, 3, 1 };
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int answer = 0;
		for (int now : enemy) {
			pq.add(now);
			if (n < now) {
				while (!pq.isEmpty() && k > 0 && n < now) {
					k -= 1;
					n += pq.remove();
				}
				if (n < now)
					break;
			}
			n -= now;
			answer += 1;
		}
		System.out.println(answer);

	}

}
