import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17266
public class Main {
    private static int N; // 굴다리의 길이
    private static int M; // 가로등의 개수
    private static int[] positions; // 가로등의 위치


    public static void main(String[] args) throws IOException {
        init();

        // 굴다리의 길이 N을 모두 비추기 위한 가로등의 최소 높이를 출력
        int height = getHeight();
        System.out.print(height);
    }//main


    private static int getHeight() {
        int start = 1;
        int end = N;
        int mid;

        while(start < end) {
            mid = (start + end) >> 1;

            if(isPossible(mid)) end = mid;
            else start = mid + 1;
        }

        return end;
    }//getHeight


    private static boolean isPossible(int height) {
        int end = 0; // 불빛 끝

        for(int i=0; i<M; i++) {
            if(positions[i] - height > end) return false;
            end = positions[i] + height;
        }

        // 끝까지 밝힐 수 있는지 확인
        return end >= N;
    }//isPossible


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 굴다리의 길이
        M = Integer.parseInt(br.readLine()); // 가로등의 개수

        positions = new int[M]; // 가로등의 위치

        // 가로등의 위치는 오름차순으로 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class