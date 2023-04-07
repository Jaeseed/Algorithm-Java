package dfs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Lv3_여행경로 {
	static String[] answer;
    static int length;
    static HashMap<String, List> dict = new HashMap<>();
    static HashMap<String, boolean[]> visited = new HashMap<>();
    static public void dfs(String departure, int idx) {
        if (answer[length-1] != null) {
            return;
        }
        if (!dict.containsKey(departure)) return;
        List<String> targets = dict.get(departure);
        for (int i = 0; i < targets.size(); i++) {
            boolean[] vis = visited.get(departure);
            if (vis[i]) {
                continue;
            }
            String arrival = targets.get(i);
            answer[idx] = arrival;
            vis[i] = true;
            visited.replace(departure, vis);
            dfs(arrival, idx + 1);
            if (answer[length-1] != null) {
                return;
            }
            answer[idx] = null;
            vis[i] = false;
            visited.replace(departure, vis);
        }
    }
	public static void main(String[] args) {
		String[][] input = {{"ICN","JFK"}, {"HND", "IAD"}, {"JFK", "HND"}, {"ICN","AAA"}};
		String[] ret = solu(input);
		int a = 3;

	}
    static String[] solu(String[][] tickets) {
        for (String[] ticket : tickets) {
            String departure = ticket[0];
            String arrival = ticket[1];
            if (dict.containsKey(departure)) {
                List<String> now = dict.get(departure);
                now.add(arrival);
                dict.replace(departure, now);
            }
            else {
                List<String> now = new ArrayList<>();
                now.add(arrival);
                dict.put(departure, now);
            }
        }
        length = dict.size();
        answer = new String[length];
        Iterator<String> keys = dict.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            List<String> forSort = dict.get(key);
            Collections.sort(forSort);
            dict.replace(key, forSort);
            visited.put(key, new boolean[forSort.size()]);
        }
        answer[0] = "ICN";
        dfs("ICN", 1);
        return answer;
    }

}
