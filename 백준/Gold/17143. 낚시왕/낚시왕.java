import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17143
public class Main {
	static int R, C, M, sum;
	static int[][] map;
	static int[][] tmp;
	static int[] dr = {0, -1, 1, 0, 0}; // 상하우좌
	static int[] dc = {0, 0, 0, 1, -1};
	static int[] od = {0, 2, 1, 4, 3}; // 반대방향
	static List<Shark> sharkList;

	public static void main(String[] args) throws Exception {
		init();	
		if(M != 0) fishing();
		
		System.out.print(sum);
	}//main

	private static void fishing() {
		int king = -1; // 낚시왕은 처음에 1번 열의 한 칸 왼쪽에 있다.
		
		// 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
		while(++king < C) {			
			getShark(king);	// 2. 낚시왕이 땅과 제일 가까운 상어를 잡는다.	
			tmp = new int[R][C];
			move(); // 3. 상어가 이동한다.
			copyArr();
		}//while
		
	}//fishing

	private static void copyArr() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = tmp[i][j];
			}//for j
		}//for i		
	}//copyArr

	// 3. 상어가 이동한다.
	private static void move() {
		int nr, nc, d, s;
		Shark rival;
		for(Shark shark : sharkList) {
			if(!shark.live) continue;
			d = shark.d;
			s = shark.s;
			nr = shark.r + (dr[d] * s);
			nc = shark.c + (dc[d] * s);
			if(rangeCheck(nr, nc)) {
				dirChange(shark);
			}else {
				shark.r = nr;
				shark.c = nc;
			}
			shark.cnt++;
			
			// 한 칸에 상어가 두 마리 이상 있을 수 있다.
			if(tmp[shark.r][shark.c] != 0) {
				rival = sharkList.get(tmp[shark.r][shark.c]-1);
				if(shark.cnt == rival.cnt) {					
					// 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
					if(shark.z > rival.z) {
						rival.live = false;
					}else {
						shark.live = false;
						continue;
					}//else
				}//if
			}//if
			
			tmp[shark.r][shark.c] = shark.idx;
		}//for
		
	}//move

	private static void dirChange(Shark shark) {
		int r=shark.r, c=shark.c, s=shark.s, d=shark.d;
		if(d == 1 || d == 2) {
			while(s>0) {
				if(rangeCheck(r+dr[d], c)) d = od[d];
				r = r + dr[d];
				s--;
			}//while
		}else {
			while(s>0) {
				if(rangeCheck(r, c+dc[d])) d = od[d];
				c = c + dc[d];
				s--;
			}//while			
		}//else
		
		shark.r = r;
		shark.c = c;
		shark.d = d;		
	}//dirChange

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}//rangeCheck

	// 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
	private static void getShark(int king) {
		int idx = 0;
		
		for(int r=0; r<R; r++) {
			if(map[r][king] != 0) {
				idx = map[r][king];				
				map[r][king] = 0; // 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
				break;
			}//if
		}//for
		
		if(idx == 0 || !sharkList.get(idx-1).live) return;
		idx--;
		sum += sharkList.get(idx).z;
		sharkList.get(idx).live = false;
	}//getShark

	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[R][C];		
		sharkList = new ArrayList<>();
		sum = 0;
		
		int r, c, s, d, z;
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			sharkList.add(new Shark(i, r, c, s, d, z, true));
			map[r][c] = i;
		}//for
		br.close();
	}//init
	
	static class Shark {
		int idx; // 상어 고유번호
		int r; // 행
		int c; // 열
		int s; // 속력
		int d; // 방향  1:상, 2:하, 3:좌, 4:우
		int z;//크기
		int cnt; // 이동횟수
		boolean live; // 살아있는지 여부
		public Shark(int idx, int r, int c, int s, int d, int z, boolean live) {
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;	
			this.live = live;
		}//constructor
	}//Shark

}//class