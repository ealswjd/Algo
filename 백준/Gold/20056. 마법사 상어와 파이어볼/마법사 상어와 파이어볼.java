import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, sum; // 격자판 크기 , 파이어볼 질량의 합
	static int[][] visited; // 방문체크
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 행 방향
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1}; // 열 방향
	static Queue<Fire> fireQ;
	static Map<Node, Queue<Fire>> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자판 크기
		int M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		int K = Integer.parseInt(st.nextToken()); // 명령 횟수		
		
		fireQ = new LinkedList<>();		
		int r, c, m, s, d;
		sum = 0;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			fireQ.offer(new Fire(r, c, m, s, d));
			sum += m;
		}//while
		
		// 마법사 상어가 이동을 K번 명령
		while(K-->0) {
			map = new HashMap<>();
			visited = new int[N][N];
			move();
			magic();
		}//while
		
		System.out.print(sum);
	}//main
	
	private static void magic() {
		int prevDist, curDist, sSum, mSum, cnt, r, c;		
		Queue<Fire> q;
		Fire fire;
		
		for(Node key : map.keySet()) {
			q = map.get(key);
			cnt = q.size();
			fire = q.poll();
			prevDist = fire.d % 2;
			sSum = fire.s;
			mSum = fire.m;
			r = fire.r;
			c = fire.c;			
			
			while(!q.isEmpty()) {
				fire = q.poll();
				curDist = fire.d % 2;
				if(prevDist == -1 || prevDist != curDist) prevDist = -1;
				sSum += fire.s;
				mSum += fire.m;				
			}//while
			
			int s = sSum / cnt;
			int m = mSum / 5;
			int d = prevDist==-1 ? 1 : 0;
			// 질량이 0인 파이어볼은 소멸되어 없어진다.
			if(m < 1) continue;
			sum += m*4;
			for(int i=d; i<8; i+=2) {
				fireQ.offer(new Fire(r, c, m, s, i));
			}
		}//for		
		
	}//magic

	private static void move() {		
		int size = fireQ.size();
		int r, c;
		Fire fire;
		while(size-->0) {
			fire = fireQ.poll();
			r = getRC(fire.r + (dr[fire.d] * fire.s));
			c = getRC(fire.c + (dc[fire.d] * fire.s));
			visited[r][c]++;
			fireQ.offer(new Fire(r, c, fire.m, fire.s, fire.d));
		}//while
		
		size = fireQ.size();
		while(size-->0) {
			fire = fireQ.poll();
			r = fire.r;
			c = fire.c;			
			if(visited[r][c] >= 2) {
				Node key = new Node(r, c);
				Queue<Fire> q = map.getOrDefault(key, new LinkedList<>());
				q.offer(new Fire(r, c, fire.m, fire.s, fire.d));
				map.put(key, q);
				sum -= fire.m;
				continue;
			}
			fireQ.offer(new Fire(r, c, fire.m, fire.s, fire.d));
		}
		
	}//move

	
	private static int getRC(int d) {
		if(d>=N) return d %= N;
		else if(d<0) {
			d = N + (d%N);
			if(d==N) d = 0;
		}		
		return d;
	}//getRC


	static class Fire {
		int r; // 행
		int c; // 열 
		int m; // 질량
		int s; // 속력
		int d; // 방향
		
		public Fire(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}//Fire
	
	static class Node {
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}		
	}//node

}//class