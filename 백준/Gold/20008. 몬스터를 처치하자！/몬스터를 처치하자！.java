import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		skills = new int[N][2]; // 스킬 (대기시간, 대미지)
		order = new int[N]; // 스킬 순서
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			skills[i][C] = Integer.parseInt(st.nextToken()); // 스킬의 대기 시간 정수 C
			skills[i][D] = Integer.parseInt(st.nextToken()); // 스킬의 대미지 정수 D
			totalTime += skills[i][C]; // 스킬 총 대기시간
			totalDamage += skills[i][D]; // 스킬 총 대미지
		}//for
		br.close();
		
		min = 987654321;
		int t = totalTime * ((HP/totalDamage)+1);
		perm(0, t);
		System.out.print(min);
	}//main

	private static void perm(int cnt, int t) {
		if(cnt == N) {
			min = Math.min(min, getTime(t));
			return;
		}//if
		
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			order[cnt] = i; 
			perm(cnt+1, t);
			visited[i] = false;
		}//for
		
	}//perm

	private static int getTime(int t) {
		int idx;
		int[] timeArr = new int[t];
		
		for(int i=0; i<N; i++) {
			idx = order[i]; // 스킬 번호
			for(int j=i; j<t; j+=skills[idx][C]) {
				while(j+2 < t && timeArr[j+1] != 0) j++; // 스킬 사용한 시간이면 다음 시간으로
				if(j+1 < t) timeArr[j+1] += skills[idx][D]; // 1초후에 대미지 적용
			}
		}
		
		int hp = HP;
		int time = 0;
		for(; time<t; time++) {
			hp -= timeArr[time]; // 몬스터 처치
			if(hp <= 0) break; // 몬스터 처치 완료
		}
		
		return time; // 몬스터 처치 시간
	}//getTime

}//class