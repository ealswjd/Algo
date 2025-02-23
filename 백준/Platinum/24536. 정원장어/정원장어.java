import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24536
public class Main {
    private static int N; // 정원장어의 수
    private static char[] dir; // 각 정원장어가 바라보고 있는 방향
    private static int[] H; // 각 정원장어의 키

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }//main

    private static void solution() {
        int[] leftLIS = getLeftLIS(); // 왼쪽
        int[] rightLIS = getRightLIS(); // 오른쪽

        int max = 0;
        for(int i=0; i<N; i++) {
            max = Math.max(max, leftLIS[i] + rightLIS[i]);
        }

        System.out.print(N - max);
    }//solution


    private static int[] getLeftLIS() {
        int[] result = new int[N]; // lis 길이
        List<Integer> lis = new ArrayList<>();
        int idx;

        for(int i=0; i<N; i++) {
            if(dir[i] == 'L') {
                idx = binarySearch(H[i], lis);

                if(idx == lis.size()) lis.add(H[i]);
                else lis.set(idx, H[i]);

                result[i] = lis.size();
            }
            else {
                result[i] = (i > 0) ? result[i - 1] : 0;
            }
        }

        return result;
    }//getLeftLIS


    private static int[] getRightLIS() {
        int[] result = new int[N];
        List<Integer> lis = new ArrayList<>();
        int idx;

        for(int i=N-1; i>=0; i--) {
            if(dir[i] == 'R') {
                idx = binarySearch(H[i], lis);

                if(idx == lis.size()) lis.add(H[i]);
                else lis.set(idx, H[i]);

                result[i] = lis.size();
            }
            else {
                result[i] = (i < N - 1) ? result[i + 1] : 0;
            }
        }

        return result;
    }//getRightLIS


    private static int binarySearch(int target, List<Integer> lis) {
        int start = 0;
        int end = lis.size();
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(lis.get(mid) < target) start = mid + 1;
            else end = mid;
        }

        return start;
    }//binarySearch


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 정원장어의 수

        dir = br.readLine().toCharArray(); // 각 정원장어가 바라보고 있는 방향
        H = new int[N];  // 각 정원장어의 키

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init

    
}//class