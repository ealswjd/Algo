package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_숨바꼭질 {
	static int n, k;
    static int[] checked;
    static int size = 100001;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        checked = new int[size];
        
        System.out.print(bfs(n));
    }//main
    
    static int bfs(int x){
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        checked[x] = 1;
        
        while(!q.isEmpty()){
            x = q.poll();
            if(x == k) return checked[x] - 1;
            
            if(x-1 >= 0 && checked[x-1] == 0){
                checked[x-1] = checked[x] + 1;
                q.offer(x-1);
            }
            if(x+1 < size && checked[x+1] == 0){
                checked[x+1] = checked[x] + 1;
                q.offer(x+1);
            }
            if(x*2 < size && checked[x*2] == 0){
                checked[x*2] = checked[x] + 1;
                q.offer(x*2);
            }            
        }
        
        return 0;
    }//bfs
    
}//class
