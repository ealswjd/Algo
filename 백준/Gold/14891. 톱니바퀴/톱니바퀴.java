import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 링크 : https://www.acmicpc.net/problem/14891
 * */
public class Main {
	static int[][] gear; // 8개의 톱니를 가지고 있는 톱니바퀴 4개
	static int[][] idx;  // 톱니바퀴 4개 index

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new int[4][8]; // 8개의 톱니를 가지고 있는 톱니바퀴 4개
		idx = new int[4][8]; // 톱니바퀴 4개 index
		
		for(int i=0; i<4; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<8; j++) {
				gear[i][j] = tmp[j] - '0';
				idx[i][j] = j;
			}//for j
		}//for i

		// 총 K번 회전
		StringTokenizer st;
		int K = Integer.parseInt(br.readLine());
		while(K-->0) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			int l = gear[num][idx[num][6]];
			int r = gear[num][idx[num][2]];
			rotation(num, dir, l, r, new boolean[4]);
		}//while
		br.close();
		
		// 총 K번 회전시킨 이후에 네 톱니바퀴의 점수의 합을 출력
		int score = 0;
		for(int i=0, j=1; i<4; i++, j*=2) {
			score += gear[i][idx[i][0]] * j;
		}//for

		System.out.print(score);
	}//main

	private static void rotation(int num, int dir, int l, int r, boolean[] checked) {
		checked[num] = true;

		// 왼쪽
		if(num-1 >= 0 && !checked[num-1] && l != gear[num-1][idx[num-1][2]]) {
			rotation(num-1, -1 * dir, gear[num-1][idx[num-1][6]], gear[num-1][idx[num-1][2]], checked);
		}
		// 오른쪽
		if(num+1 < 4 && !checked[num+1] && gear[num+1][idx[num+1][6]] != r) {
			rotation(num+1, -1 * dir, gear[num+1][idx[num+1][6]], gear[num+1][idx[num+1][2]], checked);
		}

		int tmp;
		switch (dir) {
		case 1: // 시계 방향
			int next;
			tmp = idx[num][0];
			for(int i=7; i>0; i--) {
				next = (i + 1) % 8;
				idx[num][next] = idx[num][i];
			}//for
			idx[num][1] = tmp;
			break;			
		case -1: //반시계 방향
			tmp = idx[num][0];
			for(int i=0; i<7; i++) {
				idx[num][i] = idx[num][i+1];
			}//for
			idx[num][7] = tmp;
			break;
		}//switch
		
	}//rotation

}//class