import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19942
public class Main {
	static int N, minCost; // 식재료의 개수, 최소 비용
	static int[] m; // 단백질, 지방, 탄수화물, 비타민의 최소 영양성분
	static int[] mSum; // 영양성분 합
	static Info[] info; // 식재료 정보
	static PriorityQueue<String> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 식재료의 개수
		info = new Info[N]; // 식재료 정보
		
		m = new int[4]; // 단백질, 지방, 탄수화물, 비타민의 최소 영양성분
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}//for
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()); // 단백질
			int f = Integer.parseInt(st.nextToken()); // 지방
			int s = Integer.parseInt(st.nextToken()); // 탄수화물
			int u = Integer.parseInt(st.nextToken()); // 비타민
			int cost = Integer.parseInt(st.nextToken()); // 가격
			info[i] = new Info(i+1, p, f, s, u, cost);
		}//for
		br.close();		
		
		minCost = 987654321;
		pq = new PriorityQueue<>();
		mSum = new int[4];

		getMin(0, 0, 0);

		if(minCost == 987654321) System.out.print(-1);
		else {
			StringBuilder ans = new StringBuilder();
			ans.append(minCost).append("\n");
			ans.append(pq.poll());
			System.out.print(ans);
		}//else
		
	}//main
	
	private static void getMin(int i, int sum, int pick) {
		if(i == N) {
			check(sum, pick);						
			return;
		}//if

		add(info[i]);
		getMin(i+1, sum+info[i].cost, pick | (1 << info[i].num));
		subtract(info[i]);
		
		getMin(i+1, sum, pick);
	}//getMin

	private static void check(int sum, int pick) {
		if(!possible() || sum > minCost) return;
		
		if(sum < minCost) pq.clear();
		minCost = sum;
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			if((pick & (1 << i+1)) != 0) sb.append(i+1).append(" ");
		}//for
		
		pq.offer(String.valueOf(sb));
		
	}//check	

	private static void add(Info info) {
		mSum[0] += info.p;
		mSum[1] += info.f;
		mSum[2] += info.s;
		mSum[3] += info.u;		
	}//add

	private static void subtract(Info info) {
		mSum[0] -= info.p;
		mSum[1] -= info.f;
		mSum[2] -= info.s;
		mSum[3] -= info.u;	
	}//subtract

	private static boolean possible() {
		for(int i=0; i<4; i++) {
			if(mSum[i] < m[i]) return false;
		}//for

		return true;
	}//possible

	static class Info {
		int num;
		int p, f, s, u, cost;
		public Info(int num, int p, int f, int s, int u, int cost) {
			this.num = num;
			this.p = p;
			this.f = f;
			this.s = s;
			this.u = u;
			this.cost = cost;
		}
	}//Info

}//class