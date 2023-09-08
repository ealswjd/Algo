import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// https://www.acmicpc.net/problem/1352
public class Main {
	static int N, totalCnt;
	static char[] alpha;
	static int[] sum, idx;
	static PriorityQueue<char[]> pq;
	static Queue<Integer> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 문자열의 길이
		br.close();
		
		init();
		int max = getCnt();
		for(int i=1; i<=max; i++) {
			totalCnt = i; // 알파벳 개수
			idx = new int[totalCnt];
			getIdx(1, 0, 0);
			if(!pq.isEmpty()) break;
		}
		
		print();
	}//main

	private static void print() {
		StringBuilder ans = new StringBuilder();
		if(pq.isEmpty()) ans.append(-1);
		else {
			char[] result = pq.poll();
			for(char c : result) {
				ans.append(c);
			}//for			
		}
		System.out.print(ans);
	}//print

	private static void getIdx(int start, int cnt, int sum) {
		if(cnt == totalCnt) {
			if(sum == N) {
				makeIdealString();
			}//if
			return;
		}//if
		
		for(int i=start, max=N; i<=max; i++) {
			idx[cnt] = i;
			if(sum+i > N || sum+1 < i) break;
			getIdx(i+1, cnt+1, sum+i);
		}//for
		
	}//getIdx

	private static void makeIdealString() {		
		int[] start = Arrays.copyOf(idx, totalCnt);
		int[] idxTmp = Arrays.copyOf(idx, totalCnt);
		boolean[] visited = new boolean[N];
		char[] result = new char[N];
		for(int i=0; i<totalCnt; i++) {
			result[idxTmp[i]-1] = alpha[i];
			visited[idxTmp[i]-1] = true;
			idxTmp[i]--;
		}//for		
		
		q.clear();
		for(int i=0; i<totalCnt; i++) {
			for(int j=0; j<idxTmp[i]; j++) {
				q.offer(i); 
			}
		}//for

		int size = q.size();
		while(!q.isEmpty() && size-->0) {
			int startIdx = start[q.peek()];
			for(int i=startIdx, cnt=1; i<N && cnt < startIdx; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				result[i] = alpha[q.poll()];
				cnt++;
			}//for		
		}//if
		
		int i = idx[totalCnt-1];
		while(!q.isEmpty() && i < N) {
			if(visited[i]) {
				i++;
				continue;
			}
			result[i++] = alpha[q.poll()];
		}//while
		
		if(!q.isEmpty()) return;
		pq.offer(result);		
	}//makeIdealString

	private static int getCnt() {
		int start = 1, end = sum.length-1;
		int mid = (start+end) / 2;
		
		while(start <= end) {
			mid = (start+end) / 2;
			if(sum[mid] < N) start = mid+1;
			else if(sum[mid] > N) end = mid-1;
			else break;
		}//while

		if(sum[mid] > N) mid--; 
		return mid;
	}//getCnt

	private static void init() {
		q = new LinkedList<>();
		alpha = new char[26]; // 알파벳
		int i = 0;
		for(char c='A'; c<='Z'; c++) {
			alpha[i++] = c;
		}//for		
		
		sum = new int[N+1];
		i = 1;
		for(int j=1; j<sum.length; j++, i++) {
			sum[j] = sum[j-1] + i;
		}//for
		
		pq = new PriorityQueue<>(new Comparator<char[]>() {
			@Override
			public int compare(char[] o1, char[] o2) {
				for(int i=0; i<o1.length; i++) {
					if(o1[i] == o2[i]) continue;
					return o1[i] - o2[i];
				}
				return 0;
			}
		});
	}//init

}//class