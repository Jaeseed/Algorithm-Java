package binary_search;

public class Lv3_선입선출스케줄링 {
	class Solution {
	    public int solution(int n, int[] cores) {
	        int answer = 0;
	        int start = 0;
	        int end = 50000 * 10000;
	        int mid = 0;
	        long completedCnt = 0;
	        int target = n;
	        while (start <= end) {
	            mid = (start + end) / 2;
	            completedCnt = 0;
	            for (int core : cores) {
	                completedCnt += mid / core;
	                if (mid % core != 0) {
	                    completedCnt += 1;
	                }
	            }
	            if (completedCnt == target) break;
	            if (completedCnt < target) {
	                start = mid + 1;
	            }
	            else {
	                end = mid - 1;
	            }
	        }
	        System.out.println(mid);
	        if (completedCnt > target || completedCnt < target) {
	            if (completedCnt > target && mid != 0) {
	                mid -= 1;
	                completedCnt = 0;
	                for (int core : cores) {
	                    completedCnt += mid / core;
	                    if (mid % core != 0) {
	                        completedCnt += 1;
	                    }
	                }
	            }
	            int idx = -1;
	            while (completedCnt < target) {
	                idx += 1;
	                if (mid % cores[idx] == 0) {
	                    completedCnt += 1;
	                }
	            }
	            answer = idx + 1;
	        }
	        else {
	            int minTime = 10001;
	            int minCore = -1;
	            for (int i = 0; i < cores.length; i++) {
	                int timeRemaining = mid % cores[i];
	                if (timeRemaining == 0) {
	                    timeRemaining = cores[i];
	                }
	                if (timeRemaining <= minTime) {
	                    minTime = timeRemaining;
	                    minCore = i + 1;
	                }

	            }
	            if (minCore == -1) {
	                minCore = 1;
	            }
	            answer = minCore;
	        }
	        System.out.println(mid);
	        return answer;
	    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
