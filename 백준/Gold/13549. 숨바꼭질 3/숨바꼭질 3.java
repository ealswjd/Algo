import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int size = 100001;
	static int[] checked;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
		br.close();
		
		checked = new int[size];
		Arrays.fill(checked, -1);
		
		int time = bfs();
		System.out.print(time);
	}//main

	private static int bfs() {
		checked[N] = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		
		int x;
		while(!q.isEmpty()) {
			x = q.poll();
			if(x == K) return checked[K];
			
			if(x*2 < size && (checked[x*2]==-1 || checked[x*2] > checked[x])) {
				q.offer(x*2);
				checked[x*2] = checked[x];
			}
			
			if(x-1 >= 0 && (checked[x-1]==-1 || checked[x-1] > checked[x]+1)) {
				q.offer(x-1);
				checked[x-1] = checked[x]+1;
			}
			
			if(x+1 < size && (checked[x+1]==-1 || checked[x+1] > checked[x]+1)) {
				q.offer(x+1);
				checked[x+1] = checked[x]+1;
			}
			
		}//while
		
		return 0;
	}//bfs

}//class