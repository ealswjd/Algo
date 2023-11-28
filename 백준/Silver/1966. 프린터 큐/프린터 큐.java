import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1966
public class Main {
	static int N, M;
	static int[] arr;
	static Queue<Document> printer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());		
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 문서의 개수
			M = Integer.parseInt(st.nextToken()); // 순서가 궁금한 문서
			
			arr = new int[N];
			printer = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int idx=0; idx<N; idx++) {
				int num = Integer.parseInt(st.nextToken());
				arr[idx] = num;
				printer.offer(new Document(idx, num));
			}//for
			
			Arrays.sort(arr);
			ans.append(getOrder()).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main
	
	private static int getOrder() {
		int order = 1;
		int idx = N-1;
		
		Document cur;
		while(!printer.isEmpty()) {
			cur = printer.poll();
			
			if(arr[idx] > cur.num) printer.offer(cur);				
			else {
				if(cur.idx == M) return order;
				idx--;
				order++;
			}//else
			
		}//while
		
		return order;
	}//getOrder

	static class Document implements Comparable<Document> {
		int idx;
		int num;
		public Document(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}
		@Override
		public int compareTo(Document d) {
			return d.num - this.num;
		}
	}//Document

}//class