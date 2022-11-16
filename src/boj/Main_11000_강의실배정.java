package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11000
public class Main_11000_강의실배정 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Class[] arr = new Class[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			arr[i] = new Class(s, t);
		}
		
		Arrays.sort(arr);
		Queue<Class> q = new PriorityQueue<>(new Comparator<Class>() {
			@Override
			public int compare(Class o1, Class o2) {
				return o1.end == o2.end ? o1.start - o2.start : o1.end - o2.end;
			}
		});
		q.offer(arr[0]);
		int result = getSchedule(q, arr, N);
		System.out.println(result);

	}//main

	private static int getSchedule(Queue<Class> q, Class[] arr, int N) {
		Class c;
		
		for(int i=1; i<N; i++) {
			c = q.peek();
			if(c.end <= arr[i].start) {
				q.poll();
			}
			q.offer(arr[i]);
		}
		
		return q.size();
	}

}//class

/*
5
1 7
2 3
3 4
4 8
7 10
-> 2

3
999999999 1000000000
999999998 999999999 
1 999999998
-> 1
 */

class Class implements Comparable<Class>{
	int start;
	int end;
	public Class(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Class o) {
		return this.start - o.start;
	}
}
