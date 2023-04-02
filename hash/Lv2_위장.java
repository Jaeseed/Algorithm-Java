package hash;

import java.util.HashMap;

public class Lv2_위장 {
	static class Solution {
	    public int solution(String[][] clothes) {
	        int answer = 1;
	        HashMap<String, Integer> hm = new HashMap<>();
	        for (String[] cloth : clothes) {
	            if (hm.containsKey(cloth[1])) {
	                hm.put(cloth[1], hm.get(cloth[1])+1);
	            }
	            else {
	                hm.put(cloth[1], 1);
	            }
	        }
	        
	        for (String key : hm.keySet()) {
	            answer *= hm.get(key) + 1;
	        }
	        answer -= 1;
	        return answer;
	    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	}

}
