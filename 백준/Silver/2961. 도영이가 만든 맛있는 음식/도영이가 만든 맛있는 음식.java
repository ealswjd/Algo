import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Queue<Ingredient> q;
	static int N;
	static Ingredient[] arr;
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 재료의 개수
		
		arr = new Ingredient[N];
		isSelected = new boolean[N];
		q = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sour = Integer.parseInt(st.nextToken());
			int bitter = Integer.parseInt(st.nextToken());
			arr[i] = new Ingredient(sour, bitter);
		}
		Cook(0, 1, 0);
		
		System.out.println(q.poll().gap);

	}//main
	
	private static void Cook(int idx, int sour, int bitter) {
		if(idx == N) {
			if(bitter>=1) q.offer(new Ingredient(sour, bitter));
			return;
		}
		
		isSelected[idx] = true;
		Cook(idx+1, sour*arr[idx].sour, bitter+arr[idx].bitter);
		
		isSelected[idx] = false;
		Cook(idx+1, sour, bitter);
	}

}//class

class Ingredient implements Comparable<Ingredient>{
	int sour;
	int bitter;
	int gap;
	public Ingredient(int sour, int bitter) {
		this.sour = sour;
		this.bitter = bitter;
		gap = Math.abs(sour-bitter); 
	}
	
	@Override
	public int compareTo(Ingredient o) {
		return this.gap - o.gap;
	}	
}