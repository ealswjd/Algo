import java.util.ArrayList;
import java.util.Collections;

class Solution {
    private boolean[] visited;
    private ArrayList<String> routeList;
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        routeList = new ArrayList<>();
        
        dfs("ICN", "ICN", tickets, 0);
        Collections.sort(routeList);
        
        String[] answer = routeList.get(0).split(" ");
        return answer;
    }//solution
    
    private void dfs(String start, String route, String[][] tickets, int cnt){
        if(cnt == tickets.length){
            routeList.add(route);
            return;
        }
        
        for(int i=0; i<tickets.length; i++){
            if(tickets[i][0].equals(start) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, cnt+1);
                visited[i] = false;
            }
        }
        
    }//dfs
}