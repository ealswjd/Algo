import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20008
public class Main {
	static final int C=0, D=1;
	static int N, HP, min, totalTime, totalDamage;
	static int[] order;
	static boolean[] visited;
	static int[][] skills;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 스킬 개수
		HP = Integer.parseInt(st.nextToken()); // 몬스터의 체력(HP)
		skills = new int[N][2];
		order = new int[N];
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			skills[i][C] = Integer.parseInt(st.nextToken()); // 스킬의 대기 시간 정수 C
			skills[i][D] = Integer.parseInt(st.nextToken()); // 스킬의 대미지 정수 D
			totalTime += skills[i][C];
			totalDamage += skills[i][D];
		}//for
		br.close();
		
		min = 987654321;
		perm(0);
		System.out.print(min);
	}//main

	private static void perm(int cnt) {
		if(cnt == N) {
			min = Math.min(min, getTime());
			return;
		}//if
		
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			order[cnt] = i; 
			perm(cnt+1);
			visited[i] = false;
		}//for
		
	}//perm

	private static int getTime() {
		int idx, len = 100000 + 1;
		int[] timeArr = new int[len];
		
		for(int i=0; i<N; i++) {
			idx = order[i];
			for(int j=i; j<len; j+=skills[idx][C]) {
				if(j+2 < len) {
					while(j+2 < len && timeArr[j+1] != 0) j++;
					timeArr[j+1] += skills[idx][D];
				}
			}
		}
		
		int hp = HP;
		int time = 0;
		for(int i=0; i<len; i++) {
			hp -= timeArr[i];
			if(hp <= 0) {
				time = i;
				break;
			}
		}
		
		return time;
	}//getTime

}//class