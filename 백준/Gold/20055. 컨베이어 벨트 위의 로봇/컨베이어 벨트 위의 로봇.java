import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 링크 : https://www.acmicpc.net/problem/20055
 * */
public class Main {
	static int N, K, size; // 컨베이어 벨트 사이즈, 0 목표 개수, 벨트 사이즈
	static int zeroCnt; // 0 개수
	static int[] belt; // 벨트 내구도

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		size = 2 * N; // 벨트 사이즈 2N
		
		belt = new int[size];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<size; i++) {
			belt[i] = Integer.parseInt(st.nextToken());			
			if(belt[i] == 0) zeroCnt++;
		}//for
		br.close();

		int cnt = rotation();
		System.out.print(cnt);
	}//main

	
	private static int rotation() {
		if(zeroCnt >= K) return 1;
		
		int cnt = 0;
		int nIdx, start;
		boolean[] robot = new boolean[N];
		int[] index = new int[N];
		int[] s = new int[size];
		
		for(int i=0; i<size; i++) {
			s[i] = size - i - 1;
		}
		
		while(zeroCnt < K) {			
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			start = s[cnt%size];
			for(int i=0, j=N-1; j>=0; j--) {
				i = start + j;
				index[j] = i%size;
				if(j < N-1 && robot[j]) {
					robot[j] = false;
					if(j+1 != N-1) robot[j+1] = true;										
				}//if

			}//for
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
			for(int i=N-2; i>=0; i--) {
				nIdx = index[i+1];
				if(belt[nIdx] > 0 && robot[i] && !robot[i+1]) {
					robot[i] = false;
					if(i+1 != N-1) robot[i+1] = true;
					if(--belt[nIdx] < 1) zeroCnt++;
				}
			}//for
			
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if(belt[index[0]] > 0) {
				robot[0] = true;
				if(--belt[index[0]] < 1) zeroCnt++;
			}//if
			
			cnt++;
		}//while
		
		return cnt;
	}//rotation

}//class