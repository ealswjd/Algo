import java.util.*;

class Solution {
    
    public int solution(int[][] targets) {
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i=0; i<targets.length; i++) {
            nodes.add(new Node(targets[i][0], targets[i][1]));
        }//for
        Collections.sort(nodes);
        
        int answer = 1;
        int s=nodes.get(0).s, e=nodes.get(0).e, ns, ne;
        double mark = (double)(s+e)/2, nm;
        for(Node node : nodes) {
            ns = node.s;
            ne = node.e;
            nm = (double)(ns+ne)/2;
            
            if(ns >= e) {
                answer++;
                s = ns;
                e = ne;
            }
            if(s<nm && e>nm ){
                s = Math.max(s, ns);
                e = Math.min(e, ne);
            }
            
        }//for
        
        return answer;
    }//solution
    
    static class Node implements Comparable<Node> {
        int s;
        int e;
        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }
        @Override
        public int compareTo(Node n) {
            if(this.s == n.s) return n.e - this.e;
            return this.s - n.s;
        }
    }//Node
    
}//class