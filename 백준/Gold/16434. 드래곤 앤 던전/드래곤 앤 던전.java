import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16434
public class Main {
	static int N, ATK;
	static int[][] rooms;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		ATK = Integer.parseInt(st.nextToken());
		rooms = new int[N][3];
		for(int i=0, t=0,a=0,h=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			rooms[i] = new int[] {t, a, h};
		}//for
		br.close();
		
		long maxHP = getHP();

		System.out.print(maxHP);
	}//main

	private static long getHP() {
		long min = 1; 
		long max = Long.MAX_VALUE-1;
		long maxHP = (min+max) / 2;
		long result = maxHP;
		long hp;
		while(min <= max) {
			maxHP = (min + max) / 2;
			hp = battle(maxHP);
			if(hp < 0) min = maxHP + 1;
			else if(hp > 0) {
				result = maxHP;
				max = maxHP-1;
			}
			else break;
		}//while
		
		return result;
	}//getHP
	
	private static long battle(long maxHP) {
		long curHP = maxHP; // 현재 생명력
		long atk = ATK; // 현재 공격력
		
		long mCnt, cnt;
		int t, a, h;
		for(int[] room : rooms) {
			t = room[0]; // 1몬스터, 2포션
			a = room[1]; // 몬스터(포션) 공격력
			h = room[2]; // 몬스터(포션) 생명력

			switch (t) {
			case 2: // 포션
				atk += a;
				if(curHP + h > maxHP) curHP = maxHP;
				else curHP += h;
				break;
			default: // 몬스터
				cnt = h % atk == 0 ? h / atk : h / atk + 1;
				mCnt = curHP % a == 0 ? curHP / a : curHP / a + 1;
				if(cnt <= mCnt) {
					curHP -= (cnt-1) * a;
					if(curHP <= 0) return -1;
				}else return -1;
				
				break;
			}//switch

		}//for
		
		return curHP;
	}//battle

}//class