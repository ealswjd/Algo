import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2532
public class Main {
    private static int N; // 동물의 수
    private static Animal[] animals; // 동물 정보


    public static void main(String[] args) throws IOException{
        init();

        // 먹이사슬 구조를 가지는 최대 집단의 크기를 출력
        int max = getMax();
        System.out.print(max);
    }//main


    private static int getMax() {
        int[] dp = new int[N];
        int cnt = 1;
        int idx, r;

        dp[0] = -animals[0].right;

        for(int i=1; i<N; i++) {
            // left와 right가 둘 다 동일하면 안 된다.
            if(isSame(i)) continue;

            r = -animals[i].right;

            if(r >= dp[cnt - 1]) {
                dp[cnt++] = r;
                continue;
            }

            idx = getIndex(r, cnt - 1, dp);
            dp[idx] = r;
        }

        return cnt;
    }//getMax


    private static int getIndex(int target, int end, int[] dp) {
        int idx = 0;
        int start = 0;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(dp[mid] > target) {
                end = mid - 1;
                idx = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return idx;
    }//getIndex


    private static boolean isSame(int i) {
        return animals[i].left == animals[i-1].left && animals[i].right == animals[i-1].right;
    }//isSame


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 동물의 수

        animals = new Animal[N]; // 동물 정보

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            // 동물의 번호, 동물의 활동영역의 왼쪽 위치, 오른쪽 위치
            int num = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            animals[i] = new Animal(num, left, right);
        }

        // 정렬
        Arrays.sort(animals);
        br.close();
    }//init


    private static class Animal implements Comparable<Animal> {
        int num;   // 동물의 번호
        int left;  // 활동영역의 왼쪽 위치
        int right; // 활동영역의 오른쪽 위치

        Animal(int num, int left, int right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Animal a) {
            if(this.left == a.left) return a.right - this.right;
            return this.left - a.left;
        }

        @Override
        public String toString() {
            return "num=" + num +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }//Animal


}//class