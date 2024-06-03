import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/8983
public class Main {
    static int N, M, L;
    static int[] mArr;  // 사대의 위치
    static Animal[] animals; // 동물의 위치

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 사대의 수
        N = Integer.parseInt(st.nextToken()); // 동물의 수
        L = Integer.parseInt(st.nextToken()); // 사정거리

        init();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            mArr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            animals[i] = new Animal(x, y);
        }
        br.close();

        int cnt = getCnt(); // 잡을 수 있는 동물의 수
        System.out.print(cnt);
    }//main

    private static int getCnt() {        
        Arrays.sort(mArr); // 정렬

        int cnt = 0; // 잡을 수 있는 동물의 수
        int min, max, mid, start, end;

        for(Animal animal : animals) {
            if(animal.y > L) continue;

            min = animal.x + animal.y - L;
            max = animal.x - animal.y + L;
            start = 0;
            end = M-1;

            while(start <= end) {
                mid = (start+end) / 2;

                if(mArr[mid] >= min && mArr[mid] <= max) {
                    cnt++;
                    break;
                }
                else if(mArr[mid] < max) start = mid+1;
                else end = mid-1;
            }
            
        }


        return cnt;
    }//getCnt

    private static void init() {
        mArr = new int[M]; // 사대의 위치
        animals = new Animal[N]; // 동물의 위치
    }//init

    static class Animal {
        int x;
        int y;
        public Animal(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}//class